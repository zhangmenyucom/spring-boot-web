package com.taylor.controller;

import com.taylor.common.CommonResponse;
import com.taylor.common.PageResultBean;
import com.taylor.dto.GoodsTastesUnitQueryBean;
import com.taylor.vo.GoodsTastesUnit;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2017/8/14 16:52
 */
@Data
@RestController
@RequestMapping("/taste/")
public class TasteUnitController {
    @PostMapping("/list")
    public PageResultBean<GoodsTastesUnit> list(@RequestBody GoodsTastesUnitQueryBean queryBean) {
        PageResultBean<GoodsTastesUnit> goodsTastesUnitPageResultBean=new PageResultBean<>();
        GoodsTastesUnit goodsTastesUnit=new GoodsTastesUnit();
        goodsTastesUnit.setGoodsTastesUnitId(1L);
        goodsTastesUnit.setMerchantId(1129L);
        goodsTastesUnit.setStatus(2);
        goodsTastesUnit.setTastesName("酸辣");
        goodsTastesUnit.setThirdTastesId("thirdId");
        List<GoodsTastesUnit> list = new ArrayList<>();
        list.add(goodsTastesUnit);
        list.add(goodsTastesUnit);
        list.add(goodsTastesUnit);
        list.add(goodsTastesUnit);
        list.add(goodsTastesUnit);
        list.add(goodsTastesUnit);
        goodsTastesUnitPageResultBean.setItems(list);
        goodsTastesUnitPageResultBean.setTotalCount(100);
        goodsTastesUnitPageResultBean.setTotalPage(10);
        goodsTastesUnitPageResultBean.setPageNum(1);
        goodsTastesUnitPageResultBean.setPageSize(20);
        return goodsTastesUnitPageResultBean;
    }

    @PostMapping("/add")
    public CommonResponse<Void> addTaste(@RequestBody GoodsTastesUnitQueryBean goodsTastesUnitQueryBean){
        CommonResponse<Void> booleanCommonResponse=new CommonResponse<>();
        booleanCommonResponse.setMessage("添加口味成功");
        return booleanCommonResponse;
    }

}
