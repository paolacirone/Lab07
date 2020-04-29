package it.polito.tdp.poweroutages.model;

import java.time.LocalDateTime;

public class PowerOutages {
	
	private int id; 
	private Nerc nerc; 
	private int numeroPersone; 
	private LocalDateTime dataInizio; 
	private LocalDateTime dataFine;
	
	
	public PowerOutages(int id, Nerc nerc, int numeroPersone, LocalDateTime dataInizio, LocalDateTime dataFine) {
		
		this.id = id;
		this.nerc = nerc;
		this.numeroPersone = numeroPersone;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Nerc getNerc() {
		return nerc;
	}


	public void setNerc(Nerc nerc) {
		this.nerc = nerc;
	}


	public int getNumeroPersone() {
		return numeroPersone;
	}


	public void setNumeroPersone(int numeroPersone) {
		this.numeroPersone = numeroPersone;
	}


	public LocalDateTime getDataInizio() {
		return dataInizio;
	}


	public void setDataInizio(LocalDateTime dataInizio) {
		this.dataInizio = dataInizio;
	}


	public LocalDateTime getDataFine() {
		return dataFine;
	}


	public void setDataFine(LocalDateTime dataFine) {
		this.dataFine = dataFine;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PowerOutages other = (PowerOutages) obj;
		if (id != other.id)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return id+ " "+nerc.getId()+ " " + this.numeroPersone+" "+this.dataInizio+ " "+ this.dataFine;
	} 
	
	
	
	
	
	

}
