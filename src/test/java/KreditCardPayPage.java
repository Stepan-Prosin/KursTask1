import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class KreditCardPayPage {
    @BeforeAll
    static void setUpAllure() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAllure() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setup() {
        open("http://localhost:8080");
        $("[class='button button_view_extra button_size_m button_theme_alfa-on-white']").click();
    }

    @Test
    void succesKreditBuy() {
        $("[placeholder='0000 0000 0000 0000']").sendKeys("1111 2222 3333 4444");
        $("[placeholder='08']").sendKeys("11");
        $("[placeholder='22']").sendKeys("24");
        $("#root > div > form > fieldset > div:nth-child(3) > span > span:nth-child(1) > span > span > span.input__box > input").sendKeys("Влад");
        $("[placeholder='999']").sendKeys("999");
        $("form button.button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(30))
                .shouldHave(Condition.exactText("Операция одобрена Банком."));
    }

    @Test
    void emptyCardKreditBuy() {
        $("[placeholder='0000 0000 0000 0000']").sendKeys("");
        $("[placeholder='08']").sendKeys("11");
        $("[placeholder='22']").sendKeys("24");
        $("#root > div > form > fieldset > div:nth-child(3) > span > span:nth-child(1) > span > span > span.input__box > input").sendKeys("Влад");
        $("[placeholder='999']").sendKeys("999");
        $("form button.button").click();
        $("[class='input__sub']")
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .shouldHave(Condition.exactText("Неверный формат"));
    }

    @Test
    void notFullCardKreditBuy() {
        $("[placeholder='0000 0000 0000 0000']").sendKeys("1111 1111 1");
        $("[placeholder='08']").sendKeys("11");
        $("[placeholder='22']").sendKeys("24");
        $("#root > div > form > fieldset > div:nth-child(3) > span > span:nth-child(1) > span > span > span.input__box > input").sendKeys("Влад");
        $("[placeholder='999']").sendKeys("999");
        $("form button.button").click();
        $("[class='input__sub']")
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .shouldHave(Condition.exactText("Неверный формат"));
    }

    @Test
    void notFigureCardKreditBuy() {
        $("[placeholder='0000 0000 0000 0000']").sendKeys("qeqe breq a");
        $("[placeholder='08']").sendKeys("11");
        $("[placeholder='22']").sendKeys("24");
        $("#root > div > form > fieldset > div:nth-child(3) > span > span:nth-child(1) > span > span > span.input__box > input").sendKeys("Влад");
        $("[placeholder='999']").sendKeys("999");
        $("form button.button").click();
        $("[class='input__sub']")
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .shouldHave(Condition.exactText("Неверный формат"));
    }

    @Test
    void wrongCardKreditBuy() {
        $("[placeholder='0000 0000 0000 0000']").sendKeys("1111 1111 1111 1111");
        $("[placeholder='08']").sendKeys("11");
        $("[placeholder='22']").sendKeys("24");
        $("#root > div > form > fieldset > div:nth-child(3) > span > span:nth-child(1) > span > span > span.input__box > input").sendKeys("Влад");
        $("[placeholder='999']").sendKeys("999");
        $("form button.button").click();
        $("#root > div > div.notification.notification_visible.notification_status_error.notification_has-closer.notification_stick-to_right.notification_theme_alfa-on-white > div.notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(60))
                .shouldHave(Condition.exactText("Ошибка! Банк отказал в проведении операции."));
    }

    @Test
    void emptyMonthKreditBuy() {
        $("[placeholder='0000 0000 0000 0000']").sendKeys("1111 2222 3333 4444");
        $("[placeholder='08']").sendKeys("");
        $("[placeholder='22']").sendKeys("24");
        $("#root > div > form > fieldset > div:nth-child(3) > span > span:nth-child(1) > span > span > span.input__box > input").sendKeys("Влад");
        $("[placeholder='999']").sendKeys("999");
        $("form button.button").click();
        $("[class='input__sub']")
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .shouldHave(Condition.exactText("Неверный формат"));
    }

    @Test
    void notFullMonthKreditBuy() {
        $("[placeholder='0000 0000 0000 0000']").sendKeys("1111 2222 3333 4444");
        $("[placeholder='08']").sendKeys("5");
        $("[placeholder='22']").sendKeys("24");
        $("#root > div > form > fieldset > div:nth-child(3) > span > span:nth-child(1) > span > span > span.input__box > input").sendKeys("Влад");
        $("[placeholder='999']").sendKeys("999");
        $("form button.button").click();
        $("[class='input__sub']")
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .shouldHave(Condition.exactText("Неверный формат"));
    }

    @Test
    void notFigureMonthKreditBuy() {
        $("[placeholder='0000 0000 0000 0000']").sendKeys("1111 2222 3333 4444");
        $("[placeholder='08']").sendKeys("at");
        $("[placeholder='22']").sendKeys("24");
        $("#root > div > form > fieldset > div:nth-child(3) > span > span:nth-child(1) > span > span > span.input__box > input").sendKeys("Влад");
        $("[placeholder='999']").sendKeys("999");
        $("form button.button").click();
        $("[class='input__sub']")
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .shouldHave(Condition.exactText("Неверный формат"));
    }

    @Test
    void unrealMonthKreditBuy() {
        $("[placeholder='0000 0000 0000 0000']").sendKeys("1111 2222 3333 4444");
        $("[placeholder='08']").sendKeys("24");
        $("[placeholder='22']").sendKeys("24");
        $("#root > div > form > fieldset > div:nth-child(3) > span > span:nth-child(1) > span > span > span.input__box > input").sendKeys("Влад");
        $("[placeholder='999']").sendKeys("999");
        $("form button.button").click();
        $("[class='input__sub']")
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .shouldHave(Condition.exactText("Неверно указан срок действия карты"));
    }

    @Test
    void pastMonthKreditBuy() {
        $("[placeholder='0000 0000 0000 0000']").sendKeys("1111 2222 3333 4444");
        $("[placeholder='08']").sendKeys("01");
        $("[placeholder='22']").sendKeys("24");
        $("#root > div > form > fieldset > div:nth-child(3) > span > span:nth-child(1) > span > span > span.input__box > input").sendKeys("Влад");
        $("[placeholder='999']").sendKeys("999");
        $("form button.button").click();
        $("[class='input__sub']")
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .shouldHave(Condition.exactText("Неверно указан срок действия карты"));
    }

    @Test
    void emptyYearKreditBuy() {
        $("[placeholder='0000 0000 0000 0000']").sendKeys("1111 2222 3333 4444");
        $("[placeholder='08']").sendKeys("11");
        $("[placeholder='22']").sendKeys("");
        $("#root > div > form > fieldset > div:nth-child(3) > span > span:nth-child(1) > span > span > span.input__box > input").sendKeys("Влад");
        $("[placeholder='999']").sendKeys("999");
        $("form button.button").click();
        $("[class='input__sub']")
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .shouldHave(Condition.exactText("Неверный формат"));
    }

    @Test
    void notFullYearKreditBuy() {
        $("[placeholder='0000 0000 0000 0000']").sendKeys("1111 2222 3333 4444");
        $("[placeholder='08']").sendKeys("11");
        $("[placeholder='22']").sendKeys("2");
        $("#root > div > form > fieldset > div:nth-child(3) > span > span:nth-child(1) > span > span > span.input__box > input").sendKeys("Влад");
        $("[placeholder='999']").sendKeys("999");
        $("form button.button").click();
        $("[class='input__sub']")
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .shouldHave(Condition.exactText("Неверный формат"));
    }

    @Test
    void notFigureYearKreditBuy() {
        $("[placeholder='0000 0000 0000 0000']").sendKeys("1111 2222 3333 4444");
        $("[placeholder='08']").sendKeys("11");
        $("[placeholder='22']").sendKeys("y&");
        $("#root > div > form > fieldset > div:nth-child(3) > span > span:nth-child(1) > span > span > span.input__box > input").sendKeys("Влад");
        $("[placeholder='999']").sendKeys("999");
        $("form button.button").click();
        $("[class='input__sub']")
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .shouldHave(Condition.exactText("Неверный формат"));
    }

    @Test
    void unrealYearKreditBuy() {
        $("[placeholder='0000 0000 0000 0000']").sendKeys("1111 2222 3333 4444");
        $("[placeholder='08']").sendKeys("11");
        $("[placeholder='22']").sendKeys("55");
        $("#root > div > form > fieldset > div:nth-child(3) > span > span:nth-child(1) > span > span > span.input__box > input").sendKeys("Влад");
        $("[placeholder='999']").sendKeys("999");
        $("form button.button").click();
        $("[class='input__sub']")
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .shouldHave(Condition.exactText("Неверно указан срок действия карты"));
    }

    @Test
    void pastYearKreditBuy() {
        $("[placeholder='0000 0000 0000 0000']").sendKeys("1111 2222 3333 4444");
        $("[placeholder='08']").sendKeys("11");
        $("[placeholder='22']").sendKeys("11");
        $("#root > div > form > fieldset > div:nth-child(3) > span > span:nth-child(1) > span > span > span.input__box > input").sendKeys("Влад");
        $("[placeholder='999']").sendKeys("999");
        $("form button.button").click();
        $("[class='input__sub']")
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .shouldHave(Condition.exactText("Истёк срок действия карты"));
    }

    @Test
    void notRussiaOwnerKreditBuy() {
        $("[placeholder='0000 0000 0000 0000']").sendKeys("1111 2222 3333 4444");
        $("[placeholder='08']").sendKeys("11");
        $("[placeholder='22']").sendKeys("24");
        $("#root > div > form > fieldset > div:nth-child(3) > span > span:nth-child(1) > span > span > span.input__box > input").sendKeys("jаc4");
        $("[placeholder='999']").sendKeys("999");
        $("form button.button").click();
        $("[class='input__sub']")
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void toManyLetterKreditBuy() {
        $("[placeholder='0000 0000 0000 0000']").sendKeys("1111 2222 3333 4444");
        $("[placeholder='08']").sendKeys("11");
        $("[placeholder='22']").sendKeys("24");
        $("#root > div > form > fieldset > div:nth-child(3) > span > span:nth-child(1) > span > span > span.input__box > input").sendKeys("Владдддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддд");
        $("[placeholder='999']").sendKeys("999");
        $("form button.button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .shouldHave(Condition.exactText("Операция одобрена Банком."));
    }

    @Test
    void emptyOwnerKreditBuy() {
        $("[placeholder='0000 0000 0000 0000']").sendKeys("1111 2222 3333 4444");
        $("[placeholder='08']").sendKeys("11");
        $("[placeholder='22']").sendKeys("24");
        $("#root > div > form > fieldset > div:nth-child(3) > span > span:nth-child(1) > span > span > span.input__box > input").sendKeys("");
        $("[placeholder='999']").sendKeys("999");
        $("form button.button").click();
        $("[class='input__sub']")
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void emptyVerificationCodeKreditBuy() {
        $("[placeholder='0000 0000 0000 0000']").sendKeys("1111 2222 3333 4444");
        $("[placeholder='08']").sendKeys("11");
        $("[placeholder='22']").sendKeys("24");
        $("#root > div > form > fieldset > div:nth-child(3) > span > span:nth-child(1) > span > span > span.input__box > input").sendKeys("Влад");
        $("[placeholder='999']").sendKeys("");
        $("form button.button").click();
        $("[class='input__sub']")
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .shouldHave(Condition.exactText("Неверный формат"));
    }

    @Test
    void notFullVerificationCodeKreditBuy() {
        $("[placeholder='0000 0000 0000 0000']").sendKeys("1111 2222 3333 4444");
        $("[placeholder='08']").sendKeys("11");
        $("[placeholder='22']").sendKeys("24");
        $("#root > div > form > fieldset > div:nth-child(3) > span > span:nth-child(1) > span > span > span.input__box > input").sendKeys("Влад");
        $("[placeholder='999']").sendKeys("99");
        $("form button.button").click();
        $("[class='input__sub']")
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .shouldHave(Condition.exactText("Неверный формат"));
    }

    @Test
    void notFigureVerificationCodeKreditBuy() {
        $("[placeholder='0000 0000 0000 0000']").sendKeys("1111 2222 3333 4444");
        $("[placeholder='08']").sendKeys("11");
        $("[placeholder='22']").sendKeys("24");
        $("#root > div > form > fieldset > div:nth-child(3) > span > span:nth-child(1) > span > span > span.input__box > input").sendKeys("Влад");
        $("[placeholder='999']").sendKeys("abc");
        $("form button.button").click();
        $("[class='input__sub']")
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .shouldHave(Condition.exactText("Неверный формат"));
    }

    @Test
    void toManyVerificationCodeKreditBuy() {
        $("[placeholder='0000 0000 0000 0000']").sendKeys("1111 2222 3333 4444");
        $("[placeholder='08']").sendKeys("11");
        $("[placeholder='22']").sendKeys("24");
        $("#root > div > form > fieldset > div:nth-child(3) > span > span:nth-child(1) > span > span > span.input__box > input").sendKeys("Влад");
        $("[placeholder='999']").sendKeys("9999");
        $("form button.button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(30))
                .shouldHave(Condition.exactText("Операция одобрена Банком."));
    }

    @Test
    void toManyYearKreditBuy() {
        $("[placeholder='0000 0000 0000 0000']").sendKeys("1111 2222 3333 4444");
        $("[placeholder='08']").sendKeys("11");
        $("[placeholder='22']").sendKeys("244");
        $("#root > div > form > fieldset > div:nth-child(3) > span > span:nth-child(1) > span > span > span.input__box > input").sendKeys("Влад");
        $("[placeholder='999']").sendKeys("999");
        $("form button.button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(30))
                .shouldHave(Condition.exactText("Операция одобрена Банком."));
    }

    @Test
    void toManyMonthKreditBuy() {
        $("[placeholder='0000 0000 0000 0000']").sendKeys("1111 2222 3333 4444");
        $("[placeholder='08']").sendKeys("111");
        $("[placeholder='22']").sendKeys("24");
        $("#root > div > form > fieldset > div:nth-child(3) > span > span:nth-child(1) > span > span > span.input__box > input").sendKeys("Влад");
        $("[placeholder='999']").sendKeys("999");
        $("form button.button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(30))
                .shouldHave(Condition.exactText("Операция одобрена Банком."));
    }

    @Test
    void toManyCardKreditBuy() {
        $("[placeholder='0000 0000 0000 0000']").sendKeys("1111 2222 3333 4444 2222");
        $("[placeholder='08']").sendKeys("11");
        $("[placeholder='22']").sendKeys("24");
        $("#root > div > form > fieldset > div:nth-child(3) > span > span:nth-child(1) > span > span > span.input__box > input").sendKeys("Влад");
        $("[placeholder='999']").sendKeys("999");
        $("form button.button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(30))
                .shouldHave(Condition.exactText("Операция одобрена Банком."));
    }
}

