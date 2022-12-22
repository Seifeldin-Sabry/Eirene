import {chartsConfigs, colors} from "./utils.js";

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
		options: chartsConfigs
	});
};

const allCanvases = document.querySelectorAll("canvas");
allCanvases.forEach(canvas => makeChart(canvas));
