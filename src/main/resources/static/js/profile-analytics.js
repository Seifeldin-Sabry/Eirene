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
				borderColor: "#3e95cd",
				fill: false
			},
				{
					data: readings.map(d => d.sensorData.heartRate),
					label: "Heart Rate",
					borderColor: "#8e5ea2",
					fill: false
				},
				{
					data: readings.map(d => d.sensorData.temperature),
					label: "Temperature",
					borderColor: "#3cba9f",
					fill: false
				}
			]
		}
	});
};

const allCanvases = document.querySelectorAll("canvas");
allCanvases.forEach(canvas => makeChart(canvas));
