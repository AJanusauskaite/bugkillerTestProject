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

/**Test Menu navigation bar
 * "1) open menu navbar (click on menu button)
 * 2) click on each option in navbar"
 */

public class SideMenuNavigation extends AbstractTest {
    LoginPage loginPage;
    MainPage mainPage;
    FileReaderUtils fileReaderUtils;
    SideNavigation sideNavigation;

    @Before
    public void openPage(){
        driver.get("http://141.136.44.216:8080/kodas-spring-1.0-SNAPSHOT/");
    }

    @Test
    public void testSideMenuNavigation() throws IOException {
        loginPage=new LoginPage(driver);
        mainPage=new MainPage(driver);
        fileReaderUtils=new FileReaderUtils();
        sideNavigation=new SideNavigation(driver);

        List<String> testData = fileReaderUtils.getTestData("src/test/resources/LoginTestData.txt");

        loginPage.fillInputFieldUsername(testData.get(0));
        loginPage.fillInputFieldPassword(testData.get(1));
        loginPage.clickBtnPrisijungti();

        sideNavigation.clickBtnProfilis();
        assertThat(mainPage.getTextOfPageHeader(), is(equalTo("Naudotojo profilis")));

        sideNavigation.clickBtnIkelti();
        assertThat(mainPage.getTextOfPageHeader(), is(equalTo("Naujo dokumento kūrimas")));

        sideNavigation.clickBtnNaudotojai();
        assertThat(mainPage.getTextOfPageHeader(), is(equalTo("Naudotojų administravimas")));

        sideNavigation.clickBtnNustatymai();
        assertThat(mainPage.getTextOfPageHeader(), is(equalTo("Nustatymai")));

        sideNavigation.clickBtnStatistika();
        assertThat(mainPage.getTextOfPageHeader(), is(equalTo("Statistika")));

    }

}
