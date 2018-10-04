package com.scc.judge.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.scc.judge.Application;
import com.scc.judge.model.Judge;
import com.scc.judge.repository.JudgeRepository;
import com.scc.judge.template.JudgeObject;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Application.class, webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = { "spring.zipkin.enabled: false" })
public class JudgeServiceTest {

	@TestConfiguration
    static class JudgeServiceTestContextConfiguration {
  
        @Bean
        public JudgeService judgeService() {
            return new JudgeService();
        }
    }
 
    @Autowired
    private JudgeService judgeService;
 
    @MockBean
    private JudgeRepository judgeRepository;
    
    @Before
    public void setUp() {
    	
    	Optional<Judge> judge = createJudge();
     
        Mockito.when(judgeRepository.findById(judge.get().getId()))
          .thenReturn(judge);
    }
    
	@Test
    public void whenValidId_thenJudgeShouldBeFound() throws Exception {
		
	    int id = 1;
	    JudgeObject found = judgeService.getJudgeById(id);
	  
	     assertThat(found.getId())
	      .isEqualTo(id);			
	}
	
    private Optional<Judge> createJudge() {
    	Judge judge = new Judge()
			.withId(1)
			.withFirstName("CHARLIE")
			.withLastName("BROWN")
		;
		return Optional.of(judge);
    }
}
