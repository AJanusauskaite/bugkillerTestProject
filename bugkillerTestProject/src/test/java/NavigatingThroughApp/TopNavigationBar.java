package NavigatingThroughApp;

import Pages.LoginPage;
import Pages.MainPage;
import Test.AbstractTest;
import Utils.FileReaderUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**Test navigation through document viewing windows (Visi, Sukurti, Pateikti, Patvirtinti, Atmesti, Tvirtinimui)
 * "1)  click on 'Sukurti dokumentai'
 * 2) click on Visi dokumentai'
 * 3) click on 'Pateikti dokumentai'
 * 4) click on 'Patvirtinti dokumentai'
 * 5) click on 'Atmesti dokumentai'
 * 6) click on 'Dokumentai tvirtinimui
 * 7) click on 'Ankstesnis puslapis'"
 */
public class TopNavigationBar extends AbstractTest {
    LoginPage loginPage;
    MainPage mainPage;
    FileReaderUtils fileReaderUtils;

    @Before
    public void openPage(){
        driver.get("http://141.136.44.216:8080/kodas-spring-1.0-SNAPSHOT/");
    }

    @Test
    public void testTopNavigationBar() throws IOException {
        loginPage=new LoginPage(driver);
        mainPage=new MainPage(driver);
        fileReaderUtils=new FileReaderUtils();

        List<String> testData = fileReaderUtils.getTestData("src/test/resources/LoginTestData.txt");

        loginPage.fillInputFieldUsername(testData.get(0));
        loginPage.fillInputFieldPassword(testData.get(1));
        loginPage.clickBtnPrisijungti();

        mainPage.clickNavSukurtiDokumentai();
        assertThat(mainPage.getTextOfPageHeader(), is(equalTo("Sukurti dokumentai")));

        mainPage.clickNavVisiDokumentai();
        assertThat(mainPage.getTextOfPageHeader(), is(equalTo("Visi dokumentai")));

        mainPage.clickNavPateiktiDokumentai();
        assertThat(mainPage.getTextOfPageHeader(), is(equalTo("Pateikti dokumentai")));

        mainPage.clickNavPatvirtintiDokumentai();
        assertThat(mainPage.getTextOfPageHeader(), is(equalTo("Patvirtinti dokumentai")));

        mainPage.clickNavAtmestiDokumentai();
        assertThat(mainPage.getTextOfPageHeader(), is(equalTo("Atmesti dokumentai")));

        mainPage.clickNavDokumentaiTvirtinimui();
        assertThat(mainPage.getTextOfPageHeader(), is(equalTo("Dokumentai tvirtinimui")));
    }
}
