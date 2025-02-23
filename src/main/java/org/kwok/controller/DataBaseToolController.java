package org.kwok.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Base64;

/**
 * @description: 数据库直查工具
 * @author: guohao
 * @date: 2023/8/15
 */
@Controller
@RequestMapping("dbtool")
public class DataBaseToolController {

    //curl '127.0.0.1:8080/dbtool/oracle?host=127.0.0.1&port=1521&db=test&username=root&password=123456&sql=select+*+from+t_user'
    @RequestMapping("oracle")
    @ResponseBody
    public String oracle(@RequestParam("host") String host,
                         @RequestParam("port") String port,
                         @RequestParam("db") String db,
                         @RequestParam("username") String username,
                         @RequestParam("password") String password,
                         @RequestParam("sql") String sql){

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            String url = String.format("jdbc:oracle:thin:@%s:%s:%s", host, port, db);
            Connection conn = DriverManager.getConnection(url, username, password);
            if(null != conn.getSchema()){
                System.out.println(">·>> 连接成功...");
            }

            //sql 作为 url 参数有问题，可以使用 URLEncoder，不必 Base64.encode。
            //sql = new String(Base64.getDecoder().decode(sql.getBytes("UTF-8")), "UTF-8");

            System.out.println(">·>> 查询 SQL:");
            System.out.println("------------------------------------");
            System.out.println(sql);
            System.out.println("------------------------------------");

            if (sql.contains("update") || sql.contains("delete") || sql.contains("drop")){
                return "reject";
            }

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int colCount = rs.getMetaData().getColumnCount();

            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= colCount; i++) {
                sb.append("");
                sb.append(rs.getMetaData().getColumnName(i));
                sb.append(", ");
            }
            sb.append("\n");
            while (rs.next()) {
                for (int i = 1; i <= colCount; i++) {
                    sb.append("");
                    sb.append(rs.getObject(i));
                    sb.append(", ");
                }
                sb.append("\n");
            }

            stmt.close();
            conn.close();

            System.out.println("<<·< 查询结束！");
            return sb.toString();
        }catch (Exception e){
            System.out.println("<<·< 连接失败！");
            e.printStackTrace();
            return e.getMessage();
        }

    }

    public static void main(String[] args) throws UnsupportedEncodingException {

        // String sql = "select * from t_user";
        // String result = new DataBaseToolController().oracle("127.0.0.1", "1521", "test", "root", "123456", sql);
        // System.out.println(result);

        String sql = "select * from t_user";
        String encode_sql = new String(Base64.getEncoder().encode(sql.getBytes("UTF-8")), "UTF-8");
        System.out.println(encode_sql);

        sql = new String(Base64.getDecoder().decode(encode_sql.getBytes("UTF-8")), "UTF-8");
        System.out.println(sql);

        System.out.println(URLEncoder.encode(sql,"UTF-8"));

        System.out.println(URLDecoder.decode("select+*+from+t_user", "UTF-8"));

    }

}
