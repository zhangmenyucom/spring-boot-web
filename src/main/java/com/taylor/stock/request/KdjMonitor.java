package com.taylor.stock.request;

import com.taylor.common.CommonRequest;
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
public class KdjMonitor extends Thread {

    public static volatile int a = 0;

    private String stockCode;

    public KdjMonitor(String stockCode) {
        this.stockCode = stockCode;
    }

    @Override
    public void run() {
        while (a == 0) {
            /**如果超过14：40则逢高抛出所有股票**/
            if(DateCompare(new SimpleDateFormat("HH:MM").format(new Date()), "14:40", "HH:mm")>=0){
                paly("audio/alarm.wav");
                sendMail("时间警告","当前时间超过14：40，后期跳水，逢高全抛，见好就收" );
            }
            int check = KdjTimeDataRequest.check(stockCode);
            StockPanKouData stockFundInOutData = CommonRequest.getStockPanKouData(stockCode);
            if (check == 1) {
                paly("audio/goodNew.wav");
                if (stockFundInOutData != null) {
                    sendMail(stockFundInOutData.getStockName() + "-->立即买进", "股票(" + stockFundInOutData.getStockName() + ")出现临界值，请立即买进手上的股票");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (check == -1) {
                paly("audio/alarm.wav");
                if (stockFundInOutData != null) {
                    sendMail(stockFundInOutData.getStockName() + "-->立即抛售", "股票(" + stockFundInOutData.getStockName() + ")出现临界值，请立即抛售手上的股票");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (check == 2) {
                paly("audio/timeCount.wav");
                if (stockFundInOutData != null) {
                    sendMail(stockFundInOutData.getStockName() + "-->预警", "股票(" + stockFundInOutData.getStockName() + ")出现临界值，请留意");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (check == 0) {
                System.out.println(stockFundInOutData.getStockName() + "一切正常，正在密切监视");
            }
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static int DateCompare(String source, String traget, String type) {
        int ret = 2;
        try {
            SimpleDateFormat format = new SimpleDateFormat(type);
            Date sourcedate = format.parse(source);
            Date tragetdate = format.parse(traget);
            ret = sourcedate.compareTo(tragetdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static void main(String... args) {
        KdjMonitor kdjMonitor1 = new KdjMonitor("sh510900");
        kdjMonitor1.start();
    }
}
