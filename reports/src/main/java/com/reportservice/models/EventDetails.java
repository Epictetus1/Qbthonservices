package com.reportservice.models;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "EVENT_DETAILS")
public class EventDetails {
	 @Field ("EventName")
	private String eventName;
	private Date event_date;
	private String Event_skills;
	private String event_slot;
	private int TotalNoofSkillsCovered;
	
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	private int TotalNoofApprovedQuestions;
	private int TotalNoofRejectedQuestions;
	
	public Date getEvent_date() {
		return event_date;
	}
	public void setEvent_date(Date event_date) {
		this.event_date = event_date;
	}
	public String getEvent_skills() {
		return Event_skills;
	}
	public void setEvent_skills(String event_skills) {
		Event_skills = event_skills;
	}
	public String getEvent_slot() {
		return event_slot;
	}
	public void setEvent_slot(String event_slot) {
		this.event_slot = event_slot;
	}
	public int getTotalNoofSkillsCovered() {
		return TotalNoofSkillsCovered;
	}
	public void setTotalNoofSkillsCovered(int totalNoofSkillsCovered) {
		TotalNoofSkillsCovered = totalNoofSkillsCovered;
	}
	public int getTotalNoofApprovedQuestions() {
		return TotalNoofApprovedQuestions;
	}
	public void setTotalNoofApprovedQuestions(int totalNoofApprovedQuestions) {
		TotalNoofApprovedQuestions = totalNoofApprovedQuestions;
	}
	public int getTotalNoofRejectedQuestions() {
		return TotalNoofRejectedQuestions;
	}
	public void setTotalNoofRejectedQuestions(int totalNoofRejectedQuestions) {
		TotalNoofRejectedQuestions = totalNoofRejectedQuestions;
	}
	

}
