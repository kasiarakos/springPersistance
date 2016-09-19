package com.kasiarakos.main;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.kasiarakos.Dao.HibernateDaoImpl;
import com.kasiarakos.Dao.JdbcDaoImpl;
import com.kasiarakos.Dao.SupportJdbcDaoImpl;
import com.kasiarakos.model.Circle;
import com.kasiarakos.springConfig.SpringConfig;

public class Main {

	public static void main(String[] args) {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
		
		
		JdbcDaoImpl dao = context.getBean(JdbcDaoImpl.class);
		SupportJdbcDaoImpl  daoSupport = context.getBean(SupportJdbcDaoImpl.class);
		HibernateDaoImpl daoHibernte = context.getBean(HibernateDaoImpl.class);
		
		System.out.println(daoSupport.getCircle(3));
		
		try{
			dao.getCircle(4);
		}catch(Exception e){
			dao.insertCircle(new Circle(4, "fourthCircle"));
		}
		
		
		
		System.out.println("count: "+dao.getCount());
		System.out.println("Circle name: "+dao.getCircleName(1));
		Circle c = dao.getCircle(1);
		System.out.println("circle 1: "+c);
		
		System.out.println("\n>>All Circles");
		List<Circle> circles = dao.getAllCircles();
		circles.forEach((ci)->{System.out.println(ci);});
		
		if(dao.createTable()){
			System.out.println("Triangle Table created");
		}else{
			System.out.println("problem creating triangle table it may already exists");
		}
		
		System.out.println("\n\n\n");
		Circle circleHi = daoHibernte.findOne(2);
		System.out.println("hybernate count: "+circleHi);
		
		context.close();
	}
	

}
