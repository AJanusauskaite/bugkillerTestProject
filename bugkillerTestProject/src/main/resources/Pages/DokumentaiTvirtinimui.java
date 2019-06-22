package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class DokumentaiTvirtinimui extends AbstractPage {

    public DokumentaiTvirtinimui(WebDriver driver) {
        super(driver);
    }

    @FindBy(name = "searchField")
    private WebElement searchField;

    @FindBy(className = "button2 ")
    private WebElement buttonIeskoti;



    public void fillSearchField(String searchFor){
        searchField.sendKeys(searchFor);
    }

    public void clickButtonIeskoti(){
        buttonIeskoti.click();
        //explicit wait doesn't work when you need to search with wrong phrase - no results are loaded, nothing to wait for
        WebDriverWait wait=new WebDriverWait(driver, 4);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("tbody a")));
       // driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public List<WebElement> getListOfSearchResultTableRows(){
        List<WebElement> listOfSearchResultTableRows =driver.findElements(By.tagName("tr"));
        System.out.println("amount of search results:" +listOfSearchResultTableRows.size());
        return listOfSearchResultTableRows;
    }

    //make a list of all usernames in the table, in order to compare them with the search phrase
    public List<WebElement> findAllUsernamesInSearchResults(){
        return driver.findElements(By.cssSelector("tr >td:nth-child(2)"));
    }

    public boolean compareUsernameListToSearchPhrase(String searchphrase){
        List<WebElement> usernamesList=findAllUsernamesInSearchResults();
        for(WebElement element:usernamesList){
            System.out.println(element.getText());
            if(!element.getText().equals(searchphrase)){
                return false;
            }
        }
        return true;
    }


    //find all doctypes from search result and put them i a list
    public List<WebElement> findAllDoctypesInSearchResults(){
        return driver.findElements(By.cssSelector("tr >td:nth-child(4)"));
    }

    public boolean compareDoctypeListToSearchPhrase(String searchphrase){
        List <WebElement> doctypes=findAllDoctypesInSearchResults();
        for(WebElement doctype:doctypes){
            if(!doctype.getText().equals(searchphrase)){
                return false;
            }
        }
        return true;
    }

}
