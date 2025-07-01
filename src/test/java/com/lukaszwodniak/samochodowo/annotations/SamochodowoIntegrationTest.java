package com.lukaszwodniak.samochodowo.annotations;

import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles({"integration"})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface SamochodowoIntegrationTest {
}
