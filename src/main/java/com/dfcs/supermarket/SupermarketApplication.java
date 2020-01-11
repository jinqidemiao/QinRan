package com.dfcs.supermarket;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 超市应用程序
 *
 * @author caoxiny
 * @date 2020/01/11
 */
@SpringBootApplication
@MapperScan("com.dfcs.supermarket.main.mapper")
public class SupermarketApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SupermarketApplication.class, args);
	}
	
}
