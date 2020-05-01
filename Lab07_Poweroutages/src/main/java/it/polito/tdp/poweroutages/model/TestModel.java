package it.polito.tdp.poweroutages.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		System.out.println(model.getNercList());
		
		Nerc c = new Nerc(4, "WECC");
		System.out.println(model.getAllBlackoutAnni(3,c ));
		System.out.println(model.getAllBlackoutAnni(3,c ).size());
	
		//"1036"	"4"	"241000"	"2003-12-28 21:00:00"	"2004-01-01 11:30:00"

	    PowerOutages p = new PowerOutages(1036,c, 241000, LocalDateTime.of(2003, 12, 28, 21, 00, 00), 
	    		LocalDateTime.of(2004, 01, 01, 11, 30, 00)); 
	    
	    //"1037"	"4"	"109750"	"2003-12-22 11:15:00"	"2003-12-22 11:16:00"

	    PowerOutages l = new PowerOutages(1037,c, 109567, LocalDateTime.of(2003, 12, 22, 11, 15, 00), 
	    		LocalDateTime.of(2003, 12, 22, 18, 16, 00)); 
	    
	   // int x = l.get
		
		//System.out.println(model.verificaValida2(p, l, 500));
		
	
	}
	


}
