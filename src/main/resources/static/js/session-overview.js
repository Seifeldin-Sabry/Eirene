const makeChart = (element) => {
	const data = JSON.parse(element.dataset.sensorReadings);
	return new Chart(element, {
		type: "line",
		data: {
			labels: data.map((d, idx) => idx + 1),
			datasets: [
				{
					data: data,
					label: element.dataset.title,
					borderColor: "#8e5ea2",
					fill: false
				}
			]
		}
	});
};

const allCanvases = document.querySelectorAll("canvas");
allCanvases.forEach(canvas => makeChart(canvas));

