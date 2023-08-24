<%@page import="java.sql.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html class="no-js" lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Lịch sử đơn hàng</title>
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
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap"
              rel="stylesheet">
    </head>
    <% String status = (String) request.getAttribute("BillNew");%>
    <% Date date3 = (Date) request.getAttribute("Date3");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate3 = (date3 != null) ? sdf.format(date3) : null;
    %>
    <% Date date2 = (Date) request.getAttribute("Date2");
        String formattedDate2 = (date2 != null) ? sdf.format(date2) : null;
    %>
    <% Date date1 = (Date) request.getAttribute("Date1");
        String formattedDate1 = (date1 != null) ? sdf.format(date1) : null;
    %>
    <% Date date4 = (Date) request.getAttribute("Date4");
        String formattedDate4 = (date4 != null) ? sdf.format(date4) : null;
    %>
    <body>
        <fmt:setLocale value ="vi_VN"/>
        <div class="off_canvars_overlay"></div>
        <fmt:setLocale value ="vi_VN"/>
        <jsp:include page="layout/menu.jsp"/>
        <!--breadcrumbs area start-->
        <div class="breadcrumbs_area other_bread">
            <div class="container">   
                <div class="row">
                    <div class="col-12">
                        <div class="breadcrumb_content">
                            <ul>
                                <li><a href="home">Trang chủ</a></li>
                                <li>/</li>
                                <li>Lịch sử đơn hàng</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>         
        </div>

        <div class="shopping_cart_area">
            <div class="container">  
                <form> 
                    <div class="row">
                        <div class="col-12">
                            <div class="table_desc">
                                <div class="cart_page table-responsive">
                                    <table>

                                        <thead>
                                            <tr>
                                                <th>Ảnh</th>
                                                <th>Tên sản phẩm</th>
                                                <th>Số lượng</th>
                                                <th>Đơn giá</th>
                                                <th>Đánh giá</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${Detail}" var="d">
                                                <tr>
                                                    <td><img src="${d.img}" alt="" width="100px;"></td>
                                                    <td>${d.product_name}</td>                                            
                                                    <td>${d.quantity}</td>
                                                    <td><fmt:formatNumber value="${d.price}" type = "currency" currencySymbol="VNĐ"/></td>
                                                    <% if (formattedDate4 != null) {%>
                                                    <td> <a href="#" class="reviewBtn" data-product-id="${d.product_id}" data-bill-id="${d.bill_id}">Viết đánh giá</a></td>
                                                    <% } else { %>
                                                    <td>Đơn hàng hoàn thành bạn có thể đánh giá.</td>
                                                    <% } %>

                                                </tr>
                                            </c:forEach>
                                            <c:forEach items="${Detail_package}" var="p">
                                                <tr>
                                                    <td><img src="${p.img}" alt="" width="100px;"></td>
                                                    <td>${p.product_name}</td>                                            
                                                    <td>${p.quantity}</td>
                                                    <td><fmt:formatNumber value="${p.price}" type = "currency" currencySymbol="VNĐ"/></td>
                                                    <% if (formattedDate4 != null) {%>
                                                    <td> <a href="#" class="reviewBtn" data-product-id="${d.product_id}" data-bill-id="${d.bill_id}">Viết đánh giá</a></td>
                                                    <% } else { %>
                                                    <td>Đơn hàng hoàn thành bạn có thể đánh giá.</td>
                                                    <% } %>
                                                </tr>
                                            </c:forEach>
                                            <tr>
                                                <th></th>
                                                <th></th>
                                                <th>Phí vận chuyển</th>
                                                <th></th>
                                                <td><strong><fmt:formatNumber value="30000" type = "currency" currencySymbol="VNĐ"/></strong></td>
                                            </tr>
                                        </tbody>
                                    </table>   
                                </div> 
                            </div>
                        </div>
                    </div>
                </form> 
            </div>     
        </div>
        <div class="container">
            <h2 style="font-weight: bold; margin-top: 14px;">Quá trình đơn hàng</h2>

            <div class="row">
                <div class="hh-grayBox pt45">
                    <div class="row justify-content-between">                        
                        <div class="order-tracking" >
                            <!--<span class="is-complete"></span>-->
                            <% if (formattedDate1 != null) {%>
                            <h4>Chờ xác nhận</h4><p style="color: #2dc258; font-size: 20px"><%= formattedDate1%></p>
                            <% } else { %>
                            <h4>Chờ xác nhận</h4>
                            <% } %>
                        </div>

                        <div class="order-tracking" >
                            <% if (formattedDate2 != null) {%>
                            <!--<span class="is-complete"></span>-->
                            <h4>Đã lấy hàng</h4><p style="color: #2dc258; font-size: 20px"><%= formattedDate2%></p>
                            <% } else { %>
                            <h4>Đã lấy hàng</h4>
                            <% } %>
                        </div>

                        <div class="order-tracking" >
                            <!--<span class="is-complete"></span>-->
                            <% if (formattedDate3 != null) {%>
                            <h4>Đang giao</h4><p style="color: #2dc258; font-size: 20px"><%= formattedDate3%></p>
                            <% } else { %>
                            <h4>Đang giao</h4>
                            <% }%>
                        </div>
                        <div class="order-tracking">
                            <!--<span class="is-complete"></span>-->
                            <% if (formattedDate4 != null) {%>
                            <h4>Hoàn Thành</h4><p style="color: #2dc258; font-size: 20px"><%= formattedDate4%></p>
                            <% } else { %>
                            <h4>Hoàn Thành</h4>
                            <% }%>
                        </div>
                        <script>
                            var status = '<%= status%>';
                            if (status === 'xac nhan don') {
                                document.getElementById('confirm').classList.add('completed');
                            } else if (status === 'hoan thanh') {
                                document.getElementById('complete').classList.add('completed');
                                document.getElementById('deliver').classList.add('completed');
                                document.getElementById('pick').classList.add('completed');
                                document.getElementById('confirm').classList.add('completed');
                            } else if (status === 'dang giao') {
                                document.getElementById('deliver').classList.add('completed');
                                document.getElementById('pick').classList.add('completed');
                                document.getElementById('confirm').classList.add('completed');
                            } else if (status === 'cho lay hang') {
                                document.getElementById('pick').classList.add('completed');
                                document.getElementById('confirm').classList.add('completed');
                            }
                        </script>
                    </div>
                </div>
            </div>
        </div>
        <div class="review_form_wrapper">
            <h3>Viết đánh giá của bạn</h3>
            <form action="MainController?action=addReview" method="POST" onSubmit="return validateForm()">
                <input type="hidden" name="bill_id" >
                <input type="hidden" name="product_review_id" >
                <input type="hidden" name="user_id" value="${sessionScope.user.user_id}">
                <%
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String formattedDate = dateFormat.format(new java.util.Date());
                %>
                <input type="hidden" name="review_date" value="<%= formattedDate%>">
                <div class="form-group">
                    <label for="rating">Đánh giá của bạn:</label>
                    <select class="form-control" id="rating" name="rating">
                        <option value="5">5 sao</option>
                        <option value="4">4 sao</option>
                        <option value="3">3 sao</option>
                        <option value="2">2 sao</option>
                        <option value="1">1 sao</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="review">Nhận xét của bạn:</label>
                    <textarea class="form-control" rows="5" id="review" name="review"></textarea>
                </div>
                <div style="margin-top: 10px">
                    <button type="submit" class="btn btn-primary">Gửi đánh giá</button>
                    <button type="button" class="btn btn-secondary" id="cancelBtn">Hủy</button>
                </div>
            </form>
        </div>
        <jsp:include page="layout/footer.jsp"/>
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAdWLY_Y6FL7QGW5vcO3zajUEsrKfQPNzI"></script>
        <script  src="https://www.google.com/jsapi"></script>
        <script src="assets/js/map.js"></script>


        <!-- Plugins JS -->
        <script src="assets/js/plugins.js"></script>

        <!-- Main JS -->
        <script src="assets/js/main.js"></script>
        <script>
                $(document).ready(function () {
                    $('.reviewBtn').click(function (e) {
                        e.preventDefault();

                        var productId = $(this).data('product-id');
                        var billId = $(this).data('bill-id');
                        $.ajax({
                            url: 'MainController?action=checkRating',
                            data: {
                                user_id: ${sessionScope.user.user_id},
                                product_id: productId,
                                bill_id: billId
                            },
                            success: function (response) {
                                if (response === 'exists') {
                                    alert('Bạn đã đánh giá sản phẩm này rồi.');
                                    var thankYouMsg = $('<p>', {text: 'Cảm ơn bạn đã đánh giá.'});
                                    $('.reviewBtn[data-product-id="' + productId + '"][data-bill-id="' + billId + '"]').replaceWith(thankYouMsg);
                                } else {
                                    // Otherwise, display the review form
                                    $('input[name="bill_id"]').val(billId);
                                    $('input[name="product_review_id"]').val(productId);
                                    $('.off_canvars_overlay').fadeIn();
                                    $('.review_form_wrapper').fadeIn();
                                }
                            },
                            error: function () {
                                alert('Có lỗi xảy ra, vui lòng thử lại sau.');
                            }
                        });
                    });
                });
        </script>
        <script>
            $('#cancelBtn').click(function () {
                $('.off_canvars_overlay').fadeOut();
                $('.review_form_wrapper').fadeOut();
            });
        </script>
        <style>
            .review_form_wrapper {
                display: none;
                position: fixed;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                z-index: 9999;
                background-color: #fff;
                padding: 20px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
            }

            .review_form_wrapper h3 {
                margin-top: 0;
            }

            .off_canvars_overlay {
                display: none;
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background-color: rgba(0, 0, 0, 0.5);
                z-index: 9998;
            }
            .review_form_wrapper::before,
            .review_form_wrapper::after {
                content: '';
                display: block;
                position: absolute;
                pointer-events: none; /* disable click events */
                /* rest of the styles */
            }
            .hh-grayBox {
                background-color: #F8F8F8;
                margin-bottom: 20px;
                padding: 35px;
                margin-top: 20px;
            }
            .pt45{
                padding-top:45px;
            }
            .order-tracking{
                text-align: center;
                width: 25%;
                position: relative;
                display: block;
            }

            h2 {
                font-weight: bold;
                margin-top: 30px;
            }
        </style>
    </body>

</html>