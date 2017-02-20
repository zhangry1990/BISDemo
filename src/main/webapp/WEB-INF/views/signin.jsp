<%--
  Created by IntelliJ IDEA.
  User: zy14s
  Date: 2017/1/19
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctxp" value="${pageContext.request.contextPath}"></c:set>

<%
    Object error = request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
    String errorMessage = null;
    if (error != null) {
        errorMessage = "\"" + ((Exception) error).getMessage() + "\"";
        request.getSession().removeAttribute("SPRING_SECURITY_LAST_EXCEPTION");
    }

    response.setHeader("X-Responded-JSON", "{\"status\":401}");
%>

<html>
    <head>
        <meta name="viewport" content="width=device-width, minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--网站logo-->
        <%--<link rel="shortcut icon" href="${ctxp}/content/icons/favicon.ico" type="image/x-icon" />--%>
        <title>无锡市公路养护现代化综合决策分析系统</title>

        <!-- Bootstrap core CSS -->
        <link href="${ctxp}/css/bootstrap/bootstrap.css" rel="stylesheet" >

        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <link href="${ctxp}/css/bootstrap/ie10-viewport-bug-workaround.css" rel="stylesheet" >

        <!-- Custom styles for this template -->
        <link href="${ctxp}/css/themes/signin.css" rel="stylesheet" >

        <style style="text/css">
            #errorPanel {
                width: 100%;
                height: auto;
                margin-left: auto;
                margin-right: auto;
                line-height: 30px;
                text-align: center;
                color: #F44336;
                display: none;
                line-height: 1.5em;
            }
        </style>

        <script type="text/javascript" src="${ctxp}/js/bootstrap/ie8-responsive-file-warning.js"></script>
        <script type="text/javascript" src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script type="text/javascript" src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    </head>
    <body>
        <div class="container">
            <form class="form-signin" id="loginForm" name="loginForm" action="${ctxp}/login" method="POST">
                <h2 class="form-signin-heading">Please sign in</h2>
                <label for="username" class="sr-only"></label>
                <input type="text" id="username" name="username" class="form-control" placeholder="username" value="admin" required autofocus>
                <label for="password" class="sr-only">Password</label>
                <input type="password" id="password" name="password" autocomplete="off" class="form-control" placeholder="password" value="1" required>
                <div class="checkbox">
                    <label>
                        <input type="checkbox" value="remember-me"> Remember me
                    </label>
                </div>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
            </form>
            <div id="errorPanel"></div>
        </div>

        <script type="text/javascript" src="${ctxp}/js/jquery-1.12.0.min.js"></script>
        <script type="text/javascript" src="${ctxp}/js/bootstrap/bootstrap.min.js"></script>

        <script type="text/javascript">
            $(document).ready(function () {
                var errorMessage = <%=(errorMessage == null ? "null" : errorMessage)%>;
                login_helper.showError(errorMessage);
            });

            var login_helper = {
                showError: function(errorMessage) {
                    if (errorMessage !== null && errorMessage !== "") {
                        $("#errorPanel").css("display", "block").html(errorMessage);
                    }
                }
            };
        </script>

        <script src="${ctxp}/js/bootstrap/ie10-viewport-bug-workaround.js"></script>
    </body>
</html>
