import myJPA.dao.UserDao;
import myJPA.model.Address;
import myJPA.model.User;
import myJPA.model.UserStatus;

import java.time.ZonedDateTime;

public class Main {

    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        //UserDao userDao2 = new UserDao();

        User user = userDao.getUserBId(2L);
        User user2 = userDao.getUserBId(5L);
        User user3 = userDao.getUserBId(1L);

        System.out.println(user);
        System.out.println(user2);
        System.out.println(user3);

        User newUser = newUser();

        //userDao.c
    }

    private static User newUser(){
        User newUser = new User();
        newUser.setUsername("Pista");
        newUser.setStatus(UserStatus.PENDING);
        newUser.setCreatedAt(ZonedDateTime.now());
        Address address = new Address();
        address.setCity("Miskolc");
        address.setStreet("Szerencsi");
        address.setHouseNumber(8);
        newUser.setAddress(address);
        return newUser;
    }
}
