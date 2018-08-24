package com.taylor;

import com.taylor.common.Constants;
import com.taylor.common.PropertiesUtil;
import com.taylor.stock.request.BetRequestForDanShuang;
import com.taylor.stock.request.BetRequestForZuLiu;
import com.taylor.yicai.entity.BillEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Map;

import static com.taylor.common.Constants.*;

/**
 * 启动类
 *
 * @author taylor
 */
@SpringBootApplication
@Slf4j
public class StockApplicationStarter {
    public static void main(String... args) throws InterruptedException, IOException {
        SpringApplication.run(StockApplicationStarter.class, args);

        Map properties = PropertiesUtil.loadProperties("c:/config.properties");
        if (properties != null) {
            initTime = Integer.valueOf(properties.get("initTime").toString());
            FAIL_LIMIT = Integer.valueOf(properties.get("failLimit").toString());
            BILLUNIT = BillEnum.valueOf(properties.get("unit").toString());
            FACTOR = Integer.valueOf(properties.get("factor").toString());
            COOKIE = properties.get("COOKIE").toString();
        }

        if (args != null && args.length > 0 && args[0] != null) {
            initTime = Integer.valueOf(args[0]);
        }
        if (args != null && args.length > 1 && args[1] != null) {
            FAIL_LIMIT = Integer.valueOf(args[1]);
        }
        if (args != null && args.length > 2 && args[2] != null) {
            BILLUNIT = BillEnum.valueOf(args[2]);
        }
        if (args != null && args.length > 4 && args[3] != null) {
            FACTOR = Integer.valueOf(args[3]);
        }
        log.info("初始化完成,配置如下");
        log.info("初始倍数：{},失败上限:{},加注单位：{},校验因子：{}", initTime, FAIL_LIMIT, BILLUNIT.getName(), FACTOR);
        log.info("Cookie:{}", COOKIE);
        BetRequestForZuLiu.bet(initTime);
    }
}
