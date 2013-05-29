package com.dasbiersec.reloader.dao;

import com.dasbiersec.reloader.model.CostPerRound;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class ReportingDAO
{
	private JdbcTemplate jdbcTemplate;

	public ReportingDAO(DataSource dataSource)
	{
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void setDataSource(DataSource dataSource)
	{
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

//	public Collection<CostPerRound> getCPR()
//	{
//		String query = "select id, description, primer_cost, brass_Cost, powder_cost, bullet_cost,\n" +
//				"(primer_cost + brass_cost + powder_cost + bullet_cost) as total_cost\n" +
//				"from (\n" +
//				"select \n" +
//				"rs.id, rs.description,\n" +
//				"round(primer.total_cost / primer.amount, 4) as primer_cost,\n" +
//				"round(brass.total_cost / brass.amount, 4) as brass_cost,\n" +
//				"round(rs.powder_charge * (powder.total_cost / powder.amount), 4) as powder_cost,\n" +
//				"round(bullet.total_cost / bullet.amount, 4) as bullet_cost\n" +
//				"from rounds rs\n" +
//				"inner join components bullet on bullet.id = rs.bullet_id\n" +
//				"inner join components powder on powder.id = rs.powder_id\n" +
//				"inner join components primer on primer.id = rs.primer_id\n" +
//				"inner join components brass on brass.id = rs.brass_id\n" +
//				") as t1 ;\n";
//
//		Collection rounds = this.jdbcTemplate.query(query, new org.springframework.jdbc.core.RowMapper()
//		{
//			@Override
//			public Object mapRow(ResultSet resultSet, int i) throws SQLException
//			{
//				CostPerRound costPerRound = new CostPerRound();
//				costPerRound.setDescription(resultSet.getString("description"));
//				costPerRound.setCostPerRound(resultSet.getString("total_cost"));
//				return costPerRound;
//			}
//		});
//
//		return rounds;
//	}
}

