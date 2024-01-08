package com.qa.adnabuteststore.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DriverFactory {
    WebDriver driver;
    Properties prop;
    /*
    * This method is used to initialize the webDriver on the
    * basis of given browser name.
    * @Param browserName
    * @return driver.
     */
    public WebDriver init_driver(Properties prop){
        String browserName = prop.getProperty("browser").trim();
        if(browserName.equalsIgnoreCase("chrome")){
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("safari")) {
            driver = new SafariDriver();
        }
        else{
            System.out.println("please pass the right browser : "+ browserName);
        }
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get(prop.getProperty("url").trim());
        return driver;
    }
    /*
    * This method is used to initialize the properties.
    * */
    public Properties init_Prop() throws FileNotFoundException {
      try {
          FileInputStream ip  =  new FileInputStream("./src/test/resources/config/config.properties");
          prop = new Properties();
          prop.load(ip);
      }
      catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
          throw new RuntimeException(e);
      }
      return prop;
    }
}
