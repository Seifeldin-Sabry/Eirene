package be.kdg.eirene.model.readers;

import java.util.ArrayList;
import java.util.List;

//TODO: refactor for spring
//NOTE: this is just to fix the code right now, logic is flawed
public class Readings {
    List<DataStream> dataStreams;

    public Readings() {
        this.dataStreams = new ArrayList<>();
    }

    public boolean addReading(DataStream dataStream) {
        return dataStreams.add(dataStream);
    }
}
