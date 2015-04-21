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

	// 在构造方法中进行数据库的连接
	public DatabaseConnection() {
		//连接信息		 
		String DBDRIVER = "com.mysql.jdbc.Driver";
		String DBURL = "jdbc:mysql://localhost:3306/aisdb";
		String DBUSER = "root";     
		String DBPASSWORD = "123456";

		try {
			Class.forName(DBDRIVER); // 运用反射机制加载数据库驱动程序
			this.conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD); // 建立与数据库的连接
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.getStm();
		
		System.out.println(DBURL);
	}

	public Connection getConnection() {
		return this.conn; // 取得当前连接
	}

	public static void getStm() {
		// 创建语句
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
				this.conn.close(); // 如果已建立连接则断开当前连接
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