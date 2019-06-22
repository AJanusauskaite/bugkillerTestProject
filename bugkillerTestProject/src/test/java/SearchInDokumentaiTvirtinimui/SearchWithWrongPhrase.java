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
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;

/**Test search field in window 'Dokumentai tvirtinimui' with wrong phrase
 * "1)  click on 'Dokumentai tvirtinimui'
 * 2) click on search field
 * 3) enter 'susitarimas'
 * 4) click search button"
 */

public class SearchWithWrongPhrase extends AbstractTest {
    LoginPage loginPage;
    MainPage mainPage;
    DokumentaiTvirtinimui dokumentaiTvirtinimui;
    FileReaderUtils fileReaderUtils;

    @Before
    public void openPage() {
        driver.get("http://141.136.44.216:8080/kodas-spring-1.0-SNAPSHOT/");
    }

    @Test
    public void testSearchWithWrongPhrase() throws IOException {
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        dokumentaiTvirtinimui = new DokumentaiTvirtinimui(driver);
        fileReaderUtils=new FileReaderUtils();

        List<String> loginData = fileReaderUtils.getTestData("src/test/resources/LoginTestData.txt");
        List<String> searchPhrases = fileReaderUtils.getTestData("src/test/resources/SearchPhrases.txt");

        loginPage.fillInputFieldUsername(loginData.get(0));
        loginPage.fillInputFieldPassword(loginData.get(1));
        loginPage.clickBtnPrisijungti();
        mainPage.clickNavDokumentaiTvirtinimui();
        dokumentaiTvirtinimui.fillSearchField(searchPhrases.get(0));
        dokumentaiTvirtinimui.clickButtonIeskoti();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        // because explicit wait doesn't work, implicit wait in method clickBtnIeskoti doesn't wait

        assertTrue("Search results are shown. There are not supposed to be any.",
                dokumentaiTvirtinimui.getListOfSearchResultTableRows().size()<2);

    }
}
