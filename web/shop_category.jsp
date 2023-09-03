
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html class="no-js" lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Shop category</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Favicon -->
        <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">

        <!-- CSS 
        ========================= -->


        <!-- Plugins CSS -->
        <link rel="stylesheet" href="assets/css/plugins.css">

        <!-- Main Style CSS -->
        <link rel="stylesheet" href="assets/css/style.css">

    </head>

    <body>
<fmt:setLocale value ="vi_VN"/>
        <!-- Main Wrapper Start -->
        <!--Offcanvas menu area start-->
        <div class="off_canvars_overlay"></div>
        <jsp:include page="layout/menu.jsp"/>

        <!--breadcrumbs area start-->
        <div class="breadcrumbs_area">
            <div class="container">   
                <div class="row">
                    <div class="col-12">
                        <div class="breadcrumb_content">
                            <ul>
                                <li><a href="home">Trang chủ</a></li>
                                <li>/</li>
                                <li>Cửa hàng</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>         
        </div>
        <!--breadcrumbs area end-->

        <!--shop  area start-->
        <div class="shop_area shop_reverse">
            <div class="container">
                <div class="shop_inner_area">
                    <div class="row">
                        <div class="col-lg-3 col-md-12">
                            <!--sidebar widget start-->
                            <div class="sidebar_widget">
                                <div class="widget_list widget_categories">
                                    <h2>Danh mục</h2>
                                    <ul>
                                        <li style="font-size: medium"><a href="MainController?action=productall">TẤT CẢ</a></li>
                                        <c:forEach items="${CategoryData}" var="c">
                                        <li style="font-size: 15px"><a  href="MainController?action=listByCategory&category_id=${c.category_id}">${c.category_name}</a></li>
                                            </c:forEach>
                                            <!--<li><a href="MainController?action=packageall">Package</a></li>-->
                                    </ul>
                                </div>
                            </div>
                            <!--sidebar widget end-->
                        </div>
                        <div class="col-lg-9 col-md-12">
                            <!--shop wrapper start-->
                            <!--shop toolbar start-->
                            <div class="shop_title">
                                <h2>Sản phẩm</h2>
                            </div>
                            <div class="shop_toolbar_wrapper" style="border:none">
                                <div class="dropdown ">
                                    <button style="color: #000; background-color: #ffffff;border:none; font-family: sans-serif;" class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        Bộ lọc
                                    </button>
                                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                        <a class="dropdown-item" href="MainController?action=sortlow">Giá từ thấp đến cao</a>
                                        <a class="dropdown-item" href="MainController?action=sorthigh">Giá từ cao xuống thấp</a>
                                        <a class="dropdown-item" href="MainController?action=sorta">A-Z</a>
                                    </div>
                                </div>
                            </div>
                            <div class="row shop_wrapper">
                                <c:forEach items="${ProductData}" var="p">
                                    <div class="col-lg-4 col-md-4 col-12 ">
                                        <div class="single_product">
                                            <div class="product_thumb" style=" height: 260px; width: 100%;">
                                                <a class="primary_img" href="MainController?action=productdetail&product_id=${p.product_id}" style="width: 100%;height: 100%; display: block"><img src="${p.img}"  style="height: 100%;width: 100%; object-fit: fill"alt=""></a>

                                                <div class="quick_button">
                                                    <a href="MainController?action=productdetail&product_id=${p.product_id}"title="quick_view">Xem sản phẩm</a>
                                                </div>
                                            </div>

                                            <div class="product_content grid_content">
                                                <h3><a href="MainController?action=productdetail&product_id=${p.product_id}">${p.product_name}</a></h3>
                                                <span class="current_price"><fmt:formatNumber value="${p.product_price}" type = "currency" currencySymbol="VNĐ"/></span>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                            <c:if test="${num != 1}"> 
                            <c:set var="page" value="${page}"/>
                            <div class="shop_toolbar t_bottom" style="border: none;">
                                <div class="pagination">
                                    <ul>
                                        <c:forEach begin="${1}" end="${num}" var="i">
                                            <li class="${i==page?"current":""}"><a href="MainController?action=product&page=${i}">${i}</a></li>
                                            </c:forEach>

                                    </ul>
                                </div>
                            </div>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--shop  area end-->

        <!--footer area start-->
        <jsp:include page="layout/footer.jsp"/>
        <!--footer area end-->

        <!-- Plugins JS -->
        <script src="assets/js/plugins.js"></script>

        <!-- Main JS -->
        <script src="assets/js/main.js"></script>
    </body>

</html>