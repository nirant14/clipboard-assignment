package amazon.pages;

import amazon.utils.PageActionsHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class Homepage {
    WebDriverWait wait;
    PageActionsHelper pageActionsHelper;

    @FindBy(xpath = "//a[@id='nav-hamburger-menu']")
    public WebElement hamBurger;

    @FindBy(xpath = "//div[contains(text(),'shop by category')]")
    public WebElement shopBydepart;

    @FindBy(xpath = "//div[contains(text(),'TV, Appliances, Electronics')]")
    WebElement tvAppEle;

    @FindBy(xpath = "//div[contains(text(),'tv, audio & cameras')]")
    WebElement tvAudioCam;

    @FindBy(linkText = "Televisions")
    WebElement Televisions;

    @FindBy(xpath = "//li//span[contains(text(),'Samsung')]")
    WebElement SamsungText;

    @FindBy(xpath = "//li//span[contains(text(),'Samsung')]")
    WebElement Samsung;

    @FindBy(css = "#a-autoid-0-announce")
    WebElement sortText;

    @FindBy(css = "#s-result-sort-select_2")
    WebElement highToLow;

    @FindBy(xpath = "(//span[@class='a-price'])[2]")
    WebElement secondHighestProduct;

    @FindBy(xpath = "//h1[contains(text(),'About this item')]")
    WebElement aboutThisItem;

    //Initializing the Page Objects:
    public Homepage(WebDriver driver){
        PageFactory.initElements(driver,this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        pageActionsHelper = new PageActionsHelper(driver, wait);
    }

    //Click on hamburger menu & return the Shop by Department
    public String getShopBydepartText() throws InterruptedException {
        pageActionsHelper.waitAndClick(hamBurger);
        return pageActionsHelper.waitAndGetText(shopBydepart);
    }

    //Click on TV, Appliances, Electronics menu & Find "Tv, Audio & Cameras" sub section
    public String gettvAudioCam() throws InterruptedException {
        pageActionsHelper.waitAndClick(tvAppEle);
        return pageActionsHelper.waitAndGetText(tvAudioCam);
    }

    //Find "Televisions" link & click & Find "Samsung" filter & return text
    public String getSamsungText()
    {
        pageActionsHelper.waitAndClick(Televisions);
        return pageActionsHelper.waitAndGetText(SamsungText);
    }

    // Click on "Samsung" & Find Sort by:Featured
    public String getSortText()
    {
         pageActionsHelper.waitAndClick(Samsung);
        return pageActionsHelper.waitAndGetText(sortText);
    }

    //CLick on Sort By filter & Sort with price High to Low.
    public String getFilterValue()
    {
         pageActionsHelper.waitAndClick(sortText);
         pageActionsHelper.waitAndClick(highToLow);
        return pageActionsHelper.waitAndGetText(sortText);
    }

    //Click on second highest product
    public void clickOnSecondHighestProduct(){
         pageActionsHelper.waitAndClick(secondHighestProduct);
    }

    //Find About this item
    public Boolean isDisplayAboutThisItem(){
        return pageActionsHelper.waitAndIsDisplayed(aboutThisItem);
    }

    //Return text "About this item"
    public String getAboutthisItem()
    {
        return pageActionsHelper.waitAndGetText(aboutThisItem);
    }
}
