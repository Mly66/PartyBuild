package cn.nbmly.party.service.Imp;

import cn.nbmly.party.mapper.StudyMapper;
import cn.nbmly.party.model.Study;
import cn.nbmly.party.model.StudyExample;
import cn.nbmly.party.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudyServiceImp implements StudyService {
    @Autowired
    private StudyMapper studyMapper;

    @Override
    public void insert(Study study) {
        studyMapper.insert(study);
    }

    @Override
    public void update(Study study) {
        studyMapper.updateByPrimaryKey(study);
    }

    @Override
    public void delete(Study study) {
        studyMapper.deleteByPrimaryKey(study.getId());
    }

    @Override
    public List<Study> selectByBelongTo(Study study) {
//        StudyExample studyExample = new StudyExample();
//        StudyExample.Criteria criteria = studyExample.createCriteria();
//        criteria.andBelongtoEqualTo(study.getBelongto());
        return studyMapper.selectByBelong(study);
    }

    @Override
    public List<Study> selectAll(Study study) {
        return studyMapper.selectByExample(null);
    }
}
