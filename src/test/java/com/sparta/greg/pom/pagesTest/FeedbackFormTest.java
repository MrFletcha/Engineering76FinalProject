package com.sparta.greg.pom.pagesTest;

import com.sparta.greg.pom.pages.FeedbackForm;
import com.sparta.greg.pom.pages.Login;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class FeedbackFormTest {

    private static WebDriver webDriver;
    private static FeedbackForm feedbackForm;

    @BeforeAll
    static void setup(){
        webDriver = new ChromeDriver();
        webDriver.get("http://localhost:8080/trainee/report/12");
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("src/test/resources/login.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Login login = new Login(webDriver);
        login.enterUsernameAddress(properties.getProperty("traineeUsername"));
        login.enterPassword(properties.getProperty("traineePassword"));
        login.clickSubmitButton();
        feedbackForm = new FeedbackForm(webDriver);
        feedbackForm.clickTraineeOptions();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        feedbackForm.goToFeedbackForm();
    }


    @Test
    @DisplayName("Checking can access and edit the start text box")
    void checkCanAccessStart(){
        Assertions.assertTrue(feedbackForm.enterStart("Editing start"));
    }


    @Test
    @DisplayName("Checking start method can handle null")
    void checkStartCanHandleNull(){
        Assertions.assertFalse(feedbackForm.enterStart(null));
    }


    @Test
    @DisplayName("Checking can access and edit the stop text box")
    void checkCanAccessStop(){
        Assertions.assertTrue(feedbackForm.enterStop("Editing stop"));
    }

    @Test
    @DisplayName("Checking stop method can handle empty String")
    void checkStopCanHandleEmpty(){
        Assertions.assertFalse(feedbackForm.enterStop(" "));
    }

    @Test
    @DisplayName("Checking can access and edit the continue text box")
    void checkCanAccessContinue(){
        Assertions.assertTrue(feedbackForm.enterContinue("Editing continue"));
    }

    @Test
    @DisplayName("Checking continue method can handle empty String")
    void checkContinueCanHandleEmpty(){
        Assertions.assertFalse(feedbackForm.enterContinue(" "));
    }


    @Test
    @DisplayName("Checking that the technical grade can be selected")
    void checkCanAccessTechnicalButton(){
         Assertions.assertTrue(feedbackForm.isTechnicalGradeSelected("A"));
    }


    @Test
    @DisplayName("Checking that the technical grade method can handle null values")
    void checkCanHandleNull(){
        Assertions.assertFalse(feedbackForm.isTechnicalGradeSelected(null));
    }

    @Test
    @DisplayName("Checking that the consultant grade can be selected")
    void checkCanAccessConsultantButton(){
        Assertions.assertTrue(feedbackForm.isConsultantGradeSelected("D"));
    }

    @Test
    @DisplayName("Checking that the consultant grade method can handle empty values")
    void checkCanHandleEmpty(){
        Assertions.assertFalse(feedbackForm.isConsultantGradeSelected(" "));
    }

}
