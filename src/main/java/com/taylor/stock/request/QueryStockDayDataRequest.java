package com.taylor.stock.request;

import com.taylor.common.CommonRequest;
import com.taylor.common.Constants;
import com.taylor.common.JsonUtil;
import com.taylor.entity.RecmdStock;
import com.taylor.entity.StockBaseInfo;
import com.taylor.entity.StockBusinessinfo;
import com.taylor.entity.stock.StockFundInOut;
import com.taylor.entity.stock.StockPanKouData;
import com.taylor.entity.stock.TencentTodayBaseInfo;
import com.taylor.service.RecmdStockService;
import com.taylor.stock.common.OperatorEnum;
import com.taylor.stock.strategy.IStrategy;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.methods.GetMethod;

import java.text.DecimalFormat;
import java.util.List;

import static com.taylor.common.CommonRequest.queryLatestResult;

/**
 * 获取股票日K数据
 *
 * @author taylor
 */
@Slf4j
public class QueryStockDayDataRequest extends Thread {

    private RecmdStockService recmdStockService;

    private List<String> stockCodeList;

    private IStrategy strategy;


    public static int run_flag = 1;

    public QueryStockDayDataRequest(IStrategy strategy, RecmdStockService recmdStockService, List<String> stockCodeList, String taskName) {
        super(taskName);
        this.recmdStockService = recmdStockService;
        this.stockCodeList = stockCodeList;
        this.strategy = strategy;
    }

    @Override
    public void run() {
        DecimalFormat df = new DecimalFormat("######0.000");

        GetMethod methodBase = new GetMethod(Constants.METHOD_URL_STOCK_BASE_INFO);
        for (String stockCode : stockCodeList) {
            if (run_flag == 0) {
                break;
            }

            log.info("正在检测股票代码：{}", stockCode);
            List<StockBaseInfo> stockBaseInfos = QueryStockBaseDataRequest.queryStockBaseInfo(stockCode.toLowerCase(), methodBase);
            TencentTodayBaseInfo stckTodayBaseInfo = CommonRequest.getStckTodayBaseInfo(stockCode);
            /**停牌的过滤掉**/
            if (stockBaseInfos == null || stockBaseInfos.isEmpty() || "0".equals(stockBaseInfos.get(0).getStockStatus())) {
                continue;
            }
            IStrategy temp = strategy;
            while (strategy != null && run_flag == 1) {
                int checkResult = strategy.doCheck(stckTodayBaseInfo);
                StockFundInOut stockFundInOutData = CommonRequest.getStockFundInOutData(stockCode);
                StockBusinessinfo stockBusinessinfo = QueryStockBusinessDataRequest.queryStockBasicBussinessInfo(stockCode);
                /**只查买入意见的股票**/
                StockPanKouData stockPanKouData = CommonRequest.getStockPanKouData(stockCode);
                if (checkResult == 1 && stockPanKouData != null) {
                    RecmdStock recmdStock = new RecmdStock();
                    recmdStock.setStockCode(stockCode);
                    recmdStock.setTurnoverRatio(stockPanKouData.getExchangeRatio());
                    recmdStock.setStockName(stockPanKouData.getStockName());
                    recmdStock.setRecordPrice(Double.valueOf(df.format(stockBaseInfos.get(0).getClose())));
                    recmdStock.setCurrentPrice(Double.valueOf(df.format(stockBaseInfos.get(0).getClose())));
                    recmdStock.setStrategy(strategy.getStrategyEnum().getDesc());
                    recmdStock.setStrategyType(strategy.getStrategyEnum().getCode());
                    recmdStock.setMainIn(stockFundInOutData == null ? null : stockFundInOutData.getMainTotalIn());
                    recmdStock.setMainInBi(stockFundInOutData == null ? null : stockFundInOutData.getMainInBi());
                    recmdStock.setRecmdOperate(OperatorEnum.OPERATOR_ENUM_MAP.get(checkResult));
                    recmdStock.setLiangbi(stockPanKouData.getLiangBi());
                    recmdStock.setChangeRatioYestoday(stockBaseInfos.get(0).getNetChangeRatio());
                    recmdStock.setOuterPan(stockPanKouData.getOuter());
                    recmdStock.setInnerPan(stockPanKouData.getInner());
                    recmdStock.setIndustry(stockBusinessinfo.getIndustry());
                    recmdStock.setMajoGrow(stockBusinessinfo.getMajoGrow());
                    recmdStock.setNetIncreaseRate(stockBusinessinfo.getNetIncreaseRate());
                    recmdStockService.save(recmdStock);
                    log.info("股票代码：{}中标策略:{}", stockCode, strategy.getStrategyEnum().getDesc());
                }
                strategy = strategy.getNext();
            }
            strategy = temp;
        }
        System.out.println("##############################线程：" + Thread.currentThread().getName() + "已执行完毕###############################");

    }

    public static void main(String... args) {
        System.out.println(JsonUtil.transfer2JsonString(queryLatestResult("SZ300615",10)));
    }
}