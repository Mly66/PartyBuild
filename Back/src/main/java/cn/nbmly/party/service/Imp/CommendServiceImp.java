package cn.nbmly.party.service.Imp;

import cn.nbmly.party.mapper.CommendMapper;
import cn.nbmly.party.model.Commend;
import cn.nbmly.party.model.CommendExample;
import cn.nbmly.party.service.CommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommendServiceImp implements CommendService {
    @Autowired
    private CommendMapper commendMapper;

    @Override
    public void insert(Commend commend) {
        commendMapper.insert(commend);
    }

    @Override
    public void update(Commend commend) {
        commendMapper.updateByPrimaryKey(commend);
    }

    @Override
    public void delete(Commend commend) {
        commendMapper.deleteByPrimaryKey(commend.getId());
    }

    @Override
    public List<Commend> selectAll() {
        return commendMapper.selectByExample(null);
    }

    @Override
    public List<Commend> selectByParty(Commend commend) {
        CommendExample commendExample = new CommendExample();
        CommendExample.Criteria criteria = commendExample.createCriteria();
        criteria.andPartynameEqualTo(commend.getPartyname());
        return commendMapper.selectByExample(commendExample);
    }
}
