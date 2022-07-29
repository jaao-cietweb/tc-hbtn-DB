package models;

import entities.Curso;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class CursoModel {
    public void create(Curso curso) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.persist(curso);
            em.getTransaction().commit();
            System.out.println("Curso criado com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao criar um curso !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }

    public Curso findById(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();
        try {
            Curso curso = em.find(Curso.class, id);
            System.out.println("Curso de nome: " + curso.getNome() + " encontrado");
            return curso;
        } catch (Exception e) {
            System.out.println("Curso não encontrado");
            return null;
        }
    }

    public List<Curso> findAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();
        try {
            List<Curso> cursos = em.createQuery("FROM "+ Curso.class.getName()).getResultList();
            System.out.println("Lista de todos alunos recuperada!");
            return cursos;
        } catch (Exception e) {
            System.out.println("Não foi possivel retornar todos cursos");
            return null;
        }
    }

    public void update(Curso curso) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(curso);
            em.getTransaction().commit();
            System.out.println("Curso atualizado!");
        } catch (Exception e) {
            System.out.println("Impossivel atualizar o curso: " + curso.getNome());
            em.getTransaction().rollback();
        }
    }

    public void delete(Curso curso) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();
        try {
            Curso curso1 = em.find(Curso.class, curso.getId());
            em.getTransaction().begin();
            em.remove(curso1);
            em.getTransaction().commit();
            System.out.println("Curso deletado!");
        } catch (Exception e) {
            System.out.println("Não foi possivel deletar curso: " + curso.getNome());
            em.close();
            em.getTransaction().rollback();
        }
    }
}
