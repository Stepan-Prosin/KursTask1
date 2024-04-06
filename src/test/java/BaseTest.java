import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BaseTest {

    @BeforeAll
    static void setUpAllure() {
        SelenideLogger.addListener("allure", new AllureSelenide());


    }

    @AfterAll
    static void tearDownAllure() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    public void baseTest() {
        String url = "jdbc:postgresql://185.119.57.164:5432/db";
        String user = "postgres";
        String password = "boring";
        String actualText;
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT *\n" +
                    "FROM order_entity\n" +
                    "JOIN credit_request_entity  ON order_entity.credit_id  = credit_request_entity.bank_id ;");

            if (resultSet.next()) {
                String value = resultSet.getString("credit_id");
                actualText = "Value in the cell";
            } else {
                actualText = "No value found in the cell";
            }
            assertEquals("Value in the cell", actualText);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}