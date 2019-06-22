package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

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

    public WebElement getInputSearchField(){
        return inputSearchField;
    }

    public void clickBtnSearch(){
        buttonIeskoti.click();
    }

    public void clickBtnRegisterNewUser() {
        btnRegistrutiNaujaNaudotoja.click();
    }

    public String getAlertMsgText(){
        System.out.println(driver.switchTo().alert().getText());
        return driver.switchTo().alert().getText();
    }

    public void acceptAlertMsg(){
        driver.switchTo().alert().accept();

    }

    public List<WebElement> getListOfSearchResults(){
        System.out.println("amount of results found: "+driver. findElements(By.cssSelector("#userListTable > table > tbody>tr")).size());
        return driver. findElements(By.cssSelector("#userListTable > table > tbody>tr"));
    }

    public List<WebElement> getAllUsernamesFromSearchResults(){
        return driver.findElements(By.cssSelector("#userListTable > table > tbody>tr>td:nth-child(1)"));
    }

    public boolean compareFoundUsernameToSearchedEquals(String searchedFor){
        List<WebElement> usernames=getAllUsernamesFromSearchResults();
        for(WebElement username:usernames){
            if(username.getText().equals(searchedFor)){
                return true;
            }
        }
        return false;
    }

    public boolean compareFoundUsernameToSearchedContains(String searchedFor){
        List<WebElement> usernames=getAllUsernamesFromSearchResults();
        for(WebElement username:usernames){
            if(username.getText().contains(searchedFor)){
                return true;
            }
        }
        return false;
    }

}

