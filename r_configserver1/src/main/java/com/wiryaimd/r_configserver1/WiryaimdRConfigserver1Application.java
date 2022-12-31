package com.wiryaimd.r_configserver1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer // agar dapat mengakses endpoint file konfi properties yg ada pada git-local-config
@SpringBootApplication
public class WiryaimdRConfigserver1Application {

	public static void main(String[] args) {
		SpringApplication.run(WiryaimdRConfigserver1Application.class, args);
	}

}
