package be.kdg.eirene.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Entity
@Table(name = "brainwaves")
@NoArgsConstructor
@Immutable
@Getter
@Setter
public class BrainWaveReading {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "brainwave_id", nullable = false)
	private Long id;
	@Column(name = "signal", nullable = false)
	private int signal;
	@Column(name = "focus", nullable = false)
	private int focus;
	@Column(name = "meditation", nullable = false)
	private int meditation;

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
