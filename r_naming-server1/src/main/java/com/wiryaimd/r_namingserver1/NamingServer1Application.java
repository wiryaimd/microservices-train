package com.wiryaimd.r_namingserver1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class NamingServer1Application {

	public static void main(String[] args) {
		SpringApplication.run(NamingServer1Application.class, args);
	}

}
