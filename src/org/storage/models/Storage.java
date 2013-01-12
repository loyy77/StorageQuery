package org.storage.models;

import java.sql.Timestamp;

public class Storage {
	private int id;
	private String name;
	private float price;
	private String type;
	private int number;
	private Timestamp makeTime;
	private int useDay;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Timestamp getMakeTime() {
		return makeTime;
	}
	public void setMakeTime(Timestamp makeTime) {
		this.makeTime = makeTime;
	}
	public int getUseDay() {
		return useDay;
	}
	public void setUseDay(int useDay) {
		this.useDay = useDay;
	}
	
	
}
