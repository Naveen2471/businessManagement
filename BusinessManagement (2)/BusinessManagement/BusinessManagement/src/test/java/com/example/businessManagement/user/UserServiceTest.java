package com.example.businessManagement.user;

import com.example.businessManagement.Model.User;
import jakarta.transaction.Transactional;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@RunWith(Enclosed.class)
public class UserServiceTest {
    @SpringBootTest
    @Transactional
    public static class userservicetest {

        @Autowired
        UserService service;

        @Test
        public void createUser_success() {
            User user = new User();
            user.setUsername("test");
            user.setPassword("pass");
            user.setRole(Role.valueOf("ROLE_USER"));

            User saved = service.create(user);
            assertNotNull(saved.getId());
        }
    }

}
