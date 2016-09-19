package com.kasiarakos.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import com.kasiarakos.model.Circle;

@Component
public class SupportJdbcDaoImpl extends JdbcDaoSupport{

	
	public Circle getCircle(int id) {
		String sql = "select * from Circle where id = ?";
		return getJdbcTemplate().queryForObject(sql, new Object[] { id }, new CircleMapper());
	}
	
	private static final class CircleMapper implements RowMapper<Circle> {

		@Override
		public Circle mapRow(ResultSet res, int numRow) throws SQLException {
			return new Circle(res.getInt("id"), res.getString("name"));
		}

	}
}
