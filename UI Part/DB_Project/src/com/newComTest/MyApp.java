package com.newComTest;

/**
 * @(#)MyApp.java
 *
 * MyApp application
 *
 * @author
 * @version 1.00 2015/3/11
 */

import java.sql.*;
import java.util.*;

public class MyApp {

    public static void main(String[] args) {
		try{
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception ex){}
    	// TODO, add your application code
    	System.out.println("Hello World!");
    	new MainFrame();
    }
}
