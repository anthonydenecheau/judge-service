package com.scc.judge.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "enci_juge_habilitation_expos")
@IdClass(JudgeBreedId.class)
public class JudgeBreed{

	@Id
	@Column(name = "id", nullable = false)
	private int id;

	@Id
	@Column(name = "id_race", nullable = false)
	private int idRace;

	@Column(name = "date_qualifie")
	private Timestamp dateQualifie;
	
	@Column(name = "date_maj")
	private Timestamp timestamp;

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public int getIdRace() { return idRace; }
	public void setIdRace(int idRace) { this.idRace = idRace; }
	
	public Timestamp getDateQualifie() { return dateQualifie; }
	public void setDateQualifie(Timestamp isInternational) { this.dateQualifie = dateQualifie; }

	public Timestamp getTimestamp() { return timestamp; }
	public void setTimestamp(Timestamp timestamp) { this.timestamp = timestamp; }

	public JudgeBreed withId(int id){ this.setId( id ); return this; }
	public JudgeBreed withIdRace(int idRace){ this.setIdRace(idRace); return this; }
	public JudgeBreed withDateQualifie(Timestamp dateQualifie){ this.setDateQualifie( dateQualifie ); return this; }
	public JudgeBreed withTimestamp(Timestamp timestamp){ this.setTimestamp(timestamp); return this; }

}
