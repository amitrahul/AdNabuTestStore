package com.qa.adnabuteststore.page;

import com.qa.adnabuteststore.constants.Constants;
import com.qa.adnabuteststore.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;
    private ElementUtil eleUtil;
//    Private By Locators
    private  By homeMenu = By.id("HeaderMenu-home");
    private  By catalogMenu = By.id("HeaderMenu-catalog");
    private By serachBtn = By.xpath("// button[@class = \"search__button field__button\"]");
    private By serachIcon = By.xpath("//summary[@class=\"header__icon header__icon--search header__icon--summary link focus-inset modal__toggle\"]");

   private By cartButton = By.cssSelector("a#cart-icon-bubble");
    // page Constructor
    public HomePage(WebDriver driver){
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
    }

    // page Actions
    public String getHomePageTitle(){
        return eleUtil.waitforTitle(Constants.HOME_PAGE_TITLE, Constants.DEFAULT_ELEMENT_TIMEOUT);
    }

    public String getHomePageUrl(){
        return  eleUtil.waitforurlcontains(Constants.HOME_PAGE_URL_FRACTION,Constants.DEFAULT_ELEMENT_TIMEOUT);
    }


    public ProductPage gotToCategorySection(){
        eleUtil.doClick(catalogMenu);
        return new ProductPage(driver);
    }
    public void goToHomeSection(){
        eleUtil.doClick(homeMenu);
    }

    public boolean isCategorySectionLinkExist(){
        return eleUtil.doIsDisplayed(catalogMenu);
    }
    public boolean ishOMESectionLinkExist(){
        return eleUtil.doIsDisplayed(homeMenu);
    }
    public CartPage doClickCartButton(){
        eleUtil.waitForElementVisible(cartButton,Constants.DEFAULT_ELEMENT_TIMEOUT).click();
        return new CartPage(driver);
    }
}
