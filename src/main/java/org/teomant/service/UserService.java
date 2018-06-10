package org.teomant.service;

import org.teomant.entity.UserEntity;

import java.util.Optional;

public interface UserService {

    UserEntity addUser(UserEntity userEntity);
    void deleteUser(UserEntity userEntity);
    Optional<UserEntity> getUserById(Long id);

}
