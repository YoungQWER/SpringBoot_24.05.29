package com.example.demo.service;

import com.example.demo.model.UserEntity;
import com.example.demo.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

                            // final이 있어서 user값 변경 x
    public UserEntity create(final UserEntity userEntity) {

        // 널 값 체크
        if(userEntity == null || userEntity.getUsername() == null) {
            throw new RuntimeException("Invalid UserEntity");
        }

        final String username = userEntity.getUsername();

        // 중복 체크
        if(userRepository.existsByUsername(username)) {
            log.warn("Username {} already exists", username);
            throw new RuntimeException("Username " + username + " already exists");
        }

        //테이블 저장
        return userRepository.save(userEntity);
    }

    public UserEntity getByCredentials(final String username, final String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

}
