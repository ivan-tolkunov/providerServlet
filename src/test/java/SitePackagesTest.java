import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.ivan.provider.dao.SitePackageDAO;

public class SitePackagesTest {
    SitePackageDAO sitePackageDAO;

    @Before
    public void start() {
        sitePackageDAO = new SitePackageDAO();


    }

    @Test
    public void getAllPackagesTest() throws Exception {
        Assert.assertFalse(sitePackageDAO.getAllPackages().isEmpty());
    }

    @Test
    public void getByIdTest() throws Exception {
        Assert.assertEquals(sitePackageDAO.getById(1L).getName(), "Mega");
    }

    @Test
    public void addNewPackageTest() throws Exception {
        sitePackageDAO.addNewPackage("Mega-", "150gb og internet", "100", "Internet");
        Assert.assertEquals(sitePackageDAO.getByName("Mega-").getName(), "Mega-");
        sitePackageDAO.deletePackageById(String.valueOf(sitePackageDAO.getByName("Mega-").getId()));
    }

    @Test
    public void deletePackageByIdTest() throws Exception {
        sitePackageDAO.addNewPackage("Mega-", "150gb og internet", "100", "Internet");
        Assert.assertEquals(sitePackageDAO.getByName("Mega-").getName(), "Mega-");
        sitePackageDAO.deletePackageById(String.valueOf(sitePackageDAO.getByName("Mega-").getId()));
        Assert.assertNull(sitePackageDAO.getByName("Mega-").getName());
    }

}
