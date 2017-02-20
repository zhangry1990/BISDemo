<%--
  Created by IntelliJ IDEA.
  User: zhangying
  Date: 2017/1/19
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.thinvent.wxgl.security.support.UserContext" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctxp" value="${pageContext.request.contextPath}"></c:set>
<c:set var="currentUser" value="<%=UserContext.getCurrentUser()%>"></c:set>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>

        <title>无锡市公路养护现代化综合决策分析系统</title>

        <!-- Common styles and scripts -->
        <link type="text/css" rel="stylesheet" href="${ctxp}/css/bootstrap/bootstrap.min.css" />
        <link type="text/css" rel="stylesheet" href="${ctxp}/css/bootstrap/bootstrap-responsive.min.css" >
        <link type="text/css" rel="stylesheet" href="${ctxp}/css/themes/style-metro.css" >
        <link type="text/css" rel="stylesheet" href="${ctxp}/css/themes/style.css" >
        <link type="text/css" rel="stylesheet" href="${ctxp}/css/themes/style-responsive.css" >

        <!-- 皮肤样式 -->

        <link type="text/css" rel="stylesheet" href="${ctxp}/css/themes/default.css" >

        <!--/* Per-page placeholder for additional links */-->
        <sitemesh:write property='ex-section.CSS' />

    </head>

    <%-- update by zhangying 20170213 start--%>
    <body class="page-header-fixed page-sidebar-fixed">
        <div class="header navbar navbar-inverse navbar-fixed-top">
            <div class="navbar-inner">
                <p class="logo"></p>

                <ul class="kjMenu">
                    <c:forEach items="${currentUser.resources}" var="topMenu">
                        <li id="top_li_${topMenu.id}">
                            <a href="javascript:void(0)">${topMenu.resourceName}</a>
                        </li>
                    </c:forEach>
                </ul>
                <ul class="kjMenu loginInfo">
                    <!--通过如下方式,可以当登录者的真实姓名（或其他属性）展示于此-->
                    <li>
                        欢迎您 <strong>${currentUser.username}</strong>
                    </li>
                    <li>
                        <a href="javascript:void(0)" onclick="user_helper.logout();">退出</a>
                    </li>
                </ul>
            </div>
        </div>

        <div class="page-container row-fluid">

            <!-- 左侧菜单 -->
            <div class="page-sidebar nav-collapse collapse">
                <div class="slimScrollDiv">
                    <ul class="page-sidebar-menu">
                        <!-- 一级菜单 -->
                        <div class="firstMenuDiv">
                            <p class="sscd sidebar-toggler">
                                <a href="javascript:void(0)">
                                    <span class="sscd-Icon"></span>
                                </a>
                            </p>
                            <div class="firstMenu" id="scrollDiv">
                                <c:forEach items="${currentUser.resources}" var="topItem">
                                    <ul id="first_ul_${topItem.id}">
                                        <c:forEach items="${topItem.children}" var="firstMenu">
                                            <li id="first_li_${firstMenu.id}">
                                                <c:choose>
                                                    <c:when test="${not empty firstMenu.resourceUri}">
                                                        <a href="${ctxp}${firstMenu.resourceUri}">
                                                            <span class="jggl-Icon"></span>${firstMenu.resourceName}
                                                        </a>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <a href="javascript:void(0)">
                                                            <span class="jggl-Icon"></span>${firstMenu.resourceName}
                                                        </a>
                                                    </c:otherwise>
                                                </c:choose>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </c:forEach>
                            </div>
                            <p class="btn-up"><small id="btn1"></small></p>
                            <p class="btn-down"><small id="btn2"></small></p>
                        </div>

                        <!-- 二级菜单 -->
                        <div id="collapseExample" class="secondMenuDiv">
                        <div class="secondMenu" id="scrollBar">

                            <c:forEach items="${currentUser.resources}" var="topItem">
                                <c:forEach items="${topItem.children}" var="firstMenu">
                                    <ul id="second_ul_${firstMenu}">
                                        <c:forEach items="${firstMenu.children}" var="secondMenu">
                                            <li class="click-on" id="second_li_${secondMenu.id}"><a href="${ctxp}${secondMenu.resourceUri}">${secondMenu.resourceName}</a></li>
                                        </c:forEach>
                                    </ul>
                                </c:forEach>
                            </c:forEach>

                        </div>
                    </div>

                    </ul>
                </div>

            </div>

            <!-- 右侧主体 -->
            <div class="page-content">
                <div class="container-fluid">
                    <!-- 位置导航 -->
                    <div class="row-fluid">
                        <p class="breadcrumb" style="background-color: #fff; border-radius: 0;">
                            您所在的位置：系统设置 > 基础信息 > 角色权限管理
                        </p>
                    </div>

                    <!-- 页面主体 -->
                    <div class="row-fluid">
                        <sitemesh:write property='body'/>
                    </div>
                </div>
            </div>

            <!-- 页脚 -->
            <div class="footer">
                <div class="footer-inner">
                    版权所有  思创数码科技股份有限公司
                </div>
            </div>
        </div>



        <%-- update by zhangying 20170214 end--%>

        <script type="text/javascript">
            var user_helper = {
                logout: function() {
                    if (window.confirm("确认退出吗？")) {
                        window.location.href = "${ctxp}/logout";
                    }
                }
            };
        </script>

        <script type="text/javascript" src="${ctxp}/js/jquery-1.12.0.min.js"></script>
        <script type="text/javascript" src="${ctxp}/js/jquery-ui-1.10.1.custom.min.js"></script>
        <script type="text/javascript" src="${ctxp}/js/bootstrap/bootstrap.min.js"></script>

        <script type="text/javascript" src="${ctxp}/js/ui/app.js"></script>
        <script type="text/javascript" src="${ctxp}/js/ui/jq_scroll.js"></script>


        <script type="text/javascript">

            /*一级菜单高度*/
            function fristMenuHeight() {
                var showlineNum;
                if($(".page-sidebar").outerHeight() == 0){

                    var windowH = $(".secondMenuDiv").outerHeight();

                } else {

                    var windowH = $(".page-sidebar").outerHeight();
                }

                var firstMenuH = windowH - 120;
                showlineNum = parseInt(firstMenuH / 76);
                $(".firstMenu").css("height", showlineNum*76+"px");
                $("#scrollDiv").Scroll({ line: 1, speed: 500,up: "btn2", down: "btn1", showline:showlineNum });
            }

            $(document).ready(function (e) {
                App.init();
                fristMenuHeight();
                $(window).resize(function(e){
                    fristMenuHeight();
                });


                /*注册一级菜单单击事件*/
                var $firstMenus = $("#scrollDiv").find("ul");
                var $firstMenuLis = $firstMenus.find("li");

                //一级菜单点击后如果存在二级菜单则显示二级菜单
                $firstMenuLis.click(function() {
                    $("#scrollBar").find("ul").hide();
                    var currentFirstMenuId = this.id.substring(9);
                    $("#second_ul_" + currentFirstMenuId).show();
                });


                initMenu();
            });


            var initMenu = function() {

            };
        </script>

        <!-- 页面js引用script-->
        <sitemesh:write property='ex-section.ScriptHead' />
        <!-- 页面js代码-->
        <sitemesh:write property='ex-section.ScriptBody' />

    </body>
</html>