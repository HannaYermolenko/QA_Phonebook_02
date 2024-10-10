package com.phonebook;

import com.phonebook.model.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateAccountTests extends TestBase {
    String email1 = "testuser"+ System.currentTimeMillis() + "@gmail.com";
    String password = "QwertyuycZ1204@@";

    @BeforeMethod
    public void preCondition(){

    }


    @Test
    public void createAccountPositiveTest1() {
        app.getUserHelper().clickLoginLink();
        app.getUserHelper().fillInRegistrationForm(new User()
                .setEmail(email1)
                .setPassword(password));
        app.getUserHelper().clickRegistrationButton();
        Assert.assertTrue(app.getUserHelper().isSignOutButtonPresent());
    }

    @Test
    public void createAccountPositiveTest2() {
        String email = "testuser"+ System.currentTimeMillis() + "@gmail.com";
        app.getUserHelper().register(email, password);
       // Assert.assertTrue(app.getUserHelper().isSignOutButtonPresent());
    }

    @Test
    public void createAccountLoginPositiveTest() {
        String email = "delete_account_" + System.currentTimeMillis() + "@gmail.com";
        String password = "Password1@";
        app.getUserHelper().register(email, password);
        app.getUserHelper().logout();
        app.getUserHelper().login(email, password);
    }


    @Test
    public void createAccountNegativeTest() {
        SoftAssert softAssert = new SoftAssert();
        app.getUserHelper().clickLoginLink();
        app.getUserHelper().fillInRegistrationForm(new User()
                .setEmail("usertestqaphone4@gmail.com")
                .setPassword(password));
        app.getUserHelper().clickRegistrationButton();
        softAssert.assertTrue(app.getUserHelper().isAlertPresent());
        softAssert.assertTrue(app.getUserHelper().isError409Present());
        softAssert.assertAll();
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
