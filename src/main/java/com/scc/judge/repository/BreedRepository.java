package com.scc.judge.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.scc.judge.model.JudgeBreed;

@Repository
public interface BreedRepository extends CrudRepository<JudgeBreed, String> {
	
    public List<JudgeBreed> findById(int id);

}
