

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<style>
    .imglogo{
        width: 80px;
        height:80px;
    }
</style>

<div class="offcanvas_menu">
    <div class="canvas_open">
        <a href="javascript:void(0)"><i class="ion-navicon"></i></a>
    </div>
    <div class="offcanvas_menu_wrapper">
        <div class="canvas_close">
            <a href="javascript:void(0)"><i class="ion-android-close"></i></a>  
        </div>
        <div class="welcome_text">
            <ul>
                <li><span>Giao hàng miễn phí: </span>Hãy tận dụng thời gian của chúng tôi để lưu lại sự kiện </li>
                <li><span>Trả hàng miễn phí: </span> Đảm bảo sự hài lòng</li>
            </ul>
        </div>
        <div class="top_right">
            <ul>
                <li class="top_links"><a href="#">Tài Khoản của tôi <i class="ion-chevron-down"></i></a>
                    <ul class="dropdown_links">
                        <c:if test="${sessionScope.user.user_name!=null}">
                            <li><a href="my-account.jsp">${sessionScope.user.user_name}</a></li>
                            </c:if>

                        <c:if test="${sessionScope.user.user_name == null}">
                            <li><a href="my-account.jsp">Tài khoản của tôi</a></li>
                            </c:if>

                        <c:if test="${sessionScope.user == null}">
                            <li><a href="login.jsp">Đăng nhập</a></li>
                            </c:if>

                        <c:if test="${sessionScope.user != null}">
                            <li><a href="user?action=login">Đăng xuất</a></li>
                            </c:if>

                        <c:if test="${fn: toUpperCase(sessionScope.user.isAdmin) == 'ADMIN'}">
                            <li><a href="MainController?action=dashboard">Quản lý</a></li>
                            </c:if>
                    </ul>
                </li> 
            </ul>
        </div>
        
        <div class="search_bar">
            <form action="MainController?action=search" method="POST">
                <input name="text" placeholder="Tìm kiếm..." type="text">
                <button type="submit"><i class="ion-ios-search-strong"></i></button>
            </form>
        </div>
        <div class="cart_area">
            <div class="cart_link">
                <a href="cart?action=showcart"><i class="fa fa-shopping-basket">${sessionScope.size}</i>Giỏ Hàng</a>
            </div>
        </div>
        <div id="menu" class="text-left ">
            <ul class="offcanvas_main_menu">
                <li class="active">
                    <a href="home">Trang chủ</a>
                </li>
                <li class="active">
                    <a href="product">Sản phẩm</a>
                </li>
                <li class="menu-item-has-children">
                    <a href="about">Chúng tôi</a>
                </li>
                <li class="menu-item-has-children">
                    <a href="contact">Liên hệ</a> 
                </li>
            </ul>
        </div>
        <div class="offcanvas_footer">
            <span><a href="#"><i class="fa fa-envelope-o"></i> phumap942002@gmail.com</a></span>
            <ul>
                <li class="facebook"><a href="https://www.facebook.com/phu.duong.3551380/"><i class="fa fa-facebook"></i></a></li>
                <li class="twitter"><a href="#"><i class="fa fa-twitter"></i></a></li>
                <li class="pinterest"><a href="#"><i class="fa fa-pinterest-p"></i></a></li>
                <li class="google-plus"><a href="#"><i class="fa fa-google-plus"></i></a></li>
                <li class="linkedin"><a href="#"><i class="fa fa-linkedin"></i></a></li>
            </ul>
        </div>
    </div>
</div>
<!--Offcanvas menu area end-->

<!--header area start-->
<header class="header_area header_three">
    <!--header top start-->
    <div class="header_top">
        <div class="container-fluid">   
            <div class="row align-items-center">
                <div class="col-lg-7 col-md-12">
                    <div class="welcome_text">
                        <ul>
                            <li><span>Giao hàng miễn phí:</span>Hãy tận dụng thời gian của chúng tôi để lưu lại sự kiện </li>
                            <li><span>Trả hàng miễn phí</span> Đảm bảo sự hài lòng</li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-5 col-md-12">
                    <div class="top_right text-right">
                        <ul>

                            <c:if test="${sessionScope.user != null}">
                                <li class="top_links"><a href="#">Xin chào ${sessionScope.user.user_name}<i class="ion-chevron-down"></i></a>
                                    </c:if>
                                    <c:if test="${sessionScope.user == null}">
                                <li class="top_links"><a href="login.jsp">Đăng nhập<i class="ion-chevron-down"></i></a>
                                    </c:if>
                                <ul class="dropdown_links">
                                    <c:if test="${sessionScope.user != null}">
                                        <li><a href="MainController?action=myaccount">Tài khoản của tôi</a></li>
                                        <!--                                        <form action="MainController">
                                                                                    <input type="submit" name="action" value="myaccount"></input>
                                                                                </form>-->

                                    </c:if>

                                    <c:if test="${fn: toUpperCase(sessionScope.user.isAdmin) == 'ADMIN'}">
                                        <li><a href="MainController?action=dashboard">Quản lý ADMIN</a></li>
                                        </c:if>
                                        <c:if test="${fn: toUpperCase(sessionScope.user.isAdmin) == 'STAFF'}">
                                        <li><a href="MainController?action=dashboard">Quản lý STAFF</a></li>
                                        </c:if>

                                    <c:if test="${sessionScope.user == null}">
                                        <li><a href="login.jsp">Đăng nhập</a></li>
                                        </c:if>

                                    <c:if test="${sessionScope.user != null}">
                                        <li><a href="MainController?action=logout">Đăng xuất</a></li>
                                        </c:if>
                                </ul>
                            </li> 
                        </ul>
                    </div>   
                </div>
            </div>
        </div>
    </div>
    <!--header top start-->

    <!--header middel start-->
    <div class="header_middel">
        <div class="container-fluid">
            <div class="middel_inner">
                <div class="row align-items-center">

                    <div class="col-lg-3">
                        <div class="logo" style="display: flex; align-items: center">
                            <a href="home"><img 
                                    style ="border-radius: 50%; border: 10px solid dark; margin-right: 10px "
                                    class="imglogo" src="https://top10tphcm.com/wp-content/uploads/2023/02/ga-dep-nhat-17.jpg" alt="logo"></a>
                                    <h3 href="home" style="font-weight: bold">Fhicken Shop</h3>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="search_bar">
                            <form action="MainController?action=search" method="POST">
                                <input name="text" placeholder="Tìm kiếm..." type="text">
                                <button type="submit"><i class="ion-ios-search-strong"></i></button>
                            </form>
                        </div>
                    </div>
                    <div class="col-lg-3">
                        <div class="cart_area">
                            <div class="cart_link">
                                <a href="cart.jsp"><i class="fa fa-shopping-basket"> ${sessionScope.size}</i> Sản Phẩm</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="horizontal_menu">
                <div class="left_menu">
                    <div class="main_menu"> 
                        <nav >  
                            <ul >
                                <li ><a  href="home">Trang chủ<i class="fa"></i></a>
                                </li>
                                <li class="mega_items"><a href="shop_category.jsp">Sản phẩm</a>
                                </li>
                                <!--<li class="mega_items"><a href="shop_package.jsp">Package</a></li>-->
                            </ul> 
                        </nav> 
                    </div>
                </div>
                <div class="right_menu">
                    <div class="main_menu"> 
                        <nav>  
                            <ul>
                                <li><a href="MainController?action=blog">Blog</a></li>
                                <li><a href="about">Chúng tôi</a></li>
                                <li><a href="contact">Liên hệ</a></li>

                            </ul> 
                        </nav> 
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--header middel end-->

    <!--header bottom satrt-->
    <div class="header_bottom sticky-header">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-12">
                    <div class="main_menu_inner">
                        <div class="main_menu"> 
                            <nav>  
                                <ul>
                                    <li class="active"><a href="home">Trang chủ</a></li>
                                    <li><a href="MainController?action=productall">Sản phẩm</a></li>
                                    <li><a href="MainController?action=packageall">Package</a></li>
                                    <li><a href="MainController?action=blog">Blog</a></li>
                                    <li><a href="about">Chúng tôi</a></li>
                                    <li><a href="contact">Liên hệ</a></li>

                                </ul>   
                            </nav> 
                        </div>
                    </div> 
                </div>
            </div>
        </div>
    </div>
    <!--header bottom end-->
</header>
