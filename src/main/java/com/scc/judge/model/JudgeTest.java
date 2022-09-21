package com.scc.judge.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "ws_juge_habilitation_travail")
@IdClass(JudgeTestId.class)
public class JudgeTest {

   @Id
   @Column(name = "id", nullable = false)
   private int id;

   @Id
   @Column(name = "epreuve", nullable = false)
   private String epreuve;

   @Column(name = "date_eleve")
   private Timestamp dateEleve;

   @Column(name = "date_juge")
   private Timestamp dateJuge;

   @Column(name = "id_commission", nullable = false)
   private String idCommission;

   @Column(name = "date_maj")
   private Timestamp timestamp;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getEpreuve() {
      return epreuve;
   }

   public void setEpreuve(String epreuve) {
      this.epreuve = epreuve;
   }

   public Timestamp getDateEleve() {
      return dateEleve;
   }

   public void setDateEleve(Timestamp dateEleve) {
      this.dateEleve = dateEleve;
   }

   public String getIdCommission() {
      return idCommission;
   }

   public void setIdCommission(String idCommission) {
      this.idCommission = idCommission;
   }
   
   public Timestamp getDateJuge() {
      return dateJuge;
   }

   public void setDateJuge(Timestamp dateJuge) {
      this.dateJuge = dateJuge;
   }

   public Timestamp getTimestamp() {
      return timestamp;
   }

   public void setTimestamp(Timestamp timestamp) {
      this.timestamp = timestamp;
   }

   public JudgeTest withId(int id) {
      this.setId(id);
      return this;
   }

   public JudgeTest withEpreuve(String epreuve) {
      this.setEpreuve(epreuve);
      return this;
   }

   public JudgeTest withDateEleve(Timestamp dateEleve) {
      this.setDateEleve(dateEleve);
      return this;
   }

   public JudgeTest withDateJuge(Timestamp dateJuge) {
      this.setDateJuge(dateJuge);
      return this;
   }

   public JudgeTest withIdCommission(String idCommission) {
      this.setIdCommission(idCommission);
      return this;
   }

   public JudgeTest withTimestamp(Timestamp timestamp) {
      this.setTimestamp(timestamp);
      return this;
   }

}
