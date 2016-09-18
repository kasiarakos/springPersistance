package com.kasiarakos.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.kasiarakos.model.Circle;

@Component
public class JdbcDaoImpl {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private JdbcTemplate template;

	public Circle getCircle(int id) {

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("select * from circle where id = ?");
			ps.setInt(1, id);

			Circle circle = null;
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				circle = new Circle(id, rs.getString("name"));
			}

			rs.close();
			ps.close();

			return circle;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public int getCount() {
		String sql = "SELECT COUNT(*) FROM circle";
		return template.queryForObject(sql, Integer.class);
	}

	public String getCircleName(int id) {
		String sql = "Select name from circle where id = ?";
		return template.queryForObject(sql, new Object[] { id }, String.class);
	}

	public Circle getCircleObject(int id) {
		String sql = "select * from circle where id = ?";
		return template.queryForObject(sql, new Object[] { id }, (res, numRow) -> {
			return new Circle(res.getInt("id"), res.getString("name"));
		});

	}

	public List<Circle> getAllCircles() {
		String sql = "select * from circle";
		return template.query(sql, (res, numRow) -> {
			return new Circle(res.getInt("id"), res.getString("name"));
		});
	}

}
