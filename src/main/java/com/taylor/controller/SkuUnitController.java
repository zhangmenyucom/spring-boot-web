package com.taylor.controller;

import com.taylor.common.CommonResponse;
import com.taylor.common.PageResultBean;
import com.taylor.dto.GoodsSkuUnitQueryBean;
import com.taylor.vo.GoodsSkuUnitVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2017/8/15 10:44
 */
@RestController
@RequestMapping("/sku/")
public class SkuUnitController {
    @RequestMapping("list")
    public CommonResponse<PageResultBean<GoodsSkuUnitVo>> listGoodsSkuUnit(@RequestBody GoodsSkuUnitQueryBean queryBean) {
        CommonResponse<PageResultBean<GoodsSkuUnitVo>> pageResultBeanCommonResponse = new CommonResponse<>();
        PageResultBean<GoodsSkuUnitVo> pageResultBean = new PageResultBean<>();
        pageResultBean.setPageSize(20);
        pageResultBean.setPageNum(1);
        pageResultBean.setTotalPage(10);
        pageResultBean.setTotalCount(200);
        GoodsSkuUnitVo goodsSkuUnitVo = new GoodsSkuUnitVo();
        goodsSkuUnitVo.setGoodsSkuUnitId(1);
        goodsSkuUnitVo.setSkuName("大小");
        goodsSkuUnitVo.setThirdCode("第三方代码");
        List<GoodsSkuUnitVo> list = new ArrayList<>();
        list.add(goodsSkuUnitVo);
        pageResultBean.setItems(list);
        pageResultBeanCommonResponse.setData(pageResultBean);
        return pageResultBeanCommonResponse;
    }


    @PostMapping("/add")
    public CommonResponse<Void> add(@RequestBody GoodsSkuUnitQueryBean queryBean) {
        CommonResponse<Void> goodsSkuUnitCommonResponse=new CommonResponse<>();
        goodsSkuUnitCommonResponse.setMessage("添加规格成功");
        return goodsSkuUnitCommonResponse;
    }
}
