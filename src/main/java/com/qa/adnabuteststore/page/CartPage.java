package com.qa.adnabuteststore.page;

import com.qa.adnabuteststore.constants.Constants;
import com.qa.adnabuteststore.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private WebDriver driver;
    private ElementUtil eleUtil;

    public CartPage(WebDriver driver){
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
    }

    public String getCartPageTitle(){
       return eleUtil.waitforTitlecontains(Constants.CART_PAGE_TITLE_FRACTION,Constants.DEFAULT_ELEMENT_TIMEOUT);
    }

    public String getCartPageUrl(){
    return eleUtil.waitforurlcontains(Constants.CART_PAGE_URL_FRACTION,Constants.DEFAULT_ELEMENT_TIMEOUT);
    }

    public boolean deleteSelectedProduct(String productName){
            By product = By.linkText(productName);
            String actualProduct  = eleUtil.waitForElementVisible(product,Constants.DEFAULT_ELEMENT_TIMEOUT).getText();
//            String deleteIconPath ="//a[@aria-label = 'Remove "+productName+"']";
        String deleteIconPath ="//a[contains(@aria-label, 'Remove "+productName+"')]";
            By deleteIcon = By.xpath(deleteIconPath);
             System.out.println(actualProduct);
            if (actualProduct.equals(productName)){
                eleUtil.doClick(deleteIcon);
            }
          return eleUtil.doIsDisplayed(product);
    }
}
