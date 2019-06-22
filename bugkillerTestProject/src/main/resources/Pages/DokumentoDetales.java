package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DokumentoDetales extends AbstractPage {

    @FindBy(className = "text-center")
    private WebElement tableHeading;


    public DokumentoDetales(WebDriver driver){
        super(driver);
    }


    public String getTextOfTableHeading(){
        return tableHeading.getText();
    }
}
