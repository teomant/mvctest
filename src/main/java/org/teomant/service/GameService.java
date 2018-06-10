package org.teomant.service;

import org.teomant.entity.GameEntity;

import java.util.Optional;

public interface GameService {

    GameEntity addGame(GameEntity gameEntity);
    void deleteGame(GameEntity gameEntity);
    Optional<GameEntity> getGameById(Long id);
}
