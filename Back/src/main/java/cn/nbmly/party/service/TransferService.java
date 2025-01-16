package cn.nbmly.party.service;

import cn.nbmly.party.model.Transfer;

import java.util.List;

public interface TransferService {
    public void insert(Transfer transfer);

    public List<Transfer> selectByFrom(Transfer transfer);

    public List<Transfer> pendingApproval(Transfer transfer);

    public List<Transfer> selectAll();

    public void updateStatus(Transfer transfer);
}
