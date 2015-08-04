package Frameworks;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

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
	public ConnectionManager(String url,String password, String userName){
            if(password == null || userName == null || url == null){
                throw new NullPointerException("user name or password or the url is 'null'");
            }
            this.password = password;
            this.url = url;
            this.username = userName;
	}
        public LinkedList<String> getTables(){
            LinkedList<String> tablesNames = new LinkedList<>();
            try{
                DatabaseMetaData meta = this.connection.getMetaData();
                String[] types = {"TABLE"};
                ResultSet tables = meta.getTables(null, null, "%", types);
                
                while(tables.next()){
                    tablesNames.add(tables.getString("TABLE_NAME"));
                    
                }
                return tablesNames;
            }
            catch(Exception ex){}
            return null;
        }
        /**
         * Removes all entries from table.
         * @param tableName name of the table
         * @return <code>OperationResult</code> object.
         */
        public OperationResult deleteAll(String tableName){
            String message = "Done!";
            try{
                Statement s = this.connection.createStatement();
                s.executeUpdate("delete * from "+tableName);
                return new OperationResult(true, message);
            }
            catch(Exception ex){
                return new OperationResult(false,ex.getMessage());
            }
        }
        /**
         * Close the currently open connection.
         * @return <code>OperationResult</code> object.
         */
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
	 * deletes a specific row from a table.
	 * @param value the row that has the given value.
         * @param column the column that has the value.
	 * @param tableName the name of the table.
         * @return <code>OperationResult</code> object.
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
	 * Executes a specific query.
	 * @param query the query that will be executed
         * @return <code>OperationResult</code> object.
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
        /**
         * Returns the connection that is currently used for database management.
         * @return <code>Connection</code> Object.
         */
	public Connection getConnection(){
		return this.connection;
	}

	/**
	 * Insert new row in a table.
	 * @param value the values that will be inserted.
	 * @param tabelName the name of the table.
         * @return <code>OperationResult</code> object.
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
        /**
         * Returns the result set that is generated from executing a query. 
         * @return <code>ResultSet</code> object.
         */
        public ResultSet getResultSet(){
            return this.resultSet;
        }
        /**
         * Open the connection using the given url, username and password.
         * @return <code>OperationResult</code> object.
         */
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