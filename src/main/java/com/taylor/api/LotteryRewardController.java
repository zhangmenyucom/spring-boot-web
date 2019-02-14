package com.taylor.api;

import java.util.List;
import java.util.Map;

import com.taylor.common.Query;
import com.taylor.common.R;
import com.taylor.entity.LotteryRewardEntity;
import com.taylor.service.LotteryRewardService;
import com.taylor.utils.PageUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
@RequestMapping("lotteryreward")
public class LotteryRewardController {
    @Autowired
    private LotteryRewardService lotteryRewardService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("lotteryreward:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<LotteryRewardEntity> lotteryRewardList = lotteryRewardService.queryList(query);
        int total = lotteryRewardService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(lotteryRewardList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("lotteryreward:info")
    public R info(@PathVariable("id") Long id) {
        LotteryRewardEntity lotteryReward = lotteryRewardService.queryObject(id);
        return R.ok().put("lotteryReward", lotteryReward);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("lotteryreward:save")
    public R save(@RequestBody LotteryRewardEntity lotteryReward) {
        lotteryRewardService.save(lotteryReward);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("lotteryreward:update")
    public R update(@RequestBody LotteryRewardEntity lotteryReward) {
        lotteryRewardService.update(lotteryReward);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("lotteryreward:delete")
    public R delete(@RequestBody Long... ids) {
        lotteryRewardService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {
        List<LotteryRewardEntity> list = lotteryRewardService.queryList(params);
        return R.ok().put("list", list);
    }
}
