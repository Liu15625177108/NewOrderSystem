package hsbc.groupthree.ordersystem.commons.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CommonsUtils {

	//produce uuid method
	public  String getUUID(){
		return UUID.randomUUID().toString();
	}
	
}
