package tejaswi.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import tejaswi.pageObject.*;
import tejaswi.testComponents.BaseTests;

import java.io.IOException;
import java.util.List;

public class StepDefinitionImpl extends BaseTests {

    public LandingPage landingPage;
    public ProductCateloguePage productCateloguePage;
    public CheckoutPage checkoutPage;
    public ConfirmationPage confirmationPage;
    @Given("I landed on Ecommerce Page")
    public void i_Landed_on_Ecommerce_Page() throws IOException {
        landingPage = launchApplication();
    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void logged_in_username_and_password(String username , String password)
    {
        productCateloguePage = landingPage.loginAppplication(username, password);
    }

    @When("^I add product (.+) from cart$")
    public void i_Add_Product_to_Cart(String productName) throws InterruptedException {
        List<WebElement> products = productCateloguePage.getProductList();
        productCateloguePage.addProductToCart(productName);
    }

    @When("^Checkout (.+) and submit the order$")
    public void checkout_submit_order(String productName)
    {
        CartPage cartPage = productCateloguePage.gotoCartPage();
        Boolean match = cartPage.verifyProductDisplay(productName);
        Assert.assertTrue(match);
        checkoutPage = cartPage.gotoCheckout();
        checkoutPage.selectCountry("india");
        confirmationPage = checkoutPage.submitOrder();
    }

    @Then("{string} message is displayed on ConfirmationPage")
    public void message_Displayed_ConfirmationPage(String string)
    {
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
        driver.close();
    }

    @Then("{string} message is displayed")
    public void something_Message_is_Displayed(String message)
    {
        Assert.assertEquals(message, landingPage.getErrorMessage());
        driver.close();
    }

}
