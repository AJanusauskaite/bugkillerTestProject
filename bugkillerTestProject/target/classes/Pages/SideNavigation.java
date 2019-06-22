package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SideNavigation extends AbstractPage {
    public SideNavigation(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id=\"mysidenav\"]/button")
    private WebElement buttonSidenavToggle;

    @FindBy(css = "#mysidenav > div > div:nth-child(1)")
    private WebElement buttonHome;

    @FindBy(css = "#mysidenav > div > div:nth-child(2)")
    private WebElement buttonProfilis;

    @FindBy(css = "#mysidenav > div > div:nth-child(3)")
    private WebElement buttonIkelti;

    @FindBy(css = "#mysidenav > div > div:nth-child(4)")
    private WebElement buttonNaudotojai;

    @FindBy(css = "#mysidenav > div > div:nth-child(5)")
    private WebElement buttonNustatymai;

    @FindBy(css = "#mysidenav > div > div:nth-child(6)")
    private WebElement buttonStatistika;

    public void clickBtnSidenavToggle(){
        buttonSidenavToggle.click();
    }

    public void clickBtnHome(){
        buttonHome.click();
    }

    public void clickBtnProfilis(){
        buttonProfilis.click();
    }

    public void clickBtnIkelti(){
        buttonIkelti.click();
    }

    public void clickBtnNaudotojai(){
        buttonNaudotojai.click();
    }

    public void clickBtnNustatymai(){
        buttonNustatymai.click();
    }

    public void clickBtnStatistika(){
        buttonStatistika.click();
    }
}
