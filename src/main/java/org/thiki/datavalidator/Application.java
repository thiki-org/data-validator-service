package org.thiki.datavalidator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@SpringBootApplication
@RestController
@EnableAutoConfiguration
@ComponentScan
public class Application {

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.out.println("启动成功...........");
	}
}
