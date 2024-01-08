package com.qa.adnabuteststore.test;

import com.qa.adnabuteststore.base.BaseTest;
import com.qa.adnabuteststore.constants.Constants;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    @Test(priority = 1)
    public void homePageTitleTest(){
       String actualTitle =  homePage.getHomePageTitle();
        Assert.assertEquals(actualTitle, Constants.HOME_PAGE_TITLE);
    }
   @Test(priority = 2)
    public void homePageUrlTest(){
        String actualUrl = homePage.getHomePageUrl();
        // check whole url
//        Assert.assertEquals(actualUrl,Constants.HOME_PAGE_URL);
       // check only fraction part of url
       Assert.assertTrue(actualUrl.contains(Constants.HOME_PAGE_URL_FRACTION));
    }

    @Test(priority = 3)
    public void cateogySectionLinkTest(){
      Boolean categoryFlag = homePage.isCategorySectionLinkExist();
      Assert.assertTrue(categoryFlag);
    }
    @Test(priority = 4)
    public void homeSectionLinkTest(){
        Boolean homeFlag = homePage.ishOMESectionLinkExist();
        Assert.assertTrue(homeFlag);
    }

    @Test(priority = 5)
    public void categoryLinkNavigationTest(){
     Assert.assertTrue(homePage.gotToCategorySection().getProductPageHeaderVisible());
    }

}

