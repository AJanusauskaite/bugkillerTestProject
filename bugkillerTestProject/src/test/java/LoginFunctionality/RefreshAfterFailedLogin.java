package LoginFunctionality;

import Pages.LoginPage;
import Test.AbstractTest;
import Utils.FileReaderUtils;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

/**Refresh the page after failed login attempt - refresh button
 * "1) Enter wrong username: username;
 * 2) enter wrond password: password;
 * 3) click button 'Prisijungti'
 *  4)press Refresh on browser window;"
 */

public class RefreshAfterFailedLogin extends AbstractTest {

    LoginPage loginPage;
    FileReaderUtils fileReaderUtils;


    @Before
    public void openPage() {
        driver.get("http://141.136.44.216:8080/kodas-spring-1.0-SNAPSHOT/");
    }

    @Test
    public void testRefreshAfterFailedLogin() throws IOException{
        loginPage = new LoginPage(driver);
        fileReaderUtils=new FileReaderUtils();

        List<String> testData = fileReaderUtils.getTestData("src/test/resources/LoginTestData.txt");

        loginPage.fillInputFieldUsername(testData.get(2));
        loginPage.fillInputFieldPassword(testData.get(3));
        loginPage.clickBtnPrisijungti();

        assertTrue(loginPage.getMessageKlaidaUnauthorized().isDisplayed());

        //click refresh button on browser
        driver.navigate().refresh();

        //check if input fields are empty
        assertTrue(loginPage.getInputFieldUsername().getText().isEmpty());
        assertTrue(loginPage.getInputFieldPassword().getText().isEmpty());

        //check if error message is not shown
        assertEquals(0, driver.findElements(By.className("loginAlert")).size());
    }
    }
