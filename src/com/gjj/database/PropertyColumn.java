package com.gjj.database;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PropertyColumn {
	// 类成员名称
	private String propertyName;
	// 表字段名称
	private String columnName;
	// 类成员的Setter方法
	private Method setterMethod;
	
	public PropertyColumn() {
	}

	/**
	 * 获取类成员名称
	 * @return
	 */
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * 设置类成员名称
	 * @param propertyName
	 */
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	/**
	 * 获取表字段名称
	 * @return
	 */
	public String getColumnName() {
		return columnName;
	}

	public void setSetterMethod(Method setterMethod) {
		this.setterMethod = setterMethod;
	}

	/**
	 * 设置表字段名称
	 * @param columnName
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	/**
	 * 设置表字段的值
	 * @param columnValue
	 * @throws SQLException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	public void setColumnValue(ResultSet resultSet, Object object) throws Exception {
		String columnValue = resultSet.getString(columnName);
		setterMethod.invoke(object, columnValue);
	}

	@Override
	public String toString() {
		return "成员:" + propertyName + ", 字段:" + columnName;
	}
}
