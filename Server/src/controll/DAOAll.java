package controll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import model.DTOAll;


public class DAOAll {

	// table의 column 값
	String table = "user_info_soma";
	public static String key = "num";
	public static String column2 = "id";
	public static String column3 = "name";
	public static String column4 = "img";
	public static String column5 = "first_install_time";
	public static String column6 = "signup_time";
	public static String column7 = "play_time";
	public static String column8 = "end_time";
	public static String column9 = "score";
	public static String column10 = "rank";
	public static String column11 = "friend";
	public static String column12 = "item";
	public static String column13 = "money";
	public static String column14 = "reg_id";
	
	//
	String url = "jdbc:mysql://localhost/test";
//	String url = "jdbc:mysql://localhost/test?useUnicode=yes&amp;characterEncoding=UTF-8&amp;autoReconnect=true";
	String user = "root";
	String ps = "111111";

	// DTOConfirm의 리스트
	ArrayList<DTOAll> modelList;

	// 연결
	Connection conn = null;

	// db 명령어 및 결과 변수
	Statement st = null;
	ResultSet rs = null;
	
	// DTOConfirm get, set
	public ArrayList<DTOAll> getModelList() {
		return modelList;
	}

	public void setModelList(ArrayList<DTOAll> modelList) {
		this.modelList = modelList;
	}

	// 생성자
	public DAOAll(ArrayList<DTOAll> modelList) {
		this.modelList = modelList;
	}

	public DAOAll() {
		modelList = new ArrayList<>();
	}

	// db 생성
	public void create() {
		try {
			// table이 있는지 확인하고 싶음.
			// 테이블 생성
			st.executeUpdate(createQuery());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 생성 구문
	public String createQuery() {
		/*
		 * String cr = "CREATE TABLE REGISTRATION " + "(id INTEGER not NULL, " +
		 * " first VARCHAR(255), " + " last VARCHAR(255), " + " age INTEGER, " +
		 * " PRIMARY KEY ( id ))";
		 */
		String cr = "CREATE TABLE " + table + " " + "(num int(10) unsigned NOT NULL AUTO_INCREMENT, "
				+ "writer varchar(30) NOT NULL, " + "title varchar(30) DEFAULT NULL, " + "content text DEFAULT NULL, "
				+ "PRIMARY KEY (num)) " + "ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8";
		return cr;
	}
	//

	// db 제거
	public void drop() {
		try {
			st.executeUpdate(dropQuery());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// db 제거 명령문
	public String dropQuery() {
		String dr = "drop table " + table;
		return dr;
	}

	// db 연결
	public void dbConn() {
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, user, ps);
			st = conn.createStatement();

			// System.out.println("Success : " + conn.toString());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void download() {
		try {
			rs = st.executeQuery("select * from " + table);
			ResultSetMetaData resultSetMetaData = rs.getMetaData();
			
			while (rs.next()) {
				
				DTOAll board = new DTOAll();
				board.setNum(rs.getInt(key));
				board.setId(rs.getString(column2));
				board.setName(rs.getString(column3));
				board.setImg(rs.getString(column4));
				board.setFirst_install_time(rs.getString(column5));
				board.setSignup_time(rs.getString(column6));
				board.setPlay_time(rs.getString(column7));
				board.setEnd_time(rs.getString(column8));
				board.setScore(rs.getString(column9));
				board.setRank(rs.getString(column10));
				board.setFriend(rs.getString(column11));
				board.setItem(rs.getString(column12));
				board.setMoney(rs.getString(column13));
				board.setReg_id(rs.getString(column14));
				modelList.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// db조회
	public void select() {
		try {

			rs = st.executeQuery("select * from " + table);

			while (rs.next()) {
				printSelect(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 조회 결과 출력
	public void printSelect(ResultSet rs) {
		try {
			System.out.print(key + ":" + rs.getInt(key) + " ");
			System.out.print(column2 + ":" + rs.getString(column2) + " ");
			System.out.print(column3 + ":" + rs.getString(column3) + " ");
			System.out.print(column4 + ":" + rs.getString(column4) + " ");
			System.out.println(column5 + ":" + rs.getString(column5) + " ");
			System.out.println();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	//

	// db 삽입
	public void insert() {

	}

	// db 삽입
	public void insert(String col2, String col3, String col4, String col5) {

		try {
			//
			st.executeUpdate(insertQuery(col2, col3, col4, col5));
			insertToList();
			// System.out.println("새 글이 추가 되었습니다.");
			//

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// db 삽입 구문
	public String insertQuery(String col2, String col3, String col4, String col5) {

		String in = "INSERT INTO " + table + "(" + 
				column2 + ", " + 
				column3 + ", " + 
				column4 + ", " + 
				column5 + 
				") values('" + 
				col2 + "', '" + 
				col3 + "', '" + 
				col4 + "', '" + 
				col5 + "')";
		System.out.println(in);
		return in;
	}
	//

	// list에 db의 내용 넣기
	public void insertToList() {
		try {
			rs = st.executeQuery("select * from " + table);
			if (rs.last()) {
				DTOAll board = new DTOAll();
				board.setNum(rs.getInt(key));
				board.setId(rs.getString(column2));
				board.setName(rs.getString(column3));
				board.setImg(rs.getString(column4));
				board.setFirst_install_time(rs.getString(column5));
				board.setSignup_time(rs.getString(column6));
				board.setPlay_time(rs.getString(column7));
				board.setEnd_time(rs.getString(column8));
				board.setScore(rs.getString(column9));
				board.setRank(rs.getString(column10));
				board.setFriend(rs.getString(column11));
				board.setItem(rs.getString(column12));
				board.setMoney(rs.getString(column13));
				board.setReg_id(rs.getString(column14));
				modelList.add(board);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	//

	// db 내용 수정
	public void update() {

	}

	// db 내용 수정
	public void update(int num, String column, String con) {
		try {
			//
			st.executeUpdate(updateQuery(num, column, con));
			//updateToList(num, column, con);
			// System.out.println("글이 수정되었습니다.");
			//

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// db 내용 수정
	public void update(String num, String column, String con) {
		try {
			//
			st.executeUpdate(updateQuery(num, column, con));
			updateToList(num, column, con);
			// System.out.println("글이 수정되었습니다.");
			//

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// db 내용 수정 명령문
	public String updateQuery(int num, String column, String con) {
		String up = "update " + table + " set " + column + "='" + con + "'" + " where " + key + "='" + num + "'";
		return up;
	}
	
	// db 내용 수정 명령문
	public String updateQuery(String num, String column, String con) {
		String up = "update " + table + " set " + column + "='" + con + "'" + " where " + column2 + "='" + num + "'";
		return up;
	}
	//

	// list에서 수정
	public void updateToList(String num, String column, String con) {
		for (int cnt = 0; cnt < modelList.size(); cnt++) {
			String id = modelList.get(cnt).getId();
			if (String.valueOf(num).equals(id)) {
				if (column.equals(column6)) {
					modelList.get(cnt).setSignup_time(con);
				} else if (column.equals(column7)) {
					modelList.get(cnt).setPlay_time(con);
				} else if (column.equals(column8)) {
					modelList.get(cnt).setEnd_time(con);
				} else if (column.equals(column9)) {
					modelList.get(cnt).setScore(con);
				}
			}
		}

	}

	//
	// db에서 제거
	public void delete(int id) {
		try {
			//
			// System.out.println(deleteQuery(id));
			st.executeUpdate(deleteQuery(id));
			deleteToList(id);
			// System.out.println("글이 삭제 되었습니다.");
			//

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// db에서 제거 명령문
	public String deleteQuery(int num) {
		String de = "delete from " + table + " where num=" + num;
		return de;
	}
	//

	// list에서 제거
	public void deleteToList(int id) {
		for (int cnt = 0; cnt < modelList.size(); cnt++) {
			int i = modelList.get(cnt).getNum();
			if (i == id) {
				modelList.remove(cnt);
			}
		}

	}
	//

	// db 닫기
	public void close() {
		try {
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("failed to close statement or connection");
			e.printStackTrace();
			return;
		}
	}

}
