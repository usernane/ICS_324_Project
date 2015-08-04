package Frameworks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Ibrahim
 * @version 1.0
 * @created 04-Aug-2015 2:34:02 AM
 */
public final class ConnectionManager {

	private Connection connection;
	private String password;
	private String url;
	private String username;
        private ResultSet resultSet;

	/**
	 * 
	 * @param password
	 * @param userName
	 * @param url
	 */
	public ConnectionManager(String password, String userName, String url){
            if(password == null || userName == null || url == null){
                throw new NullPointerException("user name or password or the url is 'null'");
            }
            this.password = password;
            this.url = url;
            this.username = username;
	}

	public OperationResult closeConnection(){
            String message = "Connection Closed!";
            try{
                this.connection.close();
            }
            catch(Exception ex){
                return new OperationResult(false,ex.getMessage());
            }
            return new OperationResult(true,message);
	}

	/**
	 * 
	 * @param value
	 * @param tableName
	 */
	public OperationResult delete( String tableName, String column, String value){
            String message = "Done!";
            try{
                Statement s = this.connection.createStatement();
                s.executeUpdate("delete from "+tableName+" where "+column+" = "+value);
                return new OperationResult(true, message);
            }
            catch(Exception ex){
                return new OperationResult(false,ex.getMessage());
            }
            
	}

	/**
	 * 
	 * @param query
	 */
	public OperationResult executeQuery(String query){
		String message = "done";
                try{
                    Statement s = this.connection.createStatement();
                    this.resultSet = s.executeQuery(url);
                    return new OperationResult(true,message);
                }
                catch(Exception ex){
                    return new OperationResult(false, ex.getMessage());
                }
	}

	public Connection getConnection(){
		return this.connection;
	}

	/**
	 * 
	 * @param value
	 * @param tabelName
	 */
	public OperationResult insert(String tabelName, String value){
            String message = "Done!";
            try{
                Statement s = this.connection.createStatement();
                s.executeUpdate("insert into "+tabelName+" values"+value);
                return new OperationResult(true, message);
            }
            catch(Exception ex){
                return new OperationResult(false,ex.getMessage());
            }
	}
        
        public ResultSet getResultSet(){
            return this.resultSet;
        }

	public OperationResult openConnection(){
		String message = "Connecion is open!";
            try{
                this.connection = DriverManager.getConnection(this.url, this.username, this.password);
                return new OperationResult(true,message);
            }
            catch(Exception ex){
                return new OperationResult(false,ex.getMessage());
            }
	}

}