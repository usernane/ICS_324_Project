/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frameworks.UI;

import Frameworks.table.RowData;
import Frameworks.table.TableData;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Ibrahim
 */
public class DataTable extends JScrollPane{
    private JTable table;
    private DataTableModel tableModel;
    /**
     * Creates new instance of <code>DataTable</code>.
     */
    public DataTable(){
        init();
        buildUI();
    }
    private void buildUI(){
        super.getViewport().add(this.table);
    }
    private void init(){
       this.tableModel = new DataTableModel();
       this.table = new JTable(this.tableModel);
      
    }
    /**
     * Returns the number of rows in the table.
     * @return number of rows.
     */
    public int rows(){
        return this.tableModel.getRowCount();
    }
    /**
     * Returns the number of columns in the table.
     * @return number of columns.
     */
    public int columns(){
        return this.tableModel.getColumnCount();
    }
    
    /**
     * Adds new empty row to the table.
     */
    public void addRow(){
        this.tableModel.addRow();
        
    }
    /**
     * Updates the value of a cell in the table.
     * @param value the new value.
     * @param rowIndex the index of the row.
     * @param columnIndex the index of the column.
     */
    public void setValueAt(Object value, int rowIndex, int columnIndex){
        this.tableModel.setValueAt(value, rowIndex, columnIndex);
    }
    /**
     * Returns the model that is used to store data.
     * @return <code>DataTableModel</code> object.
     */
    public DataTableModel getModel(){
        return (DataTableModel)this.table.getModel();
    }
    /**
     * Updates the data that is currently displayed with another one.
     * @param data the new data.
     */
    public void updateData(TableData data){
        if(data != null){
            this.tableModel.updateData(data);
            this.tableModel.fireTableStructureChanged();
            this.tableModel.fireTableDataChanged();
        }
    }
    
    public TableData getTableData(){
        return this.tableModel.getTableData();
    } 

    public Object getValueAt(int rowIndex, int columnIndex) {
        return this.tableModel.getValueAt(rowIndex, columnIndex);
    }

    public void addRow(RowData data) {
        this.tableModel.addRow(data);
    }
}
