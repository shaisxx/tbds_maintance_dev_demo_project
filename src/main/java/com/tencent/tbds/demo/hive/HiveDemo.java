package com.tencent.tbds.demo.hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * usage:
 * java -cp  dev-demo-<version>.jar:$(echo /usr/hdp/2.2.0.0-2041/hive/lib/\*.jar | tr ' ' ':'):/usr/hdp/2.2.0.0-2041/hadoop/hadoop-common.jar com.tencent.tbds.demo.hive.HiveDemo --user <user name> --password <password> --zk-list <host1:port1,host2:port2>
 */
public class HiveDemo {
    public static void main(String[] args) throws Exception {
//        HiveDemoOption option = new HiveDemoOption(args);
//        if (option.hasHelp()) {
//            option.printHelp();
//            return;
//        }

        Class.forName("org.apache.hive.jdbc.HiveDriver");

        // String url = "jdbc:hive2://host:10000/default";
        //高可用方式：客户端选择可用的hiveserver
        String url = String.format("jdbc:hive2://172.16.40.19:10000/");

        Connection conn = DriverManager.getConnection(url, "admin", "Tbds@2020");
        Statement st = conn.createStatement();
        String sqlString = "SHOW DATABASES";
        ResultSet rs = st.executeQuery(sqlString);
        System.out.println("Show all databases in hive:");
        while (rs.next()) {
            System.out.println(rs.getString(1));
        }
        rs.close();
        st.close();
        conn.close();
    }
}
