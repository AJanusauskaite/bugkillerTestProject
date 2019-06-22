package DetaliauButtonFunctionality;

import Pages.DokumentoDetales;
import Pages.LoginPage;
import Pages.MainPage;
import Test.AbstractTest;
import Utils.FileReaderUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

/**Test functionality 'Detaliau' for documents in window 'Sukurti dokumentai'
 * "1) click on 'Sukurti dokumentai'
 * 2) click button 'Detaliau' of 1st document on 1st page
 * 3) click button 'Detaliau' of last document on 1st page"
 */
public class BtnDetaliauSukurtiDokumentai extends AbstractTest {

    LoginPage loginPage;
    MainPage mainPage;
    DokumentoDetales dokumentoDetales;
    FileReaderUtils fileReaderUtils;

    @Before
    public void openPage(){
        driver.get("http://141.136.44.216:8080/kodas-spring-1.0-SNAPSHOT/");
    }

    @Test
    public void testBtnDetaliauInSukurtiDokumentai() throws IOException, InterruptedException {
        loginPage=new LoginPage(driver);
        mainPage=new MainPage(driver);
        dokumentoDetales=new DokumentoDetales(driver);
        fileReaderUtils=new FileReaderUtils();

        List<String> testData = fileReaderUtils.getTestData("src/test/resources/LoginTestData.txt");

        loginPage.fillInputFieldUsername(testData.get(0));
        loginPage.fillInputFieldPassword(testData.get(1));
        loginPage.clickBtnPrisijungti();

        mainPage.clickNavSukurtiDokumentai();

        //click on first Detaliau btn on first page
        mainPage.clickButtonDetaliau(0);
        assertThat(dokumentoDetales.getTextOfTableHeading(), containsString("DOKUMENTO DETALĖS"));

        mainPage.clickButtonDVS();
        mainPage.clickNavSukurtiDokumentai();

        //click on last Detaliau btn on first page
        mainPage.clickButtonDetaliau(mainPage.getLastIndexOfListButtonsDetaliau());
        assertThat(dokumentoDetales.getTextOfTableHeading(), containsString("DOKUMENTO DETALĖS"));

        mainPage.clickButtonDVS();
        mainPage.clickNavSukurtiDokumentai();

        //go to last page
        mainPage.clickPageButtonFromList(mainPage.getLastIndexOfPagesList());
        //click on the first document button Detaliau
        mainPage.clickButtonDetaliau(0);
        assertThat(dokumentoDetales.getTextOfTableHeading(), containsString("DOKUMENTO DETALĖS"));

        mainPage.clickButtonDVS();
        mainPage.clickNavSukurtiDokumentai();
        //go to last page
        mainPage.clickPageButtonFromList(mainPage.getLastIndexOfPagesList());

        //click on the last document on the last page
        mainPage.clickButtonDetaliau(mainPage.getLastIndexOfListButtonsDetaliau());
        assertThat(dokumentoDetales.getTextOfTableHeading(), containsString("DOKUMENTO DETALĖS"));

        //mainPage.clickButtonDVS();

    }

}
