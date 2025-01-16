package cn.nbmly.party.service.Imp;

import cn.nbmly.party.mapper.LifeMapper;
import cn.nbmly.party.model.Life;
import cn.nbmly.party.model.LifeExample;
import cn.nbmly.party.service.LifeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LifeServiceImp implements LifeService {
    @Autowired
    private LifeMapper lifeMapper;

    @Override
    public void insert(Life life) {
        lifeMapper.insert(life);
    }

    @Override
    public void update(Life life) {
        lifeMapper.updateByPrimaryKey(life);
    }

    @Override
    public void updateContext(Life life) {
        lifeMapper.updateContext(life);
    }

    @Override
    public void delete(Life life) {
        lifeMapper.deleteByPrimaryKey(life.getId());
    }

    @Override
    public List<Life> selectAll() {
        return lifeMapper.selectByExample(null);
    }

    @Override
    public List<Life> selectByBelongTo(Life life) {
        LifeExample lifeExample = new LifeExample();
        LifeExample.Criteria criteria = lifeExample.createCriteria();
        criteria.andBelongtoEqualTo(life.getBelongto());
        return lifeMapper.selectByExample(lifeExample);
    }
}
