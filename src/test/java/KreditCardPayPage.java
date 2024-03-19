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
    DataHelper.SelectInfo selInfo = DataHelper.getSelectInfo();
    DataHelper.PayInfo info = DataHelper.getPayInfo();
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
        $(selInfo.getCard()).sendKeys(info.getCard());
        $(selInfo.getMonth()).sendKeys(info.getMonth());
        $(selInfo.getYear()).sendKeys(info.getYear());
        $(selInfo.getOwner()).sendKeys(info.getOwner());
        $(selInfo.getCode()).sendKeys(info.getCode());
        $(selInfo.getButton()).click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(100))
                .shouldHave(Condition.exactText("Операция одобрена Банком."));
    }

    @Test
    void emptyCardKreditBuy() {

        $(selInfo.getCard()).setValue("");

        $(selInfo.getMonth()).sendKeys(info.getMonth());
        $(selInfo.getYear()).sendKeys(info.getYear());
        $(selInfo.getOwner()).sendKeys(info.getOwner());
        $(selInfo.getCode()).sendKeys(info.getCode());
        $(selInfo.getButton()).click();
        $("[class='input__sub']")
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .shouldHave(Condition.exactText("Неверный формат"));
    }

    @Test
    void notFullCardKreditBuy() {
        $(selInfo.getCard()).sendKeys("1111 1111 1");

        $(selInfo.getMonth()).sendKeys(info.getMonth());
        $(selInfo.getYear()).sendKeys(info.getYear());
        $(selInfo.getOwner()).sendKeys(info.getOwner());
        $(selInfo.getCode()).sendKeys(info.getCode());
        $(selInfo.getButton()).click();
        $("[class='input__sub']")
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .shouldHave(Condition.exactText("Неверный формат"));
    }

    @Test
    void notFigureCardKreditBuy() {
        $(selInfo.getCard()).sendKeys("qeqe breq a");
        $(selInfo.getMonth()).sendKeys(info.getMonth());
        $(selInfo.getYear()).sendKeys(info.getYear());
        $(selInfo.getOwner()).sendKeys(info.getOwner());
        $(selInfo.getCode()).sendKeys(info.getCode());
        $(selInfo.getButton()).click();
        $("[class='input__sub']")
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .shouldHave(Condition.exactText("Неверный формат"));
    }

    @Test
    void wrongCardKreditBuy() {
        $(selInfo.getCard()).sendKeys("1111 1111 1111 1111");
        $(selInfo.getMonth()).sendKeys(info.getMonth());
        $(selInfo.getYear()).sendKeys(info.getYear());
        $(selInfo.getOwner()).sendKeys(info.getOwner());
        $(selInfo.getCode()).sendKeys(info.getCode());
        $(selInfo.getButton()).click();
        $("#root > div > div.notification.notification_visible.notification_status_error.notification_has-closer.notification_stick-to_right.notification_theme_alfa-on-white > div.notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(100))
                .shouldHave(Condition.exactText("Ошибка! Банк отказал в проведении операции."));
    }

    @Test
    void emptyMonthKreditBuy() {


        $(selInfo.getCard()).sendKeys(info.getCard());
        $(selInfo.getMonth()).sendKeys("");
        $(selInfo.getYear()).sendKeys(info.getYear());
        $(selInfo.getOwner()).sendKeys(info.getOwner());
        $(selInfo.getCode()).sendKeys(info.getCode());
        $(selInfo.getButton()).click();
        $("[class='input__sub']")
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .shouldHave(Condition.exactText("Неверный формат"));
    }

    @Test
    void notFullMonthKreditBuy() {


        $(selInfo.getCard()).sendKeys(info.getCard());
        $(selInfo.getMonth()).sendKeys("5");
        $(selInfo.getYear()).sendKeys(info.getYear());
        $(selInfo.getOwner()).sendKeys(info.getOwner());
        $(selInfo.getCode()).sendKeys(info.getCode());
        $(selInfo.getButton()).click();
        $("[class='input__sub']")
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .shouldHave(Condition.exactText("Неверный формат"));
    }

    @Test
    void notFigureMonthKreditBuy() {


        $(selInfo.getCard()).sendKeys(info.getCard());
        $(selInfo.getMonth()).sendKeys("at");
        $(selInfo.getYear()).sendKeys(info.getYear());
        $(selInfo.getOwner()).sendKeys(info.getOwner());
        $(selInfo.getCode()).sendKeys(info.getCode());
        $(selInfo.getButton()).click();
        $("[class='input__sub']")
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .shouldHave(Condition.exactText("Неверный формат"));
    }

    @Test
    void unrealMonthKreditBuy() {


        $(selInfo.getCard()).sendKeys(info.getCard());
        $(selInfo.getMonth()).sendKeys("24");
        $(selInfo.getYear()).sendKeys(info.getYear());
        $(selInfo.getOwner()).sendKeys(info.getOwner());
        $(selInfo.getCode()).sendKeys(info.getCode());
        $(selInfo.getButton()).click();
        $("[class='input__sub']")
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .shouldHave(Condition.exactText("Неверно указан срок действия карты"));
    }

    @Test
    void pastMonthKreditBuy() {


        $(selInfo.getCard()).sendKeys(info.getCard());
        $(selInfo.getMonth()).sendKeys("01");
        $(selInfo.getYear()).sendKeys(info.getYear());
        $(selInfo.getOwner()).sendKeys(info.getOwner());
        $(selInfo.getCode()).sendKeys(info.getCode());
        $(selInfo.getButton()).click();
        $("[class='input__sub']")
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .shouldHave(Condition.exactText("Неверно указан срок действия карты"));
    }

    @Test
    void emptyYearKreditBuy() {


        $(selInfo.getCard()).sendKeys(info.getCard());
        $(selInfo.getMonth()).sendKeys(info.getMonth());
        $(selInfo.getYear()).sendKeys("");
        $(selInfo.getOwner()).sendKeys(info.getOwner());
        $(selInfo.getCode()).sendKeys(info.getCode());
        $(selInfo.getButton()).click();
        $("[class='input__sub']")
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .shouldHave(Condition.exactText("Неверный формат"));
    }

    @Test
    void notFullYearKreditBuy() {


        $(selInfo.getCard()).sendKeys(info.getCard());
        $(selInfo.getMonth()).sendKeys(info.getMonth());
        $(selInfo.getYear()).sendKeys("2");
        $(selInfo.getOwner()).sendKeys(info.getOwner());
        $(selInfo.getCode()).sendKeys(info.getCode());
        $(selInfo.getButton()).click();
        $("[class='input__sub']")
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .shouldHave(Condition.exactText("Неверный формат"));
    }

    @Test
    void notFigureYearKreditBuy() {

        $(selInfo.getCard()).sendKeys(info.getCard());
        $(selInfo.getMonth()).sendKeys(info.getMonth());
        $(selInfo.getYear()).sendKeys("y&");
        $(selInfo.getOwner()).sendKeys(info.getOwner());
        $(selInfo.getCode()).sendKeys(info.getCode());
        $(selInfo.getButton()).click();
        $("[class='input__sub']")
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .shouldHave(Condition.exactText("Неверный формат"));
    }

    @Test
    void unrealYearKreditBuy() {

        $(selInfo.getCard()).sendKeys(info.getCard());
        $(selInfo.getMonth()).sendKeys(info.getMonth());
        $(selInfo.getYear()).sendKeys("55");
        $(selInfo.getOwner()).sendKeys(info.getOwner());
        $(selInfo.getCode()).sendKeys(info.getCode());
        $(selInfo.getButton()).click();
        $("[class='input__sub']")
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .shouldHave(Condition.exactText("Неверно указан срок действия карты"));
    }

    @Test
    void pastYearKreditBuy() {

        $(selInfo.getCard()).sendKeys(info.getCard());
        $(selInfo.getMonth()).sendKeys(info.getMonth());
        $(selInfo.getYear()).sendKeys("11");
        $(selInfo.getOwner()).sendKeys(info.getOwner());
        $(selInfo.getCode()).sendKeys(info.getCode());
        $(selInfo.getButton()).click();
        $("[class='input__sub']")
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .shouldHave(Condition.exactText("Истёк срок действия карты"));
    }

    @Test
    void notRussiaOwnerKreditBuy() {


        $(selInfo.getCard()).sendKeys(info.getCard());
        $(selInfo.getMonth()).sendKeys(info.getMonth());
        $(selInfo.getYear()).sendKeys(info.getYear());
        $(selInfo.getOwner()).sendKeys("jac4");
        $(selInfo.getCode()).sendKeys(info.getCode());
        $(selInfo.getButton()).click();
        $("[class='input__sub']")
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void toMuchLetterKreditBuy() {

         $(selInfo.getCard()).sendKeys(info.getCard());
        $(selInfo.getMonth()).sendKeys(info.getMonth());
        $(selInfo.getYear()).sendKeys(info.getYear());
        $(selInfo.getOwner()).sendKeys("Владддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддддд");
        $(selInfo.getCode()).sendKeys(info.getCode());
        $(selInfo.getButton()).click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(100))
                .shouldHave(Condition.exactText("Операция одобрена Банком."));
    }

    @Test
    void emptyOwnerKreditBuy() {
        $(selInfo.getCard()).sendKeys(info.getCard());
        $(selInfo.getMonth()).sendKeys(info.getMonth());
        $(selInfo.getYear()).sendKeys(info.getYear());
        $(selInfo.getOwner()).sendKeys("");
        $(selInfo.getCode()).sendKeys(info.getCode());
        $(selInfo.getButton()).click();
        $("[class='input__sub']")
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void emptyVerificationCodeKreditBuy() {

        $(selInfo.getCard()).sendKeys(info.getCard());
        $(selInfo.getMonth()).sendKeys(info.getMonth());
        $(selInfo.getYear()).sendKeys(info.getYear());
        $(selInfo.getOwner()).sendKeys(info.getOwner());
        $(selInfo.getCode()).sendKeys("");
        $(selInfo.getButton()).click();
        $("[class='input__sub']")
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .shouldHave(Condition.exactText("Неверный формат"));
    }

    @Test
    void notFullVerificationCodeKreditBuy() {

        $(selInfo.getCard()).sendKeys(info.getCard());
        $(selInfo.getMonth()).sendKeys(info.getMonth());
        $(selInfo.getYear()).sendKeys(info.getYear());
        $(selInfo.getOwner()).sendKeys(info.getOwner());
        $(selInfo.getCode()).sendKeys("99");
        $(selInfo.getButton()).click();
        $("[class='input__sub']")
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .shouldHave(Condition.exactText("Неверный формат"));
    }

    @Test
    void notFigureVerificationCodeKreditBuy() {

        $(selInfo.getCard()).sendKeys(info.getCard());
        $(selInfo.getMonth()).sendKeys(info.getMonth());
        $(selInfo.getYear()).sendKeys(info.getYear());
        $(selInfo.getOwner()).sendKeys(info.getOwner());
        $(selInfo.getCode()).sendKeys("abc");
        $(selInfo.getButton()).click();
        $("[class='input__sub']")
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .shouldHave(Condition.exactText("Неверный формат"));
    }

    @Test
    void toManyVerificationCodeKreditBuy() {
        $(selInfo.getCard()).sendKeys(info.getCard());
        $(selInfo.getMonth()).sendKeys(info.getMonth());
        $(selInfo.getYear()).sendKeys(info.getYear());
        $(selInfo.getOwner()).sendKeys(info.getOwner());
        $(selInfo.getCode()).sendKeys("9999");
        $(selInfo.getButton()).click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(30))
                .shouldHave(Condition.exactText("Операция одобрена Банком."));
    }

    @Test
    void toManyYearKreditBuy() {


        $(selInfo.getCard()).sendKeys(info.getCard());
        $(selInfo.getMonth()).sendKeys(info.getMonth());
        $(selInfo.getYear()).sendKeys("244");
        $(selInfo.getOwner()).sendKeys(info.getOwner());
        $(selInfo.getCode()).sendKeys(info.getCode());
        $(selInfo.getButton()).click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(30))
                .shouldHave(Condition.exactText("Операция одобрена Банком."));
    }

    @Test
    void toManyMonthKreditBuy() {

        $(selInfo.getCard()).sendKeys(info.getCard());
        $(selInfo.getMonth()).sendKeys("111");
        $(selInfo.getYear()).sendKeys(info.getYear());
        $(selInfo.getOwner()).sendKeys(info.getOwner());
        $(selInfo.getCode()).sendKeys(info.getCode());
        $(selInfo.getButton()).click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(30))
                .shouldHave(Condition.exactText("Операция одобрена Банком."));
    }

    @Test
    void toManyCardKreditBuy() {

        $(selInfo.getCard()).sendKeys("1111 2222 3333 4444 2222");
        $(selInfo.getMonth()).sendKeys(info.getMonth());
        $(selInfo.getYear()).sendKeys(info.getYear());
        $(selInfo.getOwner()).sendKeys(info.getOwner());
        $(selInfo.getCode()).sendKeys(info.getCode());
        $(selInfo.getButton()).click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(30))
                .shouldHave(Condition.exactText("Операция одобрена Банком."));
    }
}

