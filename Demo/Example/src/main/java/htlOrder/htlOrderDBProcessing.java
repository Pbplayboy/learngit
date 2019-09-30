package htlOrder;
import java.sql.*;

public class htlOrderDBProcessing {
    public static void main(String[] args){
        try{
            //调用Class.forName()方法加载驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("成功加载MySQL驱动！");

            String url="jdbc:mysql://localhost:3306/htlorderlib?serverTimezone=GMT";    //JDBC的URL
            Connection conn = DriverManager.getConnection(url,"root","root");
            Statement stmt = conn.createStatement(); //创建Statement对象
            System.out.println("成功连接到数据库！");

            String sql = "select * from hotelorder";    //要执行的SQL
            ResultSet rs = stmt.executeQuery(sql);//创建数据对象
            System.out.println("ID"+"\t"+"orderID"+"\t"+"姓名");
            while (rs.next()){
                System.out.print(rs.getInt(1) + "\t");
                System.out.print(rs.getInt(2) + "\t");
                System.out.print(rs.getString(3) + "\t");
                System.out.println();
            }
            rs.close();
            stmt.close();
            conn.close();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    private static final String URL="jdbc:mysql://localhost:3306/htlorderlib?serverTimezone=GMT";
    private static final String NAME="root";
    private static final String PASSWORD="root";
    private static final String hotelorderDao="hotelorder";
    private static Connection conn=null;
    //静态代码块（将加载驱动、连接数据库放入静态块中）
    static{
        try {
            //1.加载驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("成功加载MySQL驱动！");
            //2.获得数据库的连接
            conn = DriverManager.getConnection(URL, NAME, PASSWORD);
            System.out.println("成功连接到数据库！");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public int addOrder2DB(htlOrderProcessing doHtlOrder){
        try {
            Statement statement = conn.createStatement();
            if (doHtlOrder.getBookingName()==null || doHtlOrder.getBookingName().length()<=0
                    || doHtlOrder.getRoomType()==null || doHtlOrder.getRoomType().length()<=0
                    || doHtlOrder.getCheckinDate()==null || doHtlOrder.getCheckinDate().length()<=0
                    || doHtlOrder.getCheckoutDate()==null || doHtlOrder.getCheckoutDate().length()<=0){
                System.out.println("=====订单录入失败=====");
                return 0;
            }
            String sql_addOrder = "insert into "+ hotelorderDao +" (orderID,bookingName,roomType,checkinDate,checkoutDate,isValid,datachange_createtime,datachange_lasttime) "
                    +"values("+ doHtlOrder.getOrderID() + ","
                    + '"' + doHtlOrder.getBookingName() + '"' + ","
                    + '"' + doHtlOrder.getRoomType() + '"' + ","
                    + '"' + doHtlOrder.getCheckinDate() + '"' + ","
                    + '"' + doHtlOrder.getCheckoutDate() + '"' + ","
                    + doHtlOrder.getIsValid() + ","
                    + "now(),now())" ;
            // 代码调试段
//            String sql_addOrder = "insert into "+ hotelorderDao +" (orderID,bookingName,roomType,checkinDate,checkoutDate,isValid,datachange_createtime,datachange_lasttime) "
//                    +"values("+ "1565782591361" + ","
//                    + '"' + doHtlOrder.getBookingName() + '"' + ","
//                    + '"' + doHtlOrder.getRoomType() + '"' + ","
//                    + '"' + doHtlOrder.getCheckinDate() + '"' + ","
//                    + '"' + doHtlOrder.getCheckoutDate() + '"' + ","
//                    + doHtlOrder.getIsValid() + ","
//                    + "now(),now())" ;
            int result = statement.executeUpdate(sql_addOrder);//创建数据对象
            System.out.println("=====订单录入成功=====");
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("=====订单录入失败=====");
            return 0;
        }

    }
    public htlOrderProcessing queryOrderinDB(String orderID){
        htlOrderProcessing htlOrderInfo = new htlOrderProcessing();
        try {
            Statement statement = conn.createStatement();
            if (orderID==null || orderID.length()<=0){
                System.out.println("=====订单号为空=====");
                return null;
            }
            String sql_addOrder = "select * from "+hotelorderDao+" where orderID="+orderID;

            ResultSet result = statement.executeQuery(sql_addOrder);//创建数据对象
            result.next();
            System.out.println(result.getInt(1));
            System.out.println(result.getString(2));
            System.out.println(result.getString(3));
            System.out.println(result.getString(4));
            System.out.println(result.getString(5));

            return htlOrderInfo;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("=====订单录入失败=====");
            return null;
        }

    }

}
