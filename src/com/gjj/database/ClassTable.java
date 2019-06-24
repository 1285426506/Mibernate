package com.gjj.database;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gjj.ann.MecField;
import com.gjj.ann.MecId;
import com.gjj.ann.MecTable;



public class ClassTable {
	private Class<?> klass;
	private String tableName;
	private List<PropertyColumn> propertiesList;
	private PropertyColumn key;
	
	public ClassTable() {
	}
	
	public Object setProperties(ResultSet resultSet) {
		Object object = null;
		try {
			object = klass.newInstance();
			for(PropertyColumn col : propertiesList) {
				col.setColumnValue(resultSet, object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return object;
	}
	
	public String getColumnsString() {
		if(propertiesList == null) {
			return null;
		}
		
		StringBuffer str = new StringBuffer();
		boolean first = true;
		for(PropertyColumn column : propertiesList) {
			str.append(first ? "" : ", ")
				.append(column.getColumnName());
			first = false;
		}
		
		return str.toString();
	}
	
	public void setClass(Class<?> klass) throws Exception {
		if(!klass.isAnnotationPresent(MecTable.class)) { 
			throw new ClassWithoutTableException("类[" + klass.getName() + "]没有对应的数据库表！"); 
		}
		this.klass = klass;
		propertiesList = new ArrayList<>();
		
		MecTable mecTable = klass.getAnnotation(MecTable.class);
		this.tableName = mecTable.table();
		
		Field[] fields = klass.getDeclaredFields();
		for(Field field : fields) {
			String fieldName = field.getName();
			String setterMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			Method setterMethod = klass.getMethod(setterMethodName, String.class);

			PropertyColumn propertyColumn = new PropertyColumn();
			
			propertyColumn.setSetterMethod(setterMethod);
			propertyColumn.setPropertyName(fieldName);
			propertyColumn.setColumnName(fieldName);
			if(field.isAnnotationPresent(MecField.class)) {
				MecField mecField = field.getAnnotation(MecField.class);
				propertyColumn.setColumnName(mecField.column());
			}
			
			if(field.isAnnotationPresent(MecId.class)) {
				if(key != null) {
					throw new PrimaryKeyNotAloneException("主键[" + field.getName() + "]重复！");
				}
				key = propertyColumn;
			}
			
			propertiesList.add(propertyColumn);
		}
	}

	public String getTableName() {
		return tableName;
	}

	public List<PropertyColumn> getPropertiesList() {
		return propertiesList;
	}

	public PropertyColumn getKey() {
		return key;
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer("类[");
		if(klass == null) {
			return "无效的类！";
		}
		str.append(klass.getName())
			.append("]，对应的表[")
			.append(tableName)
			.append("]，关键字:")
			.append(key.toString());
		for(PropertyColumn prop : propertiesList) {
			str.append("\n\t")
				.append(prop);
		}
		
		return str.toString();
	}
}
