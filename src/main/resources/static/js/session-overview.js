const wrapper = document.getElementById("sensor-type-tab-content");
const type = wrapper.dataset.brainType;
const brainData = JSON.parse(wrapper.dataset.brain);
const makeChart = (element) => {
	const data = JSON.parse(element.dataset.sensorReadings);
	return new Chart(element, {
		type: "line",
		data: {
			labels: data.map((d, idx) => idx),
			datasets: [
				{
					data: data,
					label: element.dataset.title,
					borderColor: "#51b90d",
					fill: false,
					yAxisID: "sensor"
				},
				{
					data: brainData,
					label: type,
					borderColor: "#8b29b0",
					fill: false,
					yAxisID: "brainwave"
				}
			]
		},
		options: {
			plugins: {
				tooltip: {
					interaction: {
						intersect: false,
						mode: "index"
					}
				}
			},
			scales: {
				sensor: {
					type: "linear",
					display: true,
					position: "left"
				},
				brainwave: {
					type: "linear",
					display: true,
					position: "right",
					min: 0,
					max: 100,

					// grid line settings
					grid: {
						drawOnChartArea: false // only want the grid lines for one axis to show up
					}
				}
			}
		}
	});
};

const allCanvases = document.querySelectorAll("canvas");
allCanvases.forEach(canvas => makeChart(canvas));
