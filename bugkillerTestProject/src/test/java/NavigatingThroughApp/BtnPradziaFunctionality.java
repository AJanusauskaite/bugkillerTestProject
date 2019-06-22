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

/**Test navigation button 'Pradžia' functionality
 * "1) go to each section on top navigation and side menu bar
 * 2)after navigating to each section, click button 'Pradžia'"
 */

public class BtnPradziaFunctionality extends AbstractTest {
    LoginPage loginPage;
    MainPage mainPage;
    FileReaderUtils fileReaderUtils;
    SideNavigation sideNavigation;

    @Before
    public void openPage(){
        driver.get("http://141.136.44.216:8080/kodas-spring-1.0-SNAPSHOT/");
    }

    @Test
    public void testBtnPradziaFunctionality() throws IOException {
        loginPage=new LoginPage(driver);
        mainPage=new MainPage(driver);
        fileReaderUtils=new FileReaderUtils();
        sideNavigation=new SideNavigation(driver);

        List<String> testData = fileReaderUtils.getTestData("src/test/resources/LoginTestData.txt");

        loginPage.fillInputFieldUsername(testData.get(0));
        loginPage.fillInputFieldPassword(testData.get(1));
        loginPage.clickBtnPrisijungti();

        //go to each page of top navigation
        mainPage.clickNavSukurtiDokumentai();
        sideNavigation.clickBtnHome();
        assertThat(mainPage.getTextOfPageHeader(), is(equalTo("Visi dokumentai")));

        mainPage.clickNavPateiktiDokumentai();
        sideNavigation.clickBtnHome();
        assertThat(mainPage.getTextOfPageHeader(), is(equalTo("Visi dokumentai")));

        mainPage.clickNavAtmestiDokumentai();
        sideNavigation.clickBtnHome();
        assertThat(mainPage.getTextOfPageHeader(), is(equalTo("Visi dokumentai")));

        mainPage.clickNavPatvirtintiDokumentai();
        sideNavigation.clickBtnHome();
        assertThat(mainPage.getTextOfPageHeader(), is(equalTo("Visi dokumentai")));

        mainPage.clickNavDokumentaiTvirtinimui();
        sideNavigation.clickBtnHome();
        assertThat(mainPage.getTextOfPageHeader(), is(equalTo("Visi dokumentai")));

        //go to each menu navigation page and click button DVS
        sideNavigation.clickBtnStatistika();
        sideNavigation.clickBtnHome();
        assertThat(mainPage.getTextOfPageHeader(), is(equalTo("Visi dokumentai")));

        sideNavigation.clickBtnNustatymai();
        sideNavigation.clickBtnHome();
        assertThat(mainPage.getTextOfPageHeader(), is(equalTo("Visi dokumentai")));

        sideNavigation.clickBtnNaudotojai();
        sideNavigation.clickBtnHome();
        assertThat(mainPage.getTextOfPageHeader(), is(equalTo("Visi dokumentai")));

        sideNavigation.clickBtnIkelti();
        sideNavigation.clickBtnHome();
        assertThat(mainPage.getTextOfPageHeader(), is(equalTo("Visi dokumentai")));

        sideNavigation.clickBtnProfilis();
        sideNavigation.clickBtnHome();
        assertThat(mainPage.getTextOfPageHeader(), is(equalTo("Visi dokumentai")));
    }

}
