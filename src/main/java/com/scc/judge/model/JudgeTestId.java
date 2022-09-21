package com.scc.judge.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class JudgeTestId implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String epreuve;
	
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
}
