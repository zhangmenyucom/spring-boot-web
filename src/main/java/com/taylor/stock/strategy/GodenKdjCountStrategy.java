package com.taylor.stock.strategy;

import com.taylor.entity.stock.MashData;
import lombok.Data;

import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/9 0:18
 */
@Data
public class GodenKdjCountStrategy extends IStrategy {


    public GodenKdjCountStrategy() {
        super("最近100天kdj黄金交叉数量");
    }

    @Override
    public int doCheck(List<MashData> mashDataList) {
        int result = 0;
        for (int i = 0; i < mashDataList.size() - 1; i++) {
           if(check(mashDataList.get(i),mashDataList.get(i+1))){
               result++;
           }
        }
        mashDataList.get(0).setKdjCount(result);

        if(result<6){
            return 1;
        }

        return 0;
    }

    private boolean check(MashData mashDataToday, MashData mashDataYestoday) {
        if (mashDataToday.getKdj().getK() - mashDataToday.getKdj().getD() >= 0 && mashDataYestoday.getKdj().getK() - mashDataYestoday.getKdj().getD() <= 0) {
            return true;
        }
        return false;
    }
}
