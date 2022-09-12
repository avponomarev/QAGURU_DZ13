package ponomarev.andrei;


import org.junit.jupiter.api.Test;
import pages.PracticeFormPage;


public class PracticeForm extends TestBase {
    PracticeFormPage practiceFormPage = new PracticeFormPage();

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

}
