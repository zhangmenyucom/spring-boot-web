package com.taylor.stock.request;

import com.taylor.common.CommonRequest;
import com.taylor.entity.stock.StockFundInOut;
import lombok.Data;

import static com.taylor.common.SoundUtil.paly;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/31 10:31
 */
@Data
public class KdjMonitor extends Thread {

    private String stockCode;

    public  KdjMonitor(String stockCode){
        this.stockCode=stockCode;
    }

    @Override
    public void run() {
        while (true) {
            int check = KdjTimeDataRequest.check(stockCode);
            if(check==1){
                paly("audio/goodNew.wav");
                StockFundInOut stockFundInOutData = CommonRequest.getStockFundInOutData(stockCode);
                if(stockFundInOutData!=null) {
                    System.out.println(stockFundInOutData.getStockName() + "快抄底呀呀。。。。");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if(check==-1){
                paly("audio/alarm.wav");
                StockFundInOut stockFundInOutData = CommonRequest.getStockFundInOutData(stockCode);
                if(stockFundInOutData!=null) {
                    System.out.println(stockFundInOutData.getStockName() + "快抛呀呀。。。。");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if(check==2){
                paly("audio/timeCount.wav");
                StockFundInOut stockFundInOutData = CommonRequest.getStockFundInOutData(stockCode);
                if(stockFundInOutData!=null) {
                    System.out.println(stockFundInOutData.getStockName() + "预警,立马过来关注。。。。");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String... args) {
        KdjMonitor kdjMonitor1 = new KdjMonitor("sh510900");
        KdjMonitor kdjMonitor2 = new KdjMonitor("sh510710");
        kdjMonitor1.start();
        kdjMonitor2.start();
    }
}
