/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frameworks.UI;

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
    
    public DataTable(){
        init();
        buildUI();
    }
    public int rows(){
        return this.tableModel.getRowCount();
    }
    public int columns(){
        return this.tableModel.getColumnCount();
    }
    private void init(){
       this.tableModel = new DataTableModel();
       this.table = new JTable(this.tableModel);
    }
    public void addRow(){
        this.tableModel.addRow();
        
    }
    private void buildUI(){
        super.getViewport().add(this.table);
    }
    public void setValueAt(Object value, int rowIndex, int columnIndex){
        this.tableModel.setValueAt(value, rowIndex, columnIndex);
    }
    public DataTableModel getModel(){
        return (DataTableModel)this.table.getModel();
    }
    public void updateData(TableData data){
        if(data != null){
            this.tableModel.updateData(data);
            this.tableModel.fireTableStructureChanged();
            this.tableModel.fireTableDataChanged();
        }
    }
}
