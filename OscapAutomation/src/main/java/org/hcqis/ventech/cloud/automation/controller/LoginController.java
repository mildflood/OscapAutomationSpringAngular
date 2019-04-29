package org.hcqis.ventech.cloud.automation.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hcqis.ventech.cloud.automation.model.LoginUser;
import org.hcqis.ventech.cloud.automation.model.User;
import org.hcqis.ventech.cloud.automation.model.manager.AuthenticationManager;
import org.hcqis.ventech.cloud.automation.service.authentication.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

@RestController
@SessionAttributes("userLoginObj")
public class LoginController {
	private final static Logger logger = Logger.getLogger(LoginController.class.getName());

	private User loginUserObj;
	private String returnPage = "null.jsp";
	private String sessionMessage = null;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@GetMapping("/test")
    public void home(Model model) {
		logger.info("****** I am in loginController now! ******");
    }

	@PostMapping
	@RequestMapping("/Login")
	public void login(@ModelAttribute User userObj, @RequestBody LoginUser loginUserData) {
		logger.info(loginUserData.toString());
	}

	/**
	 * @author Jonas Okwara method that processes user form inputs and calls Login
	 *         Manager to validate credentials and forwards appropriate response to
	 *         view Servlet
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		/**
		 * set domain and business manager objects
		 */
		logger.info("------Processing for user Login Object Begins ------");

		EncryptionUtil encryptUtil = new EncryptionUtil();
		String userName = request.getParameter("username");
		String password = encryptUtil.encrypt(request.getParameter("password"));
		String errorMessage = null;
		// String adlogin = request.getParameter("adlogin");

		loginUserObj = new User();
		loginUserObj.setUsername(userName);
		loginUserObj.setPassword(password);

		boolean isValid = false;
		String userRoleName = null;
		String userGroupName = null;

		isValid = authenticationManager.ldapAuthenticateUser(loginUserObj);
		userRoleName = "admin"; // need to be removed after userGroupName is ready

		Date theDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM d, yyyy");
		SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
		String loginDate = dateFormat.format(theDate);
		String loginTime = timeFormat.format(theDate);

		// loginUserObj = loginManager.getNames(loginUserObj);
		HttpSession userSession = request.getSession(true);
		userSession.setMaxInactiveInterval(-1);
		Integer accessCount = (Integer) userSession.getAttribute("accessCount");
		userSession.setAttribute("errorMessage", errorMessage);

		if (accessCount == null) {
			accessCount = new Integer(0);
			sessionMessage = "Welcome";
		} else {
			sessionMessage = "Welcome Back";
			accessCount = new Integer(accessCount.intValue() + 1);
		}

		// AuthenticationDaoSvcImpl loginImpl = new AuthenticationDaoSvcImpl();
		// loginUserObj = loginImpl.getFirstnameLastname(loginUserObj);

		/**
		 * Validate the logins and forward user and data list objects to appropriate
		 * views
		 */
		if ((isValid) && (userRoleName.equalsIgnoreCase("admin"))) {
			userSession.setAttribute("loginDate", loginDate);
			userSession.setAttribute("loginTime", loginTime);
			userSession.setAttribute("userObj", loginUserObj);
			userSession.setAttribute("userRoleName", userRoleName);
			userSession.setAttribute("sessionMessage", sessionMessage);
			returnPage = "/homeGui.jsp";
			request.getServletContext().getRequestDispatcher(returnPage).forward(request, response);
			// request.getRequestDispatcher(returnPage).forward(request,response);
			return;

		} else if ((isValid) && (userRoleName.equalsIgnoreCase("user"))) {
			userSession.setAttribute("loginDate", loginDate);
			userSession.setAttribute("loginTime", loginTime);
			userSession.setAttribute("userObj", loginUserObj);
			userSession.setAttribute("sessionMessage", sessionMessage);
			userSession.setAttribute("userRoleName", userRoleName);
			returnPage = "/userGui.jsp";

			request.getServletContext().getRequestDispatcher(returnPage).forward(request, response);
			// request.getRequestDispatcher(returnPage).forward(request,response);
			return;

		} else {
			errorMessage = "There is an Error with Your Login !!!";
			userSession.setAttribute("errorMessage", errorMessage);
			returnPage = "/loginPage.jsp";
		}
		request.getServletContext().getRequestDispatcher(returnPage).forward(request, response);
		// request.getRequestDispatcher(returnPage).forward(request,response);
	}
}
