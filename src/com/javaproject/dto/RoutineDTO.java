package com.javaproject.dto;

import java.util.Date;

public class RoutineDTO {
	private int id;
	private String name, wNames, shoulder, chest, leg, back, arm, wBody;
	private Date wDate;
	
	public int getId() {
		return id;
	}
	public String getShoulder() {
		return shoulder;
	}
	public void setShoulder(String shoulder) {
		this.shoulder = shoulder;
	}
	public String getChest() {
		return chest;
	}
	public void setChest(String chest) {
		this.chest = chest;
	}
	public String getLeg() {
		return leg;
	}
	public void setLeg(String leg) {
		this.leg = leg;
	}
	public String getBack() {
		return back;
	}
	public void setBack(String back) {
		this.back = back;
	}
	public String getArm() {
		return arm;
	}
	public void setArm(String arm) {
		this.arm = arm;
	}
	public String getwBody() {
		return wBody;
	}
	public void setwBody(String wBody) {
		this.wBody = wBody;
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
	public String getwNames() {
		return wNames;
	}
	public void setwNames(String wNames) {
		this.wNames = wNames;
	}
	public Date getwDate() {
		return wDate;
	}
	public void setwDate(Date wDate) {
		this.wDate = wDate;
	}

	
}
