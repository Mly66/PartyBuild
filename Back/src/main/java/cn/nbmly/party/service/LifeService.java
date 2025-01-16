package cn.nbmly.party.service;

import cn.nbmly.party.model.Life;

import java.util.List;

public interface LifeService {

    public void insert(Life life);

    public void update(Life life);

    public void updateContext(Life life);

    public void delete(Life life);

    public List<Life> selectAll();

    public List<Life> selectByBelongTo(Life life);
}
