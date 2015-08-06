/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frameworks.table;

import java.util.LinkedList;

/**
 *
 * @author Ibrahim
 */
public class RowData {
    private Object[] rowData;
    private boolean [] isEditable;
    private boolean mode = true;
    /**
     * Creates new instance of <code>RowData</code>.
     * @param numOfColumns initial number of cells.
     * @param initialMode <code>true</code> for edit and <code>false</code> for view.
     */
    public RowData(int numOfColumns, boolean initialMode){
        this.mode = initialMode;
        if(numOfColumns > 0 && numOfColumns < 11){
            this.rowData = new Object[numOfColumns];
        }
        else{
           this.rowData = new Object[5];
        }
        initEditableArray();
    }
    public RowData(int numOfCols){
        this(numOfCols,true);
    }    
    public RowData(Object [] data){
        this(data,true);
    }
    public RowData(Object [] data, boolean initialMode){
        this.mode = initialMode;
        if(data != null){
            this.rowData = new Object[data.length];
            this.isEditable = new boolean[data.length];
            for(int i = 0 ; i < this.rowData.length ; i++){
                this.rowData[i] = data[i];
            }
        }
        else{
           this.rowData = new Object[5]; 
        }
        initEditableArray();
    }
    /*
    initialize the editable array
    */
    private void initEditableArray(){
        if(this.isEditable == null){
            this.isEditable = new boolean[this.rowData.length];
            for(int i = 0 ; i < this.isEditable.length ; i++){
                this.isEditable[i] = this.mode;
            }
        }
    }
    /**
     * Creates new instance of <code>RowData</code>.
     */
    public RowData(){
        this(-1);
    }
    public void addColumn(){
        this.addColumn(null);
    }
    /**
     * Appends new cell to the end. 
     * 
     * @param val initial value for the cell.
     */
    public void addColumn(Object val){
        if(this.rowData.length < 10){
            Object[] tmp = this.rowData;
            boolean [] tmpBoolean = this.isEditable;
            
            this.isEditable = new boolean[tmp.length+1];
            this.rowData = new Object[tmp.length+1];
            //copy old values
            for(int i = 0 ; i < tmp.length ; i++){
                this.rowData[i] = tmp[i];
                this.isEditable[i] = tmpBoolean[i];
            }
            //updates mode for the new cell
            this.rowData[tmp.length] = val;
            this.isEditable[tmp.length] = this.mode;
        }
    }
    /**
     * Updates a value of a row cell.
     * The value will be updated if and only if the cell is editable.
     * @param data the new value.
     * @param column the index of the cell.
     * @return <code>true</code> if the value is updated.
     */
    public boolean set(Object data,int column){
        if(column > -1 && column < this.rowData.length){
            if(this.isEditable(column)){
                this.rowData[column] = data;
                return true;
            }
            return true;
        }
        throw new NoSuchColumnException(column);
    }
    /**
     * updates the value that will be set for the newly added cells.
     * @param b 
     */
    public void updateInitialMode(boolean b){
        this.mode = b;
    }
    public void setEditable(boolean b, int columnIndex){
        if(columnIndex > -1 && columnIndex < this.size()){
            this.isEditable[columnIndex] = b;
        }
    }
    /**
     * Removes a cell from the row.
     * @param index the index of the cell.
     */
    public void removeColumn(int index){
        if(index >= 0 && index < this.rowData.length){
            Object [] tmp = this.rowData;
            boolean [] tmpB = this.isEditable;
            this.rowData = new Object[tmp.length - 1];
            this.isEditable = new boolean[tmp.length - 1];
            int tmpIndex = 0;
            for(int i = 0 ; i < rowData.length ; i++){
                if(i == index){
                    tmpIndex++;
                }
                this.rowData[i] = tmp[tmpIndex];
                this.isEditable[i] = tmpB[tmpIndex];
                tmpIndex++;
            }
            return;
        }
        throw new NoSuchColumnException(index);
    }
    /**
     * Removes the last cell in the row.
     */
    public void removeColumn(){
        this.removeColumn(this.size() - 1);
    }
    /**
     * Retrieve the value that stored in specific cell.
     * @param columnIndex the index of the cell.
     * @return the stored value.
     */
    public Object get(int columnIndex){
        if(columnIndex < this.rowData.length && columnIndex > -1)
            return this.rowData[columnIndex];
        throw new NoSuchColumnException(columnIndex);
    }
    /**
     * Checks if a cell can be updated or not.
     * @param columnIndex the index of the cell
     * @return <code>true</code> if the cell is editable.
     */
    public boolean isEditable(int columnIndex){
        return this.isEditable[columnIndex];
    }
    /**
     * Returns the size of the row.
     * @return size of the row.
     */
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
