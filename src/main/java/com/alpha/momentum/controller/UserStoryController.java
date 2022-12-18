package com.alpha.momentum.controller;

import com.alpha.momentum.entities.UserStory;
import com.alpha.momentum.service.UserStoryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/us")
public class UserStoryController {
    @Autowired
    private UserStoryService service;

    @RequestMapping(path = "/user-stories", method = RequestMethod.POST)
    public UserStory createStory(@RequestBody UserStory request){
        return service.createUserStory(request);
    }

    @RequestMapping(path = "/user-stories/{storyId}", method = RequestMethod.GET)
    public ResponseEntity<UserStory> findUserStory(@PathVariable(name = "storyId") Long id){
       UserStory userStory = service.fetchUserStoryById(id);
       if(null != userStory){
           return ResponseEntity.status(HttpStatus.OK).body(userStory);
       } else {
           return ResponseEntity.notFound().build();
       }
    }
}
