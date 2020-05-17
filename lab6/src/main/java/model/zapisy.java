package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="zapisy")
public class zapisy {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id_wydarzenia;
	
	@Column
	private String nazwa;
	
	@Column(unique = true)
	private String login;
	
	@Column
	private String uczestnik;
	
	@Column
	private String wyzywienie;
	
	@Column
	private String czyzapisano;

	public int getId_wydarzenia() {
		return id_wydarzenia;
	}

	public String getNazwa() {
		return nazwa;
	}

	public String getLogin() {
		return login;
	}

	public String getUczestnik() {
		return uczestnik;
	}

	public String getWyzywienie() {
		return wyzywienie;
	}

	public String getCzyzapisano() {
		return czyzapisano;
	}

	public void setId_wydarzenia(int id_wydarzenia) {
		this.id_wydarzenia = id_wydarzenia;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setUczestnik(String uczestnik) {
		this.uczestnik = uczestnik;
	}

	public void setWyzywienie(String wyzywienie) {
		this.wyzywienie = wyzywienie;
	}

	public void setCzyzapisano(String czyzapisano) {
		this.czyzapisano = czyzapisano;
	}

}
