/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

/**
 *
 * @author Ibrahim
 */
public class OperationResult {
    private final String message;
    private final boolean result;
    
    public OperationResult(boolean successOrNot, String message){
        this.message = message;
        this.result = successOrNot;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return the result
     */
    public boolean getResult(){
        return result;
    }
}
