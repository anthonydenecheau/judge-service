package com.scc.judge.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "ws_juge_habilitation_expos")
@IdClass(JudgeBreedId.class)
public class JudgeBreed{

	@Id
	@Column(name = "id", nullable = false)
	private int id;

	@Id
	@Column(name = "id_race", nullable = false)
	private int idRace;

	@Column(name = "date_juge")
	private Timestamp dateJuge;
	
	@Column(name = "date_maj")
	private Timestamp timestamp;

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public int getIdRace() { return idRace; }
	public void setIdRace(int idRace) { this.idRace = idRace; }
	
	public Timestamp getDateJuge() { return dateJuge; }
	public void setDateJuge(Timestamp dateJuge) { this.dateJuge = dateJuge; }

	public Timestamp getTimestamp() { return timestamp; }
	public void setTimestamp(Timestamp timestamp) { this.timestamp = timestamp; }

	public JudgeBreed withId(int id){ this.setId( id ); return this; }
	public JudgeBreed withIdRace(int idRace){ this.setIdRace(idRace); return this; }
	public JudgeBreed withDateJuge(Timestamp dateJuge){ this.setDateJuge( dateJuge ); return this; }
	public JudgeBreed withTimestamp(Timestamp timestamp){ this.setTimestamp(timestamp); return this; }

}
