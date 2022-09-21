package com.scc.judge.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.scc.judge.config.ServiceConfig;
import com.scc.judge.exceptions.EntityNotFoundException;
import com.scc.judge.model.Judge;
import com.scc.judge.model.JudgeBreed;
import com.scc.judge.repository.BreedRepository;
import com.scc.judge.repository.JudgeRepository;
import com.scc.judge.template.BreedObject;
import com.scc.judge.template.JudgeObject;
import com.scc.judge.template.ResponseObjectList;
import com.scc.judge.utils.CommissionEnum;
import com.scc.judge.utils.GradeEnum;
import com.scc.judge.utils.ShowEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class JudgeService {

   private static final Logger logger = LoggerFactory.getLogger(JudgeService.class);

   @Autowired
   private Tracer tracer;

   @Autowired
   private JudgeRepository judgeRepository;

   @Autowired
   private BreedRepository breedRepository;

   @Autowired
   ServiceConfig config;

   @HystrixCommand(fallbackMethod = "buildFallbackJudgeList", threadPoolKey = "frenchJudgesThreadPool", threadPoolProperties = {
         @HystrixProperty(name = "coreSize", value = "30"),
         @HystrixProperty(name = "maxQueueSize", value = "10") }, commandProperties = {
               @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
               @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "75"),
               @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "7000"),
               @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "15000"),
               @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "5") })
   public ResponseObjectList<JudgeObject> getFrenchShowJudges(ShowEnum show) {

      Span newSpan = tracer.createSpan("getFrenchShowJudges");
      logger.debug("In the judgeService.getFrenchShowJudges() call, trace id: {}",
            tracer.getCurrentSpan().traceIdString());
      try {

         List<Judge> list = new ArrayList<Judge>();
         if (show.equals(ShowEnum.ESIN))
            list = judgeRepository.findByCountryAndNatureJugementAndIsInternationalOrderByLastNameAscFirstNameAsc("FR",
                  "E", "O");
         else
            list = judgeRepository.findByCountryAndNatureJugementOrderByLastNameAscFirstNameAsc("FR", "E");

         List<JudgeObject> results = new ArrayList<JudgeObject>();
         results = buildResponseObjectJudge(list);

         return new ResponseObjectList<JudgeObject>(results.size(), results);

      } finally {
         newSpan.tag("peer.service", "postgres");
         newSpan.logEvent(org.springframework.cloud.sleuth.Span.CLIENT_RECV);
         tracer.close(newSpan);
      }
   }

   private List<JudgeObject> buildResponseObjectJudge(List<Judge> list) {
      return list.stream()
            .map(_judge -> new JudgeObject()
                  .withId(_judge.getId())
                  .withCivility(_judge.getCivility())
                  .withName(buildName(_judge.getLastName(), _judge.getFirstName())).withAddress(_judge.getAddress())
                  .withCity(_judge.getCity()).withZipCode(_judge.getZipCode()).withEmail(_judge.getEmail())
                  .withCountry(_judge.getCountry())
                  .withNumber(_judge.getNumber()))
            .collect(Collectors.toList());
   }

   private String buildName(String lastName, String firstName) {

      String completeName = "";

      if (!"".equals(lastName) && lastName != null) {
         if (!"".equals(firstName) && firstName != null)
            completeName = lastName + " " + firstName;
         else
            completeName = lastName;
      }

      return completeName;
   }

   @SuppressWarnings("unused")
   private ResponseObjectList<JudgeObject> buildFallbackJudgeList() {

      List<JudgeObject> list = new ArrayList<JudgeObject>();
      list.add(new JudgeObject().withId(0));
      return new ResponseObjectList<JudgeObject>(list.size(), list);
   }

   @SuppressWarnings("unused")
   private ResponseObjectList<JudgeObject> buildFallbackJudgeList(ShowEnum show) {

      List<JudgeObject> list = new ArrayList<JudgeObject>();
      list.add(new JudgeObject().withId(0));
      return new ResponseObjectList<JudgeObject>(list.size(), list);
   }

   @SuppressWarnings("unused")
   private ResponseObjectList<JudgeObject> buildFallbackJudgeList(CommissionEnum commission) {

      List<JudgeObject> list = new ArrayList<JudgeObject>();
      list.add(new JudgeObject().withId(0));
      return new ResponseObjectList<JudgeObject>(list.size(), list);
   }

   @SuppressWarnings("unused")
   private ResponseObjectList<JudgeObject> buildFallbackJudgeList(CommissionEnum commission, GradeEnum grade) {

      List<JudgeObject> list = new ArrayList<JudgeObject>();
      list.add(new JudgeObject().withId(0));
      return new ResponseObjectList<JudgeObject>(list.size(), list);
   }

   @HystrixCommand(fallbackMethod = "buildFallbackJudgeList", threadPoolKey = "internationalJudgesThreadPool", threadPoolProperties = {
         @HystrixProperty(name = "coreSize", value = "30"),
         @HystrixProperty(name = "maxQueueSize", value = "10") }, commandProperties = {
               @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
               @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "75"),
               @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "7000"),
               @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "15000"),
               @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "5") })
   public ResponseObjectList<JudgeObject> getInternationalJudges() {
      Span newSpan = tracer.createSpan("getInternationalJudges");
      logger.debug("In the judgeService.getInternationalJudges() call, trace id: {}",
            tracer.getCurrentSpan().traceIdString());
      try {

         List<Judge> list = new ArrayList<Judge>();
         list = judgeRepository.findByIsInternationalAndCountryNotOrderByLastNameAscFirstNameAsc("O", "FR");

         List<JudgeObject> results = new ArrayList<JudgeObject>();
         results = buildResponseObjectJudge(list);

         return new ResponseObjectList<JudgeObject>(results.size(), results);

      } finally {
         newSpan.tag("peer.service", "postgres");
         newSpan.logEvent(org.springframework.cloud.sleuth.Span.CLIENT_RECV);
         tracer.close(newSpan);
      }
   }

   @HystrixCommand(fallbackMethod = "buildFallbackJudge", threadPoolKey = "judgeByIdThreadPool", threadPoolProperties = {
         @HystrixProperty(name = "coreSize", value = "30"),
         @HystrixProperty(name = "maxQueueSize", value = "10") }, commandProperties = {
               @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
               @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "75"),
               @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "7000"),
               @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "15000"),
               @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "5") }, ignoreExceptions = {
                     EntityNotFoundException.class })
   public JudgeObject getJudgeById(int id) throws EntityNotFoundException {
      Span newSpan = tracer.createSpan("getJudgeById");
      logger.debug("In the judgeService.getJudgeById() call, trace id: {}", tracer.getCurrentSpan().traceIdString());

      JudgeObject result = new JudgeObject();

      try {

         Optional<Judge> _judge = judgeRepository.findById(id);

         if (!_judge.isPresent())
            throw new EntityNotFoundException(JudgeObject.class, "id", String.valueOf(id));

         // Construction de la réponse
         result.withId(_judge.get().getId()).withCivility(_judge.get().getCivility())
               .withName(buildName(_judge.get().getLastName(), _judge.get().getFirstName()))
               .withAddress(_judge.get().getAddress()).withZipCode(_judge.get().getZipCode())
               .withCity(_judge.get().getCity()).withEmail(_judge.get().getEmail())
               .withCountry(_judge.get().getCountry())
               .withNumber(_judge.get().getNumber());

      } finally {
         newSpan.tag("peer.service", "postgres");
         newSpan.logEvent(org.springframework.cloud.sleuth.Span.CLIENT_RECV);
         tracer.close(newSpan);
      }

      return result;
   }

   @SuppressWarnings("unused")
   private JudgeObject buildFallbackJudge(int id) {

      return new JudgeObject().withId(0);

   }

   @HystrixCommand(fallbackMethod = "buildFallbackBreedsList", threadPoolKey = "breedsByIdJudgeThreadPool", threadPoolProperties = {
         @HystrixProperty(name = "coreSize", value = "30"),
         @HystrixProperty(name = "maxQueueSize", value = "10") }, commandProperties = {
               @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
               @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "75"),
               @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "7000"),
               @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "15000"),
               @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "5") })
   public ResponseObjectList<BreedObject> getBreedsByIdJudge(int id, ShowEnum show) {

      Span newSpan = tracer.createSpan("getBreedsByIdJudge");
      logger.debug("In the judgeService.getBreedsByIdJudge() call, trace id: {}",
            tracer.getCurrentSpan().traceIdString());

      List<BreedObject> results = new ArrayList<BreedObject>();
      boolean isInternational = false;

      try {

         if (show.equals(ShowEnum.ESIN))
            isInternational = true;

         List<JudgeBreed> list = new ArrayList<JudgeBreed>();
         list = breedRepository.findById(id);

         for (JudgeBreed _breed : list) {

            // Un juge est habilité à juger une race s/ un show international
            // si il a obtenu la qualification
            if (isInternational && (_breed.getDateJuge() == null))
               continue;

            // Construction de la réponse
            results.add(new BreedObject().withIdRace(_breed.getIdRace()));
         }
         return new ResponseObjectList<BreedObject>(results.size(), results);
      } finally {
         newSpan.tag("peer.service", "postgres");
         newSpan.logEvent(org.springframework.cloud.sleuth.Span.CLIENT_RECV);
         tracer.close(newSpan);
      }

   }

   @SuppressWarnings("unused")
   private ResponseObjectList<BreedObject> buildFallbackBreedsList(int id, ShowEnum show) {

      List<BreedObject> list = new ArrayList<BreedObject>();
      list.add(new BreedObject().withIdRace(0));
      return new ResponseObjectList<BreedObject>(list.size(), list);
   }

   @HystrixCommand(fallbackMethod = "buildFallbackJudgeList", threadPoolKey = "frenchJudgesThreadPool", threadPoolProperties = {
         @HystrixProperty(name = "coreSize", value = "30"),
         @HystrixProperty(name = "maxQueueSize", value = "10") }, commandProperties = {
               @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
               @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "75"),
               @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "7000"),
               @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "15000"),
               @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "5") })
   public ResponseObjectList<JudgeObject> getFrenchWorkingJudges(CommissionEnum commission) {

      Span newSpan = tracer.createSpan("getFrenchWorkingJudges");
      logger.debug("In the judgeService.getFrenchWorkingJudges() call {}, trace id: {}",
            commission.getValue(), tracer.getCurrentSpan().traceIdString());

      List<Judge> list = new ArrayList<Judge>();
      List<JudgeObject> results = new ArrayList<JudgeObject>();

      try {

         if (commission.equals(CommissionEnum.CUNCA))
            list = judgeRepository.findByCountryAndNatureJugementAndCommission("FR","T",commission.getValue());
         else
            list = judgeRepository.findByCountryAndNatureJugement("FR","T");

         results = buildResponseObjectJudge(list);

      } 
      catch (Exception e) {
         logger.error("getFrenchWorkingJudges {} {}",commission.getValue(),e.getMessage());
      }
      finally {
         newSpan.tag("peer.service", "postgres");
         newSpan.logEvent(org.springframework.cloud.sleuth.Span.CLIENT_RECV);
         tracer.close(newSpan);
      }

      return new ResponseObjectList<JudgeObject>(results.size(), results);

   }
   
   @HystrixCommand(fallbackMethod = "buildFallbackJudgeList", threadPoolKey = "frenchJudgesThreadPool", threadPoolProperties = {
         @HystrixProperty(name = "coreSize", value = "30"),
         @HystrixProperty(name = "maxQueueSize", value = "10") }, commandProperties = {
               @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
               @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "75"),
               @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "7000"),
               @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "15000"),
               @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "5") })
   public ResponseObjectList<JudgeObject> getFrenchWorkingJudgesByGrade(CommissionEnum commission, GradeEnum grade) {

      Span newSpan = tracer.createSpan("getFrenchWorkingJudgesByGrade");
      logger.debug("In the judgeService.getFrenchWorkingJudgesByGrade() call {}, trace id: {}",
            commission.getValue(), tracer.getCurrentSpan().traceIdString());

      List<Judge> list = new ArrayList<Judge>();
      List<JudgeObject> results = new ArrayList<JudgeObject>();

      try {

         switch (grade) {
         case ELEVE:
            if (commission.equals(CommissionEnum.CUNCA))
               list = judgeRepository.findByCountryAndNatureJugementAndGradeEleveAndCommission("FR","T",commission.getValue());
            else
               list = judgeRepository.findByCountryAndNatureJugementAndGradeEleve("FR","T");
            break;
         case JUGE:
            if (commission.equals(CommissionEnum.CUNCA))
               list = judgeRepository.findByCountryAndNatureJugementAndGradeJugeAndCommission("FR","T",commission.getValue());
            else
               list = judgeRepository.findByCountryAndNatureJugementAndGradeJuge("FR","T");
            break;
         default:
            if (commission.equals(CommissionEnum.CUNCA))
               list = judgeRepository.findByCountryAndNatureJugementAndCommission("FR","T",commission.getValue());
            else
               list = judgeRepository.findByCountryAndNatureJugement("FR","T");
            break;
         }
         
         results = buildResponseObjectJudge(list);

      } 
      catch (Exception e) {
         logger.error("getFrenchWorkingJudgesByGrade {} {}",commission.getValue(),e.getMessage());
      }
      finally {
         newSpan.tag("peer.service", "postgres");
         newSpan.logEvent(org.springframework.cloud.sleuth.Span.CLIENT_RECV);
         tracer.close(newSpan);
      }

      return new ResponseObjectList<JudgeObject>(results.size(), results);

   }   
   
   @HystrixCommand(fallbackMethod = "buildFallbackJudgeList", threadPoolKey = "internationalJudgesThreadPool", threadPoolProperties = {
         @HystrixProperty(name = "coreSize", value = "30"),
         @HystrixProperty(name = "maxQueueSize", value = "10") }, commandProperties = {
               @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
               @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "75"),
               @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "7000"),
               @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "15000"),
               @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "5") })
   public ResponseObjectList<JudgeObject> getForeignerWorkingJudgesByGrade(CommissionEnum commission, GradeEnum grade) {

      Span newSpan = tracer.createSpan("getForeignerWorkingJudgesByGrade");
      logger.debug("In the judgeService.getForeignerWorkingJudgesByGrade() call {}, trace id: {}",
            commission.getValue(), tracer.getCurrentSpan().traceIdString());

      List<Judge> list = new ArrayList<Judge>();
      List<JudgeObject> results = new ArrayList<JudgeObject>();

      try {

         switch (grade) {
         case ELEVE:
            if (commission.equals(CommissionEnum.CUNCA))
               list = judgeRepository.findByNatureJugementAndGradeEleveAndIsNotFrenchAndCommission("T",commission.getValue());
            else
               list = judgeRepository.findByNatureJugementAndGradeEleveAndIsNotFrench("T");
            break;
         case JUGE:
            if (commission.equals(CommissionEnum.CUNCA))
               list = judgeRepository.findByNatureJugementAndGradeJugeAndIsNotFrenchAndCommission("T",commission.getValue());
            else
               list = judgeRepository.findByNatureJugementAndGradeJugeAndIsNotFrench("T");
            break;
         default:
            if (commission.equals(CommissionEnum.CUNCA))
               list = judgeRepository.findByNatureJugementAndIsNotFrenchAndCommission("T",commission.getValue());
            else
               list = judgeRepository.findByNatureJugementAndIsNotFrench("T");
            break;
         }
         
         results = buildResponseObjectJudge(list);

      } 
      catch (Exception e) {
         logger.error("getForeignerWorkingJudgesByGrade {} {}",commission.getValue(),e.getMessage());
      }
      finally {
         newSpan.tag("peer.service", "postgres");
         newSpan.logEvent(org.springframework.cloud.sleuth.Span.CLIENT_RECV);
         tracer.close(newSpan);
      }

      return new ResponseObjectList<JudgeObject>(results.size(), results);

   }   
   
}
