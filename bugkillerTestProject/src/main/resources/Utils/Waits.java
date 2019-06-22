package Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waits {
    public Waits(){}

    public void waitForAlertToBePresent(WebDriver driver){
        WebDriverWait wait=new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public void waitForElementsToBePresent(WebDriver driver,String CSSlocator){
        WebDriverWait wait=new WebDriverWait(driver, 4);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(CSSlocator)));
    }
}
