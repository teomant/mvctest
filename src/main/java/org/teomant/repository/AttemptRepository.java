package org.teomant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.teomant.entity.AttemptEntity;

public interface AttemptRepository extends JpaRepository<AttemptEntity, Long> {
}
