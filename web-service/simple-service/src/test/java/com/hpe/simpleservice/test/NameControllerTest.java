package com.hpe.simpleservice.test;

import com.hpe.simpleservice.NameController;
import com.hpe.simpleservice.NameGenerator;
import com.hpe.simpleservice.Person;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.naming.NamingException;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Simple unit tests for the name controller
 *
 * @author truxall
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebAppContext.class})
@WebAppConfiguration
public class NameControllerTest extends BaseControllerTest {

    @Mock
    private NameGenerator mockData;

    @InjectMocks
    private NameController controller = new NameController();

    @Before
    public void setup() throws NamingException {

        MockitoAnnotations.initMocks(this);

        setupController(controller);
    }

    /**
     * Test for successful activation of a user
     */
    @Test
    public void getRandomNames() throws Exception {

        Person fakePerson = new Person();
        fakePerson.setFirstName("Dave");
        fakePerson.setLastName("Truxall");
        fakePerson.setGender("m");

        List<Person> fakeResult = new ArrayList<>();
        fakeResult.add(fakePerson);

        when(mockData.getRandomPersons(any(Integer.class))).thenReturn(fakeResult);

        mMockMvc.perform(get("/api/names/11"))
                .andExpect(status().isOk());
    }
}
