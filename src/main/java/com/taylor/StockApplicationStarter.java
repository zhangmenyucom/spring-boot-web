package com.taylor;

import com.taylor.stock.request.BetRequestForDanShuang;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.taylor.common.Constants.initTime;

/**
 * 启动类
 *
 * @author taylor
 */
@SpringBootApplication
public class StockApplicationStarter {
    public static void main(String... args) throws InterruptedException {
        SpringApplication.run(StockApplicationStarter.class, args);
        if (args != null && args.length > 0 && args[0] != null) {
            initTime = Integer.valueOf(args[0]);
        }
        BetRequestForDanShuang.bet(initTime);
    }
}
