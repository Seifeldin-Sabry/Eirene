const signalEl = document.getElementById("signal");
const brainWaveStrengthEl = document.getElementById("brainwaveStrength");
const heartRateEl = document.getElementById("heartRate");
const environmentEl = document.getElementById("environment");
const chartEl = document.getElementById("chartContainer");
const generalAdviceEl = document.getElementById("generalAdvice");

const setElementValue = (element, value) => {
	element.innerText = value;
};

window.onload = function () {

	var dps = []; // dataPoints
	var chart = new CanvasJS.Chart(chartEl, {
		theme: "dark1",
		title: {
			text: "Live Data",
			fontFamily: "comfortana"
		},
		axisY: {
			gridThickness: 0,
			minimum: 0,
			maximum: 100

		},
		axisX: {
			gridThickness: 0,
			valueFormatString: "HH:mm:ss",
			labelAngle: -20,
			labelFontSize: 10
		},
		data: [{
			type: "spline",
			markerSize: 1,
			lineColor: "yellow",
			dataPoints: dps
		}]
	});

	var xVal = 0;
	var updateInterval = 1000;
	var dataLength = 15; // number of dataPoints visible at any point

	var updateChart = function (count) {
		$.ajax({
			type: "GET",
			url: "http://localhost:8081/newsession/api",
			dataType: "json",
			success: function (data, status) {
				yVal = data.averageBrainwaveStrength;
				xVal = new Date();
				dps.push({
					x: xVal,
					y: yVal
				});

				if (dps.length > dataLength) {
					dps.shift();
				}

				chart.render();
				setElementValue(signalEl, data.signal);
				setElementValue(brainWaveStrengthEl, data.brainwaveStrength);
				setElementValue(heartRateEl, data.heartRate);
				setElementValue(environmentEl, data.environment);
				setElementValue(generalAdviceEl, data.generalAdvice);

			}
		});
	};

	updateChart(dataLength);
	setInterval(function () {
		updateChart();
	}, updateInterval);

};