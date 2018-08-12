package org.teomant.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.teomant.entity.GameEntity;
import org.teomant.entity.UserEntity;
import org.teomant.service.AttemptService;
import org.teomant.service.GameService;
import org.teomant.service.UserService;

@RestController
public class TestRestController {

    @Autowired
    UserService userService;

    @Autowired
    GameService gameService;

    @Autowired
    AttemptService attemptService;

    @GetMapping(value= "/rest/test/{id}")
    @ResponseBody
    public UserEntity test(@PathVariable("id") Long id) {
        UserEntity userEntity = userService.getUserById(id).get();
//        GameEntity gameEntity = gameService.getGameById()
//        userEntity.setGames(null);
        System.out.println(userEntity);
        return userEntity;
    }
}
