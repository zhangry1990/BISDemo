<%--
  Created by IntelliJ IDEA.
  User: zy14s
  Date: 2017/1/19
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctxp" value="${pageContext.request.contextPath}"></c:set>

<%--<head>--%>
    <%--<meta name="decorator" content="/WEB-INF/views/layout.jsp" />--%>
<%--</head>--%>

<ex-section id="ScriptHead">
    <!--地图OpenLayers-JS-->
    <script type="text/javascript" src="${ctxp}/js/map/api/ol.js"></script>
</ex-section>

<ex-section id="CSS">
    <!--地图OpenLayers-CSS-->
    <link type="text/css" href="${ctxp}/css/map/api/ol.css" rel="stylesheet"/>

    <style type="text/css">
        /**ol比例尺样式修改*/
        .ol-scale-line {
            background: #2987CC;
            bottom: 40px;
        }

        .ol-scale-line-inner {
            font-size: 16px;
        }
    </style>
</ex-section>

<body>
    <div id="map" style="width: 100%;height: 100%;"></div>
</body>

<ex-section id="ScriptBody">
    <script type="text/javascript">
        var projection = ol.proj.get('EPSG:4326');
        var map = new ol.Map({
            controls: ol.control.defaults({
                attribution: false,
                rotate: false,
                zoom: false
            }).extend([
                new ol.control.ScaleLine()
            ]),
            layers: [
                new ol.layer.Group({
                    layers: [
                        new ol.layer.Tile({
                            id: "network_vector_base",
                            name: "矢量图层",
                            source: new ol.source.XYZ({
                                projection: projection,
                                url: "http://t{0-7}.tianditu.com/DataServer?T=vec_c&x={x}&y={y}&l={z}"
                            })
                        }),
                        new ol.layer.Tile({
                            id: "network_vector_note",
                            name: "矢量注记图层",
                            source: new ol.source.XYZ({
                                projection: projection,
                                url: "http://t{0-7}.tianditu.com/DataServer?T=cva_c&x={x}&y={y}&l={z}"
                            })
                        })
                    ]
                }),
                new ol.layer.Group({
                    layers: null
                }),
                new ol.layer.Group({
                    layers: null
                })
            ],
            view: new ol.View({
                projection: projection,
                center: [120.27, 31.64],
                zoom: 10,
                minZoom: 5,
                maxZoom: 18
            }),
            target: 'map'
        });
    </script>
</ex-section>
