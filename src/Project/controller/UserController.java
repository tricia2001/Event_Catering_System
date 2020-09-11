package Project.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;

import Project.data.EventDAO;
import Project.data.UserDAO;
import Project.model.*;

@WebServlet("/UserController")
public class UserController extends HttpServlet {

	private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());
	
	private static final long serialVersionUID = 1L;
	
	private void getUserParam (HttpServletRequest request, User user) {
		user.setUser(request.getParameter("username"), request.getParameter("password"), request.getParameter("userLast"), 
				request.getParameter("userFirst"), request.getParameter("role"),request.getParameter("utaId"),
				request.getParameter("phone"), request.getParameter("email"), request.getParameter("streetNumber"), request.getParameter("streetName"),
				request.getParameter("city"), request.getParameter("state"), request.getParameter("zipcode"));  
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String action = request.getParameter("action"), url;
		session.removeAttribute("errorMsgs");
	
			doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action"), url="";
		HttpSession session = request.getSession();
		User user = new User();
		user.setUser("", "", "", "", "", "", "", "", "", "", "", "", "");
		UserErrorMsgs UerrorMsgs = new UserErrorMsgs();int selectedUserIndex;
		String lastname;
		session.removeAttribute("errorMsgs");
		
		//login user
		if (action.equals("login")) {  //insert employee button pressed
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			user.setUsername(username);
			user.setPassword(password);
			user.validateUser(action, user, UerrorMsgs);
			if (!UerrorMsgs.getErrorMsg().equals("")) {
				session.setAttribute("errorMsgs",UerrorMsgs);
				url="/login.jsp"; 
			}
			else {// if no error messages
				String role = UserDAO.getRole(user.getUsername());
				UserErrorMsgs SerrorMsgs = new UserErrorMsgs();
				session.setAttribute("errorMsgs", SerrorMsgs);
				user.setUsername(username); 
				
				if (role.equals("Admin")) {
					session.setAttribute("user", user);
					url="/adminHomepage.jsp"; 
				}
				else if (role.equals("Caterer Manager")) {
					session.setAttribute("user", user);
					url="/mgrHomepage.jsp";
				}
				else if (role.equals("User")) {
					session.setAttribute("user", user);
					url="/userHomepage.jsp"; 
				}
				else {//role is "Caterer Staff"
					session.setAttribute("user", user);
					url="/CatererStaffHome.jsp";
				}
				session.removeAttribute("password");
			}
				
		}
		else if(action.equals("logout")) {
			session.invalidate();
			url="/index.jsp";
		}
		
		//register user
		else if(action.equals("register")) {
			getUserParam(request, user);
			user.validateUser(action, user, UerrorMsgs);
			session.setAttribute("user",user);
			if (!UerrorMsgs.getErrorMsg().equals("")) {
				getUserParam(request,user);
				session.setAttribute("errorMsgs",UerrorMsgs);
				url="/register.jsp"; 
			}
			else {// if no error messages
				UserDAO.insertUser(user);
				UserErrorMsgs SerrorMsgs = new UserErrorMsgs();
				session.removeAttribute("user");
				session.setAttribute("errorMsgs", SerrorMsgs);
				url="/index.jsp";
			}
		}
		
		else if (action.equalsIgnoreCase("adminSearch")) {
			
			url="/adminUserSearch.jsp";
		}
		
		else if (action.equalsIgnoreCase("viewUser") ) 
		{
			User user1 = new User();
			String username=request.getParameter("user");
			user1=UserDAO.searchUserwithUname(username);	
			session.setAttribute("users", user1);
			url="/viewProfile.jsp";
		}
		
		//UPDATE USER PROFILE
		
		else if(action.equalsIgnoreCase("updateUser0")) {
			String username = request.getParameter("user1");
			User selectedUser = new User();
			user = UserDAO.searchUserwithUname(username);
			selectedUser.setUsername(user.getUsername());
			selectedUser.setUtaId(user.getUtaId());
			selectedUser.setRole(user.getRole());
			session.setAttribute("user", user);
			session.setAttribute("selectedUser", selectedUser);
			session.setAttribute("errorMsgs",UerrorMsgs);
			url="/updateUser.jsp"; 
		}
		
		else if(action.equalsIgnoreCase("updateUser")) {
			getUserParam(request, user);
			String username = request.getParameter("user1");
			String id = request.getParameter("id");
			user.validateUser(action, user, UerrorMsgs);
			if (!username.equals(user.getUsername()))
				if (UserDAO.duplicateUser(user.getUsername()))
					UerrorMsgs.setUsernameError("Username already in database");
			if (!id.equals(user.getUtaId())) 
				if (UserDAO.duplicateId(user.getUtaId())) 
					UerrorMsgs.setUtaIdError("UTA Id already in use");	
			UerrorMsgs.setErrorMsg();
			session.setAttribute("user",user);
			if (!UerrorMsgs.getErrorMsg().equals("")) {
				session.setAttribute("errorMsgs",UerrorMsgs);
				url="/updateUser.jsp"; 
			}
			else {// if no error messages
				UserDAO.modifyUser(user);
				session.setAttribute("users", user);
				String role = UserDAO.getRole(username);
				UserErrorMsgs SerrorMsgs = new UserErrorMsgs();
				session.setAttribute("errorMsgs", SerrorMsgs);
				user.setUsername(username);
				
				if (role.equals("Admin")) {
					session.setAttribute("user", user);
					url="/adminHomepage.jsp"; 
				}
				else if (role.equals("Caterer Manager")) {
					session.setAttribute("user", user);
					url="/mgrHomepage.jsp";
				}
				else if (role.equals("User")) {
					session.setAttribute("user", user);
					url="/userHomepage.jsp"; 
				}
				else { //role is Caterer Staff
					session.setAttribute("user", user);
					url="/CatererStaffHome.jsp";
				}
				
			}
		}


		else if (action.equalsIgnoreCase("searchUser") ) {  
			lastname = request.getParameter("last_name");
			session.setAttribute("lname", lastname);
			user.setLastname(lastname);
			user.validateUser(action, user, UerrorMsgs);
			ArrayList<User> userInDB = new ArrayList<User>();
			
			if (UerrorMsgs.getErrorMsg().equals("")) {
                if (lastname.equals("")) {
                    
                    userInDB=UserDAO.allUsers();
                }
                else
                    userInDB=UserDAO.searchUserwithLastname(lastname);
                session.setAttribute("USERS", userInDB);
                url="/listUser.jsp";
            }
            else { //errors
                session.setAttribute("errorMsgs",UerrorMsgs);
                url="/adminUserSearch.jsp";             
            }
		}
		else
			  //REDIRECTING TO MODIFY USER JSP
			  if(action.equalsIgnoreCase("editUser")) {
					  String username = request.getParameter("user");
					  String userid = request.getParameter("id");
					  ArrayList<User> userindb = UserDAO.searchUser(username);
					  User selectedUser = new User();
					  selectedUser.setUsername(username);
					  selectedUser.setUtaId(userid);
					  session.setAttribute("user", userindb.get(0));
					  session.setAttribute("selectedUser", selectedUser);
					  url="/modifyuser.jsp";
				  }
			  
				//MODIFY USER DETAILS
		else
			if(action.equalsIgnoreCase("modifyUser")) {
				getUserParam(request, user);
				String username = request.getParameter("user1");
				String id = request.getParameter("id");
				user.validateUser(action, user, UerrorMsgs);
				if (!id.equals(user.getUtaId())) 
					if (UserDAO.duplicateId(user.getUtaId())) 
						UerrorMsgs.setUtaIdError("UTA Id already in use");	
				UerrorMsgs.setErrorMsg();
				
				if (!UerrorMsgs.getErrorMsg().equals("")) {
					session.setAttribute("user",user);
					session.setAttribute("errorMsgs",UerrorMsgs);
					url="/modifyuser.jsp"; 
				}
				else {// if no error messages
					UserDAO.modifyUser(user);
					UserErrorMsgs SerrorMsgs = new UserErrorMsgs();
					session.setAttribute("selectedUser", user);
					session.setAttribute("errorMsgs", SerrorMsgs);
					url="/userSearchresults.jsp";
				}
			
			}
		
		
		
		else { //action=listSpecificUser----------LISTSPECIFIC USER
			ArrayList<User> userInDB = new ArrayList<User>();
			User selectedUser = new User();
			if (request.getParameter("radioUser")!=null) {
				selectedUserIndex = Integer.parseInt(request.getParameter("radioUser")) - 1;
				
				
				userInDB=UserDAO.searchUserwithLastname(request.getParameter("lname"));
				
				if(request.getParameter("DeleteUserButton")!=null) {
					UserDAO.deleteUser(userInDB.get(selectedUserIndex).getUtaId());
					userInDB=UserDAO.searchUserwithLastname(request.getParameter("lname"));
					session.setAttribute("USERS", userInDB);
					url="/listUser.jsp";
				}
				else {
				selectedUser.setUser(	userInDB.get(selectedUserIndex).getUsername(), userInDB.get(selectedUserIndex).getLastname(), 
							userInDB.get(selectedUserIndex).getFirstname(), userInDB.get(selectedUserIndex).getRole(),"","","","","","","","","");
				session.setAttribute("selectedUser",userInDB.get(selectedUserIndex));
				url="/userSearchresults.jsp";	
				}
			}
			else { 
				 //ListSelectedUserButton
					String errorMsgs =  "Please select a User";
					session.setAttribute("errorMsgs",errorMsgs);
					url="/listUser.jsp";					
			
			   }
			}

			
		
		getServletContext().getRequestDispatcher(url).forward(request, response);		
	}
}
