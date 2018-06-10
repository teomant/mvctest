package org.teomant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.teomant.entity.AttemptEntity;
import org.teomant.entity.GameEntity;
import org.teomant.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query( "select ue.games from UserEntity ue where ue.id = :id" )
    List<GameEntity> getGamesById(
            @Param( "id" )
                    Long id );

    @Query( "select ue from UserEntity ue where ue.username = :username" )
    Optional<UserEntity> getUserByUsername(
            @Param( "username" )
                    String username);

}
