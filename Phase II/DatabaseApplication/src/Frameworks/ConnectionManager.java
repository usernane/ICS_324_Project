package Frameworks;

import Frameworks.table.TableData;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
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
        private TableData primaryKeys, foreignKeys;
        
        public ConnectionManager(String url,String userName,String password){
            this(url,userName,password,null);
        }
	/**
	 * 
	 * @param password
	 * @param userName
	 * @param url
         * @param keysFileDir
	 */
	public ConnectionManager(String url,String userName,String password, String keysFileDir){
            if(password == null || userName == null || url == null){
                throw new NullPointerException("user name or password or the url is 'null'");
            }
            this.password = password;
            this.url = url;
            this.username = userName;
            loadKeys(keysFileDir);
	}
        private void loadKeys(String dir){
            if(dir != null){
                DatabaseKeysExtractor ex = new DatabaseKeysExtractor(dir);
                this.primaryKeys = ex.getPrimaryKeys();
                this.foreignKeys = ex.getForeignKeys();
            }
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
                s.executeUpdate("insert into "+tabelName+" values ("+value+")");
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
        public TableData getResultSetAsTable(String query){
            if(this.connection == null){
                return null;
            }
            
            try{
                Statement s = this.connection.createStatement();
                this.resultSet = s.executeQuery(query);
                
                ResultSetMetaData meta = this.resultSet.getMetaData();
                
                String [] columnsNames = new String[meta.getColumnCount()];
                for(int i = 1 ; i <= meta.getColumnCount() ; i++){
                    columnsNames[i-1] = meta.getColumnLabel(i);
                }
                TableData returnVal = new TableData(columnsNames,0);
                while(this.resultSet.next()){
                    returnVal.addRow();
                    for(int i = 0 ; i < returnVal.columns() ; i++){
                        returnVal.set(this.resultSet.getString(i+1), returnVal.rows() - 1, i);
                    }
                }
                return returnVal;
            }
            catch(SQLException ex){System.out.println(ex);}
            
            return null;
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

    public TableData getPrimaryKeys() {
        return this.primaryKeys;
    }

    public TableData getForeignKeys() {
        return this.foreignKeys;
    }

}