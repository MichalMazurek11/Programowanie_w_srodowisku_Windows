package model;

import java.time.LocalDateTime;

public class User {
	private int id_klienta;
	private String imie;
	private String nazwisko;
	private String login;
	private String password;
	private String email;
	private String uprawnienia;
	private LocalDateTime data_rejestracji;
	public User(int id_klienta, String imie, String nazwisko, String login, String password, String email,
			String uprawnienia, LocalDateTime data_rejestracji) {
		super();
		this.id_klienta = id_klienta;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.login = login;
		this.password = password;
		this.email = email;
		this.uprawnienia = uprawnienia;
		this.data_rejestracji = data_rejestracji;
	}
	public int getId_klienta() {
		return id_klienta;
	}
	public void setId_klienta(int id_klienta) {
		this.id_klienta = id_klienta;
	}
	public String getImie() {
		return imie;
	}
	public void setImie(String imie) {
		this.imie = imie;
	}
	public String getNazwisko() {
		return nazwisko;
	}
	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUprawnienia() {
		return uprawnienia;
	}
	public void setUprawnienia(String uprawnienia) {
		this.uprawnienia = uprawnienia;
	}
	public LocalDateTime getData_rejestracji() {
		return data_rejestracji;
	}
	public void setData_rejestracji(LocalDateTime data_rejestracji) {
		this.data_rejestracji = data_rejestracji;
	}
	
	
}