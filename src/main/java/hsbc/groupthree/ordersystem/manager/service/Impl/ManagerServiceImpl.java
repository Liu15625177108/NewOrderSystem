package hsbc.groupthree.ordersystem.manager.service.Impl;

import hsbc.groupthree.ordersystem.manager.entity.ManagerInfo;
import hsbc.groupthree.ordersystem.manager.repository.ManagerRepository;
import hsbc.groupthree.ordersystem.manager.service.ManagerService;
import hsbc.groupthree.ordersystem.result.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName ManagerServiceImpl
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package hsbc.groupthree.ordersystem.manager.service.Impl
 * @Date 2018/8/17 11:34
 */
@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
   private ManagerRepository managerRepository;
    @Override
    public boolean login(String workerNum, String password) {
        ManagerInfo managerInfo=managerRepository.findOneByWorkerNum(workerNum);
        if(managerInfo.getLoginPassword().equals(password)) {
            return true;
        }
        return false;
    }

    @Override
    public ResultInfo findByworkerNum(String workerNum) {
        ManagerInfo managerInfo=new ManagerInfo();
        managerInfo=managerRepository.findOneByWorkerNum(workerNum);
        return new ResultInfo(200,"success",managerInfo);
    }

}
