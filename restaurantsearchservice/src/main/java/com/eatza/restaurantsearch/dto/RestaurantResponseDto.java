package com.eatza.restaurantsearch.dto;

import java.util.List;

import com.eatza.restaurantsearch.model.Restaurant;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RestaurantResponseDto {

	List<Restaurant> restaurants;
	int totalPages;
	long totalElements;

	public RestaurantResponseDto(List<Restaurant> restaurants, int totalPages, long totalElements) {
		super();
		this.restaurants = restaurants;
		this.totalPages = totalPages;
		this.totalElements = totalElements;
	}

//	public List<Restaurant> getRestaurants() {
//		return restaurants;
//	}
//
//	public void setRestaurants(List<Restaurant> restaurants) {
//		this.restaurants = restaurants;
//	}
//
//	public int getTotalPages() {
//		return totalPages;
//	}
//
//	public void setTotalPages(int totalPages) {
//		this.totalPages = totalPages;
//	}
//
//	public long getTotalElements() {
//		return totalElements;
//	}
//
//	public void setTotalElements(long totalElements) {
//		this.totalElements = totalElements;
//	}
//
//	public RestaurantResponseDto() {
//	}

}
