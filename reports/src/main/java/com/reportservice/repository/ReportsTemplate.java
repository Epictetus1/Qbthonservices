package com.reportservice.repository;

import java.util.HashMap;

public interface ReportsTemplate {
	public HashMap<String,Integer> getByEventName();
	public HashMap<Integer,Integer> getByTotalNoofApprovedQuestions();
}
