package tejaswi.tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tejaswi.pageObject.*;
import tejaswi.testComponents.BaseTests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTests {
    String productName= "ZARA COAT 3";

    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {

        ProductCateloguePage productCateloguePage = landingPage.loginAppplication(input.get("email") , input.get("password"));
        List<WebElement> products = productCateloguePage.getProductList();
        productCateloguePage.addProductToCart(input.get("product"));
        // wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[routerlink*= 'cart']")));
        Thread.sleep(5000);
        CartPage cartPage = productCateloguePage.gotoCartPage();
        Boolean match = cartPage.verifyProductDisplay(input.get("product"));
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.gotoCheckout();
        checkoutPage.selectCountry("india");
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
    }

    @Test(dependsOnMethods = {"submitOrder"})
    public void orderHistory()
    {
        ProductCateloguePage productCateloguePage = landingPage.loginAppplication("saitejaswiselenium@gmail.com" , "Selenium@24");
        OrderPage orderPage = productCateloguePage.gotoOrdersPage();
        Assert.assertTrue(orderPage.verifyOrderDisplay(productName));

    }

    @DataProvider
    public Object[][] getData() throws IOException {

        List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//tejaswi//data//PurchaseOrder.json");


        return new Object[][] {{data.get(0)},{data.get(1)}};
    }

//below is the first version we modefied it to map type
//    @DataProvider
//    public Object[][] getData()
//    {
//        return new Object[][] {{"saitejaswiselenium@gmail.com", "Selenium@24","ZARA COAT 3"},{"testAuto@gmail.com","Test@auto1","ADIDAS ORIGINAL"}};

    //        HashMap<String,String> map = new HashMap<String,String >();
//        map.put("email","saitejaswiselenium@gmail.com");
//        map.put("password","Selenium@24");
//        map.put("product","ZARA COAT 3");
//
//        HashMap<String,String> map1 = new HashMap<String,String >();
//        map1.put("email","testAuto@gmail.com");
//        map1.put("password","Test@auto1");
//        map1.put("product","ADIDAS ORIGINAL");
//    }


}
