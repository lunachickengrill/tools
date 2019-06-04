package eu.vrtime.bootwicketwebapptwo.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A Spring Framework Servlet to be implemented. Atm just the interface exists.
 * Purpose of the servlet is to receive xml messages, invoke a service, do somthing and send response.
 */

import org.springframework.web.servlet.FrameworkServlet;

/**
 * Interface for the ApiServlet. The to be implemented Framework servlet should receive xml requests, invoke the proper service method and send back a xml response.
 * Not implemented yet.
 * @author babis
 *
 */

public class ApiServlet extends FrameworkServlet{

	@Override
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	

}
