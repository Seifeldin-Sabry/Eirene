package be.kdg.eirene.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Entity
@Table (name = "brainwaves")
@NoArgsConstructor
@Immutable
@Getter
@Setter
public class BrainWaveReading {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "brainwave_id", nullable = false)
	private Long brainwave_id;
	@Column (name = "signal", nullable = false)
	private int signal;
	@Column (name = "focus", nullable = false)
	private int focus;
	@Column (name = "meditation", nullable = false)
	private int meditation;

	@OneToOne (mappedBy = "brainWave")
	private Reading reading;

	public BrainWaveReading(int signal, int focus, int meditation) {
		this.signal = signal;
		this.focus = focus;
		this.meditation = meditation;
	}

	public int getLevel(SessionType type) {
		return switch (type) {
			case FOCUS -> getFocus();
			case MEDITATION -> getMeditation();
		};
	}

	@Override
	public String toString() {
		return "BrainWave{" +
				"signal=" + signal +
				", level=" + focus +
				'}';
	}

	public void setReading(Reading reading) {
		this.reading = reading;
	}
}
