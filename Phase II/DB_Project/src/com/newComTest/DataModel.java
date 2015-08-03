package com.newComTest;

/**
 * @(#)DataModel.java
 *
 *
 * @author
 * @version 1.00 2015/3/11
 */
import javax.swing.table.AbstractTableModel;
import java.util.*;
public class DataModel extends AbstractTableModel{

	private String [] headers = {"SN","SNAME","MCODE","GPA"};
	private Object [][] values;

    public DataModel(String[][] values) {
    	this.values = values;
    }
	public DataModel() {
    	this.values = new String[1][4];
    }
    public String getColumnName(int column){
    	return this.headers[column];
    }

    public boolean isCellEditable(int rowIndex, int columnIndex){
    	return false;
    }
    public void setData(String[][] values){
    	this.values = values;
    	fireTableDataChanged();
    }
    public Object getValueAt(int rowIndex,int columnIndex){
    	return this.values[rowIndex][columnIndex];
    }
    public void setValueAt(Object aValue,int rowIndex,int columnIndex){
		this.values[rowIndex][columnIndex] = aValue;
		fireTableDataChanged();
    }
    public int getColumnCount(){
    	return this.headers.length;
    }
    public void removeAll(){
    	this.values = new String[1][4];
    	super.fireTableDataChanged();
    }
    public int getRowCount(){
    	return this.values.length;
    }
}