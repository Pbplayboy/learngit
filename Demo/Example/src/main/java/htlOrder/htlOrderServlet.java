package htlOrder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class htlOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        String action = request.getParameter("action");
        if ("queryOrder".equals(action)) {
            request.getRequestDispatcher("htlOrderWeb/queryOrder.jsp").forward(request, response);
        } else if ("addOrder".equals(action)) {
            request.getRequestDispatcher("htlOrderWeb/addOrder.jsp").forward(request, response);
        } else if ("modifyOrder".equals(action)) {
            request.getRequestDispatcher("htlOrderWeb/modifyOrder.jsp").forward(request, response);
        } else if ("deleteOrder".equals(action)) {
            request.getRequestDispatcher("htlOrderWeb/deleteOrder.jsp").forward(request, response);
        } else if ("return".equals(action)) {
            request.getRequestDispatcher("htlOrderWeb/htlOrderIndex.jsp").forward(request, response);
        }
        if ("addOrder_input".equals(action)) {
            String bookingName = request.getParameter("bookingName");
            String roomType = request.getParameter("roomType");
            String checkinDate = request.getParameter("checkinDate");
            String checkoutDate = request.getParameter("checkoutDate");
            Long orderID = System.currentTimeMillis();
            int isValid = 1;
            htlOrderProcessing doHtlOrder = new htlOrderProcessing();
            doHtlOrder.setCheckinDate(checkinDate);
            doHtlOrder.setCheckoutDate(checkoutDate);
            doHtlOrder.setRoomType(roomType);
            doHtlOrder.setBookingName(bookingName);
            doHtlOrder.setOrderID(orderID);
            doHtlOrder.setIsValid(isValid); //新增认为有效
            int result = doHtlOrder.addOrder(doHtlOrder);
            if (result == 1) {
                request.setAttribute("addSuccess", "录入订单成功!订单号为：" + orderID); // 设置错误属性
                request.getRequestDispatcher("htlOrderWeb/addOrder.jsp").forward(request, response);
            } else if (result == 0) {
                request.setAttribute("addError", "录入订单失败!请重新录入"); // 设置错误属性
                request.getRequestDispatcher("htlOrderWeb/addOrder.jsp").forward(request, response);
            }
        } else if ("queryOrder_input".equals(action)) {
            String orderID = request.getParameter("orderID");
            htlOrderProcessing doHtlOrder = new htlOrderProcessing();
            htlOrderProcessing htlOrderInfo = doHtlOrder.queryOrder(orderID);

        }
    }
}