package com.srh_heidelberg.mealsanddeals;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Customer {
	private String name;
	private String sex;
	private Date birthday;
	private String nationality;
	private String address;
	private String email;
	private String phonenumber;
	private String customerusername;
	private String password;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
		this.birthday = (Date) formatter.parse(birthday);
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getCustomerusername() {
		return customerusername;
	}
	public void setCustomerusername(String customerusername) {
		this.customerusername = customerusername;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
