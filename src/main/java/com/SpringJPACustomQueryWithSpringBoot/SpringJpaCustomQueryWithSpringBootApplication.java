package com.SpringJPACustomQueryWithSpringBoot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.SpringJPACustomQueryWithSpringBoot.model.Tutorial;
import com.SpringJPACustomQueryWithSpringBoot.repository.EntityRepo;
import com.SpringJPACustomQueryWithSpringBoot.repository.TutorialRepository;

@SpringBootApplication

public class SpringJpaCustomQueryWithSpringBootApplication implements CommandLineRunner {

	@Autowired
	private TutorialRepository tutorialRepository;

	@Autowired
	private EntityRepo entityRepo;

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaCustomQueryWithSpringBootApplication.class, args);
	}

	public Date RandDateGen() {
		Random rand = new Random();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2023);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, rand.nextInt(31) + 1);
		return cal.getTime();
	}

	@Override
	public void run(String... args) throws Exception {

		/*
		 * entityRepo.deleteAll(); entityRepo.save(new Tutorial("Java Spring",
		 * "Tut#2 Description", 1, false, RandDateGen())); entityRepo.save(new
		 * Tutorial("Hibernate", "Tut#3 Description", 2, false, RandDateGen()));
		 * entityRepo.save(new Tutorial("Spring Boot", "Tut#4 Description", 3, false,
		 * RandDateGen())); entityRepo.save(new Tutorial("Spring Data JPA",
		 * "Tut#5 Description", 4, false, RandDateGen())); entityRepo.save(new
		 * Tutorial("JPA EntityManager", "Tut#6 Description", 5, false, RandDateGen()));
		 * entityRepo.save(new Tutorial("Spring Security", "Tut#7 Description", 6,
		 * false, RandDateGen()));
		 */

		List<Tutorial> tutorials = new ArrayList<>();

		/*
		 * tutorials = tutorialRepository.findAll(); show(tutorials);
		 * 
		 * tutorials = tutorialRepository.findByLevelGreaterThanEqual(3);
		 * show(tutorials);
		 * 
		 * Date myDate = new SimpleDateFormat("yyyy-MM-dd").parse("2023-01-22");
		 * 
		 * tutorials = tutorialRepository.findByDateGreaterThanEqual(myDate);
		 * show(tutorials);
		 */

		/*
		 * tutorials = tutorialRepository.findByTitleAndSort("a",
		 * Sort.by("level").descending()); show(tutorials);
		 * 
		 * tutorials = tutorialRepository.findByTitleAndSort("",
		 * Sort.by("createdAt").descending()); show(tutorials);
		 */

		int page = 0;
		int size = 6;

		Pageable pageable = PageRequest.of(page, size);
		/*
		 * tutorials = tutorialRepository.findAllWithPagination(pageable).getContent();
		 * show(tutorials);
		 */

		pageable = PageRequest.of(page, size, Sort.by("level").descending());

		tutorials = tutorialRepository.findAllWithPagination(pageable).getContent();
		show(tutorials);

	}

	private void show(List<Tutorial> tutorials) {
		tutorials.forEach(System.out::println);
	}

}
