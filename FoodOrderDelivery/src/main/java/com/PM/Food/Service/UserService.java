package com.PM.Food.Service;

import com.PM.Food.Model.User;

public interface UserService {


    public User findUserByJwtToken(String jwt) throws Exception;

    public User findUserByEmail(String email) throws Exception;


}
