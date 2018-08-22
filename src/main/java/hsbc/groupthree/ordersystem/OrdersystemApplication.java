package hsbc.groupthree.ordersystem;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication/*(exclude = SecurityAutoConfiguration.class)*/

/**open transaction*/
@EnableTransactionManagement
@EnableScheduling
public class OrdersystemApplication {

    public static void main(String[] args) {

        SpringApplication.run(OrdersystemApplication.class, args);
    }
}
