package com.taylor.controller;

import java.util.List;
import java.util.Map;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.platform.entity.LotteryEntity;
import com.platform.service.LotteryService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;

/**
 * Controller
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2019-02-13 18:52:15
 */
@RestController
@RequestMapping("lottery")
public class LotteryController {
    @Autowired
    private LotteryService lotteryService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("lottery:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<LotteryEntity> lotteryList = lotteryService.queryList(query);
        int total = lotteryService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(lotteryList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("lottery:info")
    public R info(@PathVariable("id") Long id) {
        LotteryEntity lottery = lotteryService.queryObject(id);
        return R.ok().put("lottery", lottery);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("lottery:save")
    public R save(@RequestBody LotteryEntity lottery) {
        lotteryService.save(lottery);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("lottery:update")
    public R update(@RequestBody LotteryEntity lottery) {
        lotteryService.update(lottery);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("lottery:delete")
    public R delete(@RequestBody Long[] ids) {
        lotteryService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {
        List<LotteryEntity> list = lotteryService.queryList(params);
        return R.ok().put("list", list);
    }
}
