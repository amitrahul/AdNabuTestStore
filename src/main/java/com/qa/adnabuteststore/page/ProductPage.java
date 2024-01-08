package com.qa.adnabuteststore.page;

import com.qa.adnabuteststore.constants.Constants;
import com.qa.adnabuteststore.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductPage {
    private WebDriver driver;
    private ElementUtil eleUtil;

    private By productHeader = By.cssSelector("h1.collection-hero__title");
    private By serachField = By.id("Search-In-Modal-1");
    private By serachBtn = By.xpath("// button[@class = \"search__button field__button\"]");
    private By serachIcon = By.xpath("//summary[@class=\"header__icon header__icon--search header__icon--summary link focus-inset modal__toggle\"]");
    private By productAvailability = By.cssSelector("details#Details-1-template--14768207265889__main-collection-product-grid");
    private By filteredProductCount = By.cssSelector("span#ProductCountDesktop");
    private By outStockPills = By.cssSelector("div.active-facets.active-facets-desktop facet-remove:nth-of-type(2)");
    private By inStockPills = By.cssSelector("div.active-facets.active-facets-desktop facet-remove:nth-of-type(1)");
    private By selectedProductCount = By.xpath("//div[@id='Facet-1-template--14768207265889__main-collection-product-grid']/div/div/span");
    private By soldOutProduct = By.cssSelector("span#NoMediaStandardBadge-template--14768207265889__main-collection-product-grid-6876454944865");
    private By productName = By.cssSelector("ul#product-grid li h3.card__heading.h5");
    private By sortByFilter = By.cssSelector("select#SortBy");

    private By availabilityFilter = By.xpath("//div[@id='Facet-1-template--14768207265889__main-collection-product-grid']//ul[1]/li//span[@class='facet-checkbox__text']");

    // page Constructor
    ProductPage(WebDriver driver){
        this.driver  = driver;
        eleUtil = new ElementUtil(driver);
    }

    public String getProductPageTitle(){
       return eleUtil.waitforTitle(Constants.PRODUCT_PAGE_TITLE, Constants.DEFAULT_ELEMENT_TIMEOUT);
    }
    public  String getProductPageUrl(){
        return eleUtil.waitforurlcontains (Constants.PRODUCT_PAGE_URL_FRACTION,Constants.DEFAULT_ELEMENT_TIMEOUT);
    }

    public String getProductPageHeader(){
      return eleUtil.doGetText(productHeader);
    }

    public boolean getProductPageHeaderVisible(){
        return eleUtil.waitForElementVisible(productHeader,Constants.DEFAULT_ELEMENT_TIMEOUT).isDisplayed();
    }

    public boolean isSearchFieldExist(){
        eleUtil.waitForElementVisible(serachIcon,Constants.DEFAULT_ELEMENT_TIMEOUT).click();
      return eleUtil.waitForElementVisible(serachField ,Constants.DEFAULT_ELEMENT_TIMEOUT).isDisplayed();
    }

    public boolean isProductAvailabilityExist(){
        return eleUtil.waitForElementVisible(productAvailability , Constants.DEFAULT_ELEMENT_TIMEOUT).isDisplayed();
    }
    public ProductPage doClickOnAvailableProductFilter(){
        eleUtil.doClick(productAvailability);
        return this;
    }
    public ProductPage selectAvailabilityFilterProduct(String... value){
      List<WebElement> productList =  eleUtil.waitForElementsVisible(availabilityFilter,Constants.DEFAULT_ELEMENT_TIMEOUT);
      for(WebElement product : productList){
          String text = product.getText();
          System.out.println(product.getText());
          for(int i=0; i<value.length; i++ ){
              if(text.equals(value[i])){
                  product.click();
                  break;
              }
          }
      }
      return this;
    }


    public String getCountOfSelectedProductOnAvailableProduct(){
        if(isInStockAvailabilityPillsVisible()){
            return eleUtil.doGetText(selectedProductCount);
        }
        return null;
    }

    public boolean isInStockAvailabilityPillsVisible(){
       return eleUtil.waitForElementVisible(inStockPills,Constants.DEFAULT_ELEMENT_TIMEOUT).isDisplayed();
    }
    public boolean isOutOfStockAvailabilityPillsVisible(){
        return eleUtil.waitForElementVisible(outStockPills,Constants.DEFAULT_ELEMENT_TIMEOUT).isDisplayed();
    }

    public boolean isSoldOutProductVisible(){
        if(isOutOfStockAvailabilityPillsVisible()){
          return eleUtil.getElement(soldOutProduct).isDisplayed();
        }
        return false;
    }

    public String getFilteredProductCount() {
        if(isInStockAvailabilityPillsVisible()) {
            return eleUtil.waitForElementVisible(filteredProductCount,Constants.DEFAULT_ELEMENT_TIMEOUT).getText();
        } else if (isInStockAvailabilityPillsVisible() && isOutOfStockAvailabilityPillsVisible()) {
            return eleUtil.waitForElementVisible(filteredProductCount,Constants.DEFAULT_ELEMENT_TIMEOUT).getText();
        }
        return null;

    }

    public Boolean getSortedProductInAlphabeticalOrder(){
        List<String>productNameList= eleUtil.getElementTextList(productName);
        String dummyData = "";
        for(String alphaProduct : productNameList){
          if(alphaProduct.compareTo(dummyData) < 0) return false;
          dummyData = alphaProduct;
      }
      return true;
    }

    public ProductPage selectSortedProductInReverseAlphabeticalOrder(String selectedFilter){
        eleUtil.dropDownSelectValueWithGetOptions(sortByFilter,selectedFilter);
        return this;
    }

    public Boolean getSortedProductInReverseAlphabeticalOrder() {
        List<String>productNameList= eleUtil.getElementTextList(productName);
        String dummyData = "";
        for(String alphaProduct : productNameList){
            if(alphaProduct.compareTo(dummyData) < 0) return false;
            dummyData = alphaProduct;
        }
        return true;
    }



    public SearchResultPage getSearchResult(String searchValue)  {
        if(isSearchFieldExist()){
            eleUtil.dosendKeys(serachField,searchValue);
            eleUtil.doClick(serachBtn);
            return new SearchResultPage(driver);
        }
        return null;
    }
}
