/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.table;

import java.util.NoSuchElementException;

/**
 *
 * @author Ibrahim
 */
public class NoSuchColumnException extends NoSuchElementException{
    
    public NoSuchColumnException(int column){
        super("Column "+column+" was not found");
    }
}
