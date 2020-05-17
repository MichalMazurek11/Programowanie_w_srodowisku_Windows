package model;



import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;


@Entity
@Table(name="user")
public class User  {
	

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id_klienta;
	
	private String imie;
	@Column
	private String nazwisko;
	@Column
	private String login;
	@Column
	private String password;
	
	@Column
	private String email;
	
	@Column(name = "uprawnienia", insertable = false)
	private String uprawnienia = "user";
		
	@CreationTimestamp
	private Date data_rejestracji;

	public int getId_klienta() {
		return id_klienta;
	}

	public String getImie() {
		return imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getUprawnienia() {
		return uprawnienia;
	}

	public Date getData_rejestracji() {
		return data_rejestracji;
	}

	public void setId_klienta(int id_klienta) {
		this.id_klienta = id_klienta;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUprawnienia(String uprawnienia) {
		this.uprawnienia = uprawnienia;
	}

	public void setData_rejestracji(Date data_rejestracji) {
		this.data_rejestracji = data_rejestracji;
	}

	
	
	
	
}