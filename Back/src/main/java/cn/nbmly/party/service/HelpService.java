package cn.nbmly.party.service;

import cn.nbmly.party.model.Help;

import java.util.List;

public interface HelpService {
    public void insert(Help help);

    public void update(Help help);

    public void delete(Help help);

    public List<Help> selectAll();

    public List<Help> selectByBelongTo(Help help);
}
