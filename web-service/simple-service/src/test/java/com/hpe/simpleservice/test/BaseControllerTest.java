package com.hpe.simpleservice.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Base class for unit tests to reduce some of the repetitive plumbing
 *
 * @author truxall
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { WebAppContext.class})
@WebAppConfiguration
public abstract class BaseControllerTest {

    protected MockMvc mMockMvc;

    /**
     * Sets up controllers for unit testing in Spring MVC
     *
     * @param controller A string
     *
     */
    public <E> void setupController(E controller) {

        mMockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
}