package com.acciojob.bookmyshowapplication.Services;

import com.acciojob.bookmyshowapplication.Entities.User;
import com.acciojob.bookmyshowapplication.Repository.UserRepository;
import com.acciojob.bookmyshowapplication.Requests.AddUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public String addUser(@RequestBody AddUserRequest userRequest){
        User user = User.builder().emailId(userRequest.getEmailId())
                .name(userRequest.getName()).build();

        userRepository.save(user);
        return "User has been saved in Db";
    }
}
