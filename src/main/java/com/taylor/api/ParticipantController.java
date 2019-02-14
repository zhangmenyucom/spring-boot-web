package com.taylor.api;

import java.util.List;
import java.util.Map;

import com.taylor.common.Query;
import com.taylor.common.R;
import com.taylor.entity.ParticipantEntity;
import com.taylor.service.ParticipantService;
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
@RequestMapping("participant")
public class ParticipantController {
    @Autowired
    private ParticipantService participantService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("participant:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<ParticipantEntity> participantList = participantService.queryList(query);
        int total = participantService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(participantList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("participant:info")
    public R info(@PathVariable("id") Long id) {
        ParticipantEntity participant = participantService.queryObject(id);
        return R.ok().put("participant", participant);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("participant:save")
    public R save(@RequestBody ParticipantEntity participant) {
        participantService.save(participant);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("participant:update")
    public R update(@RequestBody ParticipantEntity participant) {
        participantService.update(participant);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("participant:delete")
    public R delete(@RequestBody Long... ids) {
        participantService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {
        List<ParticipantEntity> list = participantService.queryList(params);
        return R.ok().put("list", list);
    }
}
