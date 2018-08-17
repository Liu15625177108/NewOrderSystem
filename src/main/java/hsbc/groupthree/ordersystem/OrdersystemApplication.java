package hsbc.groupthree.ordersystem;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
/**open transaction*/
@EnableTransactionManagement
public class OrdersystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrdersystemApplication.class, args);
    }
}
