package guru.qa;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FillFormTests extends BaseTest {
    @BeforeAll
    static void settings() {
        open("/automation-practice-form");
        Selenide.executeJavaScript("$('#fixedban').remove()");
        Selenide.executeJavaScript("$('footer').remove()");
    }
    @Test
            void fillFormTest() {
        //Не понял что ему не понравилось, пробел в placeholder? org.openqa.selenium.InvalidSelectorException: invalid selector: An invalid or illegal selector was specified
        //$("[placeholder=First]").setValue("Roman");
        $("#firstName").setValue("Roman");
        $("#lastName").setValue("Timofeev");
        $("#userEmail").setValue("test@gmail.com");
        $("#gender-radio-1").parent().click();
        $("#userNumber").setValue("0123456789");

        //DateOfBirthday
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionByValue("0");
        $(".react-datepicker__year-select").selectOption("1998");
        $(".react-datepicker__day--011").click();

        //Subjects
        $("[id=subjectsInput]").setValue("Anime, Gaming, Coding, Anime");

        //Hobbies
        $("#hobbies-checkbox-1").parent().click();
        $("#hobbies-checkbox-2").parent().click();
        $("#hobbies-checkbox-3").parent().click();

        //Picture
        String imgName = "Anime.png";
        $("#uploadPicture").uploadFromClasspath(imgName);

        //Address
        $("#currentAddress").setValue("Current Address");
        $("#state").click();
        $("#react-select-3-option-0").click();
        $("#city").click();
        $("#react-select-4-option-0").click();

        //Submit
        $("#submit").click();

        //Result
        $(".table").shouldHave(text("Roman"));
        $(".table").shouldHave(text("Timofeev"));
        $(".table").shouldHave(text("test@gmail.com"));
        $(".table").shouldHave(text("0123456789"));
        $(".table").shouldHave(text("11 January,1998"));
        $(".table").shouldHave(text(imgName));
        $(".table").shouldHave(text("Current Address"));
        $(".table").shouldHave(text("NCR Delhi"));




    }


}