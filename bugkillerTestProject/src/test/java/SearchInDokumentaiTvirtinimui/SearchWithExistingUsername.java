package SearchInDokumentaiTvirtinimui;

import Pages.DokumentaiTvirtinimui;
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

import static junit.framework.TestCase.assertTrue;

/**Test search field in window 'Dokumentai tvirtinimui' with existing username
 * "1)  click on 'Dokumentai tvirtinimui'
 * 2) click on search field
 * 3) enter 'Dianna1'
 * 4) click search button"
 */

public class SearchWithExistingUsername extends AbstractTest {

    LoginPage loginPage;
    MainPage mainPage;
    DokumentaiTvirtinimui dokumentaiTvirtinimui;
    FileReaderUtils fileReaderUtils;

    @Before
    public void openPage() {
        driver.get("http://141.136.44.216:8080/kodas-spring-1.0-SNAPSHOT/");
    }

    @Test
    public void testSearchWithExistingUsername() throws IOException{
        loginPage=new LoginPage(driver);
        mainPage=new MainPage(driver);
        dokumentaiTvirtinimui=new DokumentaiTvirtinimui(driver);
        fileReaderUtils=new FileReaderUtils();

        List<String> loginData = fileReaderUtils.getTestData("src/test/resources/LoginTestData.txt");
        List<String> searchPhrases = fileReaderUtils.getTestData("src/test/resources/SearchPhrases.txt");

        loginPage.fillInputFieldUsername(loginData.get(0));
        loginPage.fillInputFieldPassword(loginData.get(1));
        loginPage.clickBtnPrisijungti();
        mainPage.clickNavDokumentaiTvirtinimui();
        dokumentaiTvirtinimui.fillSearchField(searchPhrases.get(1));
        dokumentaiTvirtinimui.clickButtonIeskoti();

        assertTrue(dokumentaiTvirtinimui.getListOfSearchResultTableRows().size()==3);

        //compare usernames in search results to the username that was tyoed in the search field - are proper results found
       assertTrue("search results do not match serach phrase", dokumentaiTvirtinimui.compareUsernameListToSearchPhrase(searchPhrases.get(1)));

    }

}
