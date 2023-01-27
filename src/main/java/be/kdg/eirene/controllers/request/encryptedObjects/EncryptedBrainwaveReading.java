package be.kdg.eirene.controllers.request.encryptedObjects;

import lombok.Getter;

@Getter
public class EncryptedBrainwaveReading {
	private String signal;
	private String focus;
	private String meditation;
}


//{
//  "timestamp": "2022-11-23T15:46:38.015",
//  "brainWave": {
//    "signal": 200,
//    "focus": 0,
//    "meditation": 0
//  },
//  "sensorData": {
//    "heartRate": {
//      "value": 66.0,
//      "unit": "BPM"
//    },
//    "temperature": {
//      "value": 19.9,
//      "unit": "C"
//    },
//    "sound": {
//      "value": 50.0,
//      "unit": "DB"
//    },
//    "humidity": {
//      "value": 66.0,
//      "unit": "PERCENT"
//    },
//    "light": {
//      "value": 0.0,
//      "unit": "L"
//    }
//  }
//}