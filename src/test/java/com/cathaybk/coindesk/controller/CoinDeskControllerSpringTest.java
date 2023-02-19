package com.cathaybk.coindesk.controller;

import com.cathaybk.coindesk.CoindeskApplication;
import com.cathaybk.coindesk.bean.response.CoinDeskResponse;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest(classes = CoindeskApplication.class)
@WebAppConfiguration
public class CoinDeskControllerSpringTest {
    private CoinDeskController controller;

    @Test
    void getCoinDesk_success() {
        CoinDeskResponse coinDeskResponse = controller.getCoinDesk("demo_for_test");
        System.out.println(new Gson().toJson(coinDeskResponse));
    }

    @Autowired
    public CoinDeskControllerSpringTest setController(CoinDeskController controller) {
        this.controller = controller;
        return this;
    }
}
