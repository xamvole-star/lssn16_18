package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import components.RandomUtil;


public class RegistrationWithPageObjectJavaTest extends TestBase {
    @Epic("DemoQA")
    @Feature("Registration Form")
    @Story("Регистрация студента")
    @Owner("QA Automation")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Успешная регистрация студента")
    @Test
    void testers() {
        //variables for filling out the form
        String userName = RandomUtil.randomString(10);
        String userEmail = "IvanovIvanIvanovich@mail.ru";
        String userNumber = "1234567890";
        String userLastName = RandomUtil.randomString(20);
        String day = "17";
        String month = "May";
        String year = "1958";
        String address = "Moscow city, Lenina 244 /45";
        String state = "Haryana";
        String city = "Karnal";
        String path = "pic/photo_202623.jpg";
        String gender = "1";
        String hobbySports = "1";
        String hobbyReading = "2";
        String hobbyMusic = "3";

        //variables for checks todo сделать потом списком или массивом для красоты
        String studentName = "Student Name";
        String studentEmail = "Student Email";
        String studentGender = "Gender";
        String studentCityAndState = "State and City";
        String studentAddress = "Address";
        String formName = "Thanks for submitting the form";
        String fullName = userName + " " +userLastName;

        registrationPageDemoQa
                .openPage()
                .setFirstName(userName)
                .setLastName(userLastName)
                .setUserEmail(userEmail)
                .setUserNumber(userNumber)
                .setDate(day, month, year)
                .subjectsInput("Computer Science")
                .subjectsInput("English")
                .subjectsInput("Math")
                .setAddress(address)
                .setState(state)
                .setCity(city)
                .uploadPicture(path)
                .setGender(gender)
                .setHobby(hobbySports)
                .setHobby(hobbyReading)
                .setHobby(hobbyMusic)
                .submit();

        resultRegistration
                .verifyTest(formName)
                .verifyForm(studentName, fullName)
                .verifyForm(studentEmail, userEmail)
                .verifyForm(studentCityAndState, state + " " + city)
                .verifyForm(studentAddress, address);

        //  System.out.println("Real size: " + getWebDriver().manage().window().getSize());
        //  $("#root").hover().click(); // todo разобраться как закрыть это окно
    }
}



