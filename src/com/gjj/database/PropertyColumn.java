package com.gjj.database;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PropertyColumn {
	// ���Ա����
	private String propertyName;
	// ���ֶ�����
	private String columnName;
	// ���Ա��Setter����
	private Method setterMethod;
	
	public PropertyColumn() {
	}

	/**
	 * ��ȡ���Ա����
	 * @return
	 */
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * �������Ա����
	 * @param propertyName
	 */
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	/**
	 * ��ȡ���ֶ�����
	 * @return
	 */
	public String getColumnName() {
		return columnName;
	}

	public void setSetterMethod(Method setterMethod) {
		this.setterMethod = setterMethod;
	}

	/**
	 * ���ñ��ֶ�����
	 * @param columnName
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	/**
	 * ���ñ��ֶε�ֵ
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
		return "��Ա:" + propertyName + ", �ֶ�:" + columnName;
	}
}
