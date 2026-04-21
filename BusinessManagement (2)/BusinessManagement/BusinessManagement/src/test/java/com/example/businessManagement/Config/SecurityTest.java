package com.example.businessManagement.Config;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.StatusResultMatchers;

import static javax.swing.UIManager.get;
import static org.springframework.web.servlet.function.ServerResponse.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityTest {

    @Autowired
    MockMvc mvc;

    @Test
    public void unauthorizedAccessBlocked() throws Exception {
        mvc.perform((RequestBuilder) get("/api/v1/users"))
                .andExpect(status().isUnauthorized());
    }

    private StatusResultMatchers status() {
        return null;
    }
}
