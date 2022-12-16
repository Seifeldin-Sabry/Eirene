const wrapper = document.getElementById("sensor-type-tab-content");
const type = wrapper.dataset.brainType;
const brainData = JSON.parse(wrapper.dataset.brain);
const charts = {
	Light: JSON.parse(wrapper.dataset.light),
	Temperature: JSON.parse(wrapper.dataset.temperature),
	Humidity: JSON.parse(wrapper.dataset.humidity),
	Sound: JSON.parse(wrapper.dataset.sound),
	"Heart rate": JSON.parse(wrapper.dataset.heart)
};

const colors = {
	Light: "#ffffff",
	Temperature: "#ff2b2b",
	Humidity: "#03A9F4",
	Sound: "#4CAF50",
	"Heart rate": "#9C27B0",
	Brainwave: "#FEB50D"
};

const allCanvases = document.querySelectorAll("canvas");
const makeChart = (element) => {
	const title = element.dataset.title;
	const brainWaveOptions = {
		data: brainData,
		label: type,
		borderColor: colors["Brainwave"],
		fill: false,
		yAxisID: "brainwave"
	};
	return new Chart(element, {
		type: "line",
		data: {
			labels: charts.Light.map((d, idx) => idx),
			datasets:
				!title ?
					[...Object.entries(charts).map(([key, data]) => (
						{
							data: data,
							label: key,
							borderColor: colors[key],
							fill: false,
							yAxisID: "sensor"
						})),
						brainWaveOptions] :
					[...[charts[title]].map(data => {
						return {
							data: data,
							label: title,
							borderColor: colors[title],
							fill: false,
							yAxisID: "sensor"
						};
					}),
						brainWaveOptions]
		},
		options: {
			scales: {
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
			},
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
allCanvases.forEach(canvas => makeChart(canvas));