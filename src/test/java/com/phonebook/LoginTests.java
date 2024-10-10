package com.phonebook;

import com.phonebook.model.User;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {
    private final String email = "usertestqaphone4@gmail.com";
    private final String password = "QwertyuycZ1223@@";

    @BeforeMethod
    public void preCondition() {
        if (app.getUserHelper().isSignOutButtonPresent()) {
            app.getUserHelper().logout();
        }
        app.driver.get("https://telranedu.web.app/login");
    }
    @Test
    public void loginExistedUserPositiveTest1(ITestContext context) {
        context.setAttribute("email", email);
        context.setAttribute("password", password);
        app.getUserHelper().clickLoginLink();
        //fillInRegistrationForm(new User("admin_admin_20242@gmail.com", "Password1@"));
        app.getUserHelper().fillInRegistrationForm(new User()
                .setEmail(email)
                .setPassword(password));
        app.getUserHelper().clickOnLoginButton();
        Assert.assertTrue(app.getUserHelper().isSignOutButtonPresent());
    }

    @Test
    public void loginExistedUserPositiveTest2(ITestContext context) {
        // logger.info("Okey ********For");
        String email = "usertestqaphone4@gmail.com";
        String password = "QwertyuycZ1223@@";
        context.setAttribute("email", email);
        context.setAttribute("password", password);
        app.getUserHelper().login(email, password);
    }

    @Test
    public void loginNegativeWOEmailTest() {
        app.getUserHelper().clickLoginLink();
        app.getUserHelper().fillInRegistrationForm(new User()
                //  .setEmail("admin_admin_20242@gmail.com")
                .setPassword(password));
        app.getUserHelper().clickOnLoginButton();
        Assert.assertEquals(app.getUserHelper().alertTextPresent(), "Wrong email or password");
        Assert.assertTrue(app.getContactHelper().isAlertPresent());
    }

    @AfterMethod
    public void postCondition() {
        try {
            app.getUserHelper().logout();
        } catch (Exception e) {
            // throw new RuntimeException(e);
        }
    }

}