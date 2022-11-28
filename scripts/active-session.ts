const signalEl = document.getElementById("signal") as HTMLParagraphElement
const brainWaveStrengEl = document.getElementById("brainwaveStrength") as HTMLParagraphElement
const heartRateEl = document.getElementById("heartRate") as HTMLParagraphElement
const environmentEl = document.getElementById("environment") as HTMLParagraphElement

const setElementValue = (element: HTMLElement, value: string) => {
	element.innerText = value
}