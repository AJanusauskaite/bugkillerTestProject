package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainPage extends AbstractPage {

    public String URL="http://141.136.44.216:8080/dashboard/documents/all";

    //headings
    @FindBy(xpath = "//*[@id=\"mainnavbar\"]/h5")
    private WebElement headingNameSurnameUsername;

    @FindBy(className = "page-header")
    private WebElement pageHeader;

    //TOP navigation buttons

    @FindBy(xpath = "//*[@id=\"pageContent\"]/div[2]/div[1]/div[1]/a")
    private WebElement navVisiDokumentai;

    @FindBy(xpath = "//*[@id=\"pageContent\"]/div[2]/div[1]/div[2]/a")
    private WebElement navSukurtiDokumentai;

    @FindBy(xpath = "//*[@id=\"pageContent\"]/div[2]/div[1]/div[3]/a")
    private WebElement navPateiktiDokumentai;

    @FindBy(xpath = "//*[@id=\"pageContent\"]/div[2]/div[1]/div[4]/a")
    private WebElement navPatvirtintiDokumentai;

    @FindBy(xpath = "//*[@id=\"pageContent\"]/div[2]/div[1]/div[5]/a")
    private WebElement navAtmestiDokumentai;

    @FindBy(xpath = "//*[@id=\"pageContent\"]/div[2]/div[1]/div[6]/a")
    private WebElement navDokumentaiTvirtinimui;

    @FindBy(id = "appbarText")
    private WebElement buttonDVS;

    public String getTextOfPageHeader(){
        return pageHeader.getText();
    }
    public void clickButtonDVS(){
        buttonDVS.click();
        WebDriverWait wait=new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul:nth-child(1) > li")));
    }

    public void clickNavSukurtiDokumentai() {
        navSukurtiDokumentai.click();
        WebDriverWait wait=new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("tbody a")));
        //Thread.sleep(5000);
    }
    public void clickNavVisiDokumentai(){
        navVisiDokumentai.click();
    }

    public void clickNavPateiktiDokumentai(){
        navPateiktiDokumentai.click();
    }

    public void clickNavPatvirtintiDokumentai(){
        navPatvirtintiDokumentai.click();
    }

    public void clickNavAtmestiDokumentai(){
        navAtmestiDokumentai.click();
    }


    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void clickNavDokumentaiTvirtinimui(){
        navDokumentaiTvirtinimui.click();
        WebDriverWait wait=new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("tbody a")));
    }

    public String getTextOfHeadingNameSurnameUsername(){
        return headingNameSurnameUsername.getText();
    }


    public List<WebElement> findAllButtonsDetaliau(){
        return driver.findElements(By.cssSelector("tbody a"));
    }


    public void clickButtonDetaliau(int index){
        System.out.println("buttons detaliau:"+findAllButtonsDetaliau().size());
        findAllButtonsDetaliau().get(index).click();
    }

    public int getLastIndexOfListButtonsDetaliau(){
        return findAllButtonsDetaliau().size()-1;
    }


    public List<WebElement> findAllPageButtons(){
        return driver.findElements(By.cssSelector("ul:nth-child(1) > li"));
    }

    public void clickPageButtonFromList(int index){
        findAllPageButtons().get(index).click();
        WebDriverWait wait=new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("tbody a")));
    }

    public int getLastIndexOfPagesList(){
        System.out.println(findAllPageButtons().size());
        return (findAllPageButtons().size()-2);  //because the last element is 'kitas puslapis'
    }





}

