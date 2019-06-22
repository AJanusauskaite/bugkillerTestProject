package LoginFunctionality;

import Pages.LoginPage;
import Pages.MainPage;
import Test.AbstractTest;
//import org.testng.annotations.Test;
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

/**
 *Login to system with existing user's correct data
 * 1) Enter existing user‘s name: admin,
 * 2) enter existing user's password: admin,
 * 3) click button ‘Prisijungti’
 */


public class LoginWithCorrectData extends AbstractTest {

LoginPage loginPage;
MainPage mainPage;
FileReaderUtils fileReaderUtils;


    @Before
    public void openPage(){
        driver.get("http://141.136.44.216:8080/kodas-spring-1.0-SNAPSHOT/");
    }


    @Test
public void loginWithCorrectData() throws IOException {
        loginPage=new LoginPage(driver);
        mainPage=new MainPage(driver);
        fileReaderUtils=new FileReaderUtils();

        List<String> testData = fileReaderUtils.getTestData("src/test/resources/LoginTestData.txt");

        loginPage.fillInputFieldUsername(testData.get(0));
        loginPage.fillInputFieldPassword(testData.get(1));
        loginPage.clickBtnPrisijungti();

        assertThat(mainPage.getTextOfHeadingNameSurnameUsername(), containsString(testData.get(0)));
    }

}
