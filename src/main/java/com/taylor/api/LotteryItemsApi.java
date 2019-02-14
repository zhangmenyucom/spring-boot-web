package com.taylor.api;

import java.util.List;
import java.util.Map;

import com.taylor.common.Query;
import com.taylor.common.R;
import com.taylor.entity.LotteryItemsEntity;
import com.taylor.service.LotteryItemsService;
import com.taylor.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controller
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2019-02-13 18:52:15
 */
@RestController
@RequestMapping("lotteryitems")
public class LotteryItemsApi  extends BaseApi {
    @Autowired
    private LotteryItemsService lotteryItemsService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<LotteryItemsEntity> lotteryItemsList = lotteryItemsService.queryList(query);
        int total = lotteryItemsService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(lotteryItemsList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        LotteryItemsEntity lotteryItems = lotteryItemsService.queryObject(id);
        return R.ok().put("lotteryItems", lotteryItems);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody LotteryItemsEntity lotteryItems) {
        lotteryItemsService.save(lotteryItems);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody LotteryItemsEntity lotteryItems) {
        lotteryItemsService.update(lotteryItems);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        lotteryItemsService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {
        List<LotteryItemsEntity> list = lotteryItemsService.queryList(params);
        return R.ok().put("list", list);
    }
}
