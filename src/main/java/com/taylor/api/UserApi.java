package com.taylor.api;

import com.taylor.annotation.Permission;
import com.taylor.common.Query;
import com.taylor.common.R;
import com.taylor.entity.UserEntity;
import com.taylor.service.UserService;
import com.taylor.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.taylor.common.UserLevelEnum.PLAT_FORM;
import static com.taylor.common.UserLevelEnum.VIP;


/**
 * Controller
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2019-02-13 18:52:15
 */
@RestController
@RequestMapping("/api/user")
public class UserApi extends BaseApi {
    @Autowired
    private UserService userService;

    /**
     * 查看列表
     */
    @PostMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<UserEntity> userList = userService.queryList(query);
        int total = userService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(userList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        UserEntity user = userService.queryObject(id);
        return R.ok().put("user", user);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody UserEntity user) {
        userService.save(user);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @Permission({VIP, PLAT_FORM})
    public R update(@RequestBody UserEntity user) {
        userService.update(user);
        return R.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @Permission(PLAT_FORM)
    public R delete(@RequestBody Long... ids) {
        userService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @PostMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {
        List<UserEntity> list = userService.queryList(params);
        return R.ok().put("list", list);
    }
}
