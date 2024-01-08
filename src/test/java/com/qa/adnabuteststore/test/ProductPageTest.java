package com.qa.adnabuteststore.test;

import com.qa.adnabuteststore.base.BaseTest;
import com.qa.adnabuteststore.constants.Constants;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ProductPageTest extends BaseTest {

    @BeforeClass
    public void productSetup(){
       productPage =  homePage.gotToCategorySection();
    }

// Test the product page Visibility.
    @Test(priority = 1)
    public void productPageTitleTest(){
      Assert.assertEquals(productPage.getProductPageTitle() , Constants.PRODUCT_PAGE_TITLE);
    }
    @Test(priority = 2)
    public void productPageURLTest(){
        String actualUrl = productPage.getProductPageUrl();
        Assert.assertTrue(actualUrl.contains(Constants.PRODUCT_PAGE_URL_FRACTION));
    }
    @Test(priority = 3)
    public void productPageHeaderTest(){
        Assert.assertTrue(productPage.getProductPageHeader().contains(Constants.PRODUCT_PAGE_HEADER));
    }


/*
*   Test the product based on Availability Filter
*  */
    @Test(priority = 8)
    public void CountOfInStockSelectedProductOnAvailableProductTest(){
        String actualCount =   productPage.doClickOnAvailableProductFilter().selectAvailabilityFilterProduct("In stock (20)").getCountOfSelectedProductOnAvailableProduct();
        Assert.assertTrue(actualCount.contains(Constants.COUNT_OF_SELECTED_IN_STOCK_PRODUCT));
    }
    @Test(priority = 9)
    public void inStockPillVisibilityTest(){
        Boolean visibleFlag = productPage.doClickOnAvailableProductFilter().isInStockAvailabilityPillsVisible();
        Assert.assertTrue(visibleFlag);
    }

    @Test(priority = 4 )
    public void CountOfOutOfStockSelectedProductOnAvailableProductTest(){
        String actualCount =  productPage.doClickOnAvailableProductFilter().selectAvailabilityFilterProduct("Out of stock (3)").getCountOfSelectedProductOnAvailableProduct();
        Assert.assertTrue(actualCount.contains(Constants.COUNT_OF_SELECTED_IN_STOCK_PRODUCT));
    }
    @Test(priority = 5)
    public void isOutOfStockPillVisibilityTest(){
        Boolean visibleFlag = productPage.doClickOnAvailableProductFilter().isInStockAvailabilityPillsVisible();
        Assert.assertTrue(visibleFlag);
    }

    @Test(priority = 6)
    public void outOfStockFilterProductCountTest(){
      String actualCount =   productPage.getFilteredProductCount();
      Assert.assertEquals(actualCount,Constants.FILTERED_OUT_OF_STOCK_PRODUCT_COUNT);
    }
    @Test(priority = 7)
    public void soldOutProductVisibleTestOnSelectOutOfStockProduct(){
        Boolean visibleFlag = productPage.isSoldOutProductVisible();
        Assert.assertTrue(visibleFlag);
    }


    @Test(priority = 10)
    public void inStockAndOutOfStockPillVisibilityTest() {
        Boolean inStockFlag = productPage.isInStockAvailabilityPillsVisible();
        Boolean outOfStockFlag = productPage.isOutOfStockAvailabilityPillsVisible();
        Boolean visibleFlag = inStockFlag && outOfStockFlag;
        Assert.assertTrue(visibleFlag);
    }


    /*
     *   Test the product based on Sorting Filter
     *  */
    @Test(priority = 14)
    public void sortedProductInAlphabeticalOrderTest(){
        Boolean flag = productPage.getSortedProductInAlphabeticalOrder();
        Assert.assertTrue(flag);
    }
    @Test(priority = 15)
    public void sortedProductInReverseAlphabeticalOrderTest(){
       Boolean flag =  productPage.selectSortedProductInReverseAlphabeticalOrder("Alphabetically, Z-A").getSortedProductInReverseAlphabeticalOrder();
       Assert.assertTrue(flag);

    }

    /*
     *   Test the product based on Searching the product
     *  */
    @DataProvider
    public Object[][] getSearchKey(){
        return new Object[][] {
                {"Pendant"},
                {"earrings"},
                {"Necklace"},
                {"ring"},
                {"shirt"},

        };
    }

    @Test(priority = 16, dataProvider ="getSearchKey" )
    public void searchResultTest(String searchKey){
      searchResultPage =  productPage.getSearchResult(searchKey);
      Assert.assertTrue(searchResultPage.getSearchedItemCount() > 0);
    }
    @DataProvider
    public Object[][] getProductName(){
        return new Object[][] {
                {"Pendant","18k Bloom Pendant"},
                {"earrings","18k Interlinked Earrings 1"},
                {"Necklace", "18k Fluid Lines Necklace"},
                {"ring","18k Pedal Ring"},
                {"shirt","Shirt"},

        };
    }
    @Test(priority = 17,dataProvider = "getProductName")
    public void selectProductTest(String searchKey , String productName){
        searchResultPage =  productPage.getSearchResult(searchKey);
       productInfoPage =  searchResultPage.selectProduct(productName);
      String productHeader =   productInfoPage.getProductHeaderName();
      Assert.assertEquals(productHeader, productName);
    }
    @Test(priority = 18,dataProvider = "getProductName")
    public void productPriceVisibilityTest(String searchKey , String productName){
        searchResultPage =  productPage.getSearchResult(searchKey);
        productInfoPage =  searchResultPage.selectProduct(productName);
        Assert.assertTrue(productInfoPage.isProductPriceVisible());
    }
    @DataProvider
    public Object[][] getProductNameWithPrice(){
        return new Object[][] {
                {"Pendant","18k Bloom Pendant","Rs. 28,500.00"},
                {"earrings","18k Interlinked Earrings 1","Rs. 33,600.00"},
                {"Necklace", "18k Fluid Lines Necklace","Rs. 76,500.00"},
                {"ring","18k Pedal Ring","Rs. 40,800.00"},
                {"shirt","Shirt","Rs. 200.00"},

        };
    }
    @Test(priority = 19,dataProvider = "getProductNameWithPrice")
    public void productPriceTest(String searchKey , String productName , String productPrice){
        searchResultPage =  productPage.getSearchResult(searchKey);
        productInfoPage =  searchResultPage.selectProduct(productName);
        Assert.assertEquals(productInfoPage.getProductPrice(), productPrice);
    }

    /*
     *   Test the product based on item add to cart
     *  */
    @DataProvider
    public Object[][] addProductToCart(){
        return new Object[][] {
                {"Pendant","18k Bloom Pendant","2"},
                {"earrings","18k Interlinked Earrings 1","3"},
                {"Necklace", "18k Fluid Lines Necklace","2"},
                {"ring","18k Pedal Ring","4"},
                {"shirt","Shirt","1"},

        };
    }
    @Test(priority = 20,dataProvider = "addProductToCart" )

    public void productItemCartAdditionTest(String searchKey , String productName , String qty){
        searchResultPage =  productPage.getSearchResult(searchKey);
        productInfoPage =  searchResultPage.selectProduct(productName).doEnterProductQuantity(qty);
       String successMessage =  productInfoPage.doClickAddToCartButton();
       Assert.assertEquals(successMessage,Constants.CART_NOTIFICATION_MESSAGE);
    }

    /*
     *   Test the product based on item delete from cart
     *  */
    @DataProvider
    public Object[][] deleteProductFromCart(){
        return new Object[][] {
                {"Pendant","18k Bloom Pendant","2"},
                {"earrings","18k Interlinked Earrings 1","3"},
                {"Necklace", "18k Fluid Lines Necklace","2"},
        };
    }
    @Test(priority = 21 , dataProvider = "deleteProductFromCart")
    public void deleteSelectedProductTest(String searchKey , String productName , String qty){
        searchResultPage =  productPage.getSearchResult(searchKey);
        searchResultPage.selectProduct(productName).doEnterProductQuantity(qty).doClickAddToCartButton();
        cartPage = productInfoPage.doClickViewCartButton();
        Boolean flag = cartPage.deleteSelectedProduct(productName);
        Assert.assertTrue(flag);
    }

}
