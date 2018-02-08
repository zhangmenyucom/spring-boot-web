package com.taylor.stock.request;

import com.taylor.common.CommonRequest;
import com.taylor.common.KLineTypeEnum;
import com.taylor.common.StockUtils;
import com.taylor.entity.stock.StockPanKouData;
import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.taylor.common.MailUtils.sendMail;
import static com.taylor.common.SoundUtil.paly;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/31 10:31
 */
@Data
public class KdjOneMiniMonitor extends Thread {

    public static volatile int a = 0;

    private String stockCode;

    private KLineTypeEnum kLineTypeEnum;

    public KdjOneMiniMonitor(String stockCode, KLineTypeEnum kLineTypeEnum) {
        this.stockCode = stockCode;
        this.kLineTypeEnum = kLineTypeEnum;
    }

    @Override
    public void run() {
        while (a == 1) {
            if(StockUtils.noNeedMonotorTime()){
                System.out.println(kLineTypeEnum.getDescription()+" 提示：非交易时间，不执行监控，当前时间   " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
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
            int check = KdjTimeDataRequest.check(stockCode,kLineTypeEnum);
            StockPanKouData stockFundInOutData = CommonRequest.getStockPanKouData(stockCode);
            if (check == 1) {
                paly("audio/chongfenghao.wav");
                if (stockFundInOutData != null) {
                    sendMail(kLineTypeEnum.getDescription()+stockFundInOutData.getStockName() + "-->立即买进", "股票(" + stockFundInOutData.getStockName() + ")出现临界值，请立即买进手上的股票");
                }
            }
            if (check == -1) {
                paly("audio/pur-water.wav");
                if (stockFundInOutData != null) {
                    sendMail(kLineTypeEnum.getDescription()+stockFundInOutData.getStockName() + "-->立即抛售", "股票(" + stockFundInOutData.getStockName() + ")出现临界值，请立即抛售手上的股票");
                }
            }
            if (check == 2) {
                paly("audio/timeCount.wav");
                if (stockFundInOutData != null) {
                    sendMail(kLineTypeEnum.getDescription() + stockFundInOutData.getStockName() + "-->预警", "股票(" + stockFundInOutData.getStockName() + ")出现临界值,有下跌趋势，请留意");
                }
            }
            if (check == 3) {
                paly("audio/timeCount.wav");
                if (stockFundInOutData != null) {
                    sendMail(kLineTypeEnum.getDescription() + stockFundInOutData.getStockName() + "-->预警", "股票(" + stockFundInOutData.getStockName() + ")出现临界值,有上涨趋势，请留意");
                }
            }
            if (check == 0) {
                System.out.println(stockFundInOutData.getStockName() + "一切正常，正在密切监视");
            }
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String... args) {
        KdjOneMiniMonitor kdjMonitor1 = new KdjOneMiniMonitor("sh510900",KLineTypeEnum.ONE_MINI);
        kdjMonitor1.start();
    }
}
