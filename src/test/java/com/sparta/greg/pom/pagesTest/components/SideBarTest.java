package com.sparta.greg.pom.pagesTest.components;

import com.sparta.greg.pom.pages.HomeTrainer;
import com.sparta.greg.pom.pages.Login;
import com.sparta.greg.pom.pages.WeeklyAttendance;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class SideBarTest {

    private static final Properties properties = new Properties();
    private static WebDriver webDriver;
    private static WeeklyAttendance weeklyAttendance;
    private static String trainerUsername;
    private static String trainerPassword;
    private static HomeTrainer homeTrainer;

    @BeforeAll
    static void setup() {
        webDriver = new ChromeDriver();
        Login login = new Login(webDriver);
        try {
            properties.load(new FileReader("src/test/resources/login.properties"));
            trainerUsername = properties.getProperty("trainerUsername");
            trainerPassword = properties.getProperty("trainerPassword");
        } catch (IOException e) {
            e.printStackTrace();
        }

        login.logInAsTrainer(trainerUsername, trainerPassword);
        webDriver.get("http://localhost:8080/trainer/weekly-attendance");
        weeklyAttendance = new WeeklyAttendance(webDriver);
    }

    @Test
    @DisplayName("change side bar size test")
    void changeSideBarSizeTest() {
        String initialSideBarSize = weeklyAttendance.getSideBarTrainer().getSideBarSize();
        weeklyAttendance.getSideBarTrainer().changeSideBarSize();
        String postSideBarSize = weeklyAttendance.getSideBarTrainer().getSideBarSize();
        Assertions.assertNotEquals(initialSideBarSize, postSideBarSize);
    }

    @Test
    @DisplayName("select view icon test")
    void selectViewIconTest() {
        weeklyAttendance.getSideBarTrainer().selectView();
    }

}
