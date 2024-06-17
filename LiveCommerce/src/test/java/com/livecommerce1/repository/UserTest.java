package com.livecommerce1.repository;

import com.livecommerce1.entity.User;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

@SpringBootTest
@Log4j2
@Transactional
class UserTest {

    @Autowired
    UserRepository userRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("Auditing 테스트")
    @WithMockUser(username = "gildong", roles = "USER")
    public void auditingTest(){

        User newUser = new User();
        userRepository.save(newUser);

        em.flush();
        em.clear();

        User user = userRepository.findById(newUser.getId())
                .orElseThrow(() -> new EntityNotFoundException());

        log.info(user.getRegTime());
        log.info(user.getUpdateTime());
        log.info(user.getCreatedBy());
        log.info(user.getModifiedBy());

    }
}