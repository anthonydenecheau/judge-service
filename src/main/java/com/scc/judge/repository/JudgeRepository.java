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
          + "WHERE j.natureJugement = ?2 "
          + "AND EXISTS ( SELECT t FROM JudgeTest t WHERE t.id = j.id AND t.dateEleve IS NOT NULL AND t.dateJuge IS NULL AND t.idCommission = ?3 ) "
          + "AND j.country = ?1 ")
    public List<Judge> findByCountryAndNatureJugementAndGradeEleveAndCommission(String country, String natureJugement, String commission);

    @Query(value = "SELECT j FROM Judge j "
          + "WHERE j.natureJugement = ?2 "
          + "AND EXISTS ( SELECT t FROM JudgeTest t WHERE t.id = j.id AND t.dateEleve IS NOT NULL AND t.dateJuge IS NULL ) "
          + "AND j.country = ?1 ")
    public List<Judge> findByCountryAndNatureJugementAndGradeEleve(String country, String natureJugement);

    @Query(value = "SELECT j FROM Judge j "
          + "WHERE j.natureJugement = ?2 "
          + "AND EXISTS ( SELECT t FROM JudgeTest t WHERE t.id = j.id AND t.dateEleve IS NULL AND t.dateJuge IS NOT NULL AND t.idCommission = ?3 ) "
          + "AND j.country = ?1 ")
    public List<Judge> findByCountryAndNatureJugementAndGradeJugeAndCommission(String country, String natureJugement, String commission);

    @Query(value = "SELECT j FROM Judge j "
          + "WHERE j.natureJugement = ?2 "
          + "AND EXISTS ( SELECT t FROM JudgeTest t WHERE t.id = j.id AND t.dateEleve IS NULL AND t.dateJuge IS NOT NULL ) "
          + "AND j.country = ?1 ")
    public List<Judge> findByCountryAndNatureJugementAndGradeJuge(String country, String natureJugement);

    @Query(value = "SELECT j FROM Judge j "
          + "WHERE j.natureJugement = ?2 "
          + "AND EXISTS ( SELECT t FROM JudgeTest t WHERE t.id = j.id AND t.idCommission = ?3 ) "
          + "AND j.country = ?1")
    public List<Judge> findByCountryAndNatureJugementAndCommission(String country, String natureJugement, String commission);

    public List<Judge> findByCountryAndNatureJugement(String country, String natureJugement);

    @Query(value = "SELECT j FROM Judge j "
          + "WHERE j.natureJugement = ?1 "
          + "AND EXISTS ( SELECT t FROM JudgeTest t WHERE t.id = j.id AND t.dateEleve IS NOT NULL AND t.dateJuge IS NULL AND t.idCommission = ?2 ) "
          + "AND j.country != 'FR' ")
    public List<Judge> findByNatureJugementAndGradeEleveAndIsNotFrenchAndCommission( String natureJugement, String commission);

    @Query(value = "SELECT j FROM Judge j "
          + "WHERE j.natureJugement = ?1 "
          + "AND EXISTS ( SELECT t FROM JudgeTest t WHERE t.id = j.id AND t.dateEleve IS NOT NULL AND t.dateJuge IS NULL ) "
          + "AND j.country != 'FR' ")
    public List<Judge> findByNatureJugementAndGradeEleveAndIsNotFrench(String natureJugement);

    @Query(value = "SELECT j FROM Judge j "
          + "WHERE j.natureJugement = ?1 "
          + "AND EXISTS ( SELECT t FROM JudgeTest t WHERE t.id = j.id AND t.dateEleve IS NULL AND t.dateJuge IS NOT NULL AND t.idCommission = ?2 ) "
          + "AND j.country != 'FR' ")
    public List<Judge> findByNatureJugementAndGradeJugeAndIsNotFrenchAndCommission(String natureJugement, String commission);

    @Query(value = "SELECT j FROM Judge j "
          + "WHERE j.natureJugement = ?1 "
          + "AND EXISTS ( SELECT t FROM JudgeTest t WHERE t.id = j.id AND t.dateEleve IS NULL AND t.dateJuge IS NOT NULL ) "
          + "AND j.country != 'FR' ")
    public List<Judge> findByNatureJugementAndGradeJugeAndIsNotFrench(String natureJugement);

    @Query(value = "SELECT j FROM Judge j "
          + "WHERE j.natureJugement = ?1 "
          + "AND EXISTS ( SELECT t FROM JudgeTest t WHERE t.id = j.id AND t.idCommission = ?2 ) "
          + "AND j.country != 'FR' ")
    public List<Judge> findByNatureJugementAndIsNotFrenchAndCommission(String natureJugement, String commission);

    @Query(value = "SELECT j FROM Judge j "
          + "WHERE j.natureJugement = ?1 "
          + "AND EXISTS ( SELECT t FROM JudgeTest t WHERE t.id = j.id) "
          + "AND j.country != 'FR' ")    
    public List<Judge> findByNatureJugementAndIsNotFrench(String natureJugement);

}
