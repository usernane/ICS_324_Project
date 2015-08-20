/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frameworks;

/**
 *
 * @author Ibrahim
 */
public interface DatabaseManager {
    public OperationResult insert(String tableName, String values);
    public OperationResult update(String tableName, String newVals, String condition);
    public OperationResult delete(String tableName, String condition);
}
