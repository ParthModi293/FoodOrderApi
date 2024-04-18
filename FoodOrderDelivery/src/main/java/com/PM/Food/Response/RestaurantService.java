package com.PM.Food.Response;

import com.PM.Food.Dto.RestaurantDto;
import com.PM.Food.Model.Restaurant;
import com.PM.Food.Model.User;
import com.PM.Food.Request.CreateRestaurantRequest;

import java.util.List;

public interface RestaurantService {

    public Restaurant createRestaurant(CreateRestaurantRequest req, User user);

    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception;

    public void deleteRestaurant(Long restaurantId) throws Exception;

    public List<Restaurant> getAllRestaurant();

    public  List<Restaurant> searchRestaurant(String keyword);

    public Restaurant findRestaurantById(Long id)throws Exception;

    public Restaurant getRestaurantByUserId(Long userId)throws Exception;

    public RestaurantDto addToFavourites(Long restaurantId,User user) throws Exception;

    public Restaurant updateRestaurantStatus(long id) throws Exception;




}
