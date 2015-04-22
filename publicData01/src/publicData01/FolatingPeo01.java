package publicData01;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/page/list")
public class FolatingPeo01 extends GenericServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/publicdb",
					"test01",
					"1111");
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(
					"select * from popluation");
			
			response.setContentType("text/html; chartset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html><head><title>유동인구DB</title></head>");
			out.println("<body><h1>회원목록</h1>");
			while (rs.next()) {
				out.println(
						rs.getInt("PID") + ", " +
								rs.getInt("SNO")  + "<br>" 	
						);
			}
			out.println("<body></html>");	
		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			try {if (rs != null) rs.close(); } catch(Exception e) {}
			try {if (stmt != null) rs.close(); } catch(Exception e) {}
			try {if (conn != null) rs.close(); } catch(Exception e) {}

		}
	}

}
