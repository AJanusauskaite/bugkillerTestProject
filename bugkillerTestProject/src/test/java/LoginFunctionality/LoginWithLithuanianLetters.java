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

/**Enter existing user's login details with Lithuanian letters
 * "1) Enter existing username with LT letters: šašas
 * 2) enter existing password with LT letters: šašaspašas
 * 3) click button 'Prisijungti'                                      "
 */
public class LoginWithLithuanianLetters extends AbstractTest {
    LoginPage loginPage;
    MainPage mainPage;
    FileReaderUtils fileReaderUtils;

    @Before
    public void openPage() {
        driver.get("http://141.136.44.216:8080/kodas-spring-1.0-SNAPSHOT/");
    }

    @Test
    public void testLoginWithLTLetters() throws IOException{
        loginPage=new LoginPage(driver);
        mainPage=new MainPage(driver);
        fileReaderUtils=new FileReaderUtils();

        List<String> testData = fileReaderUtils.getTestData("src/test/resources/LoginTestData.txt");

        loginPage.fillInputFieldUsername(testData.get(4));
        loginPage.fillInputFieldPassword(testData.get(5));
        loginPage.clickBtnPrisijungti();

        assertThat(mainPage.getTextOfHeadingNameSurnameUsername(), containsString(testData.get(4)));
    }

}
