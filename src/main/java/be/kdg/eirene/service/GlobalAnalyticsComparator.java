package be.kdg.eirene.service;

import be.kdg.eirene.repository.GlobalAnalCategory;
import org.springframework.stereotype.Service;

@Service
public interface GlobalAnalyticsComparator {
	GlobalAnalCategory getGlobalAverageComparison(double userAverage, double globalAverage);
}
