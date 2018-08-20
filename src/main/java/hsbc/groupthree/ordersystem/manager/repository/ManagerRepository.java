package hsbc.groupthree.ordersystem.manager.repository;

import hsbc.groupthree.ordersystem.manager.entity.ManagerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName ManagerRepository
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package hsbc.groupthree.ordersystem.manager.repository
 * @Date 2018/8/16 23:20
 */
public interface ManagerRepository extends JpaRepository<ManagerInfo,String> {
    public ManagerInfo findOneByWorkerNum(String workerNum);
}
