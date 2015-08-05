/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frameworks.table;

import Frameworks.table.RowData;

/**
 *
 * @author Ibrahim
 */
public interface Table {
    public int rows();
    public int columns();
    public RowData removeRow(int index);
    public void removeColumn(int columnIndex);
    public boolean set(Object data, int rowIndex, int columnIndex);
    public Object get(int rowIndex, int columnIndex);
    public String getColumnName(int columNumber);
}
