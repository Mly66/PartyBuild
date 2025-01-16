package cn.nbmly.party.service.Imp;

import cn.nbmly.party.mapper.RoleMapper;
import cn.nbmly.party.model.Role;
import cn.nbmly.party.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> selectAll() {
        return roleMapper.selectByExample(null);
    }
}
