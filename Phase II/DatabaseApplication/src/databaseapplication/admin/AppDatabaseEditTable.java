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
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 *
 * @author Ibrahim
 */
public class AppDatabaseEditTable extends DataTable{
    private TableData primaryKeys,fKeys;
    private ConnectionManager manager;
    private String currentTable;
    private TableData oldData;
    private String tablePrimaryKey;
    
    public AppDatabaseEditTable(ConnectionManager manager){
        if(manager != null){
            this.manager = manager;
            this.primaryKeys = this.manager.getPrimaryKeys();
            this.fKeys = this.manager.getForeignKeys();
            super.getJTable().getModel().addTableModelListener(new TableModelListener(){

                @Override
                public void tableChanged(TableModelEvent e) {
                    try{
                        System.out.println(oldData.get(getJTable().getSelectedRow(), getJTable().getSelectedColumn()));
                    }
                    catch(Exception ex){}
                }
            });
        }
    }

    public void selectData(String tableName){
        if(tableName != null){
            this.setForiegnFields(tableName);
            this.currentTable = tableName;
            //this.findPrimary(tableName);
        }
        
    }
    
    
    private void findPrimary(String tableName){
        for(int i = 0 ; i < this.primaryKeys.rows() ; i++){
            if(this.currentTable.compareToIgnoreCase((String)this.primaryKeys.get(i, 0)) == 0){
                this.tablePrimaryKey = (String )this.primaryKeys.get(i, 1);
            }
        }
    }
    private void setForiegnFields(String tableName){
        TableData data = this.manager.getResultSetAsTable("select * from "+tableName);
        this.oldData = data;
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
