package com.qa.adnabuteststore.base;

import com.qa.adnabuteststore.factory.DriverFactory;
import com.qa.adnabuteststore.page.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.io.FileNotFoundException;
import java.util.Properties;

public class BaseTest {
    public DriverFactory df;
    public WebDriver driver;

    public Properties prop;
    protected HomePage homePage;
    protected ProductPage productPage;
    protected SearchResultPage searchResultPage;
    protected ProductInfoPage productInfoPage;
    protected CartPage cartPage;
    @BeforeTest
    public void setup() throws FileNotFoundException {
        df = new DriverFactory();
        prop =  df.init_Prop();
       driver = df.init_driver(prop);
       homePage = new HomePage(driver);
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
