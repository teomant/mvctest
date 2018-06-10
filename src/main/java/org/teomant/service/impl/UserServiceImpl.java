package org.teomant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.teomant.entity.GameEntity;
import org.teomant.entity.UserEntity;
import org.teomant.repository.UserRepository;
import org.teomant.service.GameService;
import org.teomant.service.UserService;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    GameService gameService;

    @Override
    public UserEntity addUser(UserEntity userEntity){
        return userRepository.saveAndFlush(userEntity);
    }

    @Override
    public void deleteUser(UserEntity userEntity){
        userRepository.delete(userEntity);
    }

    @Override
    public Optional<UserEntity> getUserById(Long id) {
        return userRepository.findById(id).map(user ->{
            user.setGames(userRepository.getGamesById(user.getId()));
            user.setGames(user.getGames().stream().map(gameEntity -> {
                gameEntity = gameService.getGameById(gameEntity.getId()).get();
                return gameEntity;}).collect(Collectors.toList()));
        return user;});
    }


}
