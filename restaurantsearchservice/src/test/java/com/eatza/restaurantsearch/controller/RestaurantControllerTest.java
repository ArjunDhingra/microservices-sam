package com.eatza.restaurantsearch.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.eatza.restaurantsearch.dto.RestaurantRequestDto;
import com.eatza.restaurantsearch.dto.RestaurantResponseDto;
import com.eatza.restaurantsearch.exception.RestaurantBadRequestException;
import com.eatza.restaurantsearch.exception.RestaurantNotFoundException;
import com.eatza.restaurantsearch.model.Menu;
import com.eatza.restaurantsearch.model.MenuItem;
import com.eatza.restaurantsearch.model.Restaurant;
import com.eatza.restaurantsearch.service.restaurantservice.RestaurantService;

@RunWith(MockitoJUnitRunner.class)
public class RestaurantControllerTest {

	@InjectMocks
	private RestaurantController restaurantController;

	@Mock
	private RestaurantService restaurantService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getAllRestaurants_basic() throws Exception {
		List<Restaurant> restaurants = getRestaurants();
		String authorization = "t0k3n";
		RestaurantResponseDto responseDto = new RestaurantResponseDto(restaurants, 2, 20);
		Mockito.when(restaurantService.findAllRestaurants(any(Integer.class), any(Integer.class)))
				.thenReturn(responseDto);
		assertEquals(responseDto, restaurantController.getAllRestaurants(authorization, 1, 10).getBody());
	}

	@Test(expected = RestaurantBadRequestException.class)
	public void getAllRestaurants_zeroPage() {
		String authorization = "t0k3n";
		restaurantController.getAllRestaurants(authorization, 0, 10);
	}

	@Test(expected = RestaurantNotFoundException.class)
	public void getAllRestaurants_empty() throws RestaurantNotFoundException {
		String authorization = "t0k3n";
		List<Restaurant> returnedRestaurants = Arrays.asList();
		RestaurantResponseDto responseDto = new RestaurantResponseDto(returnedRestaurants, 1, 1);
		Mockito.when(restaurantService.findAllRestaurants(Mockito.any(Integer.class), Mockito.any(Integer.class)))
				.thenReturn(responseDto);
		restaurantController.getAllRestaurants(authorization, 1, 10);
	}

	@Test
	public void addRestaurant() throws Exception {
		RestaurantRequestDto requestDto = getRestaurantRequest();
		String authorization = "t0K3n";
		Mockito.when(restaurantService.saveRestaurant(Mockito.any(RestaurantRequestDto.class)))
				.thenReturn(getRestaurants().get(0));
		assertEquals("Restaurant Added successfully",
				restaurantController.addRestaurant(authorization, requestDto).getBody());
	}

	@Test
	public void getRestaurantsByRating() throws Exception {
		List<Restaurant> resturants = getRestaurants();
		String authorization = "t0k3n";
		RestaurantResponseDto responseDTO = new RestaurantResponseDto(resturants, 1, 10);
		Mockito.when(restaurantService.findByRating(Mockito.anyDouble(), Mockito.anyInt(), Mockito.anyInt()))
				.thenReturn(responseDTO);
		assertEquals(resturants,
				restaurantController.getRestaurantsByRating(authorization, 1, 1, 10).getBody().getRestaurants());
	}

	@Test
	public void getRestaurantsByRating_zeropgaenumber() {
		String authorization = "t0k3n";
		List<Restaurant> resturants = getRestaurants();
		RestaurantResponseDto responseDTO = new RestaurantResponseDto(resturants, 1, 10);
		Mockito.when(restaurantService.findByRating(Mockito.anyDouble(), Mockito.anyInt(), Mockito.anyInt()))
				.thenReturn(responseDTO);
		assertEquals(responseDTO, restaurantController.getRestaurantsByRating(authorization, 2, 1, 10).getBody());
	}

	@Test(expected = RestaurantBadRequestException.class)
	public void getRestaurantsByRating_zero_pagesize() throws Exception {
		String authorization = "t0k3n";
		restaurantController.getRestaurantsByRating(authorization, 3.5, 0, 0);
	}

	@Test(expected = RestaurantNotFoundException.class)
	public void getRestaurantsByRating_empty() throws Exception {
		String authorization = "t0k3n";
		List<Restaurant> resturants = Arrays.asList();
		RestaurantResponseDto responseDTO = new RestaurantResponseDto(resturants, 1, 10);
		Mockito.when(restaurantService.findByRating(Mockito.anyDouble(), Mockito.anyInt(), Mockito.anyInt()))
				.thenReturn(responseDTO);
		restaurantController.getRestaurantsByRating(authorization, 2, 1, 10);

	}

	@Test
	public void getRestaurantsByName() throws Exception {
		List<Restaurant> resturants = Arrays.asList(new Restaurant("Resturant1", "Location1", "Chinese", 400, 4.0),
				new Restaurant("Resturant2", "Location2", "Indian", 200, 4.3),
				new Restaurant("Resturant3", "Location3", "South Indian", 300, 4.6));

		RestaurantResponseDto responseDTO = new RestaurantResponseDto(resturants, 1, 10);
		Mockito.when(restaurantService.findByName(Mockito.any(String.class), Mockito.anyInt(), Mockito.anyInt()))
				.thenReturn(responseDTO);
		assertEquals(1,
				restaurantController.getRestaurantsByName("t0k3n", "Resturant1", 1, 10).getBody().getTotalPages());

	}

	@Test(expected = RestaurantBadRequestException.class)
	public void getRestaurantsByName_zero() throws Exception {
		restaurantController.getRestaurantsByName("t0k3n", "Dominos", 0, 10);
	}

	@Test(expected = RestaurantBadRequestException.class)
	public void getRestaurantsByName_zeropagesize() throws Exception {
		restaurantController.getRestaurantsByName("t0ken", "Dominos", 1, 0);
	}

	@Test(expected = RestaurantNotFoundException.class)
	public void getRestaurantsByName_empty() throws Exception {
		List<Restaurant> resturants = Arrays.asList();
		RestaurantResponseDto responseDTO = new RestaurantResponseDto(resturants, 1, 10);
		Mockito.when(restaurantService.findByName(any(String.class), Mockito.anyInt(), Mockito.anyInt()))
				.thenReturn(responseDTO);
		restaurantController.getRestaurantsByName("t0ken", "Dominos", 1, 10);
	}

	@Test
	public void getRestaurantsByLocationAndCuisine() throws Exception {
		List<Restaurant> resturants = getRestaurants();
		RestaurantResponseDto responseDTO = new RestaurantResponseDto(resturants, 1, 10);
		Mockito.when(restaurantService.findByLocationAndCuisine(any(String.class), any(String.class), Mockito.anyInt(),
				Mockito.anyInt())).thenReturn(responseDTO);
		assertEquals(resturants, restaurantController
				.getRestaurantsByLocationCuisine("t0k3n", "RR Nagar", "Chinese", 1, 10).getBody().getRestaurants());
	}

	@Test(expected = RestaurantBadRequestException.class)
	public void getRestaurantsByLocationAndCuisine_zero_pagenumber() throws Exception {
		restaurantController.getRestaurantsByLocationCuisine("t0k3n", "RR NAGAR", "Chinese", 0, 10);
	}

	@Test(expected = RestaurantBadRequestException.class)
	public void getRestaurantsByLocationAndCuisine_zero_pagesize() {
		restaurantController.getRestaurantsByLocationCuisine("t0k3n", "RR Nagar", "Chinese", 0, 10);
	}

	@Test(expected = RestaurantNotFoundException.class)
	public void getRestaurantsByLocationAndCuisine_empty() throws Exception {
		List<Restaurant> resturants = Arrays.asList();

		RestaurantResponseDto responseDTO = new RestaurantResponseDto(resturants, 1, 10);
		Mockito.when(
				restaurantService.findByLocationAndCuisine(any(String.class), any(String.class), anyInt(), anyInt()))
				.thenReturn(responseDTO);
		restaurantController.getRestaurantsByLocationCuisine("t0k3n", "RR Nagar", "Chinese", 1, 10);
	}

	@Test
	public void getRestaurantsByLocationAndName() throws Exception {
		List<Restaurant> resturants = getRestaurants();
		RestaurantResponseDto responseDTO = new RestaurantResponseDto(resturants, 1, 10);
		Mockito.when(restaurantService.findByLocationAndName(any(String.class), any(String.class), anyInt(), anyInt()))
				.thenReturn(responseDTO);
		assertEquals(resturants, restaurantController
				.getRestaurantsByLocationName("t0k3n", "RR Nagar", "Dominos", 1, 10).getBody().getRestaurants());
	}

	@Test(expected = RestaurantBadRequestException.class)
	public void getRestaurantsByLocationAndName_bad() throws Exception {
		restaurantController.getRestaurantsByLocationName("t0k3n", "RR Nagar", "Dominos", 0, 10);
	}

	@Test(expected = RestaurantNotFoundException.class)
	public void getRestaurantsByLocationAndName_noRestaurants() throws Exception {
		List<Restaurant> resturants = Arrays.asList();
		RestaurantResponseDto responseDTO = new RestaurantResponseDto(resturants, 1, 10);
		Mockito.when(restaurantService.findByLocationAndName(any(String.class), any(String.class), anyInt(), anyInt()))
				.thenReturn(responseDTO);
		restaurantController.getRestaurantsByLocationName("t0k3n", "RR Nagar", "KFC", 1, 10);
	}

	@Test
	public void getRestaurantsByBudget() throws Exception {
		List<Restaurant> resturants = getRestaurants();
		RestaurantResponseDto responseDTO = new RestaurantResponseDto(resturants, 1, 10);
		Mockito.when(restaurantService.findByBudget(anyInt(), anyInt(), anyInt())).thenReturn(responseDTO);
		assertEquals(resturants,
				restaurantController.getRestaurantsByBudget("t0k3n", 100, 1, 10).getBody().getRestaurants());
	}

	@Test(expected = RestaurantBadRequestException.class)
	public void getRestaurantsByBudget_zero() {
		restaurantController.getRestaurantsByBudget("t0k3n", 300, 0, 10);
	}

	@Test(expected = RestaurantNotFoundException.class)
	public void getRestaurantsByBudget_empty() {
		List<Restaurant> resturants = Arrays.asList();
		RestaurantResponseDto responseDTO = new RestaurantResponseDto(resturants, 1, 10);
		Mockito.when(restaurantService.findByBudget(anyInt(), anyInt(), anyInt())).thenReturn(responseDTO);
		restaurantController.getRestaurantsByBudget("t0k3n", 100, 1, 10);
	}

	@Test
	public void getItemsByRestaurantId_basic() throws Exception {
		Menu menu = new Menu("From", "Till", new Restaurant("Resturant2", "Location2", "Indian", 200, 4.3));
		List<MenuItem> menuItems = Arrays.asList(new MenuItem("Dosa", "Plain Dosa", 200, menu),
				new MenuItem("Khara Bath", "Bath", 200, menu));
		Mockito.when(
				restaurantService.findMenuItemByRestaurantId(Mockito.anyLong(), Mockito.anyInt(), Mockito.anyInt()))
				.thenReturn(menuItems);
		assertEquals(menuItems, restaurantController.getItemsByRestaurantId("t0k3n", 1L, 1, 10).getBody());

	}

	@Test(expected = RestaurantNotFoundException.class)
	public void getItemsByRestaurantId_empty() {
		Mockito.when(
				restaurantService.findMenuItemByRestaurantId(Mockito.anyLong(), Mockito.anyInt(), Mockito.anyInt()))
				.thenReturn(Arrays.asList());
		restaurantController.getItemsByRestaurantId("t0k3n", 1L, 1, 10);
	}

	private List<Restaurant> getRestaurants() {
		List<Restaurant> restaurants = new ArrayList<>();
		Restaurant restaurant = new Restaurant("Dominos", "RR Nagar", "Italian", 400, 4.2);
		restaurant.setId((long) 1);
		restaurants.add(restaurant);
		return restaurants;
	}

	private RestaurantRequestDto getRestaurantRequest() {
		RestaurantRequestDto requestDto = new RestaurantRequestDto();
		requestDto.setActiveFrom("10");
		requestDto.setActiveTill("22");
		requestDto.setBudget(400);
		requestDto.setCuisine("Spanish");
		requestDto.setLocation("MG Road");
		requestDto.setName("Sip n Bite");
		requestDto.setRating(4.3);
		return requestDto;
	}
}
