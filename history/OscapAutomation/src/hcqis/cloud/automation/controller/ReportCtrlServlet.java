/**
 * Author Jonas Okwara
 * Date 06-26-2018
 * This is our controller servlet
 * that manages processing of the scan
 * reports and displays them.
 */

package hcqis.cloud.automation.controller;

import hcqis.cloud.automation.model.business.manager.ScanDataManager;
import hcqis.cloud.automation.model.domain.ScanSession;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReportCtrlServlet extends HttpServlet {

	@SuppressWarnings("unused")
	private final static Logger logger = Logger
			.getLogger(ReportCtrlServlet.class.getName());
	private static final long serialVersionUID = 1L;
	ScanDataManager scanDataMgr;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Author Jonas Okwara Date 06-29-2018
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		ScanSession scanSessionObj = new ScanSession();

		scanDataMgr = ScanDataManager.getInstance();

		if (request.getParameter("hostreport") == null) {

		} else {

			scanSessionObj.setServerName(request.getParameter("hostreport"));
			System.out.println(scanSessionObj.getServerName());

			scanDataMgr.setScanSessionObj(scanSessionObj);

			List<String> returnedFileList = scanDataMgr
					.getFiles(scanSessionObj);

			String nodeName = scanSessionObj.getServerName();
			String formattedNameString = nodeName.substring(0, 1).toUpperCase()
					+ nodeName.substring(1);

			request.setAttribute("returnedFileList", returnedFileList);
			request.setAttribute("formattedNameString", formattedNameString);
		}

		String returnPage = "/reportGui.jsp";
		request.getServletContext().getRequestDispatcher(returnPage)
				.forward(request, response);
		// request.getRequestDispatcher(returnPage).forward(request,response);

	}

}