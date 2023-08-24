<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="Entity.Bill"%>
<%@page import="DAO.billDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html class="no-js" lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>My Bill</title>
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
        <div class="off_canvars_overlay">

        </div>
        <jsp:include page="layout/menu.jsp"/>
        <!--breadcrumbs area start-->
        <div class="breadcrumbs_area other_bread">
            <div class="container">   
                <div class="row">
                    <div class="col-12">
                        <div class="breadcrumb_content">
                            <ul>
                                <li><a href="home">Home</a></li>
                                <li>/</li>
                                <li>My Bill</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>         
        </div>
        <!--breadcrumbs area end-->

        <!-- my account start  -->
        <section class="main_content_area">
            <div class="container">   
                <div class="account_dashboard">
                    <div class="row">
                        <!-- Tab panes -->
                        <div class="tab-content dashboard_content"> 
                            <h3> <a href="MainController?action=bill"> Đơn hàng </a> </h3>
                            <table class="button-table">
                                <tr>
                                    <td>
                                        <a class="btn btn-add btn-sm" href="MainController?action=orderconfirm">Đơn hàng chờ xác nhận</a>
                                    </td>
                                    <td>
                                        <a class="btn btn-add btn-sm" href="MainController?action=orderprepared">Đơn hàng đang chuẩn bị</a>
                                    </td>
                                    <td>
                                        <a class="btn btn-add btn-sm" href="MainController?action=ordershipping">Đang vận chuyển</a>
                                    </td>
                                    <td>
                                        <a class="btn btn-add btn-sm" href="MainController?action=ordershipdone">Đã giao hàng thành công</a>
                                    </td>
                                    <td>
                                        <a class="btn btn-add btn-sm" href="MainController?action=ordercancel">Đơn hàng đã hủy</a>
                                    </td>
                                </tr>
                            </table>
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>Ngày mua</th>
                                            <th>Hình thức thanh toán</th>
                                            <th>Địa chỉ</th>
                                            <th>Tổng đơn</th>
                                            <th>Trạng thái</th>
                                            <th>Xem chi tiết</th>
                                            <th>Hủy đơn</th>
                                            
                                            
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <!--Dang cho xac nhan-->
                                        <c:forEach items="${requestScope.billstatus1}" var="b">  
                                            <tr>
                                                <td>${b.getDate()}</td>
                                                <td><span class="success">${b.getPayment()}</span></td>
                                                <td>${b.getAddress()}</td>
                                                <td><fmt:formatNumber value="${b.getTotal()+30000}" type = "currency" currencySymbol="VNĐ"/></td>
                                                <td>${b.getBill_status()}</td>
                                                <td><a href="MainController?action=showdetailcus&bill_id=${b.getBill_id()}" class="view">Xem chi tiết</a>
                                                </td>
                                                <td><a href="#" class="reviewBtn" data-product-id="${b.getBill_id()}" data-bill-id="${b.getBill_id()}">Hủy</a></td>
                                               

                                            </tr>
                                        </c:forEach>
                                        <!--                                            Dang chuan bi-->
                                        <c:forEach items="${requestScope.billstatus2}" var="b">
                                            <tr>
                                                <td>${b.getDate()}</td>
                                                <td><span class="success">${b.getPayment()}</span></td>
                                                <td>${b.getAddress()}</td>
                                                <td><fmt:formatNumber value="${b.getTotal()+30000}" type = "currency" currencySymbol="VNĐ"/></td>
                                                <td>${b.getBill_status()}</td>
                                                <td><a href="MainController?action=showdetailcus&bill_id=${b.getBill_id()}" class="view">Xem chi tiết</a></td>
                                                <td>Không thể hủy</td>
                                            </tr>
                                        </c:forEach>
                                        <!--                                            Dang van chuyen-->
                                        <c:forEach items="${requestScope.billstatus3}" var="b">
                                            <tr>
                                                <td>${b.getDate()}</td>
                                                <td><span class="success">${b.getPayment()}</span></td>
                                                <td>${b.getAddress()}</td>
                                                <td><fmt:formatNumber value="${b.getTotal()+30000}" type = "currency" currencySymbol="VNĐ"/></td>
                                                <td>${b.getBill_status()}</td>
                                                <td><a href="MainController?action=showdetailcus&bill_id=${b.getBill_id()}" class="view">Xem chi tiết</a></td>
                                                <td>Không thể hủy</td>
                                            </tr>
                                        </c:forEach>
                                        <!--                                            Giao hang thanh cong-->
                                        <c:forEach items="${requestScope.billstatus4}" var="b">
                                            <tr>
                                                <td>${b.getDate()}</td>
                                                <td><span class="success">${b.getPayment()}</span></td>
                                                <td>${b.getAddress()}</td>
                                                <td><fmt:formatNumber value="${b.getTotal()+30000}" type = "currency" currencySymbol="VNĐ"/></td>
                                                <td>${b.getBill_status()}</td>
                                                <td><a href="MainController?action=showdetailcus&bill_id=${b.getBill_id()}" class="view">Xem chi tiết</a></td>
                                                <td>Không thể hủy</td>
                                            </tr>
                                        </c:forEach>
<!--                                            Don da huy-->
                                        <c:forEach items="${requestScope.billstatus5}" var="b">
                                            <tr>
                                                <td>${b.getDate()}</td>
                                                <td><span class="success">${b.getPayment()}</span></td>
                                                <td>${b.getAddress()}</td>
                                                <td><fmt:formatNumber value="${b.getTotal()+30000}" type = "currency" currencySymbol="VNĐ"/></td>
                                                <td>Đã Hủy</td>
                                                <td><a href="MainController?action=showdetailcus&bill_id=${b.getBill_id()}" class="view">Xem chi tiết</a></td>
                                                <td>Không thể hủy</td>
                                            </tr>
                                        </c:forEach>
                                        <!--                                            Don tong-->
                                        <c:forEach items="${bill}" var="b">
                                            <tr>
                                                <td>${b.date}</td>
                                                <td><span class="success">${b.payment}</span></td>
                                                <td>${b.address}</td>
                                                <td><fmt:formatNumber value="${b.total+30000}" type = "currency" currencySymbol="VNĐ"/></td>
                                                <td>${b.getBill_status()}</td>
                                                <!--<td>Đã hủy</td>-->
                                                <td><a href="MainController?action=showdetailcus&bill_id=${b.getBill_id()}" class="view">Xem chi tiết</a></td>
                                                <c:if test="${b.getBill_status()=='xac nhan don'}"><td> <a href="#" class="reviewBtn" data-product-id="${b.getBill_id()}" data-bill-id="${b.getBill_id()}">Hủy</a></td></c:if>
                                          <c:if test="${b.getBill_status()!='xac nhan don'}"><td>Không thể hủy</td></c:if>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>

                        </div>
                    </div>
                </div>  
            </div>       

        </section>			
        <!-- my account end    
        
        <!--footer area start
        <jsp:include page="layout/footer.jsp"/>
        <!--footer area end

        <!-- JS
        ============================================ 

        --> <div class="review_form_wrapper">
            <h3>Hủy đơn</h3>
            <form action="MainController?action=cancelbillbycus" method="POST" onSubmit="return validateForm()">
                <input type="hidden" name="bill_id" >
                <input type="hidden" name="product_review_id" >
                <input type="hidden" name="user_id" value="${sessionScope.user.user_id}">
                <%
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String formattedDate = dateFormat.format(new java.util.Date());
                %>
                <input type="hidden" name="review_date" value="<%= formattedDate%>">
                <div class="form-group">
                    <label style="margin-bottom: 10px" for="rating">Lý do hủy đơn:</label>
                    <select class="form-control" id="rating" name="rating">
                        <option value="Cap nhat dia chi/sdt nhan hang (CUS)">Cập nhật địa chỉ/sđt nhận hàng</option>
                        <option value="Thay doi san pham (CUS)">Thay đổi sản phẩm</option>
                        <option value="Thay doi hinh thuc thanh toan (CUS)">Thay đổi hình thức thanh toán</option>
                        <option value="Tim thay cho khac tot hon (CUS)">Tìm thấy chỗ khác tốt hơn</option>
                        <option value="Khong co nhu cau mua nua (CUS)">Không có nhu cầu mua nữa</option>

                    </select>
                </div>
                <div class="btncancelOrder">
                    <button type="submit" class="btn btn-primary">Gửi</button>
                    <button type="button" class="btn btn-secondary" id="cancelBtn">Hủy</button>
                </div>
            </form>
        </div>       
        <script src="assets/js/plugins.js"></script><!--<!--

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
            .btncancelOrder{
                margin-top: 10px;
            }
            .hh-grayBox {
                background-color: #F8F8F8;
                margin-bottom: 20px;
                padding: 35px;
                margin-top: 20px;
            }
            .pt45{padding-top:45px;}
            .order-tracking{
                text-align: center;
                width: 25%;
                position: relative;
                display: block;
            }
            .order-tracking .is-complete{
                display: block;
                position: relative;
                border-radius: 50%;
                height: 30px;
                width: 30px;
                border: 0px solid #AFAFAF;
                background-color: #f7be16;
                margin: 0 auto;
                transition: background 0.25s linear;
                -webkit-transition: background 0.25s linear;
                z-index: 2;
            }
            .order-tracking .is-complete:after {
                display: block;
                position: absolute;
                content: '';
                height: 14px;
                width: 7px;
                top: -2px;
                bottom: 0;
                left: 5px;
                margin: auto 0;
                border: 0px solid #AFAFAF;
                border-width: 0px 2px 2px 0;
                transform: rotate(45deg);
                opacity: 0;
            }
            .order-tracking.completed .is-complete{
                border-color: #27aa80;
                border-width: 0px;
                background-color: #27aa80;
            }
            .order-tracking.completed .is-complete:after {
                border-color: #fff;
                border-width: 0px 3px 3px 0;
                width: 7px;
                left: 11px;
                opacity: 1;
            }
            .order-tracking p {
                color: #A4A4A4;
                font-size: 16px;
                margin-top: 8px;
                margin-bottom: 0;
                line-height: 20px;
            }
            .order-tracking p span{font-size: 14px;}
            .order-tracking.completed p{color: #000;}
            .order-tracking::before {
                content: '';
                display: block;
                height: 3px;
                width: calc(100% - 40px);
                background-color: #f7be16;
                top: 13px;
                position: absolute;
                left: calc(-50% + 20px);
                z-index: 0;
            }
            .order-tracking:first-child:before{display: none;}
            .order-tracking.completed:before{background-color: #27aa80;}
            h2 {
                font-weight: bold;
                margin-top: 30px;
            }
        </style>

    </body>

</html>

