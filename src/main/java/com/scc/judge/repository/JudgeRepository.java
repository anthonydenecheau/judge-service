package com.scc.judge.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.scc.judge.model.Judge;

@Repository
public interface JudgeRepository extends CrudRepository<Judge, String> {
	
    public Optional<Judge> findById(int id);
    
    public List<Judge> findByCountryAndIsInternationalOrderByLastNameAscFirstNameAsc(String country, String isInternational);
    public List<Judge> findByIsInternationalAndCountryNotOrderByLastNameAscFirstNameAsc(String isInternational, String county);
    public List<Judge> findByCountryOrderByLastNameAscFirstNameAsc(String country);
    
}
