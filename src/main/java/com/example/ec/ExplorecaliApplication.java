package com.example.ec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.ec.services.TourPackageService;
import com.example.ec.services.TourService;

@SpringBootApplication
public class ExplorecaliApplication implements CommandLineRunner{

	@Autowired
	private TourPackageService tourPackageService;
	
	@Autowired
	private TourService tourService;
	
	public static void main(String[] args) {
		SpringApplication.run(ExplorecaliApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		 
	}
}
 