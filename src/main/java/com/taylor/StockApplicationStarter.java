package com.taylor;

import com.taylor.stock.request.BetRequestForDanShuang;
import com.taylor.yicai.entity.BetStrategyEnum;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.ArrayList;
import java.util.List;

import static com.taylor.common.Constants.initTime;
import static com.taylor.stock.request.BetRequest.bet;

/**
 * 启动类
 *
 * @author taylor
 */
@SpringBootApplication
@EnableScheduling
public class StockApplicationStarter {
    public static void main(String... args) throws InterruptedException {
        SpringApplication.run(StockApplicationStarter.class, args);
        List<BetStrategyEnum> strategyEnumList = new ArrayList<>();
        strategyEnumList.add(BetStrategyEnum.D_DS);
        strategyEnumList.add(BetStrategyEnum.S_DS);
        strategyEnumList.add(BetStrategyEnum.DS_S);
        strategyEnumList.add(BetStrategyEnum.DS_D);
        BetRequestForDanShuang.bet(initTime, strategyEnumList);
    }
}
