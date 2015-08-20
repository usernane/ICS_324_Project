/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseapplication.instructor;

import java.sql.Connection;

import javax.swing.JFrame;

import databaseapplication.CommonMethods;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Ibrahim
 * 
 * @author ibra5_000
 * @version v1.1
 * 
 */

public class InstructorMainWindow extends JFrame{
static Connection con;
static String instructorID;
static CommonMethods cm;    	
	public InstructorMainWindow(String i) {	
            instructorID = i;
            cm = new CommonMethods();
            con = cm.getConnection();

	
		JFrame f = new JFrame();
		f.setSize(525, 275);
		f.setLayout(new GridLayout(4,0));
		
		JButton vs = new JButton("View");//TODO extract as new table
		vs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InstructorViewStudentsDialog();}});

		JButton as = new JButton("Add");//TODO extract as new table
		as.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InstructorAssignCutoffsDialog();}});
		
		JButton vc = new JButton("View");//TODO extract as new table
		vc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InstructorViewGradingComponentsDialog();}});
		
		JButton vp = new JButton("<html>View For<br />A Section</html>");//TODO extract as new table
		vp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InstructorViewPointsDialog();}});
		
		JButton vpa = new JButton("<html>View For<br />An Activity</html>");//TODO extract as new table
		vpa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InstructorViewActivityPointsDialog();}});
		
		JButton st = new JButton("<html>View<br />Statisics</html>");//TODO extract as new table
		st.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				new InstructorViewSectionStatisticsDialog();}});
		
		JButton dc = new JButton("Delete");
		dc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InstructorDeleteGradingComponenetDialog();}});
		
		JButton vgc = new JButton("View");
		vgc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InstructorViewGradingCutoffsDialog();}});
		
		JButton ap = new JButton("Assign");
		ap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InstructorAssignPointsDialog();}});
		
                JButton ag = new JButton("Add");
		ag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InstructorAssignGradingComponentDialog();}});
		
                JButton dco = new JButton("Delete");
		dco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InstructorDeleteGradingCutoffDialog();}});
		
                
                
                JPanel[] p = {new JPanel(),new JPanel(),new JPanel(),new JPanel()};
                GridLayout g = new GridLayout();
                g.setHgap(5);

                for (JPanel a : p){
                    a.setLayout(g);
                }
                
                
                p[0].setBorder(BorderFactory.createTitledBorder(
                       BorderFactory.createLineBorder(Color.black),
                        "Student menu"));
                p[1].setBorder(BorderFactory.createTitledBorder(
                       BorderFactory.createLineBorder(Color.black),
                        "Grading componenets"));
                p[2].setBorder(BorderFactory.createTitledBorder(
                       BorderFactory.createLineBorder(Color.black),
                        "Points"));
                p[3].setBorder(BorderFactory.createTitledBorder(
                       BorderFactory.createLineBorder(Color.black),
                        "Cutoffs"));
                
                
		
                p[0].add(vs);//student
                p[1].add(ag);p[1].add(vc);p[1].add(dc);//components
                p[2].add(ap);p[2].add(vp);p[2].add(vpa);p[2].add(st);//points
		p[3].add(as);p[3].add(vgc);p[3].add(dco);//cutoffs
		
		
                for (JPanel a : p)
                    f.add(a);
                
		f.setDefaultCloseOperation(2);
		f= new CommonMethods().CentralizeFrame(f);
	}	
}
