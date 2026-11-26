package pages;

import com.codeborne.selenide.SelenideElement;
import components.Calendar;

import static com.codeborne.selenide.ScrollIntoViewOptions.Block.center;
import static com.codeborne.selenide.ScrollIntoViewOptions.instant;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;

public class RegistrationPage_DemoQa {
    private SelenideElement
            lastUserNumber = $("#userNumber"),
            firstName = $("#firstName"),
            lastName = $("#lastName"),
            userEmail = $("#userEmail"),
            dateOfBirthInput = $("#dateOfBirthInput"),
            subjectsInput = $("#subjectsInput"),
            currentAddress = $("#currentAddress"),
            uploadPicture = $("#uploadPicture"),
            submitInput = $("#submit"),
            setState = $("#state"),
            setcity = $("#city");


    Calendar calendar = new Calendar();

    private static String
            GENDER_RADIO = "#gender-radio-",
            HOBBIE = "#hobbies-checkbox-";

    @Step("Открываем страницу регистрации DemoQA")
    public RegistrationPage_DemoQa openPage() {
        open("/automation-practice-form");
        // Удаляем рекламные баннеры
//      executeJavaScript("$('#fixedban').remove(); $('footer').remove();");
        return this;
    }

    @Step("Вводим имя: {value}")
    public RegistrationPage_DemoQa setFirstName(String value) {
        firstName.setValue(value);
        return this;
    }

    @Step("Вводим фамилию: {value}")
    public RegistrationPage_DemoQa setLastName(String value) {
        lastName.setValue(value);
        return this;
    }

    @Step("Вводим Email: {value}")
    public RegistrationPage_DemoQa setUserEmail(String value) {
//        Allure.addAttachment(
//                "Java code",       // Имя вложения в отчёте
//                "text/plain",      // MIME-тип (обычный текст)
//                "userEmail.setValue(value);", // Содержимое
//                ".java"            // Расширение файла
//        );

        userEmail.setValue(value);
        return this;
    }

    @Step("Вводим номер телефона: {value}")
    public RegistrationPage_DemoQa setUserNumber(String value) {
        lastUserNumber.setValue(value);
        return this;
    }

    @Step("Выбираем дату: {day}, месяц {month}, год {year}")
    public RegistrationPage_DemoQa setDate(String day, String month, String year) {
        dateOfBirthInput.click();
        calendar.setDate(day, month, year);
        return this;
    }

    @Step("Добавляем предметы, по которым учимся {value}")
    public RegistrationPage_DemoQa subjectsInput(String value) {
        subjectsInput.click();
        subjectsInput.setValue(value).pressEnter();
        return this;
    }

    @Step("Вводим адрес проживания: {value}")
    public RegistrationPage_DemoQa setAddress(String value) {
        currentAddress.setValue(value);
        return this;
    }

    @Step("Выбираем штат: {value}")
    public RegistrationPage_DemoQa setState(String value) {
        setState.scrollIntoView(instant().block(center)).click();
        setState.$(byText(value)).click();
        return this;
    }

    @Step("Выбираем город: {value}")
    public RegistrationPage_DemoQa setCity(String value) {
        setcity.click();
        setcity.$(byText(value)).click();
        return this;
    }

    @Step("Загружаем фотографию: {value}")
    public RegistrationPage_DemoQa uploadPicture(String value) {
        uploadPicture.uploadFromClasspath(value);
        return this;
    }

    @Step("Выбираем пол: {value}")
    public RegistrationPage_DemoQa setGender(String value) {
        $(GENDER_RADIO + value).click();
        return this;
    }

    @Step("Выбираем хобби: {value}")
    public RegistrationPage_DemoQa setHobby(String value) {
        $(HOBBIE + value).click();
        return this;
    }

    @Step("Нажимаем кнопку Submit")
    public RegistrationPage_DemoQa submit() {
        submitInput.click();
        return this;
    }


}
