package com.taylor;

import com.taylor.stock.request.BetRequestForDanShuang;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.taylor.common.Constants.initTime;
import static com.taylor.common.Constants.FAIL_LIMIT;

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
        if (args != null && args.length > 1 && args[1] != null) {
            FAIL_LIMIT = Integer.valueOf(args[1]);
        }
        BetRequestForDanShuang.bet(initTime);
    }
}
