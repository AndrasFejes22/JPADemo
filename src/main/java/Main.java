import myJPA.dao.UserDao;
import myJPA.model.User;

public class Main {

    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        //UserDao userDao2 = new UserDao();

        User user = userDao.getUserBId(4L);
        User user2 = userDao.getUserBId(5L);
        User user3 = userDao.getUserBId(1L);

        System.out.println(user);
        System.out.println(user2);
        System.out.println(user3);
    }
}
