package it.uniroma2.sapr.tansfer.bean;

import java.sql.Date;

/**
 * Questa classe è l'oggetto che viene creato prelevando i dati dalla RequestPilot.
 * Tale oggetto viene utilizzazto poi per tutte le operazioni che si vogliono fare sul db
 * 
 * @author Danilo Butrico
 * @version 1.0
 * @mail dbutricod@gmail.com
 *
 */
public class Pilot {
	private String name;
	private String surname;
	private String nation;
	private String state;
	private String pilotLicense;
	private String taxCode;
	private Date birthDate;
	private String residence;
	private String phone;
	private String mail;
	private String password;
	
	public Pilot(String name, String surname, String nation, String state, String pilotLicense, String taxCode,
			Date birthDate, String residence, String phone, String mail, String password) {
		super();
		this.name = name;
		this.surname = surname;
		this.nation = nation;
		this.state = state;
		this.pilotLicense = pilotLicense;
		this.taxCode = taxCode;
		this.birthDate = birthDate;
		this.residence = residence;
		this.phone = phone;
		this.mail = mail;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPilotLicense() {
		return pilotLicense;
	}

	public void setPilotLicense(String pilotLicense) {
		this.pilotLicense = pilotLicense;
	}

	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getResidence() {
		return residence;
	}

	public void setResidence(String residence) {
		this.residence = residence;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((nation == null) ? 0 : nation.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((pilotLicense == null) ? 0 : pilotLicense.hashCode());
		result = prime * result + ((residence == null) ? 0 : residence.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result + ((taxCode == null) ? 0 : taxCode.hashCode());
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
		Pilot other = (Pilot) obj;
		if (birthDate == null) {
			if (other.birthDate != null)
				return false;
		} else if (!birthDate.equals(other.birthDate))
			return false;
		if (mail == null) {
			if (other.mail != null)
				return false;
		} else if (!mail.equals(other.mail))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (nation == null) {
			if (other.nation != null)
				return false;
		} else if (!nation.equals(other.nation))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (pilotLicense == null) {
			if (other.pilotLicense != null)
				return false;
		} else if (!pilotLicense.equals(other.pilotLicense))
			return false;
		if (residence == null) {
			if (other.residence != null)
				return false;
		} else if (!residence.equals(other.residence))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		if (taxCode == null) {
			if (other.taxCode != null)
				return false;
		} else if (!taxCode.equals(other.taxCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pilot [name=" + name + ", surname=" + surname + ", nation=" + nation + ", state=" + state
				+ ", pilotLicense=" + pilotLicense + ", taxCode=" + taxCode + ", birthDate=" + birthDate
				+ ", residence=" + residence + ", phone=" + phone + ", mail=" + mail + ", password=" + password + "]";
	}

}
