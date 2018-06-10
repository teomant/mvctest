package org.teomant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.teomant.entity.GameEntity;
import org.teomant.repository.GameRepository;
import org.teomant.service.GameService;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    GameRepository gameRepository;

    @Override
    public GameEntity addGame(GameEntity gameEntity){
        return gameRepository.saveAndFlush(gameEntity);
    }

    @Override
    public void deleteGame(GameEntity gameEntity){
        gameRepository.delete(gameEntity);
    }

    @Override
    public Optional<GameEntity> getGameById(Long id) {
        return gameRepository.findById(id).map(game ->
        {game.setAttempts(gameRepository.getAttemptsById(game.getId()));return game;});
    }


}
