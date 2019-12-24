package com.eatza.restaurantsearch.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RestaurantRequestDto {

	private String name;
	private String location;
	private String cuisine;
	private int budget;
	private double rating;
	private String activeFrom;
	private String activeTill;

//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getLocation() {
//		return location;
//	}
//
//	public void setLocation(String location) {
//		this.location = location;
//	}
//
//	public String getCuisine() {
//		return cuisine;
//	}
//
//	public void setCuisine(String cuisine) {
//		this.cuisine = cuisine;
//	}
//
//	public int getBudget() {
//		return budget;
//	}
//
//	public void setBudget(int budget) {
//		this.budget = budget;
//	}
//
//	public double getRating() {
//		return rating;
//	}
//
//	public void setRating(double rating) {
//		this.rating = rating;
//	}
//
//	public String getActiveFrom() {
//		return activeFrom;
//	}
//
//	public void setActiveFrom(String activeFrom) {
//		this.activeFrom = activeFrom;
//	}
//
//	public String getActiveTill() {
//		return activeTill;
//	}
//
//	public void setActiveTill(String activeTill) {
//		this.activeTill = activeTill;
//	}
//
//	public RestaurantRequestDto() {
//	}

}
