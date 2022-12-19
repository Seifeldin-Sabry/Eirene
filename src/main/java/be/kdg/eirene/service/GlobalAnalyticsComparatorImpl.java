package be.kdg.eirene.service;

import be.kdg.eirene.repository.GlobalAnalCategory;
import org.springframework.stereotype.Service;

@Service
public class GlobalAnalyticsComparatorImpl implements GlobalAnalyticsComparator {
	@Override
	public GlobalAnalCategory getGlobalAverageComparison(double userAverage, double globalAverage) {
		double percentile = userAverage / globalAverage * 100;

		if (percentile < GlobalAnalCategory.TERRIBLE.getPercentile()) {
			return GlobalAnalCategory.TERRIBLE;
		}
		if (percentile < GlobalAnalCategory.BAD.getPercentile()) {
			return GlobalAnalCategory.BAD;
		}
		if (percentile < GlobalAnalCategory.OK.getPercentile()) {
			return GlobalAnalCategory.OK;
		}
		if (percentile < GlobalAnalCategory.GOOD.getPercentile()) {
			return GlobalAnalCategory.GOOD;
		}
		if (percentile < GlobalAnalCategory.GREAT.getPercentile()) {
			return GlobalAnalCategory.GREAT;
		}
		return GlobalAnalCategory.EXCELLENT;
	}
}
