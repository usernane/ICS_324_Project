/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frameworks.table;

import java.util.LinkedList;

/**
 * A class that can be used to hold data in a tabular form.
 * @author Ibrahim
 */
public class TableData implements Table{
    
    private LinkedList<String> headers;
    LinkedList<RowData> data;
    private int numOfCOLUMNS;
    private boolean mode = true;
    /**
     * Creates new instance of <code>TableData</code>.
     * @param headers headers of the table.
     * @param rows initial number of rows.
     * @param mode
     */
    public TableData(String [] headers, int rows, boolean mode){
        this.mode = mode;
        this.headers = new LinkedList<>();
        this.data = new LinkedList<>();
        if(headers != null){
            //max num of cols = 10
            if(headers.length > 0 && headers.length  < 10){
                this.numOfCOLUMNS = headers.length;
                for(int i = 0 ; i < this.numOfCOLUMNS; i++){
                    this.headers.add(""+headers[i]);
                }
            }
        }
        else{
            this.numOfCOLUMNS = 5;
            for(int i = 0 ; i < this.numOfCOLUMNS; i++){
                this.headers.add("Header_"+i);
            }
        }
        //adding rows
        for(int i = 0 ; i < rows; i++){
            this.data.add(new RowData(this.numOfCOLUMNS,this.mode));
        }
    }
    public TableData(String [] headers, int initialRowsCount){
        this(headers,initialRowsCount,true);
    }
    /**
     * Removes a specific row.
     * @param index index of the row.
     * @return the data on that row.
     */
    @Override
    public RowData removeRow(int index){
        try{
            return this.data.remove(index);
        }
        catch(IndexOutOfBoundsException ex){
            throw new NoSuchRowException(index);
        }
    }
    /**
     * Update the column name.
     * @param name the new column name.
     * @param columnIndex the index of the column.
     * @return <code>true</code> if the value is updated.
     */
    public boolean setColumnName(String name, int columnIndex){
        if(columnIndex >= 0 && columnIndex < this.numOfCOLUMNS){
            LinkedList<String> tmp = this.headers;
            this.headers = new LinkedList<>();
            for(int i = 0 ; i < tmp.size() ; i++){
                if(i == columnIndex){
                    this.headers.add(""+name);
                }
                else{
                    this.headers.add(tmp.get(i));
                }
            }
            return true;
        }
        throw new NoSuchColumnException(columnIndex);
    }
    /**
     * Adds new column to the table.
     * @param name the name of the new column.
     */
    public void addColumn(String name){
        if(this.numOfCOLUMNS < 10){
            this.numOfCOLUMNS++;
            this.headers.add(""+name);
            for (RowData data1 : this.data) {
                data1.addColumn();
            }
        }
    }
    public void setEditable(boolean value, int rowIndex, int columnIndex){
        if(rowIndex < this.data.size() && rowIndex > -1){
            if(columnIndex < this.numOfCOLUMNS && columnIndex > -1){
                this.data.get(rowIndex).setEditable(value,columnIndex);
            }
            throw new NoSuchColumnException(columnIndex);
        }
        throw new NoSuchRowException(rowIndex);
    }
    /**
     * Checks if a cell value can be updated or not.
     * @param rowIndex row index.
     * @param columnIndex column index.
     * @return <code>true</code> if the cell value can be updated.
     */
    public boolean isEditable(int rowIndex, int columnIndex){
        if(rowIndex < this.data.size() && rowIndex > -1){
            if(columnIndex < this.numOfCOLUMNS && columnIndex > -1){
                return this.data.get(rowIndex).isEditable(columnIndex);
            }
            throw new NoSuchColumnException(columnIndex);
        }
        throw new NoSuchRowException(rowIndex);
    }
    /**
     * Removes a specific column from the table.
     * @param index the index of the column.
     */
    @Override
    public void removeColumn(int index){
        if(index >= 0 && index < this.headers.size()){
            //move throw each row and remove a column
            for (RowData data1 : this.data) {
                data1.removeColumn(index);
            }
            this.numOfCOLUMNS--;
            this.headers.remove(index);
        }
    }
    /**
     * Set a specific cell of the table to a specific value.
     * If the cell is allowed to update the value then, it will be updated.
     * @param data the data that will be stored.
     * @param rowIndex the index of the row.
     * @param columnIndex the index of the column.
     * @return true if the value was updated successfully.
     */
    @Override
    public boolean set(Object data, int rowIndex, int columnIndex){
        if(rowIndex < this.data.size() && rowIndex > -1){
            if(columnIndex < this.numOfCOLUMNS && columnIndex > -1){
                return this.data.get(rowIndex).set(data, columnIndex);
            }
            throw new NoSuchColumnException(columnIndex);
        }
        throw new NoSuchRowException(rowIndex);
    }
    public int getColumnIndex(String columnName){
        if(columnName != null){
            int index = 0;
            for(int i = 0 ; i < this.numOfCOLUMNS ; i++){
                if(columnName.compareToIgnoreCase(this.headers.get(i)) == 0){
                    return index;
                }
                index++;
            }
        }
        return -1;
    }
    /**
     * Adds new row to the data.
     * The number of element must be equal to the number of columns in order for the row to be added.
     * Also it must not be <code>null</code>.
     * @param data the data on that row.
     * @return true if the row was added.
     */
    public boolean addRow(RowData data){
        if(data != null){
            if(data.size() == this.numOfCOLUMNS){
               return this.data.add(new RowData(data)); 
            }
        }
        return false;
    }
    /**
     * Returns the number of columns.
     * @return the number of columns.
     */
    @Override
    public int columns(){
        return this.headers.size();
    }
    /**
     * Returns the number of rows.
     * @return number of rows.
     */
    @Override
    public int rows(){
        return this.data.size();
    }
    /**
     * Adds new row with <code>null</code> values.
     */
    public void addRow(){
        this.data.add(new RowData(this.numOfCOLUMNS,this.mode));
    }
    @Override
    public String toString(){
        String retStr = "";
        for(int i = 0 ; i < this.headers.size() ; i++){
            retStr+= this.headers.get(i)+",";
        }
        retStr +="\n";
        for(int i = 0 ; i < this.data.size() ; i++){
            retStr+= this.data.get(i)+"\n";
        }
        return retStr;
    }

    

    @Override
    public Object get(int rowIndex, int columnIndex) {
        return this.data.get(rowIndex).get(columnIndex);
    }

    @Override
    public String getColumnName(int index) {
        return this.headers.get(index);
    }
    
    public void updateInitialMode(boolean b){
        this.mode = b;
    }
}
