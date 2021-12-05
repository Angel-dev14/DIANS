

    var map = L.map('map').setView([41.996, 21.4316], 13);

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '&copy; <a href="https://www.openstreetmap.org/copyright%22%3EOpenStreetMap</a> contributors'
}).addTo(map);

    var popup = marker.bindPopup('<b>Hello world!</b><br />I am a popup.');
    popup.openPopup();

