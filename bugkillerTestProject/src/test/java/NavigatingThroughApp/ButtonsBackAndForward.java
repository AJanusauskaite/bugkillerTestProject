package NavigatingThroughApp;

import Pages.LoginPage;
import Pages.MainPage;
import Pages.SideNavigation;
import Test.AbstractTest;
import Utils.FileReaderUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**Test app behaviour with browser buttons back and forward
 * "1) go to 'Atmesti dokumentai'
 * 2)go to 'Pateikti dokumentai'
 * 3)click back on browser
 * 4)click forward on browser
 * 5) click back twice on browser"
 */
public class ButtonsBackAndForward extends AbstractTest {

    LoginPage loginPage;
    MainPage mainPage;
    FileReaderUtils fileReaderUtils;

    @Before
    public void openPage(){
        driver.get("http://141.136.44.216:8080/kodas-spring-1.0-SNAPSHOT/");
    }

    @Test
    public void testBtnsBackAndFwd() throws IOException {
        loginPage=new LoginPage(driver);
        mainPage=new MainPage(driver);
        fileReaderUtils=new FileReaderUtils();

        List<String> testData = fileReaderUtils.getTestData("src/test/resources/LoginTestData.txt");

        loginPage.fillInputFieldUsername(testData.get(0));
        loginPage.fillInputFieldPassword(testData.get(1));
        loginPage.clickBtnPrisijungti();

        mainPage.clickNavAtmestiDokumentai();
        mainPage.clickNavPateiktiDokumentai();
        driver.navigate().back();
        assertThat(mainPage.getTextOfPageHeader(), is(equalTo("Atmesti dokumentai")));
        driver.navigate().forward();
        assertThat(mainPage.getTextOfPageHeader(), is(equalTo("Pateikti dokumentai")));
        driver.navigate().back();
        driver.navigate().back();
        assertThat(mainPage.getTextOfPageHeader(), is(equalTo("Visi dokumentai")));
    }
}
