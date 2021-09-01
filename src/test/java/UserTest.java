import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import ua.ivan.provider.dao.SitePackageDAO;
import ua.ivan.provider.dao.UserDAO;
import ua.ivan.provider.model.SitePackage;
import ua.ivan.provider.model.Status;
import ua.ivan.provider.model.User;

public class UserTest {
    UserDAO userDAO;
    User user;
    @Before
    public void start() {
        userDAO = new UserDAO();
        user = userDAO.getById(15L);

    }

    @Test
    public void getAllUsersTest() throws Exception {
        Assert.assertNotEquals(0, userDAO.getAllUsers().size());
    }
    @Test
    public void getByIdTest() throws Exception {
        Assert.assertEquals(userDAO.getById(13L).getEmail(), "admin@mail.com");
    }
    @Test
    public void getUserByEmailTest() throws Exception {
        Assert.assertEquals(userDAO.getUserByEmail(user.getEmail()), user);
    }

    @Test
    public void userIsExistTest() throws Exception {
        Assert.assertTrue(userDAO.userIsExist(user.getEmail(), "user"));
    }

    @Test
    public void getUserPackagesTest() throws Exception {
        Assert.assertFalse(userDAO.getUserPackages(user.getId()).isEmpty());
    }

    @Test
    public void buyUserPackageTest() throws Exception {
        SitePackageDAO sitePackageDAO = new SitePackageDAO();
        SitePackage sitePackage = sitePackageDAO.getById(2L);
        int packagesNumber = userDAO.getUserPackages(user.getId()).size();
        userDAO.buyUserPackage(user.getId(), sitePackage);
        Assert.assertNotEquals(packagesNumber, userDAO.getUserPackages(user.getId()).size());
    }

    @Test
    public void deleteUserPackageTest() throws Exception {
        SitePackageDAO sitePackageDAO = new SitePackageDAO();
        SitePackage sitePackage = sitePackageDAO.getById(2L);
        userDAO.buyUserPackage(user.getId(), sitePackage);
        int packagesNumber = userDAO.getUserPackages(user.getId()).size();
        userDAO.deleteUserPackage(user.getId(), sitePackage.getId());
        Assert.assertNotEquals(packagesNumber, userDAO.getUserPackages(user.getId()).size());
    }

    @Test
    public void getUserBalanceTest() throws Exception {
       Assert.assertEquals(user.getBalance(), userDAO.getUserBalance(user.getId()));
    }

    @Test
    public void updateUserStatusTest() throws Exception {
        Assert.assertEquals(Status.BANNED, userDAO.updateUserStatus(user.getId(), Status.BANNED).getStatus());
        userDAO.updateUserStatus(user.getId(), Status.ACTIVE);
    }

    @Test
    public void isSubscriberTest() throws Exception {
        Assert.assertFalse(userDAO.isSubscriber(user.getId(), "IP-TV"));
        Assert.assertTrue(userDAO.isSubscriber(user.getId(), "Cellular communication"));
    }
}
