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

import com.platform.entity.RewardItemsEntity;
import com.platform.service.RewardItemsService;
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
@RequestMapping("rewarditems")
public class RewardItemsController {
    @Autowired
    private RewardItemsService rewardItemsService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("rewarditems:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<RewardItemsEntity> rewardItemsList = rewardItemsService.queryList(query);
        int total = rewardItemsService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(rewardItemsList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("rewarditems:info")
    public R info(@PathVariable("id") Long id) {
        RewardItemsEntity rewardItems = rewardItemsService.queryObject(id);
        return R.ok().put("rewardItems", rewardItems);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("rewarditems:save")
    public R save(@RequestBody RewardItemsEntity rewardItems) {
        rewardItemsService.save(rewardItems);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("rewarditems:update")
    public R update(@RequestBody RewardItemsEntity rewardItems) {
        rewardItemsService.update(rewardItems);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("rewarditems:delete")
    public R delete(@RequestBody Long[] ids) {
        rewardItemsService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {
        List<RewardItemsEntity> list = rewardItemsService.queryList(params);
        return R.ok().put("list", list);
    }
}
