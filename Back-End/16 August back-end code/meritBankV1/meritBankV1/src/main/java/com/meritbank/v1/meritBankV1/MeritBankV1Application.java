package com.meritbank.v1.meritBankV1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.meritbank.v1.meritBankV1.models.CDOffering;
import com.meritbank.v1.meritBankV1.repos.CDOfferingRepository;

@SpringBootApplication
public class MeritBankV1Application implements CommandLineRunner{

	@Autowired
	CDOfferingRepository cdOfferingRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(MeritBankV1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		
		CDOffering offering1 = new CDOffering(1, 0.05);
				
		CDOffering offering2 = new CDOffering(5, 0.1);
		
		CDOffering offering3 = new CDOffering(10, 0.2);
		
		cdOfferingRepository.save(offering1);
		cdOfferingRepository.save(offering2);
		cdOfferingRepository.save(offering3);
	}
	
}
