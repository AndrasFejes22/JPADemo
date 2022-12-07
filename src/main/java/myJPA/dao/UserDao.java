package myJPA.dao;

import myJPA.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class UserDao {

    private static EntityManagerFactory emf; //ez egy JPA-s interface

    public UserDao() {
        this.emf = Persistence.createEntityManagerFactory("blogs-pu");
    }

    //create, read, update, delete = CRUD, EntityManager elsődleges feladatok

    public User getUserBId(Long id) {
        EntityManager entityManager = emf.createEntityManager();
        return entityManager.find(User.class, id);
    }

    public User createUser(User user) throws Exception {

        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = emf.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(user);
            transaction.commit();

        }catch(Exception e) {
            if(transaction != null && transaction.isActive()){
                transaction.rollback();
            }
            throw e;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return user;
    }

    public User updateUser(User user) throws Exception {

        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = emf.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(user);
            transaction.commit();

        }catch(Exception e) {
            if(transaction != null && transaction.isActive()){
                transaction.rollback();
            }
            throw e;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return user;
    }

}
