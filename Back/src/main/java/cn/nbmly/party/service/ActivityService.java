package cn.nbmly.party.service;

import cn.nbmly.party.model.Activity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ActivityService {
    public void updateContext(Activity activity);

    public void insert(Activity activity);

    public void update(Activity activity);

    public void delete(Activity activity);

    public List<Activity> selectAll();

    public List<Activity> selectByBelongTo(Activity activity);
}
