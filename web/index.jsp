<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="vi">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>My Shop</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Favicon -->
        <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">
        <!-- Plugins CSS -->
        <link rel="stylesheet" href="assets/css/plugins.css">
        <!-- Main Style CSS -->
        <link rel="stylesheet" href="assets/css/style.css">

    </head>

    <body>

        <!-- Main Wrapper Start -->
        <!--Offcanvas menu area start-->
        <div class="off_canvars_overlay"></div>

        <jsp:include page="layout/menu.jsp"/>
        <!--slider area start-->
        <div class="slider_area slider_style home_three_slider owl-carousel">
            <div class="single_slider" data-bgimg="https://antimatter.vn/wp-content/uploads/2022/06/anh-ga-choi-2.jpg">
                <div class="container">                  
                    <div class="row align-items-center">
                        <div class="col-12">
                            <div class="slider_content content_one">
                                <h2 style="color: #FAA31B; font-weight: bold; font-size: 50px;
                                    margin-bottom: 50px; text-shadow: 2px 2px 5px #FFFFE0
                                    ">Chào Mừng Bạn Đến Với Fhicken Shop</h2>
                                <a href="MainController?action=productall">Discover Now</a>
                            </div>    
                        </div>
                    </div>
                </div>    
            </div>
            <div class="single_slider" data-bgimg="https://img1.kienthucvui.vn/uploads/2020/08/26/hinh-anh-ga-choi-dep_025731539.jpg">
                <div class="container">
                    <div class="row align-items-center">
                        <div class="col-12">
                            <div class="slider_content content_two">

                                <h2 style="color: #FAA31B; font-weight: bold; font-size: 50px;
                                    margin-bottom: 50px; text-shadow: 2px 2px 5px #FFFFE0
                                    ">Chào Mừng Bạn Đến Với Fhicken Shop</h2>
                                <a href="MainController?action=productall">Discover Now</a>
                            </div>    
                        </div>
                    </div>
                </div>
            </div>
            <div class="single_slider" data-bgimg="https://antimatter.vn/wp-content/uploads/2022/05/anh-ga-choi-cuc-chien.jpg">
                <div class="container">
                    <div class="row align-items-center">
                        <div class="col-12">
                            <div class="slider_content content_three">

                                <h2 style="color: #FAA31B; font-weight: bold; font-size: 50px;
                                    margin-bottom: 50px; text-shadow: 2px 2px 5px #FFFFE0
                                    ">Chào Mừng Bạn Đến Với Fhicken Shop</h2>
                                <a href="MainController?action=productall">Discover Now</a>
                            </div>    
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--slider area end-->

        <!--banner area start-->
        <section class="product_section womens_product bottom">
            <div class="container">
                <div class="row">   
                    <div class="col-12">
                        <div class="section_title">
                            <h2>Sản phẩm của chúng tôi</h2>
                            <p>Sản phẩm dành cho bạn</p>
                        </div>
                    </div> 
                </div>    
                <div class="product_area"> 
                    <div class="row">
                        <div class="product_carousel product_three_column4 owl-carousel">
                            <c:forEach items="${top10}" var="top10">
                                <div class="col-lg-3">
                                    <div class="single_product">
                                        <div class="product_thumb" style=" height: 260px; width: 100%;">
                                            <a class="primary_img" href="MainController?action=productdetail&product_id=${top10.product_id}" style="width: 100%;height: 100%; display: block" ><img src="${top10.img}" style="height: 100%;width: 100%; object-fit: fill"alt=""></a>
                                            <div class="quick_button">
                                                <a href="MainController?action=productdetail&product_id=${top10.product_id}" title="quick_view">Xem sản phẩm</a>
                                            </div>
                                        </div>
                                        <div class="product_content">
                                            <h3><a href="MainController?action=productdetail&product_id=${top10.product_id}">${top10.product_name}</a></h3>
                                            <fmt:setLocale value ="vi_VN"/>
                                            <span class="current_price"><fmt:formatNumber value="${top10.product_price}" type = "currency" currencySymbol="VNĐ"/></span>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!--product section area start-->

        <!--product section area end-->
        <section class="product_section womens_product bottom">
            <div class="container">
                <div class="row">   
                    <div class="col-12">
                        <div class="section_title">
                            <h2>Sản phẩm thịnh hành</h2>
                            <p>Sản phẩm ấn tượng và bán chạy nhất</p>
                        </div>
                    </div> 
                </div>    
                <div class="product_area"> 
                    <div class="row">
                        <div class="product_carousel product_three_column4 owl-carousel">
                            <c:forEach items="${topTrend}" var="td">
                                <div class="col-lg-3">
                                    <div class="single_product">
                                        <div class="product_thumb" style=" height: 260px; width: 100%;">
                                            <a class="primary_img" href="MainController?action=productdetail&product_id=${td.product_id}" style="width: 100%;height: 100%; display: block"><img src="${td.img}" style="height: 100%;width: 100%; object-fit: fill"alt=""></a>
                                            <div class="quick_button">
                                                <a href="MainController?action=productdetail&product_id=${td.product_id}" title="quick_view">Xem sản phẩm</a>
                                            </div>
                                        </div>
                                        <div class="product_content">
                                            <h3><a href="MainController?action=productdetail&product_id=${td.product_id}">${td.product_name}</a></h3>
                                            <span class="current_price"><fmt:formatNumber value="${td.product_price}" type = "currency" currencySymbol="VNĐ"/></span>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--footer area start-->
        <jsp:include page="layout/footer.jsp"/>
        <!--footer area end-->
        <!-- JS
        ============================================ -->

        <!-- Plugins JS -->
        <script src="assets/js/plugins.js"></script>

        <!-- Main JS -->
        <script src="assets/js/main.js"></script>
    </body>

</html>
