package Test;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.AfterClass;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class AbstractTest {
    protected static WebDriver driver;


    //TODO change annotations to testng and make them work somehow????
    @BeforeClass
    public static void setupBrowser(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ausra\\chromedriver.exe");

        driver=new ChromeDriver();
    }

//    @AfterClass
//    public static void closeBrowser(){
//        driver.quit();
//    }
//
//    public WebDriver getDriver(){
//        return this.driver;
//    }


}
