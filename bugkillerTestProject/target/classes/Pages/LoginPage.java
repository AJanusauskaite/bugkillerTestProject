package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class LoginPage extends AbstractPage {

public String URL="http://141.136.44.216:8080/kodas-spring-1.0-SNAPSHOT/";


    @FindBy(name = "username")
    private WebElement inputFieldUsername;

    @FindBy(name = "password")
    private WebElement inputFieldPassword;

    @FindBy(className = "button1")
    private WebElement buttonPrisijungti;

    @FindBy(className = "loginAlert")
    private WebElement messageKlaidaUnauthorized;


    //constructor
    public LoginPage (WebDriver driver){
        super(driver);
    }


public void fillInputFieldUsername(String username){
    inputFieldUsername.sendKeys(username);
}

public void fillInputFieldPassword(String password){
    inputFieldPassword.sendKeys(password);
}

public void clickBtnPrisijungti(){
    buttonPrisijungti.click();
    driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

}

public WebElement getMessageKlaidaUnauthorized(){
        return messageKlaidaUnauthorized;
}

    public WebElement getInputFieldUsername(){
        return inputFieldUsername;
    }

    public WebElement getInputFieldPassword(){
        return inputFieldPassword;
    }


}


