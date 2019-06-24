package com.gjj.database;

import java.util.HashMap;
import java.util.Map;

public class ClassTableFactory {
	private static final Map<Class<?>, ClassTable> classTableMap;
	
	static {
		classTableMap = new HashMap<>();
	}
	
	public ClassTableFactory() {
	}
	
	public ClassTable getClassTable(Class<?> klass) {
		ClassTable classTable = classTableMap.get(klass);
		if(classTable == null) {
			classTable = new ClassTable();
			try {
				classTable.setClass(klass);
				classTableMap.put(klass, classTable);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return classTable;
	}
}
