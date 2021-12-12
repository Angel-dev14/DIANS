//Adding map
var map = L.map('map').setView([41.999, 21.4306], 15.4);

L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
  attribution: '&copy; <a href="https://www.openstreetmap.org/copyright%22%3EOpenStreetMap</a> contributors"'
}).addTo(map);

//Adding custom marker
var redIcon = new L.Icon({
  iconUrl: '/images/redmarker.png',
  shadowUrl: '/images/markerShadow.png',
  iconSize: [25, 41],
  iconAnchor: [12, 41],
  popupAnchor: [1, -34],
  shadowSize: [41, 41]
});

var greenIcon = new L.Icon({
  iconUrl: '/images/current-location.png',
  iconSize: [30, 30],
  iconAnchor: [12, 41],
  popupAnchor: [1, -34],
});

var userIcon = new L.Icon({
  iconUrl: '/images/user.png',
  shadowUrl: '/images/markerShadow.png',
  iconSize: [25, 41],
  iconAnchor: [12, 41],
  popupAnchor: [1, -34],
  shadowSize: [41, 41]
});
