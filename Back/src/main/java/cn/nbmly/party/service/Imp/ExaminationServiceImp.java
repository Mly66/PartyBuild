package cn.nbmly.party.service.Imp;

import cn.nbmly.party.mapper.ExaminationMapper;
import cn.nbmly.party.model.Examination;
import cn.nbmly.party.model.ExaminationExample;
import cn.nbmly.party.service.ExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExaminationServiceImp implements ExaminationService {
    @Autowired
    private ExaminationMapper examinationMapper;

    @Override
    public void insert(Examination exception) {
        examinationMapper.insert(exception);
    }

    @Override
    public void update(Examination exception) {
        examinationMapper.updateByPrimaryKey(exception);
    }

    @Override
    public void delete(Examination exception) {
        examinationMapper.deleteByPrimaryKey(exception.getId());
    }

    @Override
    public List<Examination> selectByBelongTo(Examination examination) {
//        ExaminationExample examinationExample = new ExaminationExample();
//        ExaminationExample.Criteria criteria = examinationExample.createCriteria();
//        criteria.andBelongtoEqualTo(examination.getBelongto());
//        return examinationMapper.selectByExample(examinationExample);
        return examinationMapper.selectByBelong(examination);
    }
}
