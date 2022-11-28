package be.kdg.eirene.service;

import be.kdg.eirene.model.EvaluatedData;
import be.kdg.eirene.model.Reading;
import be.kdg.eirene.model.SessionType;

import java.util.List;

public interface EvaluatorService {

	EvaluatedData formulateReport(List<Reading> toEvaluate, SessionType sessionType);

}
