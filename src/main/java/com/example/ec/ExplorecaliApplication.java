package com.example.ec;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.ec.domain.Region;
import com.example.ec.domain.Difficulty;
import com.example.ec.domain.TourPackage;
import com.example.ec.services.TourPackageService;
import com.example.ec.services.TourService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import static com.example.ec.ExplorecaliApplication.TourFromFile.importTours;

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
		//Create the default tour packages
		tourPackageService.createTourPackage("BC", "Backpack Cal");
		tourPackageService.createTourPackage("CC", "California Calm");
		tourPackageService.createTourPackage("CH", "California Hot springs");
		tourPackageService.createTourPackage("CY", "Cycle California");
		tourPackageService.createTourPackage("DS", "From Desert to Sea");
		tourPackageService.createTourPackage("KC", "Kids California");
		tourPackageService.createTourPackage("NW", "Nature Watch");
		tourPackageService.createTourPackage("SC", "Snowboard Cali");
		tourPackageService.createTourPackage("TC", "Taste of California");
		tourPackageService.lookup().forEach(tourPackage -> System.out.println(tourPackage));
		importTours().forEach(t->tourService.createTour( t.title, t.description, t.blurb, Integer.parseInt(t.price),
				t.length, t.bullets, t.keywords, t.packageType, Difficulty.valueOf(t.difficulty), Region.findByLabel(t.region) ));
		
		System.out.println("Number of tours= "+ tourService.total());
	}

	static class TourFromFile{
		
		private String packageType, title, description, blurb,
					price, length, bullets, keywords, difficulty, region;
		
		static List<TourFromFile> importTours() throws IOException {
			return new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY).
					readValue(TourFromFile.class.getResourceAsStream("/ExploreCalifornia.json"),
							new TypeReference<List<TourFromFile>>() {});
		}
	}
}
 