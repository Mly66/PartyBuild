package cn.nbmly.party.controller;

import cn.nbmly.party.mapper.ActivityMapper;
import cn.nbmly.party.model.Activity;
import cn.nbmly.party.model.Result;
import cn.nbmly.party.service.ActivityService;
import cn.nbmly.party.service.Imp.ActivityServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping("/activity")
@RestController
public class ActivityController {
    @Autowired
    private ActivityServiceImp activityServiceImp;

    @PostMapping("/insert")
    public Result<String> insert(@RequestBody Activity activity){
        try {
            activityServiceImp.insert(activity);
            return Result.success("新增成功");
        }catch (Exception err){
            return Result.error("新增失败");
        }
    }

    @PostMapping("/update")
    public Result<String> update(@RequestBody Activity activity){
        if(activity.getId() == null){
            return Result.error("缺失相关信息！");
        }
        try {
            System.out.println(activity);
            activityServiceImp.update(activity);
            activityServiceImp.updateContext(activity);
            return Result.success("修改成功");
        }catch (Exception err){
            return Result.error("修改失败");
        }
    }

    @DeleteMapping("/delete")
    public Result<String> delete(@RequestBody Activity activity){
        if(activity.getId() == null){
            return Result.error("缺失相关信息！");
        }
        try {
            activityServiceImp.delete(activity);
            return Result.success("删除成功");
        }catch (Exception err){
            return Result.error("删除失败");
        }
    }


    @PostMapping("/getList")
    public Result<List<Activity>> getList(@RequestBody Activity activity,
                                          @RequestParam(value = "page", defaultValue = "1") int page,
                                          @RequestParam(value = "size", defaultValue = "20") int size){
        if(activity.getBelongto() == null){
            return Result.error("缺失相关信息！");
        }
        List<Activity> activities;
        try {
            activities = activityServiceImp.selectByBelongTo(activity);
//        System.out.println(activities);
        }catch (Exception err){
            return Result.error("查询失败"+err.getMessage());
        }
        Result<List<Activity>> res = Result.success(activities.subList((page-1)*size, Math.min(page*size, activities.size())));
        res.setSize(activities.size());
        return res;
    }
}
