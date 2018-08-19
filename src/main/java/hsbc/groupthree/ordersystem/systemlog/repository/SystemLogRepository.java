package hsbc.groupthree.ordersystem.systemlog.repository;
import hsbc.groupthree.ordersystem.systemlog.entity.SystemLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author:Evan
 * @Date:2018/8/6 13:56
 * @Describe：
 * @Return:
 * @Param:
 */
public interface SystemLogRepository extends JpaRepository<SystemLog, String> {
    @Override
    Page<SystemLog> findAll(Pageable pageable);
}
