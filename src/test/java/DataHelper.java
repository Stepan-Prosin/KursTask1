import lombok.Value;

public class DataHelper {
    public static PayInfo getPayInfo() {
        return new PayInfo("1111 2222 3333 4444", "11", "24", "Влад", "999");
    }

    public static SelectInfo getSelectInfo() {
        return new SelectInfo("[placeholder='0000 0000 0000 0000']", "[placeholder='08']"
                , "[placeholder='22']",
                "#root > div > form > fieldset > div:nth-child(3) > span > span:nth-child(1) > span > span > span.input__box > input",
                "[placeholder='999']", "form button.button");
    }

    @Value
    public static class PayInfo {
        private String card;
        private String month;
        private String year;
        private String owner;
        private String code;


    }

    @Value
    public static class SelectInfo {
        private String card;
        private String month;
        private String year;
        private String owner;
        private String code;
        private String button;


    }
}
