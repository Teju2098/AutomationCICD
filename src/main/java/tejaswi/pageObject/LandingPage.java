package tejaswi.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tejaswi.abstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

    WebDriver driver;
    public LandingPage( WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

   // WebElement userEmail =  driver.findElement(By.id("userEmail"));

    @FindBy(id = "userEmail" )
    WebElement userEmail;

    @FindBy(id = "userPassword" )
    WebElement passwordEle;

    @FindBy(id = "login" )
    WebElement submit;
    @FindBy(css="[class*='flyInOut']")
    WebElement errorMessage;

    public ProductCateloguePage loginAppplication(String email, String password)
    {
        userEmail.sendKeys(email);
        passwordEle.sendKeys(password);
        submit.click();
        ProductCateloguePage productCateloguePage = new ProductCateloguePage(driver);
        return productCateloguePage;
    }

    public void goTo()
    {
        driver.get("https://rahulshettyacademy.com/client");
    }

    public String getErrorMessage()
    {
        waitForAnWebElementToAppear(errorMessage);
        return errorMessage.getText();
    }


}
