package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class NaudotojuAdministravimas extends AbstractPage {
    public NaudotojuAdministravimas(WebDriver driver) {
        super(driver);
    }
    public String URL="http://141.136.44.216:8080/user-administration-list";

    @FindBy(className = "mr-sm-2")
    private WebElement inputSearchField;

    @FindBy(className = "button2 ")
    private WebElement buttonIeskoti;

    @FindBy(className = "btn-outline-info")
    private WebElement btnRegistrutiNaujaNaudotoja;



    public void fillInputSearchField(String searchFor){
        inputSearchField.sendKeys(searchFor);
    }

    public void clickBtnSearch(){
        buttonIeskoti.click();
    }

    public void clickBtnRegisterNewUser(){
        btnRegistrutiNaujaNaudotoja.click();
//        WebDriverWait wait=new WebDriverWait(driver, 4);
//        wait.until(ExpectedConditions.or(ExpectedConditions.alertIsPresent()),
//            ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("#userListTable > table > thead > tr ")));
    }

    public String getAlertMsgText(){
        return driver.switchTo().alert().getText();
    }

    public void acceptAlertMsg(){
        driver.switchTo().alert().accept();
    }

    public List<WebElement> getListOfSearchResults(){
        return driver. findElements(By.cssSelector("#userListTable > table > thead > tr "));
    }

}

