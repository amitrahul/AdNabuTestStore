package com.qa.adnabuteststore.page;

import com.qa.adnabuteststore.constants.Constants;
import com.qa.adnabuteststore.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductInfoPage {
    private WebDriver driver;
    private ElementUtil eleUtil;

    private By productHeader = By.cssSelector("product-info#ProductInfo-template--14768207495265__main h1");
    private By productPrice = By.cssSelector("span.price-item.price-item--regular");

    private By productQuantity = By.cssSelector("input#Quantity-template--14768207495265__main");
    private By addToCartButton = By.cssSelector("button#ProductSubmitButton-template--14768207495265__main");
    private By cartNotificationMessage = By.cssSelector("div#cart-notification h2");
    private By viewCartButton = By.cssSelector("a#cart-notification-button");
    public ProductInfoPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
    }

    public String getProductHeaderName(){
      return  eleUtil.waitForElementVisible(productHeader, Constants.DEFAULT_ELEMENT_TIMEOUT).getText();
    }

    public boolean isProductPriceVisible(){
      return  eleUtil.waitForElementVisible(productPrice,Constants.DEFAULT_ELEMENT_TIMEOUT).isDisplayed();
    }

    public String getProductPrice(){
        return eleUtil.waitForElementVisible(productPrice,Constants.DEFAULT_ELEMENT_TIMEOUT).getText();
    }

    public ProductInfoPage doEnterProductQuantity(String qty){
        eleUtil.dosendKeys(productQuantity ,qty);
        return this;
    }
    public String doClickAddToCartButton(){
        eleUtil.doClick(addToCartButton);
       return eleUtil.waitForElementVisible(cartNotificationMessage,Constants.DEFAULT_ELEMENT_TIMEOUT).getText();
    }

    public CartPage doClickViewCartButton(){
        eleUtil.waitForElementVisible(viewCartButton,Constants.DEFAULT_ELEMENT_TIMEOUT).click();
        return new CartPage(driver);
    }

}
