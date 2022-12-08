package be.kdg.eirene.service.evaluator;

import be.kdg.eirene.model.EvaluatedData;
import be.kdg.eirene.model.Reading;
import be.kdg.eirene.model.SessionType;

import java.util.List;

public interface ReportGeneratorService {
	EvaluatedData formulateReport(List<Reading> readingList, SessionType sessionType);
}
