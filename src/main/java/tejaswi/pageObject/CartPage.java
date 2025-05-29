package tejaswi.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import tejaswi.abstractComponents.AbstractComponent;

import java.util.List;

public class CartPage extends AbstractComponent {

    WebDriver driver;

    @FindBy(css = ".cartSection h3" )
    List<WebElement> productTitles;

    @FindBy(css = ".totalRow button" )
    WebElement checkoutEle;

    public CartPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }


    public Boolean verifyProductDisplay (String productName)
    {
        Boolean match = productTitles.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
        return match;
    }

    public CheckoutPage gotoCheckout()
    {
        checkoutEle.click();
        return  new CheckoutPage(driver);
    }





}
