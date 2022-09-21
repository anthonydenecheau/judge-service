package com.scc.judge.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.commons.lang3.RandomStringUtils;

import com.scc.judge.Application;
import com.scc.judge.exceptions.EntityNotFoundException;
import com.scc.judge.model.Judge;
import com.scc.judge.services.JudgeService;
import com.scc.judge.template.JudgeObject;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Application.class, webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = { "spring.zipkin.enabled: false" })
@AutoConfigureMockMvc
public class JudgeControllerTest {

	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private JudgeService judgeService;
    
    private Judge judge;
    private JudgeObject response;
    private final String authKey = "myPersonalKey";
    
    @Before
    public void setUp() {
    	this.judge = createJudge(); 
    	this.response = populateJudgeObject(this.judge);
    }
    
    @Test
    //code = 200
    public void givenCertifiedAuthentificationKey_whenGetJudgeById_thenReturnJson() throws Exception {

    	// given
    	given(judgeService.getJudgeById(this.judge.getId())).willReturn(this.response);

    	// when + then
    	mockMvc.perform(get("/v1/judges" + "/french/" + judge.getId())
    	 		 	.header("X-SCC-authentification", authKey)
                 	.contentType(APPLICATION_JSON))
                 .andExpect(status().isOk())
                 .andExpect(jsonPath("$.id", is(this.judge.getId())))
                 .andExpect(jsonPath("$.name", is(this.judge.getFirstName()+" "+this.judge.getLastName())))
        ;
    }

    @Test
    //code = 401
    public void givenWrongAuthentificationKey_whenGetJudgeById_thenReturnJson() throws Exception {

    	// given
    	given(judgeService.getJudgeById(this.judge.getId())).willReturn(this.response);

    	// when + then
    	mockMvc.perform(get("/v1/judges" + "/french/" + judge.getId())
    	 		 	.header("X-SCC-authentification", RandomStringUtils.randomAlphanumeric(20))
                 	.contentType(APPLICATION_JSON))
                 .andExpect(status().isUnauthorized())
                 .andExpect(jsonPath("$.apierror.status", is("UNAUTHORIZED")))
        ;

    }

    @Test
    //code = 400
    public void givenWrongTypeId_whenGetJudgeById_thenReturnJson() throws Exception {

    	// given

    	// when + then
    	mockMvc.perform(get("/v1/judges" + "/french/" + RandomStringUtils.randomAlphanumeric(8))
    	 		 	.header("X-SCC-authentification", authKey)
                 	.contentType(APPLICATION_JSON))
                 .andExpect(status().isBadRequest())
                 .andExpect(jsonPath("$.apierror.status", is("BAD_REQUEST")))
        ;

    }
    
    @Test
    //code = 404
    public void givenJudgeNoFound_whenGetJudgeById_thenReturnJson() throws Exception {
    	

    	// given
    	int id = 123;
    	given(judgeService.getJudgeById(id))
        	.willThrow(new EntityNotFoundException(JudgeObject.class, "id", String.valueOf(id)));

    	// when + then
    	mockMvc.perform(get("/v1/judges" + "/french/" + id)
	 		 	.header("X-SCC-authentification", authKey)
             	.contentType(APPLICATION_JSON))
             .andExpect(status().isNotFound())
             .andExpect(jsonPath("$.apierror.status", is("NOT_FOUND")))
        ;
    }    
       
    private Judge createJudge() {
    	Judge judge = new Judge()
			.withId(1)
			.withFirstName("CHARLIE")
			.withLastName("BROWN")
		;

        return judge;
    }

    private JudgeObject populateJudgeObject(Judge _judge) {
    	
    	JudgeObject _o = new JudgeObject()
			.withId(_judge.getId())
			.withName("CHARLIE BROWN")
		;

        return _o;
    }

}

