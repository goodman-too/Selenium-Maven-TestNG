package utils;

import org.testng.annotations.DataProvider;

public class StaticDataProvider {

    @DataProvider(name = "wrongLogin")
    public static Object[][] getData() {
        return new Object[][] {
                {"wwww@wwww.www","werrqwe"},
                {"alhgiueh@ggggmail.com","7896544569"},
                {"QWERTY@QWERTY.RU","QWERRTqwerty"},
        };
    }
}
