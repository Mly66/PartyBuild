package cn.nbmly.party.service;

import cn.nbmly.party.model.Cost;

import java.util.List;

public interface CostService {
    public void insert(Cost cost);

    public void updateStatus(Cost cost);

    public List<Cost> selectByStatusAndBelongTo(Cost cost);
}
