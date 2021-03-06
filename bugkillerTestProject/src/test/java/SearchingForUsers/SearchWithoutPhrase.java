package SearchingForUsers;

import Pages.LoginPage;
import Pages.MainPage;
import Pages.NaudotojuAdministravimas;
import Pages.SideNavigation;
import Test.AbstractTest;
import Utils.FileReaderUtils;
import Utils.Waits;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;

/**Test search functionality without input
 * 1)click button 'Ieškoti'
 */
public class SearchWithoutPhrase extends AbstractTest {
    LoginPage loginPage;
    MainPage mainPage;
    NaudotojuAdministravimas naudotojuAdministravimas;
    SideNavigation sideNavigation;
    FileReaderUtils fileReaderUtils;
    Waits waitsFor;

    @Before
    public void openPage() {
        driver.get("http://141.136.44.216:8080/kodas-spring-1.0-SNAPSHOT/");
    }

    @Test
    public void testSearchUsersWithoutInput() throws IOException, InterruptedException {
        loginPage=new LoginPage(driver);
        mainPage=new MainPage(driver);
        naudotojuAdministravimas=new NaudotojuAdministravimas(driver);
        sideNavigation=new SideNavigation(driver);
        fileReaderUtils=new FileReaderUtils();
        waitsFor=new Waits();

        List<String> loginData = fileReaderUtils.getTestData("src/test/resources/LoginTestData.txt");

        loginPage.fillInputFieldUsername(loginData.get(0));
        loginPage.fillInputFieldPassword(loginData.get(1));
        loginPage.clickBtnPrisijungti();

        sideNavigation.clickBtnNaudotojai();
        naudotojuAdministravimas.clickBtnSearch();
        waitsFor.waitForAlertToBePresent(driver);

        //assert that alert message is shown and close it
        assertThat(naudotojuAdministravimas.getAlertMsgText(), containsString("Pagal paiešką nerasta "));
        naudotojuAdministravimas.acceptAlertMsg();

        //assert that the search result table is empty
        assertTrue("search results are shown", naudotojuAdministravimas.getListOfSearchResults().size()==1);

    }
}
