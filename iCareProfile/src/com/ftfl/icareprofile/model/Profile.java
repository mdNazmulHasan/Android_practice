package com.ftfl.icareprofile.model;

public class Profile {

	public String name;
	public String fatherName;
	public String motherName;
	public String dateOfBirth;
	public String weight;
	public String height;
	public String eyeColor;
	public String specialComment;

	public Profile(String name, String fateherName, String motherName,
			String dateOfBirth, String weight, String height, String eyeColor,
			String specialComment) {
		super();
		this.name = name;
		this.fatherName = fateherName;
		this.motherName = motherName;
		this.dateOfBirth = dateOfBirth;
		this.weight = weight;
		this.height = height;
		this.eyeColor = eyeColor;
		this.specialComment = specialComment;
	}

	public String getName() {
		return name;
	}

	public String getFateherName() {
		return fatherName;
	}

	public String getMotherName() {
		return motherName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public String getWeight() {
		return weight;
	}

	public String getHeight() {
		return height;
	}

	public String getEyeColor() {
		return eyeColor;
	}

	public String getSpecialComment() {
		return specialComment;
	}

}
