package cn.nbmly.party.service.Imp;

import cn.nbmly.party.mapper.ExpandMapper;
import cn.nbmly.party.mapper.UserMapper;
import cn.nbmly.party.model.Expand;
import cn.nbmly.party.model.User;
import cn.nbmly.party.model.UserExample;
import cn.nbmly.party.service.ExpandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExpandServiceImp implements ExpandService {
    @Autowired
    private ExpandMapper expandMapper;

    @Autowired
    private UserMapper userMapper;


    @Override
    public void insert(Expand expand) {
        expandMapper.insert(expand);
    }

    @Override
    public void delete(Expand expand) {
        expandMapper.deleteByPrimaryKey(expand.getId());
    }

    @Override
    public List<Map> selectByBelongTo(String belongTo) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andBelongtoEqualTo(belongTo);
        List<User> users = userMapper.selectByExample(userExample);
        List<Expand> expands = expandMapper.selectByExample(null);
        List<Map> res = new ArrayList<>();
        Map<Integer, User> map = new HashMap<>();
        for (User user: users){
            map.put(user.getId(), user);
        }
        for (Expand expand : expands){
            Integer id = expand.getFrom();
            User user = map.get(id);
            if (user == null){
                continue;
            }
            Map<String, Object> tmp = new HashMap<>();
            tmp.put("id", expand.getId());
            tmp.put("from", map.get(expand.getFrom()));
            tmp.put("to", map.get(expand.getTo()));
            tmp.put("createTime", expand.getCreatetime());
            res.add(tmp);
        }

        return res;
    }


    @Override
    public List<Map> selectAll() {
        List<User> users = userMapper.selectByExample(null);
        List<Expand> expands = expandMapper.selectByExample(null);
        List<Map> res = new ArrayList<>();
        Map<Integer, User> map = new HashMap<>();
        for (User user: users){
            map.put(user.getId(), user);
        }
        for (Expand expand : expands){
            Integer id = expand.getFrom();
            User user = map.get(id);
            if (user == null){
                continue;
            }
            Map<String, Object> tmp = new HashMap<>();
            tmp.put("id", expand.getId());
            tmp.put("from", map.get(expand.getFrom()));
            tmp.put("to", map.get(expand.getTo()));
            tmp.put("createTime", expand.getCreatetime());
            res.add(tmp);
        }

        return res;
    }
}
