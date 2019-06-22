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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertTrue;

/**Test search functionality with existing user's incomplete username
 *"1) enter Dianna to search field
 * 2) click search button"
 */
public class SearchWithPartialUsername extends AbstractTest {
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
    public void testSearchWithIncompleteUsername() throws IOException, InterruptedException {

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
        naudotojuAdministravimas.fillInputSearchField(searchUsers.get(1));
        naudotojuAdministravimas.clickBtnSearch();
        //waitsFor.waitForElementsToBePresent(driver, "#userListTable > table > tbody>tr");
        WebDriverWait wait= new WebDriverWait(driver, 3);

        do {
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        }while(naudotojuAdministravimas.getListOfSearchResults().size()<2);


        //testas randa tik viena elementa, nors yra 3
        assertTrue("amount of search results incorrect",
                naudotojuAdministravimas.getListOfSearchResults().size()==4);
        //kitaip neveikia: rezultatai kraunami vienas po kito, o ne surenkami ir pateikiami vienu kartu, todel expected conditions
        //sulaukes pirmo rezultato nulauzia testa. Implicit wait neveikia. Bet palaukus surandama ir viena papildoma tuscia eilute,
        //kurios aukstis 0

        assertTrue("search field is not cleared", naudotojuAdministravimas.getInputSearchField().getText().isEmpty());

        //check that the username in search results is same as search phrase
        assertTrue(naudotojuAdministravimas.compareFoundUsernameToSearchedContains(searchUsers.get(1)));

    }
}
