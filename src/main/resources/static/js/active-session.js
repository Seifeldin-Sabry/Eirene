const signalEl = document.getElementById("signal");
const brainWaveStrengEl = document.getElementById("brainwaveStrength");
const heartRateEl = document.getElementById("heartRate");
const environmentEl = document.getElementById("environment");

const setElementValue = (element, value) => {
	element.innerText = value;
};