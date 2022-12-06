package be.kdg.eirene.util;

import be.kdg.eirene.model.Reading;

import java.util.List;

public interface ReadingsAdapt {
	List<Reading> convert(List<Object[]> readings);
}
