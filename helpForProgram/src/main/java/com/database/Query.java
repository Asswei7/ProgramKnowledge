package com.database;

import javafx.util.Pair;

import java.sql.*;
import java.util.List;
import java.util.Set;

/**
 * @author Asswei
 */
public class Query {
    public boolean querySQL(String libraryName,int num, List<String> exceptLibrary){
        //libraryName = "ja";
        Connection con;
        boolean flag = false;
        String driver="com.mysql.cj.jdbc.Driver";
        //这里我的数据库是qcl
        String url="jdbc:mysql://localhost:3306/schematest";
        String user="root";
        String password="s374857303";
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
            if (!con.isClosed()) {
                System.out.println("数据库连接成功");
            }
            //加上后面的参数，才可以使得previous函数成立，否则报错
            Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            String notSql = " ' ' ";
            for (String lib : exceptLibrary){
                notSql = notSql + "," + "'" + lib +"'";
            }


            String sql = "SELECT pairFirst as lib, value FROM schematest.draw_lib \n" +
                    "where pairSecond='" +libraryName +"' \n"+
                    "and pairFirst not in  (" +notSql+")\n"+
                    "UNION \n" +
                    "SELECT pairSecond as lib, value FROM schematest.draw_lib \n" +
                    "where pairFirst='" +libraryName +"' \n"+
                    "and pairSecond not in  (" +notSql+")\n"+
                    "order by value desc;";
            //System.out.println(sql);
            ResultSet resultSet = statement.executeQuery(sql);

            if(resultSet.next()){
                resultSet.previous();
                flag = true;
            }else{
                return false;
            }
            String name;
            int count = 0;
            while (resultSet.next()) {
                count++;
                name = resultSet.getString("lib");
                int value = resultSet.getInt("value");
                System.out.println("类库名：" + name+"  "+"出现次数："+value);
                if(count==num){
                    break;
                }
            }

            resultSet.close();
            con.close();

        } catch (ClassNotFoundException e) {
            System.out.println("数据库驱动没有安装");

        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("数据库连接失败");
        }
        return flag;
    }

}
