package app;


import java.sql.*;
import java.util.*;


public class ConnectionManager {

	private Connection connection;

        private boolean isConnected = false;
        /**
         * 
         * @param url
         * @param userName
         * @param password
         * @throws SQLException 
         */
	public ConnectionManager(String url, String userName, String password)throws SQLException{
            this.connection = DriverManager.getConnection(url,userName,password);
            this.isConnected = true;
	}
        /**
         * 
         * @return 
         */
        public boolean isConnected(){
            return this.isConnected;
        }
        /**
         * 
         * @param val
         * @return
         * @throws SQLException 
         */
	public ResultSet executeQuery(String val)throws SQLException{
		ResultSet r;
                Statement s = connection.createStatement ();
		r = s.executeQuery(val);
		return r;
	}
        /**
         * 
         * @param tableName
         * @param values
         * @throws SQLException 
         */
        public void insert(String tableName, String values) throws SQLException {
            Statement s = connection.createStatement ();
            System.out.println("insert into "+tableName+" values("+values+")");
            s.executeUpdate("insert into "+tableName+" values("+values+")");
        }
        /**
         * 
         * @param url
         * @param userName
         * @param password
         * @throws SQLException 
         */
	public void open(String url, String userName, String password) throws SQLException{
            this.connection = DriverManager.getConnection(url,userName,password);
	}
        /**
         * 
         * @return 
         */
        public Connection getConnection(){
            return this.connection;
        }
        /**
         * 
         * @throws SQLException 
         */
	public void close() throws SQLException{
            this.connection.close();
            this.isConnected = false;
	}
}
