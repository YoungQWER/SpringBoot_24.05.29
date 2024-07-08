package com.example.demo.service;

import com.example.demo.model.UserEntity;
import com.example.demo.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserEntity create(final UserEntity userEntity) {

        //널 값 체크
        if(userEntity == null || userEntity.getUsername() == null) {
            throw new RuntimeException("Invalid UserEntity");
        }

        final String userName = userEntity.getUsername();
        // 중복체크
        if(userRepository.existsByUsername(userName)) {
           log.warn("Username {} already exists", userName);
           throw new RuntimeException("Username " + userName + " already exists");
        }
        //테이블 저장
        return userRepository.save(userEntity);
    }

    public UserEntity getByCredentials(final String username, final String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public UserEntity getByCredentials(final String username, final String password,
                                       final PasswordEncoder passwordEncoder) {

        UserEntity originalUser = userRepository.findByUsername(username);

        //matches 메소드 이용해서 패스워드가 같은지 확인
        if(originalUser != null && passwordEncoder.matches(password, originalUser.getPassword())) {
            return originalUser;
        }

        return null;
    }
}
