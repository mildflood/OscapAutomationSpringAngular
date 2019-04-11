package hcqis.cloud.automation.model.utility.fileutility;

import hcqis.cloud.automation.model.business.exception.SaveFileException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 * Author Matthew Zhao
 * Date 02-14-2019
 * Download/save scan report html files
 * from app server to local file system.
 * @param 
 */
public class SaveFileUtilityAWS extends JFrame {
	
	private final static Logger logger = Logger.getLogger(SaveFileUtilityAWS.class.getName());

	private static final long serialVersionUID = 1L;
	private JTextField filename = new JTextField(), dir = new JTextField();
	private String fileURL = "";
	private File targetFile;
	private boolean cancelled = false;

	public SaveFileUtilityAWS(String fileURL, String fileName) {
		logger.info("Passing in File URL: " + fileURL);
		logger.info("Passing in File to be saved: " + fileName);
		this.fileURL = fileURL + fileName;
		JFileChooser fileChooser = new JFileChooser();
		
		fileChooser.setSelectedFile(new File(fileName));

		//fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("HTML",
		//		"html"));
		//fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PDF",
		//		"pdf"));
		//fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("DOC",
		//		"docx"));
		
		// Shows "Save" dialog:
		int rVal = fileChooser.showSaveDialog(SaveFileUtilityAWS.this);

		if (rVal == JFileChooser.APPROVE_OPTION) {
			filename.setText(fileChooser.getSelectedFile().getName());
			dir.setText(fileChooser.getCurrentDirectory().toString());
		}
		if (rVal == JFileChooser.CANCEL_OPTION) {
			this.cancelled = true;
			this.filename.setText("");
			this.dir.setText("");
		}
		String fileType = fileChooser.getFileFilter().getDescription();
		switch (fileType) {
		case "HTML":
			fileType = ".html";
			break;
		case "PDF":
			fileType = ".pdf";
			break;
		case "DOC":
			fileType = ".docx";
			break;
		default:
			fileType = "";
		}
		this.targetFile = new File(dir.getText() + File.separator
				+ filename.getText() + fileType);
		logger.info("File to be downloaded: " + this.fileURL);
		logger.info("File to be saved locally: " + this.targetFile.getPath());
	}

	public void downloadFileFromURL() throws SaveFileException {
		ReadableByteChannel rbc = null;
		FileOutputStream fos = null;
		
		try {
			URL website = new URL(this.fileURL);
			
			rbc = Channels.newChannel(website.openStream());
			fos = new FileOutputStream(this.targetFile);
			if (fos != null && rbc != null){
				fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
				fos.close();
				rbc.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new SaveFileException("IOException when saving report to local.", e);
		} finally {
			if (fos != null) {
				fos = null;
			}
			if (rbc != null){
				rbc = null;
			}
		}
	}
	
	public boolean isCancelled() {
		return cancelled;
	}


}
