package LoginFunctionality;

import Pages.LoginPage;
import Pages.MainPage;
import Test.AbstractTest;
import Utils.FileReaderUtils;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertTrue;

/**Login to system with wrong login data
 * "1) Enter wrong username: username,
 * 2) enter wrong password: password,
 * 3) click button 'Prisijungti'"
 */


public class LogintWithWrongData extends AbstractTest {
    LoginPage loginPage;
    MainPage mainPage;
    FileReaderUtils fileReaderUtils;


    @Before
    public void openPage(){
        driver.get("http://141.136.44.216:8080/kodas-spring-1.0-SNAPSHOT/");
    }


    @Test
    public void loginWithWrongData() throws IOException {
        loginPage=new LoginPage(driver);
        fileReaderUtils=new FileReaderUtils();

        List<String> testData = fileReaderUtils.getTestData("src/test/resources/LoginTestData.txt");

        loginPage.fillInputFieldUsername(testData.get(2));
        loginPage.fillInputFieldPassword(testData.get(3));
        loginPage.clickBtnPrisijungti();

        assertTrue(loginPage.getMessageKlaidaUnauthorized().isDisplayed());
    }

}
