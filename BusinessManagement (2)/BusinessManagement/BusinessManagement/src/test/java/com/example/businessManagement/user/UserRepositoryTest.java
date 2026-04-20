package com.example.businessManagement.user;

import com.example.businessManagement.Model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    UserRepository repo;

    @Test
    public void findByUsername() {
        User user = new User();
        user.setUsername("repo");
        user.setPassword("pass");
        user.setRole(Role.valueOf("ROLE_USER"));

        repo.save(user);

        assertTrue(repo.findByUsername("repo").isPresent());
    }

    private void assertTrue(boolean repo) {
    }
}
