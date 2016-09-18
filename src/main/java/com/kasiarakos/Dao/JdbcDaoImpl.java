package com.kasiarakos.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kasiarakos.model.Circle;

@Component
public class JdbcDaoImpl {

	@Autowired
	private JdbcTemplate template;

	public int getCount() {
		String sql = "SELECT COUNT(*) FROM circle";
		return template.queryForObject(sql, Integer.class);
	}

	public String getCircleName(int id) {
		String sql = "Select name from circle where id = ?";
		return template.queryForObject(sql, new Object[] { id }, String.class);
	}

	public Circle getCircle(int id) {
		String sql = "select * from circle where id = ?";
		return template.queryForObject(sql, new Object[] { id }, new CircleMapper());
	}

	public List<Circle> getAllCircles() {
		String sql = "select * from circle";
		return template.query(sql, new CircleMapper());
	}
	
	public void insertCircle(Circle circle){
		String sql = "Insert into circle values (?, ?)";
		template.update(sql, circle.getId(), circle.getName());
	}
	
	public boolean createTable(){
		String sql = "create table triangle (id integer, name varchar(50))";
		try{
			template.execute(sql);
		}catch(Exception e){
			return false;
		}
		return true;
	}

	private static final class CircleMapper implements RowMapper<Circle> {

		@Override
		public Circle mapRow(ResultSet res, int numRow) throws SQLException {
			return new Circle(res.getInt("id"), res.getString("name"));
		}

	}

}
