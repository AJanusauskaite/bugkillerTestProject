package DetaliauButtonFunctionality;

import Pages.DokumentaiTvirtinimui;
import Pages.DokumentoDetales;
import Pages.LoginPage;
import Pages.MainPage;
import Test.AbstractTest;
import Utils.FileReaderUtils;
import org.junit.Before;
import org.junit.Test;
import sun.nio.ch.IOStatus;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

/**Test button 'Detaliau' functionality in search results window
 * "1)  click on 'Dokumentai tvirtinimui'
 * 2) click on search field
 * 3) enter 'Sol3'
 * 4) click search button
 * 5) click button 'Detaliau' on 4th document on the list in search results"
 */
public class BtnDetaliauInSearchResults extends AbstractTest {
    LoginPage loginPage;
    MainPage mainPage;
    DokumentoDetales dokumentoDetales;
    FileReaderUtils fileReaderUtils;
    DokumentaiTvirtinimui dokumentaiTvirtinimui;

    @Before
    public void openPage(){
        driver.get("http://141.136.44.216:8080/kodas-spring-1.0-SNAPSHOT/");
    }

    @Test
    public void testDetaliauBtnInSearchResults() throws IOException {
        loginPage=new LoginPage(driver);
        mainPage=new MainPage(driver);
        dokumentoDetales=new DokumentoDetales(driver);
        fileReaderUtils=new FileReaderUtils();
        dokumentaiTvirtinimui=new DokumentaiTvirtinimui(driver);

        List<String> testData = fileReaderUtils.getTestData("src/test/resources/LoginTestData.txt");
        List<String> searchPhrases = fileReaderUtils.getTestData("src/test/resources/SearchPhrases.txt");

        loginPage.fillInputFieldUsername(testData.get(0));
        loginPage.fillInputFieldPassword(testData.get(1));
        loginPage.clickBtnPrisijungti();

        mainPage.clickNavDokumentaiTvirtinimui();
        dokumentaiTvirtinimui.fillSearchField(searchPhrases.get(5));
        dokumentaiTvirtinimui.clickButtonIeskoti();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);// because explicit wait doesn't work, implicit wait in method clickBtnIeskoti doesn't wait

        //assert that there are search results shown
        assertTrue(dokumentaiTvirtinimui.getListOfSearchResultTableRows().size()>4);

        //click button Detaliau on 4th search result
        mainPage.clickButtonDetaliau(3);
        //assert that window Dokumento detales opens
        assertThat(dokumentoDetales.getTextOfTableHeading(), containsString("DOKUMENTO DETALĖS"));

    }
}
