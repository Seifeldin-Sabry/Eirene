package be.kdg.eirene.model.readers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrainWaveReading {
	private final int signal;
	private final int focus;

	private final int meditation;

	public BrainWaveReading(int signal, int focus, int meditation) {
		this.signal = signal;
		this.focus = focus;
		this.meditation = meditation;
	}

	@Override
	public String toString() {
		return "BrainWave{" +
				"signal=" + signal +
				", level=" + focus +
				'}';
	}
}
