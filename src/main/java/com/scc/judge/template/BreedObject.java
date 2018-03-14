package com.scc.judge.template;

import io.swagger.annotations.ApiModelProperty;

public class BreedObject{

	@ApiModelProperty(notes = "Race id", position = 1, allowEmptyValue=true)
	private int idRace;

	public int getIdRace() { return idRace; }
	public void setIdRace(int idRace) { this.idRace = idRace; }
	
	public BreedObject withIdRace(int idRace){ this.setIdRace(idRace); return this; }
}
