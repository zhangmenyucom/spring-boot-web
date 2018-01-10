package com.taylor.entity.stock;

import com.taylor.common.CommonResponse;
import lombok.Data;

import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/10 13:53
 */
@Data
public class TimeStockDataResponse extends CommonResponse {

    private int version;

    private int exchangeStatus;

    private List<TimeLineBean> timeLine;

    private String latestTimelineStamp;

    private double preClose;

    private List<TickBean> tick;

    private List<TradeBean> bid;

    private List<TradeBean> ask;
}
