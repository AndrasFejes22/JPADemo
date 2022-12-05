import myJPA.dao.UserDao;
import myJPA.model.Address;
import myJPA.model.User;
import myJPA.model.UserStatus;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws Exception {

        // Persist/EntityManager

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("blogs-pu");
        EntityManager entityManager = emf.createEntityManager();

        // Managed, or transient:
        /*
        User user1 = User.user("testUser");
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(user1); // ez így nem kerül be a db-be, csak a "Hibernate felkésszül rá" (Pl. uj id-t ad neki)
        System.out.println("user1: " + user1);
        entityTransaction.commit();
        */


        User foundUser = entityManager.find(User.class, 5L);
        // Detached
        entityManager.close();
        System.out.println("foundUser: " + foundUser);

        // Make entity attached again:
        entityManager = emf.createEntityManager(); // ez itt most jó post/collecton mellé kell a**

        //**a User mergedUser = entityManager.merge(foundUser)
        //**System.out.println("mergedUser: " + mergedUser.getPosts());

        User foundUser2 = entityManager.find(User.class, 3L);
        System.out.println("foundUser: " + foundUser2.getUsername());
        System.out.println("Managed? : " + entityManager.contains(foundUser2));

        // Removed:
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        User userToRemove = entityManager.find(User.class, foundUser.getId());
        entityManager.remove(userToRemove);
        entityTransaction.commit();

        // flush():
        //entityManager.flush();

        // Changing entities:
        User anotherUser = entityManager.find(User.class, 7L);
        System.out.println("anotherUser_before: "+anotherUser);
        entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        anotherUser.setStatus(UserStatus.ACTIVE);
        entityTransaction.commit();
        entityManager.refresh(anotherUser); //??
        System.out.println("anotherUser_after: "+anotherUser);




        // DAO:

        UserDao userDao = new UserDao();
        //UserDao userDao2 = new UserDao();

        //User user = userDao.getUserBId(1L);


        //user.getRoles().add("TEST2"); // így sql oldali "seve" pl sql update nélkül a db-ben nincs változás

        //Set<String> set = new HashSet<String>(); // ha itt Set<MyClass> van akkor kell equals, és hashcode!
        //set.add("TEST2");

        //user.setRoles(set); // ezzel majd mi legyen?

        //System.out.println(user);

        /*
        User user2 = userDao.getUserBId(5L);
        User user3 = userDao.getUserBId(2L);


        System.out.println(user2);
        System.out.println(user3);

        User newUser = newUser();

        userDao.createUser(newUser);
        */
    }

    private static User newUser(){
        User newUser = new User();
        newUser.setUsername("John_Doe");
        newUser.setStatus(UserStatus.ACTIVE);
        newUser.setCreatedAt(ZonedDateTime.now());
        Address address = new Address();
        address.setCity("Texas");
        address.setStreet("Red River");
        address.setHouseNumber(8);
        newUser.setAddress(address);
        return newUser;
    }
}
