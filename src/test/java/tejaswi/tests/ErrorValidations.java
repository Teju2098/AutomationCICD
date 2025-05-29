package tejaswi.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import tejaswi.pageObject.CartPage;
import tejaswi.pageObject.CheckoutPage;
import tejaswi.pageObject.ConfirmationPage;
import tejaswi.pageObject.ProductCateloguePage;
import tejaswi.testComponents.BaseTests;
import tejaswi.testComponents.Retry;

import java.io.IOException;
import java.util.List;

public class ErrorValidations extends BaseTests {



    @Test (groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
    public void loginErrorValidation() throws IOException, InterruptedException {




        landingPage.loginAppplication("saitejaswiselenium@gmail.com" , "Seleni@24");
        Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
    }

    @Test
    public void productErrorValidation() throws IOException, InterruptedException {

        String productName= "ZARA COAT 3";
        ProductCateloguePage productCateloguePage = landingPage.loginAppplication("testAuto@gmail.com" , "Test@auto1");
        List<WebElement> products = productCateloguePage.getProductList();
        productCateloguePage.addProductToCart(productName);
        Thread.sleep(8000);
        CartPage cartPage = productCateloguePage.gotoCartPage();
        Boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
        Assert.assertFalse(match);
    }

}
