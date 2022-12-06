const makeChart = (element, data, isFocusStat = false) => {
	return new Chart(element, {
		type: "line",
		data: {
			labels: data.map(d => new Date(d.timestamp).toISOString().split("T")[0]),
			datasets: [{
				data: data.map(d => isFocusStat ? d.brainWave.focus : d.brainWave.meditation),
				label: isFocusStat ? "Focus" : "Meditation",
				borderColor: "#3e95cd",
				fill: false
			},
				{
					data: data.map(d => d.sensorData.heartRate),
					label: "Heart Rate",
					borderColor: "#8e5ea2",
					fill: false
				},
				{
					data: data.map(d => d.sensorData.temperature),
					label: "Temperature",
					borderColor: "#3cba9f",
					fill: false
				}
			]
		},
		options: {
			title: {
				display: true,
				text: "Brain Wave Data"
			}
		}
	});
};


// let firstLevelTabs = document.querySelectorAll(".nav-tabs .nav-link");
//
// let focusTabs = document.querySelectorAll("#focus-tabs-links .nav-link");
// let focusTabsContent = document.querySelectorAll("#focus-tabs-content .tab-pane");
//
// let meditationTabs = document.querySelectorAll("#meditation-tabs-links .nav-link");
// let meditationTabsContent = document.querySelectorAll("#meditation-tabs-content .nav-link");
//
//
// firstLevelTabs.forEach((tab) => {
// 	tab.addEventListener("click", (event) => {
// 		event.preventDefault();
// 		firstLevelTabs.forEach((tab) => {
// 			tab.classList.remove("active");
// 		});
// 		tab.classList.add("active");
// 	});
// });
//
// focusTabs.forEach((tab) => {
// 	tab.addEventListener("click", (event) => {
// 		event.preventDefault();
// 		focusTabs.forEach((tab) => {
// 			tab.classList.remove("active");
// 		});
// 		tab.classList.add("active");
//
//
// 		focusTabsContent.forEach((tab) => {
// 			tab.classList.remove("active");
// 			console.log(tab);
// 			tab.querySelectorAll(".chart-container").classList.remove("hidden");
// 		});
// 		let contentEl = document.querySelector("#focus-tabs-content " + tab.getAttribute("href"));
// 		contentEl.classList.add("active");
// 		contentEl.querySelector(".chart-container").classList.remove("hidden");
// 	});
// });
//
// meditationTabs.forEach((tab) => {
// 	tab.addEventListener("click", (event) => {
// 		event.preventDefault();
// 		meditationTabs.forEach((tab) => {
// 			tab.classList.remove("active");
// 		});
// 		tab.classList.add("active");
//
// 		meditationTabsContent.forEach((tab) => {
// 			tab.classList.remove("active");
// 		});
// 		document.querySelector("#meditation-tabs-content " + tab.getAttribute("href")).classList.add("active");
// 	});
// });


// Select the tab links and tab content containers
const focusTabLinks = document.querySelector("#focus-tabs-links");
const focusTabContent = document.querySelector("#focus-tabs-content");
const meditationTabLinks = document.querySelector("#meditation-tabs-links");
const meditationTabContent = document.querySelector("#meditation-tabs-content");
const focusTabPane = document.querySelector("#focus");
const meditationTabPane = document.querySelector("#meditation");

// Add an event listener to the tab links to handle tab clicks
focusTabLinks.addEventListener("click", (event) => {
	// Get the clicked tab link and the associated tab content
	const clickedTabLink = event.target;
	const clickedTabContent = document.querySelector(clickedTabLink.getAttribute("href"));

	// Check if the clicked tab link and tab content exist
	if (clickedTabLink && clickedTabContent) {
		// Deactivate the currently active tab link and tab content
		const activeTabLink = focusTabLinks.querySelector(".active");
		const activeTabContent = focusTabContent.querySelector(".active");
		activeTabLink.classList.remove("active");
		activeTabContent.classList.remove("active");

		// Activate the clicked tab link and tab content
		clickedTabLink.classList.add("active");
		clickedTabContent.classList.add("active");
	}
});

// Add an event listener to the tab links to handle tab clicks
meditationTabLinks.addEventListener("click", (event) => {
	// Get the clicked tab link and the associated tab content
	const clickedTabLink = event.target;
	const clickedTabContent = document.querySelector(clickedTabLink.getAttribute("href"));

	// Check if the clicked tab link and tab content exist
	if (clickedTabLink && clickedTabContent) {
		// Deactivate the currently active tab link and tab content
		const activeTabLink = meditationTabLinks.querySelector(".active");
		const activeTabContent = meditationTabContent.querySelector(".active");
		activeTabLink.classList.remove("active");
		activeTabContent.classList.remove("active");

// Activate the clicked tab link and tab content
		clickedTabLink.classList.add("classList");
		clickedTabContent.classList.add("classList");
	}
});

// Select the "Focus" and "Meditation" tab links
const focusTabLink = document.querySelector("a[href=\"#focus\"]");
const meditationTabLink = document.querySelector("a[href=\"#meditation\"]");

// Add an event listener to the "Focus" and "Meditation" tab links to handle tab clicks
focusTabLink.addEventListener("click", (event) => {
	// Deactivate the currently active tab link and tab content in the "Meditation" tab
	const activeMeditationTabLink = meditationTabLinks.querySelector(".active");
	const activeMeditationTabContent = meditationTabContent.querySelector(".active");
	activeMeditationTabLink.classList.remove("active");
	activeMeditationTabContent.classList.remove("active");
	meditationTabPane.classList.add("hidden");
	focusTabPane.classList.remove("hidden");

// Activate the first tab link and tab content in the "Focus" tab
	const firstFocusTabLink = focusTabLinks.querySelector("a");
	const firstFocusTabContent = focusTabContent.querySelector(firstFocusTabLink.getAttribute("href"));
	firstFocusTabLink.classList.add("active");
	firstFocusTabContent.classList.add("active");
});

meditationTabLink.addEventListener("click", (event) => {
	// Deactivate the currently active tab link and tab content in the "Focus" tab
	const activeFocusTabLink = focusTabLinks.querySelector(".active");
	const activeFocusTabContent = focusTabContent.querySelector(".active");
	activeFocusTabLink.classList.remove("active");
	activeFocusTabContent.classList.remove("active");
	focusTabPane.classList.add("hidden");
	meditationTabPane.classList.remove("hidden");

	// Activate the first tab link and tab content in the "Meditation" tab
	const firstMeditationTabLink = meditationTabLinks.querySelector("a");
	const firstMeditationTabContent = meditationTabContent.querySelector(firstMeditationTabLink.getAttribute("href"));
	firstMeditationTabLink.classList.add("active");
	firstMeditationTabContent.classList.add("active");
});




