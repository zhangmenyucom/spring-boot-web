package com.taylor.stock.request;

import com.taylor.common.KLineTypeEnum;
import com.taylor.common.StockUtils;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.taylor.common.ConstantsInits.STOCK_ON_MONITOR_LIST;
import static com.taylor.common.MailUtils.sendMail;
import static com.taylor.common.SoundUtil.paly;
import static com.taylor.common.StockUtils.processStock;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/31 10:31
 */
@Data
public class KdjFiveMiniMonitor extends Thread {

    public static volatile int a = 0;

    private KLineTypeEnum kLineTypeEnum;

    public KdjFiveMiniMonitor( KLineTypeEnum kLineTypeEnum) {
        this.kLineTypeEnum = kLineTypeEnum;
    }

    @Override
    public void run() {
        while (a == 1) {
            for (String stockCode : STOCK_ON_MONITOR_LIST) {
                if (StockUtils.noNeedMonotorTime()) {
                    System.out.println(kLineTypeEnum.getDescription() + " 提示：非交易时间，不执行监控，当前时间   " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                    try {
                        Thread.sleep(60000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }
                /**如果超过14：40则逢高抛出所有股票**/
                if (StockUtils.dangerTime()) {
                    paly("audio/alarm.wav");
                    sendMail("时间警告", "当前时间超过14：40，后期跳水，逢高全抛，见好就收");
                }

                /**实时处理数据**/
                processStock(stockCode, kLineTypeEnum);
            }

            try {
                Thread.sleep(90000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String... args) {
        KdjFiveMiniMonitor kdjMonitor1 = new KdjFiveMiniMonitor(KLineTypeEnum.FIVE_MINI);
        kdjMonitor1.start();
    }

}
