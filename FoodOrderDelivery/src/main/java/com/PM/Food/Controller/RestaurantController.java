package com.PM.Food.Controller;

import com.PM.Food.Dto.RestaurantDto;
import com.PM.Food.Model.Restaurant;
import com.PM.Food.Model.User;
import com.PM.Food.Request.CreateRestaurantRequest;
import com.PM.Food.Response.RestaurantService;
import com.PM.Food.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;


    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> createRestaurant(@RequestParam String keyword,
                                                             @RequestHeader("Authorization") String jwt  ) throws Exception {


        User user = userService.findUserByJwtToken(jwt);
        List<Restaurant> restaurant = restaurantService.searchRestaurant(keyword);


        return new ResponseEntity<>(restaurant, HttpStatus.OK) ;
    }

    @GetMapping()
    public ResponseEntity<List<Restaurant>> getAllRestaurant(@RequestHeader("Authorization") String jwt  ) throws Exception {


        User user = userService.findUserByJwtToken(jwt);
        List<Restaurant> restaurant = restaurantService.getAllRestaurant();


        return new ResponseEntity<>(restaurant, HttpStatus.OK) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> findRestaurantById(@RequestHeader("Authorization") String jwt, @PathVariable Long id  ) throws Exception {


        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.findRestaurantById(id);


        return new ResponseEntity<>(restaurant, HttpStatus.OK) ;
    }

    @PutMapping("/{id}/add-favourites")
    public ResponseEntity<RestaurantDto> addToFavourites(@RequestHeader("Authorization") String jwt, @PathVariable Long id  ) throws Exception {


        User user = userService.findUserByJwtToken(jwt);
        RestaurantDto restaurant = restaurantService.addToFavourites(id,user);


        return new ResponseEntity<>(restaurant, HttpStatus.OK) ;
    }






}
