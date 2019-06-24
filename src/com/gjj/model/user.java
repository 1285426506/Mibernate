package com.gjj.model;

import com.gjj.ann.MecId;
import com.gjj.ann.MecTable;
@MecTable(table = "user")
public class user {
	@MecId
	private String user_id;
	
	private String user_password;
	
	private String user_nick;
	 
	public user() {
		
	}
	
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_nick() {
		return user_nick;
	}

	public void setUser_nick(String user_nick) {
		this.user_nick = user_nick;
	}

	public user(String user_id, String user_password, String user_nick) {
		this.user_id = user_id;
		this.user_password = user_password;
		this.user_nick = user_nick;
	}
	
	public String getValues() {
		return user_id+","+user_password+","+'"'+user_nick+'"';
	}

	public String toString() {
		return "[user_id=" + user_id + ", user_password=" + user_password + ", user_nick=" + user_nick + "]";
	}
}
