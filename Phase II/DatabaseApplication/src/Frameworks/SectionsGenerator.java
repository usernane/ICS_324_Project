/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frameworks;

import java.util.LinkedList;

/**
 *
 * @author Ibrahim
 */
public class SectionsGenerator {
    private LinkedList<String> sections;
    
    public SectionsGenerator(String collegeID, String departmentID, String courseMajor, String courseNumber, int termNum, int minSecNum, int maxSecNum){
        this.sections = new LinkedList<>();
        for(int i = minSecNum ; i <= maxSecNum ; i++){
            this.sections.add(collegeID+"-"+"departmentID"+"-"+courseMajor+courseNumber+termNum+"-"+i);
        }
    }
    public LinkedList<String> getSections(){
        return this.sections;
    }
}
