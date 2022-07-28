package models;

import entities.Pessoa;
import entities.Produto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class PessoaModel {

    public void create(Pessoa p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            System.out.println("Produto criado com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao criar o pessoa !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }

    public void update(Pessoa p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
            System.out.println("Produto atualizado");
        } catch (Exception e) {
            System.err.println("Erro ao atualizar o pessoa !!!" + e.getMessage());
            em.getTransaction().rollback();
        }
    }

    public void delete(Pessoa p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        try {
            Pessoa pessoa = em.find(Pessoa.class, p.getId());
            em.getTransaction().begin();
            em.remove(pessoa);
            em.getTransaction().commit();
            System.out.println("Produto deletado");
        } catch (Exception e) {
            System.err.println("Erro ao deletar o pessoa !!!" + e.getMessage());
            em.close();
            em.getTransaction().rollback();
        }
    }

    public Produto findById(Pessoa p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        System.out.println("Pessoa encontrado");
        return em.find(Produto.class, p.getId());
    }

    public List<Pessoa> findAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        System.out.println("Pessoas encontradas");
        return em.createQuery("FROM " +  Produto.class.getName()).getResultList();
    }
}
