package com.eatza.restaurantsearch.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.eatza.restaurantsearch.dto.ItemRequestDto;
import com.eatza.restaurantsearch.exception.ItemNotFoundException;
import com.eatza.restaurantsearch.model.Menu;
import com.eatza.restaurantsearch.model.MenuItem;
import com.eatza.restaurantsearch.model.Restaurant;
import com.eatza.restaurantsearch.service.menuitemservice.MenuItemService;

@RunWith(MockitoJUnitRunner.class)
public class MenuItemControllerTest {

	@InjectMocks
	private MenuItemController menuItemController;

	@Mock
	private MenuItemService menuItemService;

	private static final String AUTHORIZATION = "t0k3n";

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void addMenuItem() throws Exception {
		Mockito.when(menuItemService.saveMenuItem(Mockito.any(ItemRequestDto.class))).thenReturn(new MenuItem());
		assertEquals("Item Added successfully",
				menuItemController.addItemsToRestaurantMenu(AUTHORIZATION, getItemRequest()).getBody());
	}

	@Test
	public void getRestaurantsContainingItem() throws ItemNotFoundException {
		List<Restaurant> restaurants = new ArrayList<>();
		Mockito.when(menuItemService.findByName(Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt()))
				.thenReturn(restaurants);
		assertEquals(restaurants,
				menuItemController.getRestaurantsContainingItem(AUTHORIZATION, "Dosa", 1, 10).getBody());
	}

	@Test
	public void getItemById() throws Exception {
		MenuItem menuItem = new MenuItem("Rajma", "Beans", 120, new Menu());
		Optional<MenuItem> returnedItem = Optional.of(menuItem);
		Mockito.when(menuItemService.findById(Mockito.anyLong())).thenReturn(returnedItem);
		assertEquals(returnedItem.get(), menuItemController.getItemById(1L).getBody());

	}

	@Test(expected = ItemNotFoundException.class)
	public void getItemById_empty() throws ItemNotFoundException {
		when(menuItemService.findById(anyLong())).thenReturn(Optional.empty());
		menuItemController.getItemById(1L);
	}

	private ItemRequestDto getItemRequest() {
		ItemRequestDto requestDto = new ItemRequestDto();
		requestDto.setDescription("Dosa");
		requestDto.setMenuId(1L);
		requestDto.setName("Onion Dosa");
		requestDto.setPrice(200);
		return requestDto;
	}

}
