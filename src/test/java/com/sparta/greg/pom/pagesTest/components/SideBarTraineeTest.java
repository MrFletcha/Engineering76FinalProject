package com.sparta.greg.pom.pagesTest.components;

import com.sparta.greg.pom.pages.Login;
import com.sparta.greg.pom.pages.templates.Attendance;
import com.sparta.greg.pom.pages.utilities.PropertyLoader;
import com.sparta.greg.pom.pages.trainee.*;
import com.sparta.greg.pom.webDriverFactory.WebDriverFactory;
import com.sparta.greg.pom.webDriverFactory.WebDriverType;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import java.util.Properties;


public class SideBarTraineeTest {

    private static final Properties properties = new Properties();
    private static WebDriver webDriver;
    private static String traineeUsername;
    private static String traineePassword;
    private static HomeTrainee homeTrainee;

    @BeforeAll
    static void setup() {
        webDriver = WebDriverFactory.getWebDriver(WebDriverType.CHROME);
        webDriver.get("http://localhost:8080/login");
        Login login = new Login(webDriver);
        PropertyLoader.loadProperties();
        traineeUsername = PropertyLoader.properties.getProperty("traineeUsername");
        traineePassword = PropertyLoader.properties.getProperty("traineePassword");
        homeTrainee = login.logInAsTrainee(traineeUsername, traineePassword);

//        webDriver = new ChromeDriver();
//        Login login = new Login(webDriver);
//        try {
//            properties.load(new FileReader("src/test/resources/login.properties"));
//            trainerUsername = properties.getProperty("traineeUsername");
//            trainerPassword = properties.getProperty("traineePassword");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        login.logInAsTrainer(trainerUsername, trainerPassword);
//        webDriver.get("http://localhost:8080/trainee/home");
//        homeTrainee = new HomeTrainee(webDriver);
    }

    @AfterEach
    void refresh() {
        webDriver.get("http://localhost:8080/trainee/home");
    }

    @AfterAll
    static void tearDown() {
        webDriver.quit();
    }

    @Test
    @DisplayName("goToHomePageByClickingLogo returns HomePage")
    void goToHomePageByClickingLogoReturnsHomePage() {
        Assertions.assertEquals(HomeTrainee.class, homeTrainee.getSideBarTrainee().goToHomePageByClickingLogo().getClass());
    }

    @Test
    @DisplayName("goToHomePageByClickingProfile returns HomePage")
    void goToHomePageByClickingProfileReturnsHomePage() {
        Assertions.assertEquals(HomeTrainee.class, homeTrainee.getSideBarTrainee().goToHomePageByClickingProfile().getClass());
    }

    @Test
    @DisplayName("goToConsultancySkills returns TraineeConsultancySkills")
    void goToConsultancySkillsReturnsTraineeConsultancySkills() {
        homeTrainee.getSideBarTrainee().selectView();
        Assertions.assertEquals(TraineeConsultancySkills.class, homeTrainee.getSideBarTrainee().goToConsultancySkills().getClass());
    }

    @Test
    @DisplayName("goToTraineeGuide returns TraineeGuide")
    void goToTraineeGuideReturnsTraineeGuide() {
        homeTrainee.getSideBarTrainee().selectView();
        Assertions.assertEquals(TraineeGuide.class, homeTrainee.getSideBarTrainee().goToTraineeGuide().getClass());
    }

    @Test
    @DisplayName("goToFeedbackForm returns FeedbackForm")
    void goToFeedbackFormReturnsFeedbackForm() {
        homeTrainee.getSideBarTrainee().clickTraineeOptions();
        Assertions.assertEquals(FeedbackForm.class, homeTrainee.getSideBarTrainee().goToFeedbackForm().getClass());
    }

    @Test
    @DisplayName("goToReportTrainee returns ReportTrainee")
    void goToReportTraineeReturnsReportTrainee() {
        homeTrainee.getSideBarTrainee().clickTraineeOptions();
        Assertions.assertEquals(ReportTrainee.class, homeTrainee.getSideBarTrainee().goToReportTrainee().getClass());
    }

    @Test
    @DisplayName("goToTraineeAttendance returns TraineeAttendance")
    void goToTraineeAttendanceReturnsTraineeAttendance() {
        homeTrainee.getSideBarTrainee().clickTraineeOptions();
        Assertions.assertEquals(Attendance.class, homeTrainee.getSideBarTrainee().goToTraineeAttendance().getClass());
    }

    @Test
    @DisplayName("isTraineeOptionsExpanded returns false when not expanded")
    void isTraineeOptionsExpandedReturnsFalseWhenNotExpanded() {
        Assertions.assertFalse(homeTrainee.getSideBarTrainee().isTraineeOptionsExpanded());
    }

    @Test
    @DisplayName("isTraineeOptionsExpanded returns true when expanded")
    void isTraineeOptionsExpandedReturnsTrueWhenExpanded() {
        homeTrainee.getSideBarTrainee().clickTraineeOptions();
        Assertions.assertTrue(homeTrainee.getSideBarTrainee().isTraineeOptionsExpanded());
    }

    @Test
    @DisplayName("change password test")
    void changePasswordTest() {
        homeTrainee.selectProfileImage();
        Assertions.assertEquals(TraineeChangePassword.class, homeTrainee.getSideBarTrainee().changePassword().getClass());
    }

}
