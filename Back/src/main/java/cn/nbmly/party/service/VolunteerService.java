package cn.nbmly.party.service;

import cn.nbmly.party.model.Volunteer;

import java.util.List;

public interface VolunteerService {
    public void insert(Volunteer volunteer);

    public void update(Volunteer volunteer);

    public void delete(Volunteer volunteer);

    public List<Volunteer> getList(Volunteer volunteer);
}
