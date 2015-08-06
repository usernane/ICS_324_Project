/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frameworks.UI;

import Frameworks.table.RowData;
import Frameworks.table.TableData;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Ibrahim
 */
public class DataTableModel extends AbstractTableModel{
    private TableData data;
    
    public DataTableModel(){
        this(null);
    }
    public DataTableModel(TableData m){
        if(m != null){
            this.data = m;
        }
        else{
            this.data = new TableData(null,0);
        }
    }
    @Override
    public int getRowCount() {
        return this.data.rows();
    }
    public void setColumnName(String value, int columnIndex){
        this.data.setColumnName(""+value, columnIndex);
        super.fireTableDataChanged();
    }
    @Override
    public int getColumnCount() {
        return this.data.columns();
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
        System.out.println(this.data.isEditable(rowIndex, columnIndex));
        return this.data.isEditable(rowIndex, columnIndex);
    }
    public void addRow(){
        this.data.addRow();
        super.fireTableDataChanged();
    }
    public boolean addRow(RowData aRow){
        if(this.data.addRow(aRow)){
            super.fireTableDataChanged();
            return true;
        }
        return false;
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return this.data.get(rowIndex, columnIndex);
    }
    
    @Override
    public void setValueAt(Object value,int rowIndex, int columnIndex){
        this.data.set(value, rowIndex, columnIndex);
        super.fireTableDataChanged();
    }
    
    @Override
    public String  getColumnName(int columnIndex){
        return this.data.getColumnName(columnIndex);
    }

    public void updateData(TableData data) {
        if(data != null){
            this.data = data;
            super.fireTableDataChanged();
        }
    }

    public TableData getTableData() {
        return this.data;
    }

}
