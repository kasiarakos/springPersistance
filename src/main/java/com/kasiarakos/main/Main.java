package com.kasiarakos.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.kasiarakos.Dao.JdbcDaoImpl;
import com.kasiarakos.model.Circle;
import com.kasiarakos.springConfig.SpringConfig;

public class Main {

	public static void main(String[] args) {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
		
		JdbcDaoImpl dao = context.getBean(JdbcDaoImpl.class);
		Circle circle = dao.getCircle(1);
		System.out.println(circle.getName());
		System.out.println("count: "+dao.getCount());
		System.out.println("Circle name: "+dao.getCircleName(1));
		
		context.close();
	}
	

}
