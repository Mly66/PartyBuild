package cn.nbmly.party.service;

import cn.nbmly.party.model.Study;

import java.util.List;

public interface StudyService {
    public void insert(Study study);

    public void update(Study study);

    public void delete(Study study);

    public List<Study> selectByBelongTo(Study study);

    public List<Study> selectAll(Study study);
}
