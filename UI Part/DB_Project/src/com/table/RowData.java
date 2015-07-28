/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.table;

import com.table.NoSuchColumnException;
import java.util.LinkedList;

/**
 *
 * @author Ibrahim
 */
public class RowData {
    private Object[] rowData;
    //private int maxNumOfColumns;
    public RowData(int numOfColumns){
        if(numOfColumns > 0 && numOfColumns < 11){
            this.rowData = new Object[numOfColumns];
        }
        else{
           this.rowData = new Object[5]; 
        }
    }
    public RowData(Object [] data){
        if(data != null){
            this.rowData = new Object[data.length];
            for(int i = 0 ; i < this.rowData.length ; i++){
                this.rowData[i] = data[i];
            }
        }
        else{
           this.rowData = new Object[5]; 
        }
    }
    public RowData(){
        this(-1);
    }
    public void addColumn(){
        if(this.rowData.length < 10){
            Object[] tmp = this.rowData;
            this.rowData = new Object[tmp.length+1];
            for(int i = 0 ; i < tmp.length ; i++){
                this.rowData[i] = tmp[i];
            }
        }
    }
    public void set(Object data,int column){
        if(column > -1 && column < this.rowData.length){
            this.rowData[column] = data;
            return;
        }
        throw new NoSuchColumnException(column);
    }
    public void removeColumn(int index){
        if(index >= 0 && index < this.rowData.length){
            Object [] tmp = this.rowData;
            this.rowData = new Object[tmp.length - 1];
            int tmpIndex = 0;
            for(int i = 0 ; i < rowData.length ; i++){
                if(i == index){
                    tmpIndex++;
                }
                this.rowData[i] = tmp[tmpIndex];
                tmpIndex++;
            }
            return;
        }
        throw new NoSuchColumnException(index);
    }
    public void removeColumn(){
        this.removeColumn(this.size() - 1);
    }
    public Object get(int columnIndex){
        if(columnIndex < this.rowData.length)
            return this.rowData[columnIndex];
        return null;
    }
    
    public int size(){
        return this.rowData.length;
    }
    
    @Override
    public String toString(){
        String retStr = "";
        for (Object rowData1 : this.rowData) {
            retStr += rowData1 + ", ";
        }
        return retStr;
    }

    public Object[] getData() {
        return this.rowData;
    }

}
