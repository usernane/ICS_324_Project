/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newComTest;

import com.table.RowData;
import com.table.Table;
import com.table.TableData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
/**
 *
 * @author Ibrahim
 */
public class SQLTableData implements Table{
    private TableData data;
    
    public SQLTableData(ResultSet set){
        if(set != null){
            try{
                ResultSetMetaData setMeta = set.getMetaData();
                int columnsNum = setMeta.getColumnCount();
                String [] headers;
                int rowsCount = 0;
                if(columnsNum != 0){
                    //initiallize headers
                    headers = new String[columnsNum];
                    for(int i = 0 ; i < columnsNum ; i++){
                        headers[i] = setMeta.getColumnLabel(i);
                    }
                    this.data = new TableData(headers,0);
                    //initialize rows
                    if(set.last()){
                        rowsCount = set.getRow();
                        set.first();
                        for(int i = 0 ; i < rowsCount ; i++){
                            RowData d = new RowData(columnsNum);
                            for(int j = 0 ; j < columnsNum ; j++){
                                d.set(set.getObject(j), j);
                            }
                            this.data.addRow(d);
                            set.next();
                        }
                        
                    }
                }
                else{
                    headers = new String[1];
                    headers[0] = "N/A";
                    this.data = new TableData(headers,0);
                }
            }
            catch(SQLException ex){}
        }
    }
    @Override
    public int rows() {
        return this.data.rows();
    }

    @Override
    public int columns() {
        return this.data.columns();
    }

    @Override
    public RowData removeRow(int index) {
        return this.data.removeRow(index);
    }

    @Override
    public void removeColumn(int columnIndex) {
        this.data.removeColumn(columnIndex);
    }

    @Override
    public boolean set(Object data, int rowIndex, int columnIndex) {
        return this.data.set(data, rowIndex, columnIndex);
    }

    @Override
    public Object get(int rowIndex, int columnIndex) {
        return this.data.get(rowIndex, columnIndex);
    }
    
    @Override
    public String toString(){
        return this.data.toString();
    }
}
