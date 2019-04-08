package org.hcqis.ventech.cloud.automation.util.fileutility;

import org.hcqis.ventech.cloud.automation.exception.SaveFileException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.logging.Logger;

/**
 * Author Matthew Zhao Date 02-14-2019 Download/save scan report html files from
 * app server to local file system.
 * 
 * @param
 */
public class SaveFileUtility {

	private final static Logger logger = Logger.getLogger(SaveFileUtility.class
			.getName());

	private static final long serialVersionUID = 1L;
	// private JTextField filename = new JTextField(), dir = new JTextField();
	private String fileURL = "";
	private File targetFile;
	private boolean cancelled = false;

	public SaveFileUtility(String fileURL, String fileName, String username) {
		logger.info("Passing in File URL: " + fileURL);
		logger.info("Passing in File to be saved: " + fileName);
		this.fileURL = fileURL + "/" + fileName;
		this.targetFile = new File("C:\\Users\\" + username + "\\Downloads\\" + fileName);
		logger.info("File to be downloaded: " + this.fileURL);
		logger.info("File to be saved locally: " + this.targetFile.getPath());
	}

	public void downloadFileFromURL() throws SaveFileException {
		ReadableByteChannel rbc = null;
		FileOutputStream fos = null;

		try {
			URL website = new URL(this.fileURL);
			rbc = Channels.newChannel(website.openStream());
			this.targetFile.createNewFile();
			fos = new FileOutputStream(this.targetFile);
			
			if (fos != null && rbc != null) {
				fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
				logger.info("File has been saved: " + this.targetFile.getPath());
				fos.close();
				rbc.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new SaveFileException(
					"IOException when saving report to local.", e);
		}
		finally {
			if (fos != null) {
				fos = null;
			}
			if (rbc != null) {
				rbc = null;
			}
		}
	}

	public boolean isCancelled() {
		return cancelled;
	}

}
