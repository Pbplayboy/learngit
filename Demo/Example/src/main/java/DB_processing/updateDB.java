package DB_processing;

import java.io.BufferedReader;
import java.io.FileReader;

import java.io.File;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;

import java.sql.*;


public class updateDB {
    public static void main(String[] args) {
        //开始操作
        System.out.println("==========开始update htlpricinginfodb.prepaydiscountruleconfigureroom==========");
        //连数据库
        updateDB processDB = new updateDB();
        processDB.initiateDB();
        //读csv，拿数据
        String filePath = "D:\\Users\\bpeng\\Documents\\查服资料\\小结\\自测case\\携程固化报价\\携程固化报价0827.csv";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));//换成你的文件名
//            reader.readLine();//第一行信息，为标题信息，不用，如果需要，注释掉
            String line = reader.readLine();

            File writename = new File("D:\\Users\\bpeng\\Documents\\查服资料\\小结\\自测case\\携程固化报价\\主键.txt"); // 相对路径，如果没有则要建立一个新的output。txt文件
            writename.createNewFile(); // 创建新文件
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));


            while ((line = reader.readLine()) != null) {
                String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分
                //int value = Integer.parseInt(last);//如果是数值，可以转化为数值
                System.out.println(item[0] + ":" + item[1] + ":" + item[2] + ":" + item[3]);
                //查数据，根据room和促销规则，检查isvalid
                int room = Integer.valueOf(item[0]);
                int PrepayDiscountRuleConfigureId = Integer.valueOf(item[1]);
//                int IsDel = processDB.queryIsDel(room,PrepayDiscountRuleConfigureId);
//                if (IsDel == 0){
//                    System.out.println("isDel = 0");
//                }else{
//                    //需要update的数据
//                    int result = processDB.updateIsDel(room,PrepayDiscountRuleConfigureId);
//                    System.out.println("update = "+result);
//                }
                int change_line = processDB.updateTime(room,PrepayDiscountRuleConfigureId);
                int PrepayDiscountRuleConfigureRoomID =processDB.queryPrepayDiscountRuleConfigureRoomID(room,PrepayDiscountRuleConfigureId);
                System.out.println("update = "+ change_line);
                System.out.println("PrepayDiscountRuleConfigureRoomID = "+ PrepayDiscountRuleConfigureRoomID);
                out.write(PrepayDiscountRuleConfigureRoomID+"\r\n"); // \r\n即为换行
            }
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("读写文件报错：" + e);
        }

        System.out.println("==========完成update htlpricinginfodb.prepaydiscountruleconfigureroom==========");
    }

    private static final String URL="jdbc:mysql://htlpricingInfo.mysql.db.fat.qa.nt.ctripcorp.com:55111/htlpricinginfodb?serverTimezone=GMT";
    private static final String NAME="us_test_bpeng";
    private static final String PASSWORD="091001Pb";
    private static final String prepaydiscountruleconfigureroomDao="htlpricinginfodb.prepaydiscountruleconfigureroom";
    private static Connection conn=null;

    public void initiateDB(){
        try {
            //1.加载驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("成功加载MySQL驱动！");
            //2.获得数据库的连接
            conn = DriverManager.getConnection(URL, NAME, PASSWORD);
            System.out.println("成功连接到数据库htlpricinginfodb！");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int queryIsDel(int room,int PrepayDiscountRuleConfigureId){
        //查询isdel字段
        int result = 1 ;
        try {
            String sql_query = "select * from htlpricinginfodb.prepaydiscountruleconfigureroom where room = ? and PrepayDiscountRuleConfigureId = ?";
            PreparedStatement ps = conn.prepareStatement(sql_query);
            ps.setInt(1,room);
            ps.setInt(2,PrepayDiscountRuleConfigureId);
            ResultSet rs = ps.executeQuery();
//            stmt.executeQuery(sql_query);
            while (rs.next()&&result==1){
                result = rs.getInt("IsDel");

            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("=====room信息查询失败:"+room+"=====");
            return result;
        }
    }

    public int updateIsDel(int room,int PrepayDiscountRuleConfigureId){
        //
        int result = 0;
        try {
            String sql_query = "update htlpricinginfodb.prepaydiscountruleconfigureroom set IsDel = 0 where room = ? and PrepayDiscountRuleConfigureId = ?";
            PreparedStatement ps = conn.prepareStatement(sql_query);
            ps.setInt(1,room);
            ps.setInt(2,PrepayDiscountRuleConfigureId);
            result = ps.executeUpdate();

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("=====room信息更新失败:"+room+"-"+PrepayDiscountRuleConfigureId+"=====");
            return result;
        }
    }

    public int updateTime(int room,int PrepayDiscountRuleConfigureId){
        //
        int result = 0;
        try {
            String sql_query = "update htlpricinginfodb.prepaydiscountruleconfigureroom set DataChange_LastTime = now() where room = ? and PrepayDiscountRuleConfigureId = ?";
            PreparedStatement ps = conn.prepareStatement(sql_query);
            ps.setInt(1,room);
            ps.setInt(2,PrepayDiscountRuleConfigureId);
            result = ps.executeUpdate();

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("=====room信息更新失败:"+room+"-"+PrepayDiscountRuleConfigureId+"=====");
            return result;
        }
    }

    public int queryPrepayDiscountRuleConfigureRoomID(int room,int PrepayDiscountRuleConfigureId){
        //查询isdel字段
        int result = 1 ;
        try {
            String sql_query = "select * from htlpricinginfodb.prepaydiscountruleconfigureroom where room = ? and PrepayDiscountRuleConfigureId = ?";
            PreparedStatement ps = conn.prepareStatement(sql_query);
            ps.setInt(1,room);
            ps.setInt(2,PrepayDiscountRuleConfigureId);
            ResultSet rs = ps.executeQuery();
//            stmt.executeQuery(sql_query);
            rs.next();
                result = rs.getInt("PrepayDiscountRuleConfigureRoomID");


            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("=====主键信息查询失败:"+room+"=====");
            return result;
        }
    }


}
