import amazon.config.EnvFactory;
import amazon.factories.DriverFactory;
import amazon.pages.Homepage;
import com.typesafe.config.Config;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer;
import org.openqa.selenium.WebDriver;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestSandbox {
    private static Config config = EnvFactory.getInstance().getConfig();
    private static final String HOME_PAGE_URL = config.getString("HOME_PAGE_URL");
    private static WebDriver driver = DriverFactory.getDriver();
    static Logger log = Logger.getLogger(TestSandbox.class);
    Homepage homepage = new Homepage(driver);

    @Tag("smokeTest")
    @DisplayName("Amazon Automation Test Exercise")
    @BeforeAll
    static void setDriver() {
        driver.get(HOME_PAGE_URL); //Open homepage
    }

    @Test
    @Order(1)
    void assertThatHomePageTitleIsCorrect() {
        String actualString = driver.getTitle(); //get pagetitle
        assertTrue(actualString.contains("Online Shopping site in India:") );
    }

    @Test
    @Order(2)
    void assertClickOnHamburgerMenu() throws InterruptedException {
        assertEquals("Shop By Category", homepage.getShopBydepartText());
    }

    @Test
    @Order(3)
    void assertClickOnTvAppMenu() throws InterruptedException {
        assertEquals("Tv, Audio & Cameras", homepage.gettvAudioCam());
    }

    @Test
    @Order(4)
    void assertClickonTelevision() {
        assertEquals("Samsung", homepage.getSamsungText());
    }

    @Test
    @Order(5)
    void assertClickOnFilterBySamsung() {
        assertEquals("Sort by:Featured", homepage.getSortText());
    }

    @Test
    @Order(6)
    void assertSortResultHightoLow() {
        assertEquals("Sort by:Price: High to Low", homepage.getFilterValue());
    }

    @Test
    @Order(7)
    void assertClickOnSecondHighestItem() {
        homepage.clickOnSecondHighestProduct();
        assertEquals(2, driver.getWindowHandles().size()); //Assert new tab opened
    }

    @Test
    @Order(8)
    void assertSwitchWindowTab() {
        ArrayList<String> newTb = new ArrayList<String>(driver.getWindowHandles()); // New tab arraylist
        driver.switchTo().window(newTb.get(1));
        String actualString = driver.getTitle();
        assertTrue(actualString.contains("Samsung") );
    }

    @Test
    @Order(9)
    void assertAboutThisItemIsVisible() {
        Assert.assertTrue(homepage.isDisplayAboutThisItem());
        log.info("Step 9: Log is --> " + homepage.getAboutthisItem());
        System.out.println("Step 9: Log is --> " + homepage.getAboutthisItem());
    }

    @AfterAll
    public static void tearDown() {
        driver.close();
        driver.quit();
    }

}
