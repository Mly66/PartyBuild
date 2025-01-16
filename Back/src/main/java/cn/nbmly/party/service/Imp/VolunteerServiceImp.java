package cn.nbmly.party.service.Imp;

import cn.nbmly.party.mapper.VolunteerMapper;
import cn.nbmly.party.model.Volunteer;
import cn.nbmly.party.model.VolunteerExample;
import cn.nbmly.party.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VolunteerServiceImp implements VolunteerService {
    @Autowired
    private VolunteerMapper volunteerMapper;

    @Override
    public void insert(Volunteer volunteer) {
        volunteerMapper.insert(volunteer);
    }

    @Override
    public void update(Volunteer volunteer) {
        volunteerMapper.updateByPrimaryKey(volunteer);
    }

    @Override
    public void delete(Volunteer volunteer) {
        volunteerMapper.deleteByPrimaryKey(volunteer.getId());
    }

    @Override
    public List<Volunteer> getList(Volunteer volunteer) {
        VolunteerExample volunteerExample = new VolunteerExample();
        VolunteerExample.Criteria criteria = volunteerExample.createCriteria();
        criteria.andBelongtoEqualTo(volunteer.getBelongto());
        return volunteerMapper.selectByExample(volunteerExample);
    }
}
