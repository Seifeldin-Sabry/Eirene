const wrapper = document.getElementById("sensor-type-tab-content");
const type = wrapper.dataset.brainType;
const brainData = JSON.parse(wrapper.dataset.brain);
const allChart = document.querySelector("#all-chart");
const charts = {
	light: JSON.parse(allChart.dataset.light),
	temperature: JSON.parse(allChart.dataset.temperature),
	humidity: JSON.parse(allChart.dataset.humidity),
	sound: JSON.parse(allChart.dataset.sound),
	heart: JSON.parse(allChart.dataset.heart),
	brainwave: brainData
};

const colors = [
	"#FF6384",
	"#36A2EB",
	"#FFCE56",
	"#FF0000",
	"#00FF00",
	"#0000FF"
];

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
			elements: {
				line: {
					tension: 0.4
				}
			},
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

const allCanvases = document.querySelectorAll(".sensor-chart");
allCanvases.forEach(canvas => makeChart(canvas));

const makeAllChart = (element) => {
	return new Chart(element, {
		type: "line",
		data: {
			labels: charts.light.map((d, idx) => idx),
			datasets:
				[...Object.entries(charts).map(([key, data], idx) => (
					{
						data: data,
						label: key,
						borderColor: colors[idx],
						fill: false
					}))]
		},
		options: {
			elements: {
				line: {
					tension: 0.4
				}
			},
			plugins: {
				tooltip: {
					interaction: {
						intersect: false,
						mode: "index"
					}
				}
			}
		}
	});
};

makeAllChart(allChart);