package hsbc.groupthree.ordersystem.manager.repository;

import hsbc.groupthree.ordersystem.manager.entity.ManagerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName ManagerLoginRepository
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package hsbc.groupthree.ordersystem.manager.repository
 * @Date 2018/8/20 17:15
 */
public interface ManagerLoginRepository extends JpaRepository<ManagerInfo,String> {
    /**
     *@Author Jerry.Liu
     *@Description:// find the manager's information through  workerNum;
     *@Parameter
     *@Date:16:47 2018/8/20
     *@Package: hsbc.groupthree.ordersystem.manager.repository
     */
    public ManagerInfo findOneByWorkerNum(String workerNum);
}
