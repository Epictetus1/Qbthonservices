package com.qbthon.kafkaservices.models;

public class MultipleChoiceQuestion {

	@Override
	public String toString() {
		return "{skills=" + skills + ", taxonomy=" + taxonomy + ", difficultylevel="
				+ difficultylevel + ", classification=" + classification + ", Multipleanswers=" + Multipleanswers
				+ ", topic=" + topic + ", questionsource=" + questionsource + ", questiontext=" + questiontext
				+ ", option1=" + option1 + ", option1correct=" + option1correct + ", option2=" + option2
				+ ", option2correct=" + option2correct + ", option3=" + option3 + ", option3correct=" + option3correct
				+ ", option4=" + option4 + ", option4correct=" + option4correct + ", option5=" + option5
				+ ", option5correct=" + option5correct + ", submitter=" + submitter + ", comments=" + comments + "}";
	}
	String skills;
	 String  taxonomy;
	  String difficultylevel;
	  String classification;
	  String Multipleanswers;
	  String topic;
	  String questionsource;
	  String questiontext;
	  String option1;
	  String option1correct;
	  String option2;
	  String option2correct;
	  String option3;
	  String option3correct;
	  String option4;
	  public MultipleChoiceQuestion(String skills, String taxonomy, String difficultylevel, String classification,
			String multipleanswers, String topic, String questionsource, String questiontext, String option1,
			String option1correct, String option2, String option2correct, String option3, String option3correct,
			String option4, String option4correct, String option5, String option5correct, String submitter,
			String comments) {
		super();
		this.skills = skills;
		this.taxonomy = taxonomy;
		this.difficultylevel = difficultylevel;
		this.classification = classification;
		Multipleanswers = multipleanswers;
		this.topic = topic;
		this.questionsource = questionsource;
		this.questiontext = questiontext;
		this.option1 = option1;
		this.option1correct = option1correct;
		this.option2 = option2;
		this.option2correct = option2correct;
		this.option3 = option3;
		this.option3correct = option3correct;
		this.option4 = option4;
		this.option4correct = option4correct;
		this.option5 = option5;
		this.option5correct = option5correct;
		this.submitter = submitter;
		this.comments = comments;
	}
	String option4correct;
	  String option5;
	  String option5correct;
	  String submitter;
	  String comments;
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	public String getTaxonomy() {
		return taxonomy;
	}
	public void setTaxonomy(String taxonomy) {
		this.taxonomy = taxonomy;
	}
	public String getDifficultylevel() {
		return difficultylevel;
	}
	public void setDifficultylevel(String difficultylevel) {
		this.difficultylevel = difficultylevel;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public String getMultipleanswers() {
		return Multipleanswers;
	}
	public void setMultipleanswers(String multipleanswers) {
		Multipleanswers = multipleanswers;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getQuestionsource() {
		return questionsource;
	}
	public void setQuestionsource(String questionsource) {
		this.questionsource = questionsource;
	}
	public String getQuestiontext() {
		return questiontext;
	}
	public void setQuestiontext(String questiontext) {
		this.questiontext = questiontext;
	}
	public String getOption1() {
		return option1;
	}
	public void setOption1(String option1) {
		this.option1 = option1;
	}
	public String getOption1correct() {
		return option1correct;
	}
	public void setOption1correct(String option1correct) {
		this.option1correct = option1correct;
	}
	public String getOption2() {
		return option2;
	}
	public void setOption2(String option2) {
		this.option2 = option2;
	}
	public String getOption2correct() {
		return option2correct;
	}
	public void setOption2correct(String option2correct) {
		this.option2correct = option2correct;
	}
	public String getOption3() {
		return option3;
	}
	public void setOption3(String option3) {
		this.option3 = option3;
	}
	public String getOption3correct() {
		return option3correct;
	}
	public void setOption3correct(String option3correct) {
		this.option3correct = option3correct;
	}
	public String getOption4() {
		return option4;
	}
	public void setOption4(String option4) {
		this.option4 = option4;
	}
	public String getOption4correct() {
		return option4correct;
	}
	public void setOption4correct(String option4correct) {
		this.option4correct = option4correct;
	}
	public String getOption5() {
		return option5;
	}
	public void setOption5(String option5) {
		this.option5 = option5;
	}
	public String getOption5correct() {
		return option5correct;
	}
	public void setOption5correct(String option5correct) {
		this.option5correct = option5correct;
	}
	public String getSubmitter() {
		return submitter;
	}
	public void setSubmitter(String submitter) {
		this.submitter = submitter;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
}
