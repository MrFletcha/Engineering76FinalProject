package com.sparta.greg.pom.pages;

import com.sparta.greg.pom.pages.components.SideBarTrainee;
import com.sparta.greg.pom.pages.components.SideBarTrainer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ChangePassword extends Page{


    By oldPassword = By.cssSelector("input[name='oldPassword'][type='password']");
    By newPassword = By.cssSelector("input[name='password'][type='password']");
    By submitButton = By.cssSelector("button[type='submit']");

    public ChangePassword(WebDriver webDriver) {
        super(webDriver);
    }

//    I commented out the click submit button for safety in case anyone runs any tests



    public void enterOldPassword(String password) {
        webDriver.findElement(oldPassword).sendKeys(password);
    }

    public void enterNewPassword(String password) {
        webDriver.findElement(newPassword).sendKeys(password);
    }

    public void clickSubmitButton() {
//        webDriver.findElement(submitButton).click();
    }

}
