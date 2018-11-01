package edu.northeastern.cs5200.daos;

import java.sql.Date;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StoredProcedureDao {

	private static StoredProcedureDao instance = null;
	private java.sql.Connection conn;

	private StoredProcedureDao() {
		try {
			this.conn = edu.northeastern.cs5200.Connection.getConnection();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public static StoredProcedureDao getInstance() {
		if (instance == null) {
			instance = new StoredProcedureDao();
		}
		return instance;
	}

	private ResultSet results = null;

	public void getUnansweredQuestions(String moduleName) {
		CallableStatement pstatement = null;
		try {
			pstatement = conn.prepareCall("{call get_unanswered_questions(?)}");
			pstatement.setString(1, moduleName);

			results = pstatement.executeQuery();
			while (results.next()) {
				System.out.println(results.getString("question_content") + " " + results.getString("widget_name") + " "
						+ results.getString("answerid_count"));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void endorsedUserForWeek(Date weekDate) {
		CallableStatement pstatement = null;
		try {
			pstatement = conn.prepareCall("{call endorsed_user_for_week(?)}");
			pstatement.setDate(1, weekDate);

			results = pstatement.executeQuery();

			while (results.next()) {
				System.out.println(results.getString("first_name"));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
}
