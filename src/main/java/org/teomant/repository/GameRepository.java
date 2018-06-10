package org.teomant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.teomant.entity.AttemptEntity;
import org.teomant.entity.GameEntity;

import java.util.List;

public interface GameRepository  extends JpaRepository<GameEntity, Long> {


    @Query( "select ge.attempts from GameEntity ge where ge.id = :id" )
    List<AttemptEntity> getAttemptsById(
            @Param( "id" )
                    Long id );
}
