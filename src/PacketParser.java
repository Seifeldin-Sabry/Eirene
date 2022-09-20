import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PacketParser {

    boolean isDataByte(List<Integer> packet){
            return packet.size()>2 && packet.get(packet.size()-2) == 170 && packet.get(packet.size()-1) == 170;
    }

    /**
     * Parse a packet into a map of headers and values
     * @param packet a list of bytes
     * @param validateChecksum whether to validate the checksum
     * @return a map of headers and values
     */
    Map<String,Integer> parse(List<Integer> packet, boolean validateChecksum){
        String[] headers = {"SYNC","SYNC","PLENGTH","SIGNAL CODE","SIGNAL VALUE",
                "ASIC_EEG_POWER_INT","VLENGTH","DELTA START","DELTA","DELTA END","THETA START","THETA","THETA END",
                "LOWALPHA START","LOWALPHA","LOWALPHA END","HIGHALPHA START","HIGHALPHA","HIGHALPHA END",
                "LOWBETA START","LOWBETA","LOWBETA END","HIGHBETA START","HIGHBETA","HIGHBETA END",
                "LOWGAMMA START","LOWGAMMA","LOWGAMMA END","MIDGAMMA START","MIDGAMMA","MIDGAMMA END","ATTENTION CODE",
        "ATTENTION VALUE","MEDITATION CODE","MEDITATION VALUE","CHECKSUM"};
        Map<String,Integer> parsedData = new LinkedHashMap<>();
        for(int i = 0; i<headers.length;i++){
            parsedData.put(headers[i],packet.get(i));
        }
        if(isChecksumValid(parsedData)){
            return validateChecksum ? parsePayload(parsedData) : parsedData;
        }
        else{
            return null;
        }
    }
    boolean isChecksumValid(Map<String,Integer> parsedData){
        int checksum = 0;
        Map<String,Integer> payload = extractPayload(parsedData);
        for(int i = 0; i<payload.size();i++){
            checksum += payload.values().toArray()[i] != null ? (int)payload.values().toArray()[i] : 0;
        }
        checksum &= 0xFF;
        checksum = ~checksum & 0xFF;
        return checksum == parsedData.get("CHECKSUM");
    }
    Map<String,Integer> extractPayload(Map<String,Integer> parsedData){
        Map<String,Integer> payload = new HashMap<>();
        for(int i = 2; i<parsedData.size()-1;i++){
            payload.put(parsedData.keySet().toArray()[i].toString(),parsedData.values().toArray()[i] != null ? (int)parsedData.values().toArray()[i] : 0);
        }
        return payload;
    }

    Map<String,Integer> parsePayload(Map<String,Integer> parsedData){
        Map<String,Integer> payload = extractPayload(parsedData);
        Map<String,Integer> parsedPayload = new LinkedHashMap<>();
        int bytesParsed = 0;
        int length = parsedData.get("PLENGTH");
        int excode = 0x55;
        int code;
        int extendedCodeLevel;

        while(bytesParsed<length){
            extendedCodeLevel=0;
            while((int)payload.values().toArray()[bytesParsed]==excode){
                extendedCodeLevel++;
                bytesParsed++;
            }
            code=(int)payload.values().toArray()[bytesParsed++];
            if(code >= 0x80){
                length=(int)payload.values().toArray()[bytesParsed++];
            }
            else{
                length=1;
            }
            parsedPayload.put("EXCODE",excode);
            parsedPayload.put("CODE",code);
            parsedPayload.put("LENGTH",length);
            for(int i=0;i<length;i++){
                parsedPayload.put("VALUE"+i,(int)payload.values().toArray()[bytesParsed++]);
            }
            bytesParsed+=length;

        }
        return parsedPayload;
    }
}
