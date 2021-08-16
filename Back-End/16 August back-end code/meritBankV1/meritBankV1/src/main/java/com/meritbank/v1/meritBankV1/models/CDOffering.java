package com.meritbank.v1.meritBankV1.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class CDOffering {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cdOffereing_Id;
	private int term;
	private double interestRate;
	
	public CDOffering() {
		
	}

	public CDOffering(int term, double interestRate) {
		super();
		this.term = term;
		this.interestRate = interestRate;
	}

	public int getCdOffereing_Id() {
		return cdOffereing_Id;
	}

	public void setCdOffereing_Id(int cdOffereing_Id) {
		this.cdOffereing_Id = cdOffereing_Id;
	}

	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
}
