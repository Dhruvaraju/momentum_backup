package com.alpha.momentum.service;

import com.alpha.momentum.entities.UserStory;
import com.alpha.momentum.repository.UserStoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserStoryService {
    @Autowired
    private UserStoryRepository repository;

    public UserStory createUserStory(UserStory request){
        return repository.save(request);
    }

    public UserStory fetchUserStoryById(Long id){
        Optional<UserStory> userStory =  repository.findById(id);
        return  userStory.orElse(null);
       /* if(userStory.isPresent()){
            return userStory.get();
        } else {
            return null;
        }*/
    }
}
