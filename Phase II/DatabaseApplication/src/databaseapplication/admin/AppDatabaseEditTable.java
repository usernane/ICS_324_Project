/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseapplication.admin;

import Frameworks.ConnectionManager;
import Frameworks.UI.DataTable;
import Frameworks.table.TableData;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;

/**
 *
 * @author Ibrahim
 */
public class AppDatabaseEditTable extends DataTable{
    private TableData primaryKeys,fKeys;
    private ConnectionManager manager;
    
    
    public AppDatabaseEditTable(ConnectionManager manager){
        if(manager != null){
            this.manager = manager;
            this.primaryKeys = this.manager.getPrimaryKeys();
            this.fKeys = this.manager.getForeignKeys();
        }
    }

    public void selectData(String tableName){
        if(tableName != null){
            this.setForiegnFields(tableName);
        }
    }
    
    private void setForiegnFields(String tableName){
        TableData data = this.manager.getResultSetAsTable("select * from "+tableName);
        super.updateData(data);
        for(int i = 0 ; i < this.fKeys.rows() ; i++){
            String tableNameF = (String)this.fKeys.get(i, 0);
            System.out.println("Table Name = "+tableNameF);
            if(tableNameF.compareToIgnoreCase(tableName) == 0){
                
                String keyColumn = (String)this.fKeys.get(i, 1);
                String refTable = (String)this.fKeys.get(i, 2);
                String refCol = (String)this.fKeys.get(i, 3);
                System.out.println("select "+refCol+" from "+refTable);
                TableData fkData = this.manager.getResultSetAsTable("select "+refCol+" from "+refTable);
                JComboBox<String> fkComboBox = new JComboBox<>();
                for(int j = 0 ; j < fkData.rows() ; j++){
                    fkComboBox.addItem((String)fkData.get(j, 0));
                }
                
                super.getJTable().getColumnModel().getColumn(data.getColumnIndex(keyColumn)).setCellEditor(new DefaultCellEditor(fkComboBox));
            }
        }
    }
}
