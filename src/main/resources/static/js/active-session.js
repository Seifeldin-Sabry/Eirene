const signalEl = document.getElementById("signal");
const brainWaveStrengthEl = document.getElementById("brainwaveStrength");
const heartRateEl = document.getElementById("heartRate");
const environmentEl = document.getElementById("environment");
const generalAdviceEl = document.getElementById("generalAdvice");

const sessionTimerEl = document.getElementById("session-timer");

const graphEl = document.getElementById("graph");

const UPDATE_INTERVAL = 1000;
const DATA_LENGTH = 15;

const START_TIME = Date.now();

const setElementValue = (element, value) => {
	element.textContent = value;
};

const currentTime = () => {
	const delta = new Date(Date.now() - START_TIME);
	return `${delta.getUTCHours() ? delta.getUTCHours() + "h " : ""}${delta.getUTCMinutes() ? delta.getUTCMinutes() + "m " : ""}${delta.getUTCSeconds() + "s"}`;
};

const fetchSensorData = async () => {
	let response = await fetch("http://localhost:8081/newsession/api",
		{
			method: "GET",
			headers: {
				accept: "application/json"
			}
		});
	return await response.json();
};

const createChart = () => {
	return new Chart(graphEl, {
		type: "line",
		data: {
			labels: [],
			datasets: [
				{
					data: [],
					label: graphEl.dataset.type,
					borderColor: "#FEB50D",
					fill: false
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
			}
		}
	});
};

const addData = (chart, label, data) => {
	chart.data.labels.push(label);
	chart.data.datasets[0].data.push(data);
	chart.update();
};

const removeData = (chart) => {
	if (chart.data.datasets[0].data.length < DATA_LENGTH) return;
	chart.data.labels.shift();
	chart.data.datasets[0].data.shift();
	chart.update();
};

const chart = createChart();

setInterval(async () => {
	const data = await fetchSensorData();
	removeData(chart);
	addData(chart, currentTime(), data.brainwaveStrengthValue);

	setElementValue(signalEl, data.signal);
	setElementValue(brainWaveStrengthEl, data.brainwaveStrength);
	setElementValue(heartRateEl, data.heartRate);
	setElementValue(environmentEl, data.environment);
	setElementValue(generalAdviceEl, data.generalAdvice);

	setElementValue(sessionTimerEl, currentTime());
}, UPDATE_INTERVAL);
