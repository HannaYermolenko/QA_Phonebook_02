package com.phonebook;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTests extends TestBase {
    @BeforeMethod
    public void preCondition() {
      app.getHomeHelper().click(By.xpath("//a[normalize-space(text())='HOME']"));
    }

    @Test
    public void isHomeComponentPresentTest() {
        Assert.assertTrue(app.getHomeHelper().isHomeComponentPresent(), "Element didn't found on a page");
        logger.info("Element was found");

    }

    @AfterMethod
    public void postCondition(){
        try {
            app.getUserHelper().logout();
        } catch (Exception e) {
            // throw new RuntimeException(e);
        }
    }

}
