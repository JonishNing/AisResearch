package Commons;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseConnection {
	
	private static String DBDRIVER ;
	private static String DBURL;
	private static String DBUSER;
	private static String DBPASSWORD;
	private static Statement stm = null;
	private static Connection conn = null;

	// �ڹ��췽���н������ݿ������
	public DatabaseConnection() {
		//������Ϣ		 
		String DBDRIVER = "com.mysql.jdbc.Driver";
		String DBURL = "jdbc:mysql://localhost:3306/aisdb";
		String DBUSER = "root";     
		String DBPASSWORD = "123456";

		try {
			Class.forName(DBDRIVER); // ���÷�����Ƽ������ݿ���������
			this.conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD); // ���������ݿ������
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.getStm();
		
		System.out.println(DBURL);
	}

	public Connection getConnection() {
		return this.conn; // ȡ�õ�ǰ����
	}

	public static void getStm() {
		// �������
		try {
			stm = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	public static ResultSet executeQuery(String sql) {
		ResultSet rs = null;
		try {
			getStm();
			try {
				rs = stm.executeQuery(sql);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("failed to execute sql: " + sql);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	public static int executeUpdate(String sql) {
		int bl = 0;
		try {
			getStm();
			try {
				bl= stm.executeUpdate(sql);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("cuocuoc");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bl;
	}

	public void close() throws Exception {
		if (this.conn != null) {
			try {
				this.conn.close(); // ����ѽ���������Ͽ���ǰ����
			} catch (Exception e) {
				throw e;
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DatabaseConnection db = new DatabaseConnection();
		String sql="select * from aisdynamiclog where DRSPEED=104";
		int shipid=0;
		ResultSet rs=db.executeQuery(sql);
		try {
			while(rs.next()){
				shipid=rs.getInt(2);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("shipid:"+shipid);
	}
}