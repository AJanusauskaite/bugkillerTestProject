package LoginFunctionality;

import Pages.LoginPage;
import Pages.MainPage;
import Test.AbstractTest;
import Utils.FileReaderUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertTrue;

/**Enter non existing user's login details with special characters
 * "1) Enter wrong username with special characters: a%b@c$;
 * 2) enter wrong password with special characters: d#e^f&
 * 3) click button 'Prisijungti'                                      "
 */
public class LoginWrongDataSpecialChars extends AbstractTest {
    LoginPage loginPage;
    MainPage mainPage;
    FileReaderUtils fileReaderUtils;

    @Before
    public void openPage() {
        driver.get("http://141.136.44.216:8080/kodas-spring-1.0-SNAPSHOT/");
    }

    @Test
    public void testLoginWithSpecialChars() throws IOException {
        loginPage = new LoginPage(driver);
        mainPage=new MainPage(driver);
        fileReaderUtils=new FileReaderUtils();


        List<String> testData = fileReaderUtils.getTestData("src/test/resources/LoginTestData.txt");

        loginPage.fillInputFieldUsername(testData.get(6));
        loginPage.fillInputFieldPassword(testData.get(7));
        loginPage.clickBtnPrisijungti();

        assertTrue(loginPage.getMessageKlaidaUnauthorized().isDisplayed());

    }

}
