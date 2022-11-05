window.onload = function () {

    let enable = true
    let marker
    let marker2
    let flag = false
    mapboxgl.accessToken = 'pk.eyJ1IjoiZGFuaXRoZWNyZWF0b3IiLCJhIjoiY2tyMnZkbzd4MXJ1cDJ1bzdlNHpmdnNxYyJ9.789aY-Czm5oJfDpOtj-8eA';
    let map = new mapboxgl.Map({
        container: "mapLugar",
        style: 'mapbox://styles/mapbox/streets-v11',

    });
    map.addControl(new mapboxgl.NavigationControl())
    map.addControl(new mapboxgl.GeolocateControl({
        positionOptions: {
            enableHighAccuracy: true
        },

        trackUserLocation: true,

    }))


    map.on("load", function (e) {
        if ("geolocation" in navigator) {
            navigator.geolocation.getCurrentPosition(position => {
                map.flyTo({
                    center: [position.coords.longitude, position.coords.latitude],
                    zoom: 15

                })
            })
        }
    })

    map.on("click", function (e) {

        if (enable) {
            if (flag) {
                marker2.remove()
            }
            setLtnLng(e.lngLat.lat, e.lngLat.lng)
            enable = false;
            marker = new mapboxgl.Marker({
                draggable: true
            }).setLngLat([e.lngLat.lng, e.lngLat.lat]).addTo(map);
            marker.on("dragend", function () {
                let lngLat = marker.getLngLat();
                setLtnLng(lngLat.lat, lngLat.lng)


            })
        } else {
            let aux = marker.getLngLat();
            setLtnLng(e.lngLat.lat, e.lngLat.lng)
            if (e.lngLat.lat !== aux.lat && e.lngLat.lng !== aux.lng) {
                marker2 = new mapboxgl.Marker({
                    draggable: true
                }).setLngLat([e.lngLat.lng, e.lngLat.lat]).addTo(map);
                marker2.on("dragend", function () {
                    let lngLat = marker2.getLngLat();
                    setLtnLng(lngLat.lat, lngLat.lng)


                })
                marker.remove()
                flag = true
                enable = true
            }
        }

    });
}

function setLtnLng(lat, lng) {
    console.log(lat + " - " + lng)
    document.getElementById("crear-lugar:latidudLugar").value = lat;
    document.getElementById("crear-lugar:longitudLugar").value = lng;
}

