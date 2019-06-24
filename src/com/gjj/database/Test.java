package com.gjj.database;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.gjj.ann.MecField;
import com.gjj.ann.MecId;
import com.gjj.ann.MecTable;
import com.gjj.model.user;


public class Test {
	@SuppressWarnings("unchecked")
	public static <T> T get(Class<?> klass, String id) {
		if(!klass.isAnnotationPresent(MecTable.class)) {
			return null;
		}
		MecTable mecTable = klass.getAnnotation(MecTable.class);
		String table = mecTable.table();
		String idName = null;
		
		StringBuffer SQLString = new StringBuffer("SELECT ");
		Field[] fields = klass.getDeclaredFields();
		String[] fieldNames = new String[fields.length];
		for(int index = 0; index < fields.length; index++) {
			Field field = fields[index];
			String fieldName = field.getName();
			if(field.isAnnotationPresent(MecField.class)) {
				MecField mecField = field.getAnnotation(MecField.class);
				fieldName = mecField.column();
			}
			fieldNames[index] = fieldName;
			
			if(field.isAnnotationPresent(MecId.class)) {
				if(idName == null) {
					idName = fieldName;
				} else {
					// TODO ��ʾ���ֶ���ؼ��֣�
				}
			}
		}
		
		for(int index = 0; index < fieldNames.length; index++) {
			SQLString.append(index == 0 ? "" : ", ");
			SQLString.append(fieldNames[index]);
		}
		SQLString.append(" FROM ")
				.append(table)
				.append(" WHERE ")
				.append(idName)
				.append(" = '")
				.append(id)
				.append("'");
		Object result = null;
		Connection connection = MecDataBase.getConnection();
		try {
			PreparedStatement preparedStatement = 
					connection.prepareStatement(SQLString.toString());
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				result = klass.newInstance();
				for(int index = 0; index < fieldNames.length; index++) {
					String fieldValue = resultSet.getString(fieldNames[index]);
					// TODO ��fieldValue��ֵ��result�ĸ���Ա��
					Field field = fields[index];
					field.setAccessible(true);
					field.set(result, fieldValue);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return (T) result;
	}
}
