package com.scc.judge.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.scc.judge.model.Judge;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {JudgeRepository.class})
@DataJpaTest
@EntityScan(basePackageClasses = {Judge.class})
public class JudgeRepositoryTest {

	@Autowired
    private TestEntityManager entityManager;

    @Autowired
    private JudgeRepository judgeRepository;

    @Test
    public void whenFindById_thenReturnJudge() {

        // given
        Judge judge = createJudge(); 
        entityManager.persist(judge);
        entityManager.flush();
     
        // when
        Optional<Judge> found = judgeRepository.findById(judge.getId());
     
        // then
        assertThat(found.get().getLastName())
          .isEqualTo(judge.getLastName());
    	
    }

    private Judge createJudge() {
    	Judge judge = new Judge()
			.withId(1)
			.withFirstName("CHARLIE")
			.withLastName("BROWN")
		;

        return judge;
    }
	
}
