package htlOrder;

import java.util.*;

public class htlOrderProcessing {
    protected String checkinDate;
    protected String checkoutDate;
    protected String bookingName;
    protected String roomType;
    protected Long orderID;//新改成long类型的，注意后续修改
    protected int isValid;


//    public enum roomType{
//        TwinRoom(1,"双床房"),
//        DoubleRoom(2,"大床房"),
//        SingleRoom(3,"单人房");
//    }

    public String getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(String checkinDate) {
        this.checkinDate = checkinDate;
    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(String checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public String getBookingName() {
        return bookingName;
    }

    public void setBookingName(String bookingName) {
        this.bookingName = bookingName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public int getIsValid() {
        return isValid;
    }

    public void setIsValid(int isValid) {
        this.isValid = isValid;
    }

    public int addOrder(htlOrderProcessing doHtlOrder) {
        System.out.println("=====新增订单服务=====");
//        Long orderID = System.currentTimeMillis();
//        doHtlOrder.setOrderID(orderID);
//        doHtlOrder.setIsValid(1); //新增认为有效
        htlOrderDBProcessing htlOrderDB = new htlOrderDBProcessing();
        int result = htlOrderDB.addOrder2DB(doHtlOrder);
        return result;
    }

    public htlOrderProcessing queryOrder(String orderID){
        System.out.println("=====查询订单服务=====");
        htlOrderDBProcessing htlOrderDB = new htlOrderDBProcessing();
        htlOrderProcessing htlOrderInfo = htlOrderDB.queryOrderinDB(orderID);
        return htlOrderInfo;
    }
    public static void main(String[] args){
        Date date = new Date();
        System.out.println(date.toString());
    }
}
