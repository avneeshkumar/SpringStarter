package ai.edbox.SpringStarter.dao;

public class CustomerDBQueries {
	public static String getUserById() {
		
		return new StringBuilder().append("select * from master_user where id=?").toString();
	}
	
	public static String getAllUsers() {
		
		return new StringBuilder().append("select * from master_user").toString();
	}
}
