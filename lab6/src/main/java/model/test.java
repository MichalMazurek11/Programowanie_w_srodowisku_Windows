package model;

public class test {

	private Integer id_wydarzenia;
	private String login;
	private String uczestnik;
	public test(Integer id_wydarzenia, String login, String uczestnik) {
		super();
		this.id_wydarzenia = id_wydarzenia;
		this.login = login;
		this.uczestnik = uczestnik;
	}
	public Integer getId_wydarzenia() {
		return id_wydarzenia;
	}
	public void setId_wydarzenia(Integer id_wydarzenia) {
		this.id_wydarzenia = id_wydarzenia;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getUczestnik() {
		return uczestnik;
	}
	public void setUczestnik(String uczestnik) {
		this.uczestnik = uczestnik;
	}
	
	
}
