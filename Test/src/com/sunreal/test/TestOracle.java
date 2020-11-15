package com.sunreal.test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

import oracle.jdbc.OracleTypes;

public class TestOracle {
	public static void main(String[] args) throws Exception {
		//加载驱动
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//获取连接对象
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.106:1521:orcl", "scott", "123456");
		//获得语句对象
		String sql = "{call p_querysal_out(?,?)}";
		CallableStatement call = connection.prepareCall(sql);
		
		//设置输入参数
		call.setInt(1, 7839);
		
		//注册输出参数
		call.registerOutParameter(2, OracleTypes.DOUBLE);
		
		//执行存储过程
		call.execute();
		
		//获取输出参数
		double sal = call.getDouble(2);
		
		System.out.println("薪水："+sal);
		
		call.close();
		connection.close();

	}
}
