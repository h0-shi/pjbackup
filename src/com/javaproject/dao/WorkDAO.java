package com.javaproject.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.javaproject.db.DBConnection;
import com.javaproject.dto.RoutineDTO;
import com.javaproject.dto.WorkDTO;

public class WorkDAO {
	DBConnection dbc = new DBConnection();

	// 운동 입력
	public void addExercise(String workName, int set, int number, int sec, String type) {

		String sql = "INSERT INTO work (wName, wSet, wNumber, wSec, wType) VALUES (?,?,?,?,?)";
		Connection conn = dbc.getConn();
		List<WorkDTO> list = null;
		ResultSet rs = null;

		try {

			PreparedStatement pstmt = null;
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, workName);
			pstmt.setInt(2, set);
			pstmt.setInt(3, number);
			pstmt.setInt(4, sec);
			pstmt.setString(5, type);
			pstmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	// 이미 같은 이름으로 저장된 운동이 있는지 확인
	public boolean duplicationCheckWork(String workName) {

		String sql = "SELECT * FROM work WHERE wName=?";
		Connection conn = dbc.getConn();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		WorkDTO dto = new WorkDTO();

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, workName);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dto.setwName(rs.getString(2));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (dto.getwName() == null) {
			return false;
		} else {
			return true;
		}

	}

	// 운동 목록 출력
	public WorkDTO selectWork(String name) {
		WorkDTO dto = new WorkDTO();

		String sql = "SELECT * FROM work WHERE wName=?";

		try {
			Connection conn = dbc.getConn();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {

				dto.setwNo(rs.getInt(1));
				dto.setwName(rs.getString(2));
				dto.setwSet(rs.getInt(3));
				dto.setwNumber(rs.getInt(4));
				dto.setwSec(rs.getInt(5));
				dto.setwDate(rs.getString(6));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}

	// 삭제
	public void deleteExerciseByName(String exerciseName) {
		String sql = "DELETE FROM work WHERE wName=?";
		try {
			Connection conn = dbc.getConn();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			System.out.println(exerciseName);
			pstmt.setString(1, exerciseName);
			int rowsAffected = pstmt.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println(exerciseName + " 운동이 삭제되었습니다.");
			} else {
				System.out.println(exerciseName + " 운동을 찾을 수 없습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/*------이하 박시호 수정-----*/
	// close 메서드
	private void close(ResultSet rs, PreparedStatement pstmt, Connection con) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

//		루틴 추천하는 메서드
	public List<WorkDTO> recommand(String wType) {
		List<WorkDTO> list = new ArrayList<WorkDTO>();

		String sql = "SELECT * FROM work WHERE wType=?";

		Connection conn = dbc.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, wType);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				WorkDTO dto = new WorkDTO();
				dto.setwNo(rs.getInt(1));
				dto.setwName(rs.getString(2));
				dto.setwSet(rs.getInt(3));
				dto.setwNumber(rs.getInt(4));
				dto.setwSec(rs.getInt(5));
				dto.setwType(rs.getString(6));
				dto.setwDate(rs.getString(7));

				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		return list;
	}

//		루틴 추가하는 메서드
	public void insertRountine(String name, List<String> result) {
		String sql = "INSERT INTO routine (name, shoulder, chest, leg, back, arm, wBody) VALUES (?,?,?,?,?,?,?)";
		Connection conn = dbc.getConn();
		PreparedStatement pstmt = null;

		String shoulder = result.get(0);
		String chest = result.get(1);
		String leg = result.get(2);
		String back = result.get(3);
		String arm = result.get(4);
		String wBody = result.get(5);

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, shoulder);
			pstmt.setString(3, chest);
			pstmt.setString(4, leg);
			pstmt.setString(5, back);
			pstmt.setString(6, arm);
			pstmt.setString(7, wBody);
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, conn);
		}
	}

//		추가된 루틴 조회하는 메서드
	public List<RoutineDTO> showRoutine(String name) {

		List<RoutineDTO> list = new ArrayList<RoutineDTO>();

		Connection conn = dbc.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

//			----------------------------
		String sql = "SELECT * FROM routine WHERE name=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				RoutineDTO dto = new RoutineDTO();
				dto.setId(rs.getInt(1));
				dto.setName(rs.getString(2));
				dto.setShoulder(rs.getString(3));
				dto.setChest(rs.getString(4));
				dto.setLeg(rs.getString(5));
				dto.setBack(rs.getString(6));
				dto.setArm(rs.getString(7));
				dto.setwBody(rs.getString(8));
				dto.setwDate(rs.getDate(9));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		return list;
	}

	public List<String> getExerciseList() {

		List<String> exerciseList = new ArrayList<>();

		String sql = "SELECT DISTINCT wName FROM work";
		Connection conn = dbc.getConn();
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String exerciseName = rs.getString("wName");
				exerciseList.add(exerciseName);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exerciseList;
	}

//	 루틴 삭제
	public int delRoutine(int id) {
		int result = 0;

		String sql = "DELETE FROM routine WHERE id=?";
		Connection conn = dbc.getConn();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, conn);
		}

		return result;
	}
}
