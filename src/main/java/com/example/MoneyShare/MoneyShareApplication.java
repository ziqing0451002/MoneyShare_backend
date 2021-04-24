package com.example.MoneyShare;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class MoneyShareApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoneyShareApplication.class, args);
	}


}
