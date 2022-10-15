import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PacketParser {

    boolean isDataByte(List<Integer> packet) {
        return packet.size() > 3 && packet.get(packet.size() - 2) == 170 && packet.get(packet.size() - 1) == 170;
    }

    /**
     * Parse a packet into a map of headers and values
     *
     * @param packet a list of bytes
     * @return a map of headers and values
     */
    Map<String, Integer> parse(List<Integer> packet) {
        String[] headers = {"SYNC", "SYNC", "PLENGTH", "SIGNAL CODE", "SIGNAL VALUE",
                "ASIC_EEG_POWER_INT", "VLENGTH", "DELTA START", "DELTA", "DELTA END", "THETA START", "THETA", "THETA END",
                "LOWALPHA START", "LOWALPHA", "LOWALPHA END", "HIGHALPHA START", "HIGHALPHA", "HIGHALPHA END",
                "LOWBETA START", "LOWBETA", "LOWBETA END", "HIGHBETA START", "HIGHBETA", "HIGHBETA END",
                "LOWGAMMA START", "LOWGAMMA", "LOWGAMMA END", "MIDGAMMA START", "MIDGAMMA", "MIDGAMMA END", "ATTENTION CODE",
                "ATTENTION VALUE", "MEDITATION CODE", "MEDITATION VALUE", "CHECKSUM"};
        Map<String, Integer> parsedData = new LinkedHashMap<>();
        for (int i = 0; i < headers.length; i++) {
            parsedData.put(headers[i], packet.get(i));
        }
        return parsedData;
    }
}
