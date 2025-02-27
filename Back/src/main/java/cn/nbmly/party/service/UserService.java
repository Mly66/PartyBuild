package cn.nbmly.party.service;

import cn.nbmly.party.model.User;

import java.util.List;

public interface UserService {
    public User login(User user);

    public User register(User user);

    public List<User> selectAll();

    public List<User> selectUserByBelongTo(User user);

    public void update(User user);

    public void delete(User user);

    public List<User> toBeDeveloped(User user);

    public List<User> partyMember(User user);

    public User selectById(User user);
}
