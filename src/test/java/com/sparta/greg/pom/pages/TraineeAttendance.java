package com.sparta.greg.pom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TraineeAttendance {

    WebDriver webDriver;

    @FindBy(id = "accordionExample")
    List<WebElement> weeks;

    public TraineeAttendance(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public void clickWeek(int row){
        weeks.get(row).click();
    }

}