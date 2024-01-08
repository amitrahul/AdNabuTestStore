package com.qa.adnabuteststore.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ElementUtil {
    private WebDriver driver; // current value is null, right now it is not pointing to anyone.

    public ElementUtil(WebDriver driver)
    {
        this.driver =driver;

    }

    public By getBy(String locatorType, String locatorValue) {

        By locator = null;
        switch (locatorType.toLowerCase()) {
            case "id":

                locator = By.id(locatorValue);
                break;

            case "name":

                locator = By.name(locatorValue);
                break;


            default:
                break;

            case "className":

                locator = By.className(locatorValue);
                break;


            case "xpath":

                locator = By.xpath(locatorValue);
                break;

            case "cssSelector":

                locator = By.cssSelector(locatorValue);
                break;



            case "linkText":

                locator = By.linkText(locatorValue);
                break;



            case "partiallinkText":

                locator = By.partialLinkText(locatorValue);
                break;


            case "tagname":

                locator = By.tagName(locatorValue);
                break;


        }
        return locator;
    }



    public void dosendKeys(String locatorType, String locatorValue, String value) {
        getElement(getBy(locatorType, locatorValue)).sendKeys(value);
    }


    public void dosendKeys(By locator, String value) {
        WebElement element= driver.findElement(locator);
        element.clear();
        element.sendKeys(value);
    }

    public WebElement getElement(By locator) {
        return driver.findElement(locator);
    }

    public void doClick(By locator) {
        getElement(locator).click();
    }


    public String doGetText(By locator) {
        return getElement(locator).getText();
    }

    public boolean doIsDisplayed(By locator) {
        return getElement(locator).isDisplayed();
    }


    public boolean doIsENABLED(By locator) {
        return	getElement(locator).isEnabled();

    }

    public String getAttributeValue(By locator, String attrName) {
        return getElement(locator).getAttribute(attrName);

    }

    public  List<WebElement> getElements(By locator) {
        return driver.findElements(locator);

    }


    public List<String> getElementAttribute(By locator, String attributeName) {

        List<WebElement> eleList=getElements(locator);

        List<String> eleAttrList= new ArrayList<String>();

        for (WebElement e : eleList) {

            String attributeValue=e.getAttribute(attributeName);

            System.out.println(attributeValue);

            eleAttrList.add(attributeValue);

        }

        return eleAttrList;

    }


    public  List<String> getElementTextList(By locator) {

        List<WebElement> eleList=getElements(locator);

        List<String> eleTextList= new ArrayList<String>();

        for (WebElement e : eleList) {

            String eleText=e.getText();

            eleTextList.add(eleText);

        }

        return eleTextList;

    }

    public  int getElementCount(By locator) {
        return getElements(locator).size();
    }




    // *********************************Drop Down Utils*****************************//
    public void selectDropDownByIndex(By locator, int index) {
        Select select = new Select(getElement(locator));
        select.selectByIndex(index);
    }

    public void selectDropDownByVisible(By locator, String visibleText) {
        Select select = new Select(getElement(locator));
        select.selectByVisibleText(visibleText);
    }

    public void selectDropDownByValue(By locator, String value) {
        Select select = new Select(getElement(locator));
        select.selectByValue(value);
    }

    public int getDropDownValuesCount(By locator) {
        Select select = new Select(getElement(locator));
        return select.getOptions().size();
    }

    public void dropDownSelectValueWithGetOptions(By locator, String value) {
        Select select = new Select(getElement(locator));
        List<WebElement> optionsList = select.getOptions();

        for (WebElement e : optionsList) {
            String text = e.getText();
            System.out.println(text);
            if (text.equals(value)) {
                e.click();
                break;
            }
        }
    }

    public void dropDownValueUsingLocator(By locator, String value) {
        List<WebElement> list = getElements(locator);
        for (WebElement e : list) {
            String text = e.getText();
            if (text.equals(value)) {
                e.click();
                break;
            }
        }
    }

    /***************************************Wait utils**********************************************/
		/*    An expectation for checking that an element is present on the DOM of a page.
		 * This does not necessarily mean that the element is visible.

			Parameters:
			locator used to find the element
			Returns:
			the WebElement once it is located
		 *
		 */


    public  WebElement waitforpersenceofElement(By locator, int timeout) {

        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(timeout));

        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));


    }


		/*	An expectation for checking that an element is present on the DOM of a page and visible.
		 * Visibility means that the element is not only displayed but
		 * also has a height and width that is greater than 0.

			Parameters:
			locator used to find the element
			Returns:
			the WebElement once it is located and visible
		 *
		 */

    public  WebElement waitforVisibleofElement(By locator, int timeout) {

        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(timeout));

        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));


    }

		/*	An expectation for checking that the title contains a case-sensitive substring

				Parameters:
				title the fragment of title expected
				Returns:
				true when the title matches, false otherwise
		 *
		 */


    public  String waitforTitlecontains(String  partialtitle, int timeout) {

        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(timeout));

        if(	wait.until(ExpectedConditions.titleContains(partialtitle)))
        {

            return driver.getTitle();

        }
        return null;
    }


		/*		An expectation for checking the title of a page.

					Parameters:
					title the expected title, which must be an exact match
					Returns:
					true when the title matches, false otherwise
		 *
		 */

    public  String waitforTitle(String title, int timeout) {
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(timeout));
        if(	wait.until(ExpectedConditions.titleIs(title)))
        {
            return driver.getTitle();
        }
        return null;
    }


    public  String waitforurlcontains(String partialurl, int timeout) {
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(timeout));

        if(wait.until(ExpectedConditions.urlContains(partialurl)))
        {
            return driver.getCurrentUrl();
        }

        return null;

    }


    public  String waitForurlIs(String url, int timeout) {
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(timeout));

        if(wait.until(ExpectedConditions.urlToBe(url)))
        {
            return driver.getCurrentUrl();
        }

        return null;

    }


    public void acceptAlert(int timeOut) {
        waitForAlert(timeOut).accept();
    }

    public void dismissAlert(int timeOut) {
        waitForAlert(timeOut).dismiss();
    }

    public void alertSendKeys(int timeOut, String value) {
        waitForAlert(timeOut).sendKeys(value);
    }

    public String doGetAlertText(int timeOut) {
        return waitForAlert(timeOut).getText();
    }

    public Alert waitForAlert(int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.alertIsPresent()); // wait for alert and then switch to alert

    }


    /**
     * An expectation for checking an element is visible and enabled such that you
     * can click it.
     *
     * @param locator
     * @param timeOut
     */
    public void clickElementWhenReady(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }



    /**
     * An expectation for checking that all elements present on the web page that
     * match the locator are visible. Visibility means that the elements are not
     * only displayed but also have a height and width that is greater than 0.
     *
     * @param locator
     * @param timeOut
     * @return
     */
    public List<WebElement> waitForElementsVisible(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }


    public WebElement waitForElementVisible(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


}
