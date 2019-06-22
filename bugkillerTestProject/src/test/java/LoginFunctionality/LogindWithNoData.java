package LoginFunctionality;

import Pages.LoginPage;
import Pages.MainPage;
import Test.AbstractTest;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertTrue;

/**Login to system with existing user's incomplete data - no password
 * "1) Enter existing user‘s name: admin
 * 2) enter no password,
 * 3) click button ‘Prisijungti’  "
 */

public class LogindWithNoData extends AbstractTest {

    LoginPage loginPage;


    @Before
    public void openPage(){
        driver.get("http://141.136.44.216:8080/kodas-spring-1.0-SNAPSHOT/");
    }

    @Test
    public void loginWithNoData() throws IOException{
        loginPage=new LoginPage(driver);
        loginPage.clickBtnPrisijungti();

        assertTrue(loginPage.getMessageKlaidaUnauthorized().isDisplayed());
    }

}
