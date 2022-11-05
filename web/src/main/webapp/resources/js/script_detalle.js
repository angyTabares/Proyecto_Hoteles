window.onload = function () {
    const imagenInicial = document.getElementById("imagenInicial")
    let lng = document.getElementById("lng").value;
    let lat = document.getElementById("lat").value;
    let tipo = document.getElementById("tipo").value;
    mapboxgl.accessToken = 'pk.eyJ1IjoiZGFuaXRoZWNyZWF0b3IiLCJhIjoiY2tyMnZkbzd4MXJ1cDJ1bzdlNHpmdnNxYyJ9.789aY-Czm5oJfDpOtj-8eA';
    let map = new mapboxgl.Map({
        container: "mapDetalleLugar",
        style: 'mapbox://styles/mapbox/streets-v11',
        center: [lng, lat],
        zoom: 14,
    });


    map.on("load", function () {
        crearMarkerLugar(lng, lat, tipo, map);
    })


}

function crearMarkerLugar(lng, lat, tipo, map) {
    console.log(tipo)
    let el = document.createElement('div');
    if (tipo === 'Bar') {
        el.className = 'markerBar'
    } else {
        if (tipo === 'Cafe') {
            el.className = 'markerCafe'
        } else {
            if (tipo === 'Hotel') {
                el.className = 'markerHotel'
            } else {
                el.className = 'markerRestaurante'
            }
        }
    }

    let popup = new mapboxgl.Popup({closeOnClick: true, closeButton: false})
        .setLngLat([lng, lat])
        .setHTML('<p>Aqui esta el lugar!</p>')
        .addTo(map);

    let marker = new mapboxgl.Marker(el).setLngLat([lng, lat]).addTo(map)
}

function OcultarButton(btn)
{
    $(btn).style.display = "none";
    document.getElementById("aprobado").style.display="block";
    document.getElementById("denegar").style.display = "none";
}

function OcultarButtonD(btn)
{
    $(btn).style.display = "none";
    document.getElementById("denegado").style.display="block";
    document.getElementById("aprobar").style.display = "none";
}
