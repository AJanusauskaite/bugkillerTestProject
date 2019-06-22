package NavigatingThroughApp;

import Pages.LoginPage;
import Pages.MainPage;
import Test.AbstractTest;
import Utils.FileReaderUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

/**Test page navigation in document viewing windows
 * "1)  click on 'Dokumentai tvirtinimui'
 * 2) click on page 2
 * 3) click on 'Kitas puslapis'
 * 4) click on last page in navigation
 * 5) click on 'Ankstesnis puslapis'
 * 6)expand the collapsed pages and click on page 11"
 */

public class PagingNavigation extends AbstractTest {
    LoginPage loginPage;
    MainPage mainPage;
    FileReaderUtils fileReaderUtils;

    @Before
    public void openPage(){
        driver.get("http://141.136.44.216:8080/kodas-spring-1.0-SNAPSHOT/");
    }

    @Test
    public void testPagingNavigation() throws IOException {
        loginPage=new LoginPage(driver);
        mainPage=new MainPage(driver);
        fileReaderUtils=new FileReaderUtils();

        List<String> testData = fileReaderUtils.getTestData("src/test/resources/LoginTestData.txt");

        loginPage.fillInputFieldUsername(testData.get(0));
        loginPage.fillInputFieldPassword(testData.get(1));
        loginPage.clickBtnPrisijungti();

        mainPage.clickNavDokumentaiTvirtinimui();
        mainPage.clickPageButtonFromList(2);  //go to page 2
        assertTrue("page 2 is not your current page",
                mainPage.findAllPageButtons().get(2).getAttribute("class").equals("active")); //check if you're on page 2

        mainPage.clickPageButtonFromList(mainPage.findAllPageButtons().size()-1);
        assertTrue("page 3 is not your current page",
                mainPage.findAllPageButtons().get(3).getAttribute("class").equals("active")); //check if you're on page 3

        //go to last page in navigation
        mainPage.clickPageButtonFromList(mainPage.findAllPageButtons().size()-2);
        assertTrue("last page is not your current page",
                mainPage.findAllPageButtons().get(mainPage.findAllPageButtons().size()-2).
                        getAttribute("class").equals("active")); //check if you're on last page in nav

        //click ankstesnis puslapis
        mainPage.clickPageButtonFromList(0);
        assertTrue("last page is not your current page",
                mainPage.findAllPageButtons().get(mainPage.findAllPageButtons().size()-3).
                        getAttribute("class").equals("active"));

        //expand collapsed pagination
        mainPage.clickPageButtonFromList(9);
        //click on page11
        mainPage.clickPageButtonFromList(11);
        assertTrue("last page is not your current page",
                mainPage.findAllPageButtons().get(11).
                        getAttribute("class").equals("active"));
    }

}
