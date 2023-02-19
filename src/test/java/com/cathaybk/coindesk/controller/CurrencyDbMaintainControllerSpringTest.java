package com.cathaybk.coindesk.controller;

import com.cathaybk.coindesk.CoindeskApplication;
import com.cathaybk.coindesk.bean.request.CurrencyRequest;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest(classes = CoindeskApplication.class)
@WebAppConfiguration
public class CurrencyDbMaintainControllerSpringTest {
    CurrencyDbMaintainController controller;

    @Test
    void getAllCurrency_success() {
        System.out.println(
                new Gson().toJson(controller.getAllCurrency("demo_for_test")));
    }

    @Test
    void getCurrency_success() {
        System.out.println(new Gson().toJson(controller.getCurrency("demo_for_test", "USD")));
    }

    @Test
    void insertCurrency_success() {
        System.out.println("before add new: " + new Gson().toJson(controller.getAllCurrency("demo_for_test")));
        CurrencyRequest request = new Gson().fromJson("{\n" +
                "  \"currencyCode\": \"test\",\n" +
                "  \"twCurrencyName\": \"測試\",\n" +
                "  \"enCurrencyName\": \"test\"\n" +
                "}", CurrencyRequest.class);
        System.out.println(new Gson().toJson(controller.insertCurrency("demo_for_test", request)));
        System.out.println("after add new: " + new Gson().toJson(controller.getAllCurrency("demo_for_test")));
    }

    @Test
    void updateCurrency_success() {
        System.out.println("before add new: " + new Gson().toJson(controller.getAllCurrency("demo_for_test")));
        CurrencyRequest request = new Gson().fromJson("{\n" +
                "  \"currencyCode\": \"USD\",\n" +
                "  \"twCurrencyName\": \"測試2\",\n" +
                "  \"enCurrencyName\": \"test321\"\n" +
                "}", CurrencyRequest.class);
        System.out.println(new Gson().toJson(controller.updateCurrency("demo_for_test", request)));
        System.out.println("after add new: " + new Gson().toJson(controller.getAllCurrency("demo_for_test")));
    }

    @Test
    void deleteCurrency() {
        System.out.println("before add new: " + new Gson().toJson(controller.getAllCurrency("demo_for_test")));
        System.out.println(new Gson().toJson(controller.deleteCurrency("test")));
        System.out.println("after add new: " + new Gson().toJson(controller.getAllCurrency("demo_for_test")));
    }

    @Autowired
    public CurrencyDbMaintainControllerSpringTest setController(CurrencyDbMaintainController controller) {
        this.controller = controller;
        return this;
    }
}
