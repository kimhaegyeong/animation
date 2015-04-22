package publicData01;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class HelloWorld implements Servlet {
	ServletConfig config;
	
	@Override
	public void destroy() {
		System.out.println("destroy");
	}

	@Override
	public ServletConfig getServletConfig() {
		System.out.println("getServletConfig");
		return config;
	}

	@Override
	public String getServletInfo() {
		System.out.println("getServletInfo");
		return "HelloWorld";
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init");
		this.config = config;		
	}

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1)
			throws ServletException, IOException {
		System.out.println("service");
		
	}

}
