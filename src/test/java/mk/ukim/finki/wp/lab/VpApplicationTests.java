package mk.ukim.finki.wp.lab;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
class VpApplicationTests {
    private MockMvc mockMvc;

    @BeforeEach
    public void setup(WebApplicationContext webApplicationContext) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void contextLoads() {
    }

    @Test
    void testGetCourses() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/courses"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("courses"))
                .andExpect(view().name("listCourses"));
    }
}
