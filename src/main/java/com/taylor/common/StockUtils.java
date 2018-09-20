package com.taylor.common;

import com.taylor.api.ApiClient;
import com.taylor.entity.stock.StockPanKouData;
import com.taylor.entity.stock.kdj.CheckResultBean;
import com.taylor.stock.request.KdjTimeDataRequest;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.taylor.common.ConstantsInits.STOCK_ON_MONITOR_MAP;
import static com.taylor.common.MailUtils.sendMail;
import static com.taylor.common.SoundUtil.paly;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/2/7 2:36
 */
@Slf4j
public class StockUtils {
    /**
     * 不需要监控时间
     */
    public static boolean noNeedMonotorTime() {
        boolean over15_00 = DateCompare(new SimpleDateFormat("HH:mm").format(new Date()), "15:00", "HH:mm") >= 0;
        boolean before9_30 = DateCompare(new SimpleDateFormat("HH:mm").format(new Date()), "9:25", "HH:mm") <= 0;
        boolean duringBreakTime = DateCompare(new SimpleDateFormat("HH:mm").format(new Date()), "11:30", "HH:mm") > 0 && DateCompare(new SimpleDateFormat("HH:MM").format(new Date()), "13:00", "HH:mm") <= 0;
        return over15_00 || before9_30 || duringBreakTime;
    }

    public static boolean noNeedMonotorForYiDongTime() {
        boolean over15_00 = DateCompare(new SimpleDateFormat("HH:mm").format(new Date()), "14:50", "HH:mm") >= 0;
        boolean before9_30 = DateCompare(new SimpleDateFormat("HH:mm").format(new Date()), "9:35", "HH:mm") <= 0;
        boolean duringBreakTime = DateCompare(new SimpleDateFormat("HH:mm").format(new Date()), "11:30", "HH:mm") > 0 && DateCompare(new SimpleDateFormat("HH:MM").format(new Date()), "13:00", "HH:mm") <= 0;
        return over15_00 || before9_30 || duringBreakTime;
    }

    /**
     * 比较时间大小
     **/
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

    /**
     * 危险时间
     *
     * @return
     */
    public static boolean dangerTime() {
        return DateCompare(new SimpleDateFormat("HH:mm").format(new Date()), "14:40", "HH:mm") >= 0;
    }


    /**
     * 数据监控，邮件预警
     **/
    public static void processStock(String stockCode, KLineTypeEnum kLineTypeEnum) {
        log.info("now is monitoring {}", STOCK_ON_MONITOR_MAP.get(stockCode));
        CheckResultBean kdjCheck = KdjTimeDataRequest.check(stockCode, kLineTypeEnum);
        /*CheckResultBean macdCheck = MacdTimeDataRequest.check(stockCode, kLineTypeEnum, 2);*/

        StockPanKouData stockFundInOutData = ApiClient.getPanKouData(stockCode);
        if (kdjCheck.getCode() == 1) {
            paly("audio/chongfenghao.wav");
            if (stockFundInOutData != null) {
                sendMail(kLineTypeEnum.getDescription() + stockFundInOutData.getStockName() + "-->请立即买进", "");
            }
        }
        if (kdjCheck.getCode() == -1) {
            paly("audio/pur-water.wav");
            if (stockFundInOutData != null) {
                sendMail(kLineTypeEnum.getDescription() + stockFundInOutData.getStockName() + "-->请立即抛售", "");
            }
        }
        if (kdjCheck.getCode() == 2) {
            paly("audio/timeCount.wav");
            if (stockFundInOutData != null) {
                sendMail(kLineTypeEnum.getDescription() + stockFundInOutData.getStockName() + "-->预警,出现临界值,有下跌趋势，请留意", "");
            }
        }
        if (kdjCheck.getCode() == 3) {
            paly("audio/timeCount.wav");
            if (stockFundInOutData != null) {
                sendMail(kLineTypeEnum.getDescription() + stockFundInOutData.getStockName() + "-->预警,出现临界值,有上涨趋势，请留意", "");
            }
        }
        if (kdjCheck.getCode() == 0) {
            System.out.println(stockFundInOutData.getStockName() + "一切正常，正在密切监视");
        }
    }

    /**
     * 获取前后几天的日期
     **/
    public static Date getDateAfter(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }
}
