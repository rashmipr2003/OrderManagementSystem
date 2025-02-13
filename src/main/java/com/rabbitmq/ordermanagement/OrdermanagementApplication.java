package com.rabbitmq.ordermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(title = "My API", version = "v1", description = "My API documentation")
)
public class OrdermanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrdermanagementApplication.class, args);
	}
//
//	@Bean
//	public WebClient.Builder getWebClient(){
//		return WebClient.builder();
//	}
}
