package com.example.ec.repo;

import java.util.Collection;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.ec.domain.Difficulty;
import com.example.ec.domain.Region;
import com.example.ec.domain.Tour;

public interface TourRepository extends CrudRepository< Tour, Integer >{  
	
	List<Tour> findByTourPackageCode( String code);
	
	Tour findByTitle( String title);
	
	List<Tour> findByPrice( Integer price);
	
	Collection<Tour> findByDifficulty( Difficulty difficulty);
	
	List<Tour> findByRegion( Region region);
	
	List<Tour> findByTourPackageCodeAndRegion( String code, Region region);
	
	List<Tour> findByRegionIn( List<Region> regions);
	
	List<Tour> findByPriceLessThan(Integer maxPrice);
	
	List<Tour> findByKeywordsContains(String keyword);
	
	List<Tour> findByTourPackageCodeAndBulletsLike( String code, String searchString);
	
	List<Tour> findByTourPackageCodeAndDifficultyAndRegionAndPriceLessThan( String code,
			Difficulty difficulty, Region region, Integer maxPrice);
}
