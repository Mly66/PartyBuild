package cn.nbmly.party.service;

import cn.nbmly.party.model.Examination;

import java.util.List;

public interface ExaminationService {
    public void insert(Examination exception);

    public void update(Examination exception);

    public void delete(Examination exception);

    public List<Examination> selectByBelongTo(Examination exception);
}
