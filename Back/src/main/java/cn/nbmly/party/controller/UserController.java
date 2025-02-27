package cn.nbmly.party.controller;

import cn.nbmly.party.service.Imp.UserServiceImp;
import cn.nbmly.party.model.Result;
import cn.nbmly.party.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "*")
@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserServiceImp userServiceImp;

    @PostMapping("/getById")
    public Result<User> getById(@RequestBody User user){
        if(user.getId() == null){
            return Result.error("缺少关键字段!");
        }
        try {
            User user1 = userServiceImp.selectById(user);
            return Result.success(user1);
        }catch (Exception err){
            return Result.error("获取失败！");
        }
    }

    @PostMapping("/login")
    public Result<User> login(@RequestBody User user){
        if(user.getPassword() == null || user.getUsername() == null){
            return Result.error("账号或密码不能为空！");
        }
        user = userServiceImp.login(user);
        if(user == null){
            return Result.error("账号或密码错误！");
        }
        return Result.success(user);
    }

    // 新增
    @PostMapping("/register")
    public Result<User> register(@RequestBody User user){
        if(user.getIdentitycard() == null || user.getUsername() == null){
            return Result.error("省份证号码不能为空！");
        }
        user = userServiceImp.register(user);
        if(user == null){
            return Result.error("当前账号已存在！");
        }
        return Result.success(user);
    }

    @PostMapping("/updateAndInsert")
    public Result updateAndInsert(@RequestBody User user){
        if(user.getId() == null){
            if(user.getIdentitycard() == null || user.getUsername() == null){
                return Result.error("省份证号码不能为空！");
            }
            user = userServiceImp.register(user);
            if(user == null){
                return Result.error("当前账号已存在！");
            }
            return Result.success(user);
        }else{
            try {
                userServiceImp.update(user);
                return Result.success("更新成功！");
            }catch (Exception err){
                return Result.error("更新失败！");
            }
        }
    }

    @PostMapping("/getList")
    public Result<List<User>> getList(@RequestBody User user,
                                      @RequestParam(value = "page", defaultValue = "1") int page,
                                      @RequestParam(value = "size", defaultValue = "20") int size){
        List<User> users;
        if("超级管理员".equals(user.getRole())){
            users = userServiceImp.selectAll();
        }else if("分团委老师".equals(user.getRole()) || "基层支部书记".equals(user.getRole())){
            users = userServiceImp.selectUserByBelongTo(user);
        }else {
            users = new ArrayList<>();
        }
//        if()10,
        Result<List<User>> result = Result.success(users.subList((page - 1) * size, Math.min(page * size, users.size())));
        result.setSize(users.size());
        return result;
    }

    @PostMapping("/update")
    public Result<String> update(@RequestBody User user){
        try {
            userServiceImp.update(user);
            return Result.success("更新成功！");
        }catch (Exception err){
            return Result.error("更新失败！");
        }
    }

    @DeleteMapping("/delete")
    public Result<String> delete(@RequestBody User user){
        try {
            userServiceImp.delete(user);
            return Result.success("删除成功！");
        }catch (Exception err){
            return Result.error("删除失败！");
        }
    }

    @PostMapping("/toBeDeveloped")
    public Result<List<User>> toBeDeveloped(@RequestBody User user){
        return Result.success(userServiceImp.toBeDeveloped(user));
    }

    @PostMapping("/partyMember")
    public Result<List<User>> partyMember(@RequestBody User user){
        return Result.success(userServiceImp.partyMember(user));
    }
}
