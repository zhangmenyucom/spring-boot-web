package com.taylor.stock.strategy;

import com.taylor.api.ApiClient;
import com.taylor.common.Constants;
import com.taylor.entity.DanInOutEntity;
import com.taylor.entity.FoundInOutEntity;
import com.taylor.entity.stock.HistoryData;
import com.taylor.stock.common.StrategyEnum;

import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/9 0:18
 */
public class XiPanStrategy extends IStrategy {
    public XiPanStrategy() {
        super(StrategyEnum.TYPE33);
    }

    @Override
    public int doCheck(List<HistoryData> historyData, String stockCode) {
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /**至少有十个交易日数据吧**/
        if (historyData == null || historyData.size() < 10) {
            return 0;
        }
        HistoryData today = historyData.get(historyData.size() - 1);
        if (today.getClose() > Constants.CURRENT_PRICE_LIMIT) {
            return 0;
        }
        FoundInOutEntity tongHuashunFoundInOut = ApiClient.getTongHuashunFoundInOut(stockCode);
        List<DanInOutEntity> danList = tongHuashunFoundInOut.getDanList();
        double daDan = danList.get(5).getSr() - danList.get(0).getSr();
        double ZhongDan = danList.get(4).getSr() - danList.get(1).getSr();
        double xiaoDan = danList.get(3).getSr() - danList.get(2).getSr();
        if (daDan > 300 && ZhongDan > 300 && (daDan > xiaoDan && ZhongDan > xiaoDan) && (historyData.get(historyData.size() - 1).getClose() - historyData.get(historyData.size() - 2).getClose()) / historyData.get(historyData.size() - 2).getClose() <= 0.05) {
            return 1;
        }
        return 0;
    }
}
