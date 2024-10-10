package com.phonebook;

import com.phonebook.core.ApplicationManager;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class TestBase {

    protected static ApplicationManager app = new ApplicationManager(System.getProperty("browser", "chrome"));
    Logger logger = LoggerFactory.getLogger(TestBase.class);

//    @BeforeSuite
//    public void test2(){
//        logger.info("Okey **@BeforeSuite");
//    }
//
//    @BeforeClass
//    public void test(){
//        logger.info("Okey ****@BeforeSuite");
//    }




    //@BeforeMethod
    @BeforeSuite
    public void setUp() {
        logger.info("*****Testing in progress*****");
        app.init();
       // logger.info("Hello world.");
    }

    @BeforeMethod
    public void startTest(Method method){
        logger.info("test is started: [" + method.getName() + "]");
    }

    @AfterMethod
    public void stopTest(Method method, ITestResult result, ITestContext context) {
        StringBuilder parameters = new StringBuilder();
        for (String key : context.getAttributeNames()) {
            Object value = context.getAttribute(key);
            parameters.append(key).append("=").append(value).append(", ");
        }

        if (parameters.length() > 0) {
            parameters.setLength(parameters.length() - 2);
        }

        logger.info("Test is started: [" + method.getName() + "], with data: [" + parameters +"]");
        if (result.isSuccess()) {
            logger.info("Test is PASSED: [" + method.getName() + "], with data: [" + parameters +"]");
        } else {
            if (app.getUserHelper().isAlertPresent()) {
                logger.warn("Alert was present and has been accepted.");
            } else {
                logger.info("No alert present.");
            }
            String screenshotPath = app.getUserHelper().takeScreenshot();
            logger.error("Test is FAILED: [" + method.getName() + "], Screenshot: [" + screenshotPath + "]");
        }
        logger.info("Test is ended: [" + method.getName() + "]");
    }

    //@AfterMethod(enabled = false)
    @AfterSuite
    public void tearDown() {
        logger.info("*****Tests Done*****");
        app.stop();
       // logger.info("Hello world.");
    }

//    @AfterClass
//    public void postCond2(){
//        logger.info("Okey ** @AfterClass");
//    }
//
//
//    @AfterSuite
//    public void postCond(){
//        logger.info("Okey ** @AfterSuite");
//    }

}

//public void moveTo(int x, int y) {
//    new Actions(driver).moveByOffset(x, y).perform();
//}

