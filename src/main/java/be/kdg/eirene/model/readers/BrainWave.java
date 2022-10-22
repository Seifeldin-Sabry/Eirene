package be.kdg.eirene.model.readers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrainWave {
	private int signal;
	private int level;

	public BrainWave(int signal, int level) {
		this.signal = signal;
		this.level = level;
	}

	@Override
	public String toString() {
		return "BrainWave{" +
				"signal=" + signal +
				", level=" + level +
				'}';
	}
}
