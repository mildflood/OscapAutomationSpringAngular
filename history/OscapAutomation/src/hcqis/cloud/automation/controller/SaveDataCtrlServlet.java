package hcqis.cloud.automation.controller;

import hcqis.cloud.automation.model.business.manager.ScanDataManager;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveDataCtrlServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	String errorMessage;
	String successMessage;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * 
	 * @param resquest
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String fileName = request.getParameter("value");
		String fileURL = request.getParameter("url");

		response.setContentType("text/html");
		fileURL = fileURL + "/" + fileName;
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ fileName + "\"");

		URL url = new URL(fileURL);
		URLConnection connection = url.openConnection();
		InputStream inStream = connection.getInputStream();

		BufferedOutputStream outs = new BufferedOutputStream(
				response.getOutputStream());

		int len;
		byte[] buf = new byte[1024];
		while ((len = inStream.read(buf)) > 0) {
			outs.write(buf, 0, len);
		}
		outs.close();
	}
}
