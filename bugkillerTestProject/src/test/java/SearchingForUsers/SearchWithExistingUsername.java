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
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;


/**Test search functionality with existing username
 * "1) enter Dianna1 to search field
 * 2) click search button"
 */
public class SearchWithExistingUsername extends AbstractTest {
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
    public void testSearchWithExistingUsername() throws IOException {
        loginPage=new LoginPage(driver);
        mainPage=new MainPage(driver);
        naudotojuAdministravimas=new NaudotojuAdministravimas(driver);
        sideNavigation=new SideNavigation(driver);
        fileReaderUtils=new FileReaderUtils();
        waitsFor=new Waits();

        List<String> loginData = fileReaderUtils.getTestData("src/test/resources/LoginTestData.txt");
        List<String> searchUsers = fileReaderUtils.getTestData("src/test/resources/SearchUsers.txt");

        loginPage.fillInputFieldUsername(loginData.get(0));
        loginPage.fillInputFieldPassword(loginData.get(1));
        loginPage.clickBtnPrisijungti();

        sideNavigation.clickBtnNaudotojai();
        naudotojuAdministravimas.fillInputSearchField(searchUsers.get(0));
        naudotojuAdministravimas.clickBtnSearch();

        waitsFor.waitForElementsToBePresent(driver,"#userListTable > table > tbody>tr");


        assertTrue("amount of search results incorrect",
                naudotojuAdministravimas.getListOfSearchResults().size()==1);

        assertTrue("search field is not cleared", naudotojuAdministravimas.getInputSearchField().getText().isEmpty());

        //check that the username in search results is same as search phrase
        assertTrue(naudotojuAdministravimas.compareFoundUsernameToSearchedEquals(searchUsers.get(0)));

    }

}
