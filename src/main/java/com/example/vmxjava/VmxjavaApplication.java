package com.example.vmxjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example.vmxjava")
public class VmxjavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(VmxjavaApplication.class, args);
	}

}
