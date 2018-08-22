package hsbc.groupthree.ordersystem.user.repository;

import hsbc.groupthree.ordersystem.user.entity.RoleInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Jeff.Li
 * @Package: hsbc.groupthree.ordersystem.user.repository
 * @Program: NewOrderSystem
 * @Description: todo
 * @date : 2018年08月21日 16:24:19
 **/
public interface RoleInfoRepository extends JpaRepository<RoleInfo,String> {

    RoleInfo findRoleInfoByRoleId(String roleId);
}
