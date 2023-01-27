export const colors = {
	Light: "#ffffff",
	Temperature: "#ff2b2b",
	Humidity: "#03A9F4",
	Sound: "#4CAF50",
	"Heart rate": "#9C27B0",
	Brainwave: "#FEB50D"
};

export const chartsConfigs = {
	scales: {
		brainwave: {
			type: "linear",
			display: true,
			position: "right",
			min: 0,
			max: 100,
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
		legend: {
			labels: {
				color: "lightgray",
				font: {
					family: "comfortana",
					size: 13
				}
			}
		},
		tooltip: {
			interaction: {
				intersect: false,
				mode: "index"
			}
		}
	}
};
