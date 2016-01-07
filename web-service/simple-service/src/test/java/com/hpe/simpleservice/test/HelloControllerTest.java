package com.hpe.simpleservice.test;
import com.hpe.simpleservice.Constants;
import com.hpe.simpleservice.HelloController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Example unit tests for the example controller
 *
 * @author truxall
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebAppContext.class})
@WebAppConfiguration
public class HelloControllerTest extends BaseControllerTest {

    private HelloController controller = new HelloController();

    @Before
    public void setup() {
        setupController(controller);
    }


    @Test
    public void getHello_ShouldReturnString() throws Exception {
        mMockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(Constants.HELLO_MESSAGE));

    }

}
