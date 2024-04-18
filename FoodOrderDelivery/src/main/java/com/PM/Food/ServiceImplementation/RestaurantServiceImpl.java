package com.PM.Food.ServiceImplementation;

import com.PM.Food.Dto.RestaurantDto;
import com.PM.Food.Model.Address;
import com.PM.Food.Model.Restaurant;
import com.PM.Food.Model.User;
import com.PM.Food.Repository.AddressRepository;
import com.PM.Food.Repository.RestaurantRepository;
import com.PM.Food.Repository.UserRepository;
import com.PM.Food.Request.CreateRestaurantRequest;
import com.PM.Food.Response.RestaurantService;
import com.PM.Food.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository  restaurantRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;




    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user) {


        Address address = addressRepository.save(req.getAddress());

        Restaurant restaurant= new Restaurant();

        restaurant.setAddress(req.getAddress());
        restaurant.setContactInformation(req.getContactInformation());
        restaurant.setCuisineType(req.getCuisineType());
        restaurant.setDescription(req.getDescription());
        restaurant.setName(req.getName());
        restaurant.setOpeningHours(req.getOpeningHours());
        restaurant.setRegistrationDate(LocalDateTime.now());
        restaurant.setImages(req.getImages());
        restaurant.setOwner(user);


        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception {

        Restaurant restaurant= findRestaurantById(restaurantId);

        if(restaurant.getCuisineType()!=null){
            restaurant.setCuisineType(updatedRestaurant.getCuisineType());
        }
        if(restaurant.getCuisineType()!=null){
            restaurant.setDescription(updatedRestaurant.getDescription());
        }
        if(restaurant.getName()!=null){
            restaurant.setName(updatedRestaurant.getName());
        }


        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception {

        Restaurant restaurant = findRestaurantById(restaurantId);
        restaurantRepository.delete(restaurant);

    }

    @Override
    public List<Restaurant> getAllRestaurant() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurant(String keyword) {


        return restaurantRepository.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant findRestaurantById(Long id) throws Exception {

        Optional<Restaurant> opt = restaurantRepository.findById(id);

        if(opt.isEmpty()){
            throw  new Exception("restaurant not found with id "+id);
        }
        return opt.get();
    }

    @Override
    public Restaurant getRestaurantByUserId(Long userId) throws Exception {

        Restaurant restaurant = restaurantRepository.findByOwnerId(userId);
        if(restaurant==null){
            throw new Exception("Restaurant not found with userId "+userId);
        }


        return restaurant;
    }

    @Override
    public RestaurantDto addToFavourites(Long restaurantId, User user) throws Exception {

        Restaurant restaurant = findRestaurantById(restaurantId);

        RestaurantDto dto = new RestaurantDto();
        dto.setDescription(restaurant.getDescription());
        dto.setImages(restaurant.getImages());
        dto.setTitle(restaurant.getName());
        dto.setId(restaurantId);

        boolean isFavorited=false;
        List<RestaurantDto> favourites= user.getFavourites();
        for(RestaurantDto favorite:favourites){
            if(favorite.getId().equals(restaurantId)){
                isFavorited=true;
                break;
            }
        }
        if(isFavorited){
            favourites.removeIf(favorite->favorite.getId().equals(restaurantId));
        }else{
            favourites.add(dto);
        }



userRepository.save(user);

        return dto;
    }

    @Override
    public Restaurant updateRestaurantStatus(long id) throws Exception {

        Restaurant restaurant = findRestaurantById(id);
        restaurant.setOpen(!restaurant.isOpen());
        return restaurantRepository.save(restaurant);
    }
}
