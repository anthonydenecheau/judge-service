package com.scc.judge.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.scc.judge.model.Judge;

@Repository
public interface JudgeRepository extends CrudRepository<Judge, String> {
	
    public Optional<Judge> findById(int id);
    
    public List<Judge> findByCountryAndNatureJugementAndIsInternationalOrderByLastNameAscFirstNameAsc(String country, String natureJugement, String isInternational);
    public List<Judge> findByIsInternationalAndCountryNotOrderByLastNameAscFirstNameAsc(String isInternational, String county);
    public List<Judge> findByCountryAndNatureJugementOrderByLastNameAscFirstNameAsc(String country, String natureJugement);
    
    public List<Judge> findByIdAndNatureJugement(int id, String natureJugement);

    @Query(value = "SELECT j FROM Judge j "
          + "WHERE j.natureJugement = ?1 "
          + "AND EXISTS ( SELECT t FROM JudgeTest t WHERE t.id = j.id AND t.idCommission = ?2 ) "
          + "AND j.isInternational = ?3")
    public List<Judge> findByNatureJugementAndCommissionAndIsInternational(String natureJugement, String commission, String isInternational);

    public List<Judge> findByNatureJugementAndIsInternational(String natureJugement, String isInternational);

    @Query(value = "SELECT j FROM Judge j "
          + "WHERE j.natureJugement = ?1 "
          + "AND EXISTS ( SELECT t FROM JudgeTest t WHERE t.id = j.id AND t.dateEleve IS NOT NULL AND t.dateJuge IS NULL AND t.idCommission = ?2 ) "
          + "AND j.isInternational = ?3")
    public List<Judge> findByGradeEleveAndNatureJugementAndCommissionAndIsInternational(String natureJugement, String commission, String isInternational);

    @Query(value = "SELECT j FROM Judge j "
          + "WHERE j.natureJugement = ?1 "
          + "AND EXISTS ( SELECT t FROM JudgeTest t WHERE t.id = j.id AND t.dateEleve IS NOT NULL AND t.dateJuge IS NULL ) "
          + "AND j.isInternational = ?3")
    public List<Judge> findByGradeEleveAndNatureJugementAndIsInternational(String natureJugement, String isInternational);

    @Query(value = "SELECT j FROM Judge j "
          + "WHERE j.natureJugement = ?1 "
          + "AND EXISTS ( SELECT t FROM JudgeTest t WHERE t.id = j.id AND t.dateJuge IS NOT NULL AND t.idCommission = ?2 ) "
          + "AND j.isInternational = ?3")
    public List<Judge> findByGradeJugeAndNatureJugementAndCommissionAndIsInternational(String natureJugement, String commission, String isInternational);

    @Query(value = "SELECT j FROM Judge j "
          + "WHERE j.natureJugement = ?1 "
          + "AND EXISTS ( SELECT t FROM JudgeTest t WHERE t.id = j.id AND t.dateJuge IS NOT NULL ) "
          + "AND j.isInternational = ?3")
    public List<Judge> findByGradeJugeAndNatureJugementAndIsInternational(String natureJugement, String isInternational);

}
