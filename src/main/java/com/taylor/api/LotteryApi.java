package com.taylor.api;

import java.util.List;
import java.util.Map;

import com.taylor.common.Query;
import com.taylor.common.R;
import com.taylor.entity.LotteryEntity;
import com.taylor.service.LotteryService;
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
@RequestMapping("lottery")
public class LotteryApi  extends BaseApi{
    @Autowired
    private LotteryService lotteryService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
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
    public R info(@PathVariable("id") Long id) {
        LotteryEntity lottery = lotteryService.queryObject(id);
        return R.ok().put("lottery", lottery);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody LotteryEntity lottery) {
        lotteryService.save(lottery);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody LotteryEntity lottery) {
        lotteryService.update(lottery);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long... ids) {
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
