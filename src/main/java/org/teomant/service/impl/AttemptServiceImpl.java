package org.teomant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.teomant.entity.AttemptEntity;
import org.teomant.repository.AttemptRepository;
import org.teomant.service.AttemptService;

@Service
public class AttemptServiceImpl implements AttemptService {

    @Autowired
    private AttemptRepository attemptRepository;

    @Override
    public AttemptEntity addAttempt(AttemptEntity attemptEntity) {
        AttemptEntity savedAttemptEntity = attemptRepository.saveAndFlush(attemptEntity);

        return savedAttemptEntity;
    }

    @Override
    public void deleteAttempt(AttemptEntity attemptEntity) {
        attemptRepository.delete(attemptEntity);
    }

}
