package tejaswi.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tejaswi.abstractComponents.AbstractComponent;

import java.util.List;

public class ProductCateloguePage extends AbstractComponent {

    WebDriver driver;
    public ProductCateloguePage(WebDriver driver)
    {
        super(driver);
        this.driver = this.driver;
        PageFactory.initElements(driver,this);
    }

   // WebElement userEmail =  driver.findElement(By.id("userEmail"));

    @FindBy(css = ".mb-3" )
    List<WebElement> products;

    @FindBy(css = ".ng-animating" )
    WebElement spinner;

    By productsBy = By.cssSelector(".mb-3");
    By addToCart = By.cssSelector(".card-body button:last-of-type");
    By toastMessage = By.cssSelector("#toast-container");
    // By cartBy = By.cssSelector("[routerlink*= 'cart']"));

    public List<WebElement> getProductList()
    {
        waitForAnElementToAppear(productsBy);
        return products;
    }

    public WebElement getProductByName(String productName)
    {


        WebElement prod = getProductList().stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName))
                .findFirst().orElse(null);
        return prod;
    }

    public void addProductToCart(String productName) throws InterruptedException {
        WebElement prod = getProductByName(productName);
        prod.findElement(addToCart).click();
        waitForAnElementToAppear(toastMessage);
        waitForElementToDisappear(spinner);
        // waitForAnElementToBeClickable(cartBy);

    }





}
