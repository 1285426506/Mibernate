package com.gjj.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Mibernate {
	public Mibernate() {
	}

	public <T> List<T> list(Class<?> klass) {
		ArrayList<T> resultList = new ArrayList<>();
		
		SqlWorker sql = new SqlWorker();
		String sqlString = sql.selectQuery(klass);
		
		ClassTableFactory ctf = new ClassTableFactory();
		ClassTable ct = ctf.getClassTable(klass);
		
		Connection connection = MecDataBase.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				@SuppressWarnings("unchecked")
				T result = (T) ct.setProperties(resultSet);
				resultList.add(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resultList;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T get(Class<?> klass, String key) {
		Object result = null;
		
		SqlWorker sql = new SqlWorker();
		String sqlString = sql.selectQuery(klass);
		
		ClassTableFactory ctf = new ClassTableFactory();
		ClassTable ct = ctf.getClassTable(klass);
		
		PropertyColumn keyProperty = ct.getKey();
		if(keyProperty == null) {
			return null;
		}
		sqlString += " AND " + keyProperty.getColumnName() + " = '" + key + "'";
		
		Connection connection = MecDataBase.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				result = ct.setProperties(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return (T) result;
	}
	
	public void addOne(Class<?> klass,String getValues) {
		SqlWorker sql = new SqlWorker();
		String sqlString = sql.insertQuery(klass);
		sqlString = sqlString.substring(0,sqlString.length()-2)+getValues;
		sqlString = sqlString +")";
		Connection connection = MecDataBase.getConnection();
		Statement stat;
		System.out.println(sqlString);
		try {
			stat = connection.createStatement();
			stat.executeUpdate(sqlString);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} 
	
	public void deleteAll(Class<?> klass) {
		SqlWorker sql = new SqlWorker();
		String sqlString = sql.deleteQuery(klass);
		Connection connection = MecDataBase.getConnection();
		Statement stat;
		try {
			stat = connection.createStatement();
			stat.executeUpdate(sqlString);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} 
	
	public void deleteOne(Class<?> klass,String key) {
		SqlWorker sql = new SqlWorker();
		String sqlString = sql.deleteQuery(klass);
		ClassTableFactory ctf = new ClassTableFactory();
		ClassTable ct = ctf.getClassTable(klass);
		PropertyColumn keyProperty = ct.getKey();
		if(keyProperty == null) {
			System.exit(0);
		}
		sqlString = sqlString.substring(0, sqlString.length()-4)+keyProperty.getColumnName()
		+ "="+key;
		Connection connection = MecDataBase.getConnection();
		Statement stat;
		try {
			stat = connection.createStatement();
			stat.executeUpdate(sqlString);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} 
}
