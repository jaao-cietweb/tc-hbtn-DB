package models;

import entities.Aluno;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class AlunoModel {

    public void create(Aluno aluno) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.persist(aluno);
            em.getTransaction().commit();
            System.out.println("Aluno criado com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao criar um aluno !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }

    public Aluno findById(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();
        try {
            Aluno aluno = em.find(Aluno.class, id);
            System.out.println("Aluno de nome: " + aluno.getNomeCompleto() + " encontrado");
            return aluno;
        } catch (Exception e) {
            System.out.println("Aluno não encontrado");
            return null;
        }
    }

    public List<Aluno> findAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();
        try {
            List<Aluno> alunos = em.createQuery("FROM "+ Aluno.class.getName()).getResultList();
            System.out.println("Lista de todos alunos recuperada!");
            return alunos;
        } catch (Exception e) {
            System.out.println("Não foi possivel retornar todos alunos");
            return null;
        }
    }

    public void update(Aluno aluno) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(aluno);
            em.getTransaction().commit();
            System.out.println("Aluno atualizado!");
        } catch (Exception e) {
            System.out.println("Impossivel atualizar o aluno: " + aluno.getNomeCompleto());
            em.getTransaction().rollback();
        }
    }

    public void delete(Aluno aluno) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();
        try {
            Aluno aluno1 = em.find(Aluno.class, aluno.getId());
            em.getTransaction().begin();
            em.remove(aluno1);
            em.getTransaction().commit();
            System.out.println("Aluno deletado!");
        } catch (Exception e) {
            System.out.println("Não foi possivel deletar aluno: " + aluno.getNomeCompleto());
            em.close();
            em.getTransaction().rollback();
        }
    }
}
