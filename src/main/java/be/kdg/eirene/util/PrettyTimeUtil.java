package be.kdg.eirene.util;

import org.ocpsoft.prettytime.PrettyTime;

import java.sql.Timestamp;
import java.time.Duration;

public class PrettyTimeUtil {
	public static String getPrettyTimeAgo(Timestamp from) {
		PrettyTime prettyTime = new PrettyTime();
		return prettyTime.format(from);
	}

	public static String getPrettyDuration(Duration duration) {
		return duration.toString().substring(2).replaceAll("(\\d[HMS])(?!$)", "$1 ").toLowerCase();
	}
}
