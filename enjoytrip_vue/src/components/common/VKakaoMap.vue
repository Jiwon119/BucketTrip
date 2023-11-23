<!-- 
    필요한 데이터
    id
    title
    content
    mapX
    mapY
    img
-->

<script setup>
import { ref, watch, onMounted } from "vue";

var map;
const positions = ref([]);
const markers = ref([]);
const infoWindow = ref([]);
const data = ref([]);

// 맵 초기화
const initMap = () => {
    const container = document.getElementById("map");
    const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 3,
    };
    map = new kakao.maps.Map(container, options);
    // loadMarkers();
};

// const props = defineProps({ campingArea: Array, selectedOption: Object });
const props = defineProps({ data: Array, selected: Object, line: Boolean });

watch(
    () => props.selected,
    () => {
        // 이동할 위도 경도 위치를 생성합니다
        var moveLatLon = new kakao.maps.LatLng(props.selected.mapY, props.selected.mapX);
        map.setLevel(3);
        infoWindow.value.forEach(info => {
            info.setMap(null);
        });

        markers.value.forEach(m => {
            if (m.id == props.selected.id) {
                // 인포윈도우를 생성합니다
                var content = '<div class="wrap">' +
                    '    <div class="info">' +
                    '        <div class="title">' +
                    `            ${props.selected.title}` +
                    '        </div>' +
                    '        <div class="body">' +
                    '            <div class="img">' +
                    `                <img src="${props.selected.img}" width="73" height="70">` +
                    '           </div>' +
                    '            <div class="desc">' +
                    `                <div class="ellipsis">${props.selected.content}</div>` +
                    '            </div>' +
                    '        </div>' +
                    '    </div>' +
                    '</div>';

                // 인포윈도우를 생성합니다
                var mapCustomOverlay = new kakao.maps.CustomOverlay({
                    content: content,
                    removable: true,
                    map: map,
                    position: m.getPosition()
                });
                infoWindow.value.push(mapCustomOverlay);
                mapCustomOverlay.setMap(map);
            }
        });
        // // 지도 중심을 부드럽게 이동시킵니다
        // // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
        map.panTo(moveLatLon);
    },
    { deep: true }
);

onMounted(() => {
    if (window.kakao && window.kakao.maps) {
        initMap();
    } else {
        const script = document.createElement("script");
        script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${import.meta.env.VITE_KAKAO_MAP_SERVICE_KEY
            }&libraries=services,clusterer`;
        /* global kakao */
        script.onload = () => kakao.maps.load(() => initMap());
        document.head.appendChild(script);
    }
});

watch(
    () => props.data,
    () => {
        infoWindow.value.forEach(info => {
            info.setMap(null);
        });
        if (!window.kakao || !window.kakao.maps) {
            setTimeout(() => {
                setData();
                setLine();
            }, 300)
        } else {
            if (props.data.length == 0) {
                var moveLatLon = new kakao.maps.LatLng(37.5013068, 127.0396597);
                map.setLevel(5);
                map.panTo(moveLatLon);
            } else {
                setData();
                setLine();
            }
        }
    },
    { deep: true },
);

// 데이터 입력
const setData = () => {
    data.value = props.data;
    positions.value = [];
    props.data.forEach((el) => {
        let obj = {};
        obj.id = el.id;
        obj.title = el.title;
        obj.content = el.content;
        obj.latlng = new kakao.maps.LatLng(el.mapY, el.mapX);
        obj.img = el.img;

        positions.value.push(obj);
    });
    loadMarkers();
}


var polyline;
var lineNumber;
const setLine = () => {
    if (props.line) {
        if (polyline != null) {
            polyline.setMap(null);
        }
        if (lineNumber != null) {
            lineNumber.forEach(element => {
                element.setMap(null);
            });
        }


        var lineList = [];
        lineNumber = [];

        positions.value.forEach((element, index) => {
            var content = `<div class ="label" sty>
                            <span class="left">
                                </span><span class="center">
                                    ${index + 1}
                                </span>
                        </div>`;
            var customOverlay = new kakao.maps.CustomOverlay({
                position: element.latlng,
                content: content,
                zIndex: -1
            });
            lineNumber.push(customOverlay)
            customOverlay.setMap(map);

            lineList.push(element.latlng)
        });

        polyline = new kakao.maps.Polyline({
            path: lineList, // 선을 구성하는 좌표배열 입니다
            strokeWeight: 5, // 선의 두께 입니다
            strokeColor: '#40596B', // 선의 색깔입니다
            strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
            strokeStyle: 'solid' // 선의 스타일입니다
        });

        // 지도에 선을 표시합니다 
        polyline.setMap(map);
    }
}



const loadMarkers = () => {
    // 현재 표시되어있는 marker들이 있다면 map에 등록된 marker를 제거한다.
    deleteMarkers();

    // 마커 이미지를 생성합니다
    //   const imgSrc = require("@/assets/map/markerStar.png");
    // 마커 이미지의 이미지 크기 입니다
    //   const imgSize = new kakao.maps.Size(24, 35);
    //   const markerImage = new kakao.maps.MarkerImage(imgSrc, imgSize);

    // 마커를 생성합니다
    markers.value = [];
    positions.value.forEach((position) => {


        // var imageSrc = 'https://i.ibb.co/6b2j0rT/marker-2.png', // 마커이미지의 주소입니다    
        //     imageSize = new kakao.maps.Size(40, 45), // 마커이미지의 크기입니다
        //     imageOption = { offset: new kakao.maps.Point(20, 45) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
        // var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption)

        const marker = new kakao.maps.Marker({
            map: map, // 마커를 표시할 지도
            position: position.latlng, // 마커를 표시할 위치
            title: position.title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됨.
            clickable: true, // // 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
            // image: markerImage, // 마커의 이미지
        });
        marker.id = position.id;
        markers.value.push(marker);



        // 마커에 click 이벤트를 등록합니다
        kakao.maps.event.addListener(marker, 'click', function () {
            infoWindow.value.forEach(info => {
                info.setMap(null);
            });

            // 인포윈도우 생성
            var content = '<div class="wrap">' +
                '    <div class="info">' +
                '        <div class="title">' +
                `            ${position.title}` +
                '        </div>' +
                '        <div class="body">' +
                '            <div class="img">' +
                `                <img src="${position.img}" width="73" height="70">` +
                '           </div>' +
                '            <div class="desc">' +
                `                <div class="ellipsis">${position.content}</div>` +
                '            </div>' +
                '        </div>' +
                '    </div>' +
                '</div>';

            // 인포윈도우를 생성합니다
            var mapCustomOverlay = new kakao.maps.CustomOverlay({
                content: content,
                removable: true,
                map: map,
                position: position.latlng
            });

            infoWindow.value.push(mapCustomOverlay);
            var moveLatLon = position.latlng;
            map.panTo(moveLatLon);
            // 마커 위에 인포윈도우를 표시합니다
            mapCustomOverlay.setMap(map);
        });
    });

    // 4. 지도를 이동시켜주기
    // 배열.reduce( (누적값, 현재값, 인덱스, 요소)=>{ return 결과값}, 초기값);
    const bounds = positions.value.reduce(
        (bounds, position) => bounds.extend(position.latlng),
        new kakao.maps.LatLngBounds()
    );

    map.setBounds(bounds);
};

const deleteMarkers = () => {
    if (markers.value.length > 0) {
        markers.value.forEach((marker) => marker.setMap(null));
    }
};


</script>

<template>
    <div id="map"></div>
</template>

<style>
#map {
    width: 100%;
    height: 700px;
}

.wrap {
    position: absolute;
    left: 0;
    bottom: 40px;
    width: 288px;
    height: 152px;
    margin-left: -144px;
    text-align: left;
    overflow: hidden;
    font-size: 12px;
    font-family: 'Malgun Gothic', dotum, '돋움', sans-serif;
    line-height: 1.5;
}

.wrap * {
    padding: 0;
    margin: 0;
}

.wrap .info {
    width: 286px;
    height: 140px;
    border-radius: 5px;
    border-bottom: 2px solid #ccc;
    border-right: 1px solid #ccc;
    overflow: hidden;
    background: #fff;
}

.wrap .info:nth-child(1) {
    border: 0;
    box-shadow: 0px 1px 2px #888;
}

.info .title {
    padding: 5px 0 0 10px;
    height: 40px;
    background: #eee;
    border-bottom: 1px solid #ddd;
    font-size: 20px;
    font-weight: bold;
}

.info .body {
    position: relative;
    overflow: hidden;
}

.info .desc {
    position: relative;
    margin: 13px 0 0 90px;
    height: 75px;
}

.desc .ellipsis {
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: wrap;
    font-size: 14px;
}

.desc .jibun {
    font-size: 14px;
    color: #888;
    margin-top: -2px;
    margin-right: 8px;
}

.info .img {
    position: absolute;
    top: 6px;
    left: 5px;
    width: 73px;
    height: 71px;
    border: 1px solid #ddd;
    color: #888;
    overflow: hidden;
}

.info:after {
    content: '';
    position: absolute;
    margin-left: -12px;
    left: 50%;
    bottom: 0;
    width: 22px;
    height: 12px;
    background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png')
}

.info .link {
    color: #5085BB;
}

.label {
    background-color: white;
    padding: 0px 5px;
    border: solid gray 1px;
    border-radius: 5px;
    margin-bottom: 110px;
}

.label * {
    display: inline-block;
    vertical-align: top;
}
</style>
