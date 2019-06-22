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

/**Test button DVS functionality
 * 1) go to each section on navigation and menu bar and click button DVS
 */
public class BtnDVSFunctionality extends AbstractTest {

    LoginPage loginPage;
    MainPage mainPage;
    FileReaderUtils fileReaderUtils;
    SideNavigation sideNavigation;

    @Before
    public void openPage(){
        driver.get("http://141.136.44.216:8080/kodas-spring-1.0-SNAPSHOT/");
    }

    @Test
    public void testBtnDVSFunctionality() throws IOException {
        loginPage=new LoginPage(driver);
        mainPage=new MainPage(driver);
        fileReaderUtils=new FileReaderUtils();
        sideNavigation=new SideNavigation(driver);

        List<String> testData = fileReaderUtils.getTestData("src/test/resources/LoginTestData.txt");

        loginPage.fillInputFieldUsername(testData.get(0));
        loginPage.fillInputFieldPassword(testData.get(1));
        loginPage.clickBtnPrisijungti();


        //go to each section of top navigation bar and click button DVS
        mainPage.clickNavSukurtiDokumentai();
        mainPage.clickButtonDVS();
        assertThat(mainPage.getTextOfPageHeader(), is(equalTo("Visi dokumentai")));

        mainPage.clickNavPateiktiDokumentai();
        mainPage.clickButtonDVS();
        assertThat(mainPage.getTextOfPageHeader(), is(equalTo("Visi dokumentai")));

        mainPage.clickNavAtmestiDokumentai();
        mainPage.clickButtonDVS();
        assertThat(mainPage.getTextOfPageHeader(), is(equalTo("Visi dokumentai")));

        mainPage.clickNavPatvirtintiDokumentai();
        mainPage.clickButtonDVS();
        assertThat(mainPage.getTextOfPageHeader(), is(equalTo("Visi dokumentai")));

        mainPage.clickNavDokumentaiTvirtinimui();
        mainPage.clickButtonDVS();
        assertThat(mainPage.getTextOfPageHeader(), is(equalTo("Visi dokumentai")));

        //go to each menu navigation page and click button DVS
        sideNavigation.clickBtnStatistika();
        mainPage.clickButtonDVS();
        assertThat(mainPage.getTextOfPageHeader(), is(equalTo("Visi dokumentai")));

        sideNavigation.clickBtnNustatymai();
        mainPage.clickButtonDVS();
        assertThat(mainPage.getTextOfPageHeader(), is(equalTo("Visi dokumentai")));

        sideNavigation.clickBtnNaudotojai();
        mainPage.clickButtonDVS();
        assertThat(mainPage.getTextOfPageHeader(), is(equalTo("Visi dokumentai")));

        sideNavigation.clickBtnIkelti();
        mainPage.clickButtonDVS();
        assertThat(mainPage.getTextOfPageHeader(), is(equalTo("Visi dokumentai")));

        sideNavigation.clickBtnProfilis();
        mainPage.clickButtonDVS();
        assertThat(mainPage.getTextOfPageHeader(), is(equalTo("Visi dokumentai")));
    }
}
