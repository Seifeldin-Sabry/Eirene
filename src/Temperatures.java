import java.util.ArrayList;
import java.util.List;

public class Temperatures {

    private List<Temperature> temperatures;

    public Temperatures(List<Temperature> temperatures) {
        this.temperatures = temperatures;
    }

    public Temperatures() {
        this.temperatures = new ArrayList<>();
    }


    public void addTemperature(Temperature temp) {
        temperatures.add(temp);
    }
}
