package com.jumia.pay.assessment.service;

import com.jumia.pay.assessment.dto.UserDTO;
import com.jumia.pay.assessment.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private MongoTemplate mongoTemplate;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private Logger logger;

    @Mock
    private UserDTO userDTO;

    @Mock
    private Util util;

    @Mock
    private AuditService auditService;

    private String expectedMessage = "User successfully registered";

    @Before
    public void setUpBeforeTest() {


        User user = new User();

        user.setFirstname("Bolanle");
        user.setLastname("Bayo");
        user.setUsername("Boyo");
        user.setPassword("kunle");
        user.setId("Object___id");

        when(userDTO.retrieveEntity()).
                thenReturn(user);

        when(userDTO.getFirstname()).thenReturn("Bolanle");
        when(userDTO.getLastname()).thenReturn("Bayo");
        when(userDTO.getPassword()).thenReturn("kunle");
        when(userDTO.getUsername()).thenReturn("Boyo");

        when(mongoTemplate.insert(any(User.class)))
                .thenReturn(user);

        when(mongoTemplate.findOne(any(Query.class), any(Class.class)))
                .thenReturn(null);

        when(passwordEncoder.encode(any(String.class)))
                .thenReturn("$2a$10$YXK43LB/lHeKlJznhgccUukGDBnJq3h7CbZ.6eDZ8DTR.HOjUsuK6");


        Mockito.spy(logger);
        Mockito.spy(util);
        Mockito.spy(auditService);

    }

    @Test
    public void createUser() {
        assertNotNull("User dto cannot be null", userDTO);
        assertNotNull("First name cannot be null", userDTO.getFirstname());
        assertNotNull("Last name cannot be null", userDTO.getLastname());
        assertNotNull("Password cannot be null", userDTO.getPassword());
        assertNotNull("Username cannot be null", userDTO.getUsername());
        String message = userService.createUser(userDTO, null);
        assertEquals("Expected and actual message must be equal", expectedMessage, message);
    }

}