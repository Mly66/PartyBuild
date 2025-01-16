package cn.nbmly.party.service.Imp;

import cn.nbmly.party.mapper.TransferMapper;
import cn.nbmly.party.model.Transfer;
import cn.nbmly.party.model.TransferExample;
import cn.nbmly.party.model.UserExample;
import cn.nbmly.party.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferServiceImp implements TransferService {
    @Autowired
    private TransferMapper transferMapper;

    @Override
    public void insert(Transfer transfer) {
        transferMapper.insert(transfer);
    }

    @Override
    public List<Transfer> selectByFrom(Transfer transfer) {
        TransferExample transferExample = new TransferExample();
        TransferExample.Criteria criteria = transferExample.createCriteria();
        criteria.andFrombelongtoEqualTo(transfer.getFrombelongto());
        return transferMapper.selectByExample(transferExample);
    }

    @Override
    public List<Transfer> pendingApproval(Transfer transfer) {
        TransferExample transferExample = new TransferExample();
        TransferExample.Criteria criteria = transferExample.createCriteria();
        criteria.andTobelongtoEqualTo(transfer.getTobelongto());
        criteria.andStatusEqualTo("待审批");
        return transferMapper.selectByExample(transferExample);
    }

    @Override
    public List<Transfer> selectAll() {
        return transferMapper.selectByExample(null);
    }

    @Override
    public void updateStatus(Transfer transfer) {
        transferMapper.updateByPrimaryKey(transfer);
    }


}
