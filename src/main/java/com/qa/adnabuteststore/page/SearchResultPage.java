package com.qa.adnabuteststore.page;

import com.qa.adnabuteststore.constants.Constants;
import com.qa.adnabuteststore.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultPage {
    private WebDriver driver;
    private ElementUtil eleUtil;

    private By searchedProductsItems=By.cssSelector("li.grid__item.scroll-trigger.animate--slide-in");
    private By productNameLink;
    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
    }

    public int getSearchedItemCount(){
       return eleUtil.waitForElementsVisible(searchedProductsItems, Constants.DEFAULT_ELEMENT_TIMEOUT).size();
    }

    public ProductInfoPage selectProduct(String productName){
    productNameLink = By.linkText(productName);
    eleUtil.doClick(productNameLink);
    return new ProductInfoPage(driver);
    }
}
