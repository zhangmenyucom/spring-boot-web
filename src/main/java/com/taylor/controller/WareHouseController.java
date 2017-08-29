package com.taylor.controller;

import com.github.pagehelper.PageInfo;
import com.taylor.common.CommonResponse;
import com.taylor.common.PageResultBean;
import com.taylor.dto.*;
import com.taylor.vo.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2017/8/14 14:28
 */
@RestController
@RequestMapping("/goods/house/")
public class WareHouseController {

    @PostMapping("/list")
    public PageInfo<WareHouseGoods> getWareHouseGoodsPage(@RequestBody WareHoseGoodsRequestBean wareHoseGoodsRequestBean) {
        PageInfo<WareHouseGoods> wareHouseGoodsPageInfo = new PageInfo<>();
        WareHouseGoods wareHouseGoods = new WareHouseGoods();
        wareHouseGoods.setGoodsName("仓库菜名");
        wareHouseGoods.setMerchantId(1129L);
        wareHouseGoods.setPicUrl("wwww.baidu.com");
        wareHouseGoods.setPrice(BigDecimal.valueOf(12.9));
        wareHouseGoods.setStatus(0);
        wareHouseGoods.setWareHouseGoodsId(3L);
        List<WareHouseGoods> list = new ArrayList<>();
        list.add(wareHouseGoods);
        list.add(wareHouseGoods);
        list.add(wareHouseGoods);
        wareHouseGoodsPageInfo.setList(list);
        wareHouseGoodsPageInfo.setPageNum(1);
        wareHouseGoodsPageInfo.setPageSize(10);
        wareHouseGoodsPageInfo.setPages(10);
        wareHouseGoodsPageInfo.setList(list);
        return wareHouseGoodsPageInfo;
    }


    /**
     * 仓库菜品批量操作删除
     *
     * @return
     */
    @PostMapping("/batchDelete")
    public CommonResponse<Void> handleBatchHouseGoods(@RequestBody WareHouseGoodsDeleteBean wareHouseGoodsDeleteBean) {
        CommonResponse<Void> booleanCommonResponse = new CommonResponse<>();
        booleanCommonResponse.setMessage("批量删除成功");
        return booleanCommonResponse;
    }


    /**
     * 详情页
     *
     * @return
     */
    @PostMapping("/detail")
    public CommonResponse<HouseGoodsDetail> detail(@RequestBody WareHouseGoodsDetailSearchBean wareHouseGoodsDetailSearchBean) {
        CommonResponse<HouseGoodsDetail> houseGoodsDetailCommonResponse = new CommonResponse<>();
        HouseGoodsDetail houseGoodsDetail = new HouseGoodsDetail();
        WareHouseGoods wareHouseGoods = new WareHouseGoods();
        wareHouseGoods.setGoodsName("测试测试");
        wareHouseGoods.setMerchantId(1129L);
        wareHouseGoods.setWareHouseGoodsId(111L);
        houseGoodsDetail.setGoods(wareHouseGoods);

        /**口味**/
        WarehouseGoodsTastes warehouseGoodsTastes = new WarehouseGoodsTastes();
        WarehouseGoodsTastesGroup warehouseGoodsTastesGroup = new WarehouseGoodsTastesGroup();
        warehouseGoodsTastesGroup.setMerchantId(1120L);
        warehouseGoodsTastesGroup.setTastesGroupName("辣");
        WarehouseGoodsTastesItem warehouseGoodsTastesItem = new WarehouseGoodsTastesItem();
        warehouseGoodsTastesItem.setGoodsTastesName("中");
        List<WarehouseGoodsTastesItem> warehouseGoodsTastesItems = new ArrayList<>();
        warehouseGoodsTastesItems.add(warehouseGoodsTastesItem);
        warehouseGoodsTastesGroup.setGoodsTastesGroupItem(warehouseGoodsTastesItems);
        List<WarehouseGoodsTastesGroup> orderGoodsTastes = new ArrayList<>();
        orderGoodsTastes.add(warehouseGoodsTastesGroup);
        warehouseGoodsTastes.setOrderGoodsTastes(orderGoodsTastes);
        houseGoodsDetail.setGoodsTastes(warehouseGoodsTastes);

        /**图片**/
        GoodsExtPicture goodsExtPicture = new GoodsExtPicture();
        goodsExtPicture.setGoodsId(110L);
        goodsExtPicture.setGoodsPicId(12L);
        goodsExtPicture.setPicUrl("www.baidu.com");
        goodsExtPicture.setPicText("haha");
        goodsExtPicture.setStoreId(1141L);
        List<GoodsExtPicture> goodsExtPictures = new ArrayList<>();
        goodsExtPictures.add(goodsExtPicture);
        houseGoodsDetail.setPicTxtList(goodsExtPictures);

        /**套餐**/
        GoodsComboListVo goodsComboListVo = new GoodsComboListVo();
        goodsComboListVo.setCount(1);
        goodsComboListVo.setGoodsName("套餐");
        goodsComboListVo.setIsForce(1);
        goodsComboListVo.setPrice(BigDecimal.valueOf(100));
        List<GoodsComboListVo> goodsComboListVos = new ArrayList<>();
        goodsComboListVos.add(goodsComboListVo);
        houseGoodsDetail.setGoodsItems(goodsComboListVos);


        /**规格**/
        GoodsSku<WarehouseGoodsSkuGroup, WarehouseGoodsSkuPriceSales> goodsSku = new GoodsSku<>();
        WarehouseGoodsSkuGroup warehouseGoodsSkuGroup = new WarehouseGoodsSkuGroup();
        warehouseGoodsSkuGroup.setSkuGroupName("大小");
        warehouseGoodsSkuGroup.setGoodsSkuGroupId(23L);
        WarehouseGoodsSkuItem warehouseGoodsSkuItem = new WarehouseGoodsSkuItem();
        warehouseGoodsSkuItem.setGoodsSkuGroupId(23L);
        warehouseGoodsSkuItem.setSkuName("中杯");
        List<WarehouseGoodsSkuItem> warehouseGoodsSkuItems = new ArrayList<>();
        warehouseGoodsSkuItems.add(warehouseGoodsSkuItem);
        warehouseGoodsSkuGroup.setGoodsSkuGroupItem(warehouseGoodsSkuItems);
        WarehouseGoodsSkuPriceSales warehouseGoodsSkuPriceSales = new WarehouseGoodsSkuPriceSales();
        warehouseGoodsSkuPriceSales.setSkuItemsValue("中杯");
        warehouseGoodsSkuPriceSales.setSalesPrice(new BigDecimal(12L));
        warehouseGoodsSkuPriceSales.setWarehouseGoodsId(123L);
        List<WarehouseGoodsSkuPriceSales> warehouseGoodsSkuPriceSalesList = new ArrayList<>();
        warehouseGoodsSkuPriceSalesList.add(warehouseGoodsSkuPriceSales);
        GoodsSkuByType<WarehouseGoodsSkuGroup, WarehouseGoodsSkuPriceSales> inGoodsSku = new GoodsSkuByType<>();
        List<WarehouseGoodsSkuGroup> goodsSkuGroup = new ArrayList<>();
        goodsSkuGroup.add(warehouseGoodsSkuGroup);
        inGoodsSku.setGoodsSkuGroup(goodsSkuGroup);
        goodsSku.setOrderInGoodsSku(inGoodsSku);
        inGoodsSku.setGoodsSkuPriceSales(warehouseGoodsSkuPriceSalesList);
        goodsSku.setOrderOutGoodsSku(inGoodsSku);
        houseGoodsDetail.setGoodsSku(goodsSku);

        Map<String, String> skuNamesMap = new HashMap<>();
        skuNamesMap.put("112-11", "辣-中杯");
        houseGoodsDetail.setSkuNamesMap(skuNamesMap);

        houseGoodsDetailCommonResponse.setData(houseGoodsDetail);
        return houseGoodsDetailCommonResponse;
    }


    /**
     * 仓库菜品编辑和新增
     *
     * @return
     */
    @PostMapping("/save")
    public CommonResponse<Void> saveHouseGoods(@RequestBody HouseGoodsItemBean bean) {
        CommonResponse<Void> booleanCommonResponse = new CommonResponse<>();
        booleanCommonResponse.setMessage("菜品保存成功");
        return booleanCommonResponse;
    }


    /**
     * 套餐选择菜品 , 导入所有的菜品列表，以便B端，用于选择需要导入那些菜品。返回菜品列表数据
     * goodsType 0-全部 1-菜品 2-套餐
     *
     * @param
     * @return
     */
    @PostMapping("/selectData")
    public CommonResponse<PageResultBean<SelectedGoodsDto>> selectWarehouseGoods(@RequestBody SelectGoodsRequestVo requestVo) {
        CommonResponse<PageResultBean<SelectedGoodsDto>> pageResultBeanCommonResponse = new CommonResponse<>();
        PageResultBean<SelectedGoodsDto> selectedGoodsDtoPageResultBean=new PageResultBean<>();

        selectedGoodsDtoPageResultBean.setTotalCount(100);
        selectedGoodsDtoPageResultBean.setTotalPage(10);
        selectedGoodsDtoPageResultBean.setPageNum(1);
        selectedGoodsDtoPageResultBean.setPageSize(10);

        SelectedGoodsDto selectedGoodsDto=new SelectedGoodsDto();
        selectedGoodsDto.setGoodsName("么么菜");
        selectedGoodsDto.setId(1L);
        selectedGoodsDto.setImg("www.baidu.com");
        selectedGoodsDto.setPrice(new BigDecimal(12L));
        selectedGoodsDto.setRecmdPriceType(1);
        selectedGoodsDto.setStatus(1);
        selectedGoodsDto.setSuppOrderType(1);

        GoodsSkuPricesVo goodsSkuPricesVo=new GoodsSkuPricesVo();
        goodsSkuPricesVo.setPrice(new BigDecimal(12));
        List<String> nameList=new ArrayList<>();
        nameList.add("规格1");
        nameList.add("规格2");
        nameList.add("规格3");
        goodsSkuPricesVo.setSkuNames(nameList);
        goodsSkuPricesVo.setStockNum(100L);
        List<Long> ids=new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        ids.add(3L);
        goodsSkuPricesVo.setSkuIds(ids);
        List<GoodsSkuPricesVo> orderInSkus = new ArrayList<>();
        orderInSkus.add(goodsSkuPricesVo);
        selectedGoodsDto.setOrderInSkus(orderInSkus);

        List<SelectedGoodsDto> dtoList=new ArrayList<>();
        dtoList.add(selectedGoodsDto);
        dtoList.add(selectedGoodsDto);
        dtoList.add(selectedGoodsDto);
        selectedGoodsDtoPageResultBean.setItems(dtoList);
        pageResultBeanCommonResponse.setData(selectedGoodsDtoPageResultBean);
        return pageResultBeanCommonResponse;
    }


}
