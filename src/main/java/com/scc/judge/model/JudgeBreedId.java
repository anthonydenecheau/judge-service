package com.scc.judge.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class JudgeBreedId implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private int idRace;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdRace() {
		return idRace;
	}
	public void setIdRace(int idRace) {
		this.idRace = idRace;
	}
}
