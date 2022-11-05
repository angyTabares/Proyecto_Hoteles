let map
let dir
let marcadoresActuales = []

function crearMapa(lugares) {
    console.log(lugares)
    mapboxgl.accessToken = 'pk.eyJ1IjoiZGFuaXRoZWNyZWF0b3IiLCJhIjoiY2tyMnZkbzd4MXJ1cDJ1bzdlNHpmdnNxYyJ9.789aY-Czm5oJfDpOtj-8eA';
    map = new mapboxgl.Map({
        container: "mapaHome",
        style: 'mapbox://styles/mapbox/streets-v11'
    });


    map.addControl(new mapboxgl.GeolocateControl({
        positionOptions: {
            enableHighAccuracy: true
        },

        trackUserLocation: true,

    }))

    let dir = new MapboxDirections({
        accessToken: mapboxgl.accessToken,
        unit: 'metric',
        language: 'es'
    })


    map.on("load", function () {
        //  ubicacionActual(map);
        marcadorPosActual();
        ubicarLugares(lugares, map);


    })


}

function test() {
    let usuario = new mapboxgl.Marker()
    let options = {
        units: 'kilometers'
    }
    if (marcadoresActuales != null) {

        if ("geolocation" in navigator) {
            navigator.geolocation.getCurrentPosition(position => {
                usuario.setLngLat([position.coords.longitude, position.coords.latitude])

                marcadoresActuales.forEach((m) => {
                    let mLng = m._lngLat.lng
                    let mLat = m._lngLat.lat
                    let marcadorLugar = [mLng, mLat]
                    let {lng, lat} = usuario.getLngLat()
                    let marcadorUsuario = [lng, lat]
                    let distancia = turf.distance(marcadorUsuario, marcadorLugar, options);


                })
            })

        }


    }

}


function marcadorPosActual() {

    if ("geolocation" in navigator) {
        navigator.geolocation.getCurrentPosition(position => {
            new mapboxgl.Marker().setLngLat([position.coords.longitude, position.coords.latitude]).addTo(map)
        })
    }
}


function crearRuta(lugarLng, lugarLat, lugarNombre) {

    console.log(lugarLat)
    if ("geolocation" in navigator) {
        navigator.geolocation.getCurrentPosition(position => {
            let lat = position.coords.latitude
            let lng = position.coords.longitude

            dir = new MapboxDirections({
                accessToken: mapboxgl.accessToken,
                unit: 'metric',
                language: 'es',
                interactive: false,

            })
            dir.setOrigin([lng, lat])
            dir.setDestination([lugarLng, lugarLat])
            map.addControl(dir, 'top-right')
            console.log(dir.getOrigin())
            console.log(dir.getDestination())

        })
    }
}


function ubicarLugares(lugares, map) {

    let bounds = new mapboxgl.LngLatBounds()


    for (let l of lugares) {
        let el = document.createElement('div');
        if (l.tipo === 'Bar') {
            el.className = 'markerBar'
        } else {
            if (l.tipo === 'Cafe') {
                el.className = 'markerCafe'
            } else {
                if (l.tipo === 'Hotel') {
                    el.className = 'markerHotel'
                } else {
                    el.className = 'markerRestaurante'
                }
            }
        }
        let m = new mapboxgl.Marker(el).setLngLat([l.lng, l.lat]).setPopup(new mapboxgl.Popup({
            closeButton: false,
            closeOnClick: true
        }).setHTML(
            `<div class="p-3"> 
                   <a class="fs-3" href='http://localhost:8080/detalleLugar.xhtml?lugar=${l.id}'>${l.nombre}</a>
                   <p class="mb-2">${l.tipo}</p>
                   <p class="mb-1">${l.descripcion}</p>
                   <button class="btn btnComoLlegar w-100" onclick="crearRuta('${l.lng}',' ${l.lat}','${l.nombre}')">Como llegar</button>
             </div>`
        )).addTo(map)
        bounds.extend([l.lng, l.lat])
        marcadoresActuales.push(m)
    }


    map.fitBounds(bounds,
        {
            zoom: 15,
            padding: 100
        }
    )

}






