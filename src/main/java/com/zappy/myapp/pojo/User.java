package com.zappy.myapp.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "userTable")
@PrimaryKeyJoinColumn(name = "personID")
public class User extends Person {

	// @Id
	// @GeneratedValue
//	@Column(name = "memberId", unique = true, nullable = false)
//	private long memberId;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fromUser",cascade=CascadeType.ALL)
	private Set<Message> messageIsent = new HashSet<Message>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "toUser",cascade=CascadeType.ALL, orphanRemoval=true)//,orphanRemoval=true)
	private Set<Message> messageIreceived = new HashSet<Message>();

	@Column(name = "aboutMe")
	private String aboutMe;

	@Column(name = "aboutLookingFor")
	private String aboutLookingFor;

	@Column(name = "personality")
	private String personality;

	@Column(name = "favoriteActivity")
	private String favoriteActivity;

	@Column(name = "favoriteFood")
	private String favoriteFood;

	@Column(name = "height")
	private float height;

	@Column(name = "weight")
	private float weight;

	@Column(name = "smoking")
	private String smoking;

	@Column(name = "drinking")
	private String drinking;

	@Column(name = "religion")
	private String religion;

	@Column(name = "hometown")
	private String hometown;

	@Column(name = "educationLevel")
	private String educationLevel;

	@Column(name = "occupation")
	private String occupation;

	@Column(name = "bodyStyle")
	private String bodyStyle;

	@Column(name = "hairColor")
	private String hairColor;

	@Column(name = "eyeColor")
	private String eyeColor;

	public User() {
		this.messageIreceived = new HashSet<Message>();
		this.messageIsent = new HashSet<Message>();
	}

	////// How to add new contact and message?
	////// ******* Getters and Setters *******/////

//	public long getMemberId() {
//		return memberId;
//	}
//
//	public void setMemberId(long memberId) {
//		this.memberId = memberId;
//	}

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public String getAboutLookingFor() {
		return aboutLookingFor;
	}

	public void setAboutLookingFor(String aboutLookingFor) {
		this.aboutLookingFor = aboutLookingFor;
	}

	public String getPersonality() {
		return personality;
	}

	public void setPersonality(String personality) {
		this.personality = personality;
	}

	public String getFavoriteActivity() {
		return favoriteActivity;
	}

	public void setFavoriteActivity(String favoriteActivity) {
		this.favoriteActivity = favoriteActivity;
	}

	public String getFavoriteFood() {
		return favoriteFood;
	}

	public void setFavoriteFood(String favoriteFood) {
		this.favoriteFood = favoriteFood;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public String getSmoking() {
		return smoking;
	}

	public void setSmoking(String smoking) {
		this.smoking = smoking;
	}

	public String getDrinking() {
		return drinking;
	}

	public void setDrinking(String drinking) {
		this.drinking = drinking;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getHometown() {
		return hometown;
	}

	public void setHometown(String hometown) {
		this.hometown = hometown;
	}

	public String getEducationLevel() {
		return educationLevel;
	}

	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getBodyStyle() {
		return bodyStyle;
	}

	public void setBodyStyle(String bodyStyle) {
		this.bodyStyle = bodyStyle;
	}

	public String getHairColor() {
		return hairColor;
	}

	public void setHairColor(String hairColor) {
		this.hairColor = hairColor;
	}

	public String getEyeColor() {
		return eyeColor;
	}

	public void setEyeColor(String eyeColor) {
		this.eyeColor = eyeColor;
	}

	public Set<Message> getMessageIsent() {
		return messageIsent;
	}

	public void setMessageIsent(Set<Message> messageIsent) {
		this.messageIsent = messageIsent;
	}

	public Set<Message> getMessageIreceived() {
		return messageIreceived;
	}

	public void setMessageIreceived(Set<Message> messageIreceived) {
		this.messageIreceived = messageIreceived;
	}
	
	
}
