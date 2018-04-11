package com.taylor.stock.strategy;

import com.taylor.common.Constants;
import com.taylor.entity.stock.MacdBean;
import com.taylor.entity.stock.MashData;
import com.taylor.stock.common.StrategyEnum;
import lombok.Data;

import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/9 0:21
 */
@Data
public class MacdOverZeroStrategy extends IStrategy {
    public MacdOverZeroStrategy() {
        super(StrategyEnum.TYPE25);
    }

    @Override
    public int doCheck(List<MashData> mashDataList) {
        MashData today = mashDataList.get(0);
        MacdBean macdToday = today.getMacd();

        MashData yestoday = mashDataList.get(1);
        MacdBean macdYestoday = yestoday.getMacd();
        /**股价不要太高**/
        if (today.getKline().getClose() > Constants.CURRENT_PRICE_LIMIT) {
            return 0;
        }
        /**macd在0轴上方且macd>0 **/
        if (macdToday.getMacd() > 0 && macdToday.getDiff() > 0 && macdToday.getDea() > 0 && macdToday.getDiff() > macdToday.getDea()) {
            if (macdYestoday.getMacd() > 0 && macdYestoday.getDiff() > 0 && macdYestoday.getDea() > 0 && macdYestoday.getDiff() > macdYestoday.getDea()) {
                if (macdToday.getMacd() > macdYestoday.getMacd() && macdToday.getDiff()<0.26) {
                    return 1;
                }
            }

        }


        return 0;
    }
}
