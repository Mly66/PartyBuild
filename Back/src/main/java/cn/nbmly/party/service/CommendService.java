package cn.nbmly.party.service;

import cn.nbmly.party.model.Commend;

import java.util.List;

public interface CommendService {
    public void insert(Commend commend);

    public void update(Commend commend);

    public void delete(Commend commend);

    public List<Commend> selectAll();

    public List<Commend> selectByParty(Commend commend);
}
