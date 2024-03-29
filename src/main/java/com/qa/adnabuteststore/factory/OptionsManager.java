package com.qa.adnabuteststore.factory;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Properties;

public class OptionsManager {
    private Properties prop;
    private ChromeOptions co;
    private FirefoxOptions fo;
    public OptionsManager(Properties prop){
        this.prop = prop;
    }

    public ChromeOptions getChromeOptions(){
        co = new ChromeOptions();
//        if(Boolean.parseBoolean(prop.getProperty("headless"))){
//            co.setHeadless();
//        }
        if(Boolean.parseBoolean(prop.getProperty("incognito"))){
            co.addArguments("--incognito");
        }
        return co;
    }

    public FirefoxOptions getFirefoxOptions(){
        fo = new FirefoxOptions();
//        if(Boolean.parseBoolean(prop.getProperty("headless"))){
//            fo.setHeadless();
//        }
        if(Boolean.parseBoolean(prop.getProperty("incognito"))){
            fo.addArguments("--incognito");
        }
        return fo;
    }
}
