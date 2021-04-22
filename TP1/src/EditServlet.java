import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/EditServlet")

public class EditServlet extends HttpServlet {
	/**
	 * 
	 */
	private static Integer idEmp = 0;
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out=response.getWriter();
		
		
		EmpDao empDao = new EmpDao();		
		response.setContentType("text/html");		
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		String country=request.getParameter("country");
		
		
		Emp empl = new Emp();
		empl.setId(idEmp);
		empl.setCountry(country);
		empl.setEmail(email);
		empl.setName(name);
		empl.setPassword(password);
		
		out.print("chegou aqui  "+ empl.getId());
		
		int status = empDao.update(empl);
		if(status>0){
		response.sendRedirect("ViewServlet");
		}else{
		out.println("Sorry! unable to update record");
		}
		out.println("<h1>Update Employee</h1>  ");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		    response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			
			Integer id = Integer.parseInt(request.getParameter("id"));
			idEmp = id;			
			EmpDao em = new EmpDao(); 
			Emp e = em.getEmployeeById(id);
			
			out.print(e.getCountry());
			
			out.print("<form action='EditServlet' method='POST'>");
			out.print("<table>");
			out.print("<tr><td></td><td><input type='hidden' name=\"id\" value='"+e.getId()+"'/></td></tr>");
			out.print("<tr><td>Name:</td><td><input type='text' name=\"name\" value='"+e.getName()+"'/></td></tr>");
			out.print("<tr><td>Password:</td><td><input type='password' name=\"password\" value='"+e.getPassword()+"'/></td></tr>");
			out.print("<tr><td>Email:</td><td><input type='email' name=\"email\" value='"+e.getEmail()+"'/></td></tr>");
			out.print("<tr><td>Country:</td><td>");
			out.print("<select name='country' style='width:150px'>");
			out.print("<option>India</option>");
			out.print("<option>USA</option>");
			out.print("<option>UK</option>");
			out.print("<option>Other</option>");
			out.print("</select>");
			out.print("</td></tr>");
			out.print("<tr><td colspan='2'><input type='submit' value='Edit & Save'/></td></tr>");
			out.print("</table>");
			out.print("</form>");
			
			out.close();
			}
}