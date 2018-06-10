package org.teomant.service;

import org.teomant.entity.AttemptEntity;

public interface AttemptService {

    AttemptEntity addAttempt(AttemptEntity attemptEntity);
    void deleteAttempt(AttemptEntity attemptEntity);
}
