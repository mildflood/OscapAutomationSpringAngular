/**
 * Author Jonas Okwara
 * Date 12 - 29 - 2018
 * This class creates an Object whose 
 * method accepts a hostname and copies
 * generated html files from that host
 * over to a specified folder on the app server
 */

package hcqis.cloud.automation.model.utility.fileutility;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;


public class FileTransferUtility {
	
static Logger logger = Logger.getLogger(FileTransferUtility.class.getName());  	
	
    /**
     * Author Jonas Okwara
     * Date 12-19-18
     * Retrieve the Generated file
     * for each Node
     * @param nodeName
     */
	public void retrieveFile(String nodeName) {
		
		JSch jsch = new JSch();
		Session session = null;
		Channel channel = null; 
		int port = 22;

		Date theDate = new Date();
		String myPattern = "MMddyy";
		SimpleDateFormat dateFormat = new SimpleDateFormat(myPattern);
		String toDay = dateFormat.format(theDate);
		String surfixEntry = toDay+".html"; 
		String hostnameSurfix = "-mgt";
		
		String username = "oscapscn";
		String host = nodeName.toLowerCase();
		String sftpPassword = "oscapscn";
		String actualHostName = host+hostnameSurfix;
		
		
		 String  actualFileName = actualHostName+".rheldisa."+surfixEntry;
		
		String sourceFileName = "/home/oscapscn/" + actualFileName; 
		
		File destinationFileName =  new File("/Data/fileDump/" + actualFileName); 
		
		
		try {
			session = jsch.getSession(username, host, port);
			session.setPassword(sftpPassword);
			session.setTimeout(20 * 1000);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);

			session.connect();

			channel = session.openChannel("sftp");
			channel.connect();
			ChannelSftp channelSftp = (ChannelSftp) channel;

			logger.info("Starting File Tranfer from Node:"+ " " + host);  
			
			InputStream inStream = channelSftp.get(sourceFileName);
          
			copyInputStreamToFile(inStream, destinationFileName);

			inStream.close();

		} catch (JSchException | SftpException exception) {
			
			exception.printStackTrace(); 
			
		} catch (IOException ioExcep) { 
			
			ioExcep.printStackTrace(); 
			
		} finally { 
			
			if (channel != null) { 
				
				channel.disconnect();
			}
			
			if (session != null) { 
				
				session.disconnect();
			}
		}
	}

	
	
	/**
	 * Author Jonas Okwara Date 12-19-18
	 * Create and write to generated file.
	 * @param inStream
	 * @param file
	 */
	private void copyInputStreamToFile(InputStream inStream, File outputFile) {  	
		    BufferedInputStream  buffInStream = null;
 		    BufferedOutputStream buffOutStream = null; 
		
		try { 
			buffInStream = new BufferedInputStream(inStream);
			buffOutStream = new BufferedOutputStream(new FileOutputStream(outputFile));
			byte[] buf = new byte[1024];
			int dataLength; 
			
			while ((dataLength = buffInStream.read(buf)) > 0) {
				buffOutStream.write(buf, 0, dataLength);
				buffOutStream.flush();
			} 
		
			
		} catch (IOException ioExcep) {
			ioExcep.printStackTrace();
		}finally {
			try {
				buffOutStream.close();
				buffInStream.close();
				
			} catch (Exception excep) {
				
				excep.printStackTrace();
			}
			
		}
	}

	
	
	
	
    /**
	public static void main(String[] args) { 	
		List<String>hostList = new ArrayList<>();
		hostList.add("sben1a003");
		hostList.add("sben2a004");
		hostList.add("sben4a006"); 
		
		for(String str: hostList) {
		new FileTransferUtility().retrieveFile(str);
		}

	  }
    */

}


