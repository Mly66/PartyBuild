package cn.nbmly.party.service.Imp;

import cn.nbmly.party.mapper.ActivityMapper;
import cn.nbmly.party.model.Activity;
import cn.nbmly.party.model.ActivityExample;
import cn.nbmly.party.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImp implements ActivityService {
    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public void updateContext(Activity activity) {
        activityMapper.updateContext(activity);
    }

    @Override
    public void insert(Activity activity) {
        activityMapper.insert(activity);
    }

    @Override
    public void update(Activity activity) {
        activityMapper.updateByPrimaryKey(activity);
    }



    @Override
    public void delete(Activity activity) {
        activityMapper.deleteByPrimaryKey(activity.getId());
    }

    @Override
    public List<Activity> selectAll() {
        return activityMapper.selectByExample(null);
    }

    @Override
    public List<Activity> selectByBelongTo(Activity activity) {
//        ActivityExample activityExample = new ActivityExample();
//        ActivityExample.Criteria criteria = activityExample.createCriteria();
//        criteria.andBelongtoEqualTo(activity.getBelongto());
//        System.out.println(activityMapper.selectByExample(activityExample));
        return activityMapper.selectByBelong(activity);
    }
}
