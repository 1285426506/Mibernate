package com.gjj.database;

public class SqlWorker {
	private StringBuffer select;
	private StringBuffer insert;
	private StringBuffer update;
	private StringBuffer delete;
	
	private static final String FIELDS = "#";
	private static final String TABLE = "$";
	private static final String VALUES = "*";
	
	public SqlWorker() {
		this.select = new StringBuffer("SELECT # FROM $ WHERE TRUE");
		this.insert = new StringBuffer("INSERT INTO $ (#) VALUES (*)");
		this.update = new StringBuffer("UPDATE $ SET ");
		this.delete = new StringBuffer("DELETE FROM $ WHERE TRUE");
	}
	
	public String selectQuery(Class<?> klass) {
		ClassTableFactory ctf = new ClassTableFactory();
		ClassTable ct = ctf.getClassTable(klass);
		
		String sqlString = select.toString();
		sqlString = replaceTable(sqlString, ct);
		sqlString = replaceField(sqlString, ct);
		
		return sqlString;
	}
	
	private String replaceTable(String sqlString, ClassTable classTable) {
		String tableName = classTable.getTableName();
		sqlString = sqlString.replace(TABLE, tableName);
		
		return sqlString;
	}
	
	private String replaceField(String sqlString, ClassTable classTable) {
		String columnsString = classTable.getColumnsString();
		sqlString = sqlString.replace(FIELDS, columnsString);
		
		return sqlString;
	}
	
	public String insertQuery(Class<?> klass) {
		ClassTableFactory ctf = new ClassTableFactory();
		ClassTable ct = ctf.getClassTable(klass);
		
		String sqlString = insert.toString();
		sqlString = replaceTable(sqlString, ct);
		sqlString = replaceField(sqlString, ct);
		
		return sqlString;
	}
	
	public String updateQuery(Class<?> klass) {
		ClassTableFactory ctf = new ClassTableFactory();
		ClassTable ct = ctf.getClassTable(klass);
		
		String sqlString = update.toString();
		sqlString = replaceTable(sqlString, ct);
		
		return sqlString;
	}
	
	public String deleteQuery(Class<?> klass) {
		ClassTableFactory ctf = new ClassTableFactory();
		ClassTable ct = ctf.getClassTable(klass);
		
		String sqlString = delete.toString();
		sqlString = replaceTable(sqlString, ct);
		
		return sqlString;
	}
}
