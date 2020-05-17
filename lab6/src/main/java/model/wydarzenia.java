package model;


import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="wydarzenia")
public class wydarzenia {


	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id_wydarzenia;
	
	@Column(unique = true)
	private String nazwa;
	
	@Column
	private LocalDate termin;
	
	@Column
	private String agenda;

	public int getId_wydarzenia() {
		return id_wydarzenia;
	}

	public String getNazwa() {
		return nazwa;
	}

	public LocalDate getTermin() {
		return termin;
	}

	public String getAgenda() {
		return agenda;
	}

	public void setId_wydarzenia(int id_wydarzenia) {
		this.id_wydarzenia = id_wydarzenia;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public void setTermin(LocalDate termin) {
		this.termin = termin;
	}

	public void setAgenda(String agenda) {
		this.agenda = agenda;
	}
	
	
	
	
	
}
