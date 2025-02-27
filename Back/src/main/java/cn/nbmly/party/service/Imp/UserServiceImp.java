package cn.nbmly.party.service.Imp;

import cn.nbmly.party.service.UserService;
import cn.nbmly.party.mapper.UserMapper;
import cn.nbmly.party.model.User;
import cn.nbmly.party.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserMapper userMapper;

    public User login(User user){
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(user.getUsername());
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() == 0){
            return null;
        }
        if (users.get(0).getPassword().equals(user.getPassword())){
            user = users.get(0);
            user.setPassword(null);
            return user;
        }
        return null;
    }

    public User register(User user){
        UserExample userExample = new UserExample();
        boolean flag = true;
        {
            UserExample.Criteria criteria = userExample.createCriteria();
            criteria.andIdentitycardEqualTo(user.getIdentitycard());
            List<User> users = userMapper.selectByExample(userExample);
            if (users.size() != 0) {
                flag = false;
            }
        }
        {
            UserExample.Criteria criteria = userExample.createCriteria();
            criteria.andUsernameEqualTo(user.getUsername());
            List<User> users = userMapper.selectByExample(userExample);
            if (users.size() != 0) {
                flag = false;
            }
        }
        if (! flag){
            return null;
        }
        userMapper.insert(user);
        user.setPassword(null);
        return user;
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectByExample(null);
    }

    @Override
    public List<User> selectUserByBelongTo(User user) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andBelongtoEqualTo(user.getBelongto());
        return userMapper.selectByExample(userExample);
    }

    @Override
    public void update(User user) {
        UserExample userExample = new UserExample();
        {
            UserExample.Criteria criteria = userExample.createCriteria();
            criteria.andUsernameEqualTo(user.getUsername());
            List<User> users = userMapper.selectByExample(userExample);
            if (users.size() != 0 && user.getId() != users.get(0).getId()) {
//                user.setUsername(users.get(0).getUsername());
                throw new RuntimeException("姓名存在！");
            }
        }
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public void delete(User user) {
        userMapper.deleteByPrimaryKey(user.getId());
    }

    @Override
    public List<User> toBeDeveloped(User user) {
        return userMapper.toBeDeveloped(user);
    }

    @Override
    public List<User> partyMember(User user) {
        return userMapper.partyMember(user);
    }

    @Override
    public User selectById(User user) {
        return userMapper.selectByPrimaryKey(user.getId());
    }
}
