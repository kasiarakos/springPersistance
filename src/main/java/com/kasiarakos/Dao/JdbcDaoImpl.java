package com.kasiarakos.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kasiarakos.model.Circle;

@Component
public class JdbcDaoImpl {
	
	@Autowired
	private DataSource dataSource;
	

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Circle getCircle(int id){
		
		Connection conn = null;
		
		try{
		conn = dataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from circle where id = ?");
		ps.setInt(1, id);
		
		Circle circle = null;
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()){
			circle = new Circle(id, rs.getString("name"));
		}
		
		rs.close();
		ps.close();
		 
		return circle;
		}catch(SQLException e){
			e.printStackTrace();
			throw new RuntimeException();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
