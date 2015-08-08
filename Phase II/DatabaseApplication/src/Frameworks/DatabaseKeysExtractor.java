/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frameworks;

import Frameworks.UI.DataTable;
import Frameworks.table.RowData;
import Frameworks.table.TableData;
import java.io.File;
import java.text.ParseException;
import java.util.Scanner;

/**
 *
 * @author Ibrahim
 * This class will read a text file that contains the definition of primary keys and foreign keys. 
 */
public class DatabaseKeysExtractor {
   // private final String [] tokens;
    private TableData primaryKeysTable,foreignKeysTable;
    public DatabaseKeysExtractor(String fileDirectory){
       // this.tokens = new String[]{"<primary_keys>","</primary_keys>", "<foreign_keys>","<\foreign_keys>", "ref", "::"};
        init(fileDirectory);

    }
    public TableData getPrimaryKeys(){
        return this.primaryKeysTable;
    }
    public TableData getForeignKeys(){
        return this.foreignKeysTable;
    }
    private void init(String dir){
        this.primaryKeysTable = new TableData(new String[]{"Table Name","Primary Key"},0,true);
        this.foreignKeysTable = new TableData(new String[]{"Table Name","Foreign Key","Refrence Table","Refrence Column"},0,true);
        Scanner fileReader = this.openFile(dir);
            String str = fileReader.next();
            
            if(str.compareToIgnoreCase("<primary_keys>") == 0){
                try{
                    //get primary keys
                    fileReader = this.getPrimaryKeys(fileReader);
                }
                catch(ParseException ex){System.out.println("getPK "+ex);}
            }
            str = fileReader.next();
            if(str.compareToIgnoreCase("<foreign_keys>") == 0){
                //get foreign_keys
                try{
                    this.getForeignKeys(fileReader);
                }
                catch(Exception ex){System.out.println(ex);}
                
            }
        
    }
    private Scanner getPrimaryKeys(Scanner s)throws ParseException{
        while(s.hasNext()){
            String val1 = s.next();
            String val2 = s.next();
            String val3 = s.next();
            if(val2.compareToIgnoreCase("::") == 0){
                this.primaryKeysTable.addRow();
                
                this.primaryKeysTable.set(val1, this.primaryKeysTable.rows() - 1, 0);
                this.primaryKeysTable.set(val3, this.primaryKeysTable.rows() - 1, 1);
               // System.out.println("0,0"+this.primaryKeysTable.get(0, 0));
            }
            String val4 = s.next();

            if(val4.compareToIgnoreCase("</primary_Keys>") == 0){
               
                return s;
            }
        }
        throw new ParseException("err",0);
    }
    private void getForeignKeys(Scanner s)throws ParseException{
        while(s.hasNext()){
            String tableName = s.next();
            String pointToken = s.next();
            String column = s.next();
            String refToken = s.next();
            String secondTable = s.next();
            String pointToken2 = s.next();
            String refColumn = s.next();
            if((pointToken.compareToIgnoreCase("::") == pointToken2.compareToIgnoreCase("::")) == (refToken.compareToIgnoreCase("ref") == 0) ){
                this.foreignKeysTable.addRow();
                this.foreignKeysTable.set(tableName, foreignKeysTable.rows() - 1 , 0);
                this.foreignKeysTable.set(column, foreignKeysTable.rows() - 1 , 1);
                this.foreignKeysTable.set(secondTable, foreignKeysTable.rows() - 1 , 2);
                this.foreignKeysTable.set(refColumn, foreignKeysTable.rows() - 1 , 3);
            }
            String val4 = s.next();
            if(val4.compareToIgnoreCase("</foreign_Keys>") == 0){
                return ;
            }
        }
        throw new ParseException("err",0);
    }
    private Scanner openFile(String dir){
        try{
            return new Scanner(new File(dir));
        }
        catch(Exception ex){}
        return null;
    }
    public static void main(String[] s){
        new DatabaseKeysExtractor("C:\\Users\\Ibrahim\\Documents\\GitHub\\ICS_324_Project\\Phase II\\DatabaseApplication\\src\\DatabaseKeys.txt");
    }
}
