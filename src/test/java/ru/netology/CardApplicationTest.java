package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardApplicationTest {
    @Test
    void shouldSentCardApplication(){
        SelenideElement form = $(".form");
        open("http://localhost:9999/");
        form.$("[data-test-id=name] input").setValue("Мошина Ксения");
        form.$("[data-test-id=phone] input").setValue("+79544568754");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));

    }

    @Test
    void fieldNameShouldNotContainLatinSymbols(){
        SelenideElement form = $(".form");
        open("http://localhost:9999/");
        form.$("[data-test-id=name] input").setValue("Moshina Ksenia");
        form.$("[data-test-id=phone] input").setValue("+79544568754");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    void fieldNameShouldNotContainOnlySurname(){
        SelenideElement form = $(".form");
        open("http://localhost:9999/");
        form.$("[data-test-id=name] input").setValue("Мошина");
        form.$("[data-test-id=phone] input").setValue("+79544568754");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    void fieldNameShouldNotContainOnlyName(){
        SelenideElement form = $(".form");
        open("http://localhost:9999/");
        form.$("[data-test-id=name] input").setValue("Ксения");
        form.$("[data-test-id=phone] input").setValue("+79544568754");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    void shouldBeMessageAboutMistakeIfFieldNameIsNotCompleted(){
        SelenideElement form = $(".form");
        open("http://localhost:9999/");
        form.$("[data-test-id=name] input").setValue("");
        form.$("[data-test-id=phone] input").setValue("+79544568754");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));

    }
    @Test
    void fieldNameCanContainSymbolYo(){
        SelenideElement form = $(".form");
        open("http://localhost:9999/");
        form.$("[data-test-id=name] input").setValue("Мухин Пётр");
        form.$("[data-test-id=phone] input").setValue("+79544568754");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));

    }
    @Test
    void fieldNameShouldNotContainSpecialCharacters(){
        SelenideElement form = $(".form");
        open("http://localhost:9999/");
        form.$("[data-test-id=name] input").setValue("@Мошина Ксения");
        form.$("[data-test-id=phone] input").setValue("+79544568754");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    void shouldBeMessageAboutMistakeIfItHaveNotPlusBeforeNumber(){
        SelenideElement form = $(".form");
        open("http://localhost:9999/");
        form.$("[data-test-id=name] input").setValue("Мошина Ксения");
        form.$("[data-test-id=phone] input").setValue("79544568754");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

        @Test
    void shouldBeMessageAboutMistakeIfNumberHaveDashes(){
        SelenideElement form = $(".form");
        open("http://localhost:9999/");
        form.$("[data-test-id=name] input").setValue("Мошина Ксения");
        form.$("[data-test-id=phone] input").setValue("+7-954-456-87-54");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
            $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

        }

    @Test
    void shouldBeMessageAboutMistakeIfFieldNumberIsNotCompleted(){
        SelenideElement form = $(".form");
        open("http://localhost:9999/");
        form.$("[data-test-id=name] input").setValue("Мошина Ксения");
        form.$("[data-test-id=phone] input").setValue("");
        form.$("[data-test-id=agreement]").click();
        form.$("button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));

    }
}
