import {chartsConfigs, colors} from "./utils.js";

const makeChart = (element) => {
	const readings = JSON.parse(element.dataset.eireneReadings);
	const isFocus = element.id.startsWith("focus");
	return new Chart(element, {
		type: "line",
		data: {
			labels: readings.map(d => new Date(d.timestamp).toISOString().split("T")[0]),
			datasets: [{
				data: readings.map(d => isFocus ? d.brainWave.focus : d.brainWave.meditation),
				label: isFocus ? "Focus" : "Meditation",
				borderColor: colors.Brainwave,
				fill: false,
				yAxisID: "brainwave"
			},
				{
					data: readings.map(d => d.sensorData.heartRate),
					label: "Heart Rate",
					borderColor: colors["Heart rate"],
					fill: false,
					yAxisID: "sensor"
				},
				{
					data: readings.map(d => d.sensorData.temperature),
					label: "Temperature",
					borderColor: colors.Temperature,
					fill: false,
					yAxisID: "sensor"
				}
			]
		},
		options: chartsConfigs
	});
};

const allCanvases = document.querySelectorAll("canvas");
allCanvases.forEach(canvas => makeChart(canvas));
