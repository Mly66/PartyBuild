package cn.nbmly.party.service.Imp;

import cn.nbmly.party.mapper.InterviewMapper;
import cn.nbmly.party.model.Interview;
import cn.nbmly.party.model.InterviewExample;
import cn.nbmly.party.service.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterviewServiceImp implements InterviewService {
    @Autowired
    private InterviewMapper interviewMapper;

    @Override
    public void insert(Interview interview) {
        interviewMapper.insert(interview);
    }

    @Override
    public void delete(Interview interview) {
        interviewMapper.deleteByPrimaryKey(interview.getId());
    }

    @Override
    public void update(Interview interview) {
        interviewMapper.updateByPrimaryKey(interview);
    }

    @Override
    public List<Interview> selectByBelongTo(Interview interview) {
        InterviewExample interviewExample = new InterviewExample();
        InterviewExample.Criteria criteria = interviewExample.createCriteria();
        criteria.andBelongtoEqualTo(interview.getBelongto());
        return interviewMapper.selectByExample(interviewExample);
    }
}
