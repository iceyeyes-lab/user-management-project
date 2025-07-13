function initializeAddress(prefId, cityId) {
    const prefSelect = document.getElementById("prefSelect");
    const citySelect = document.getElementById("citySelect");

    function updateCityOptions(selectedPrefId) {
        citySelect.innerHTML = '<option value="">市区町村を選択してください</option>';
        if (cityMap[selectedPrefId]) {
            cityMap[selectedPrefId].forEach(function(city) {
                const option = document.createElement("option");
                option.value = city.id;
                option.textContent = city.name;
                citySelect.appendChild(option);
            });
        }
    }

    prefSelect.addEventListener("change", function() {
        updateCityOptions(this.value);
    });

    if (prefId && prefId !== "-1") {
        prefSelect.value = prefId;
        updateCityOptions(prefId);
        setTimeout(function() {
            citySelect.value = cityId;
        }, 0);
    }
}
