package ponomarev.andrei;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.Attachment;
import io.qameta.allure.selenide.AllureSelenide;
import org.checkerframework.checker.signature.qual.BinaryName;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.PracticeFormPage;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class PracticeForm {
    PracticeFormPage practiceFormPage = new PracticeFormPage();

    @BeforeAll
    static void configure() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        Configuration.browserCapabilities = capabilities;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
    }

    @Test
    void fillFormTest() {
        practiceFormPage.openPage()
                .setFirstName("Andrei")
                .setLastName("Ponomarev")
                .setEmail("test@gmail.com")
                .setGender("Male")
                .setNumber("9999999999")
                .setBirthDay("01", "January", "1970")
                .setSubject("Economics")
                .setHobbies("Reading", "Music")
                .uploadFile("src/test/resources/PracticeForm/test.jpg")
                .setAddress("Moscow")
                .setStateAndCity("Haryana", "Karnal")

                .checkText("Thanks for submitting the form")
                .checkResult("Student Name", "Andrei Ponomarev")
                .checkResult("Student Email", "test@gmail.com")
                .checkResult("Gender", "Male")
                .checkResult("Mobile", "9999999999")
                .checkResult("Date of Birth", "01 January,1970")
                .checkResult("Subjects", "Economics")
                .checkResult("Hobbies", "Reading, Music")
                .checkResult("Picture", "test.jpg")
                .checkResult("Address", "Moscow")
                .checkResult("State and City", "Haryana Karnal")
                .checkButton("Close");


    }
    @AfterEach
    void addAttachments() {

        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}
