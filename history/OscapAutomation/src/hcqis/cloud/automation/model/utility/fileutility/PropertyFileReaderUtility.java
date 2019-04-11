package hcqis.cloud.automation.model.utility.fileutility;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class PropertyFileReaderUtility {

	int poolValue;
	InputStream inputStream = null;
	String PROPERTY_FILE = "/application.properties";
	private final static Logger logger = Logger.getLogger(PropertyFileReaderUtility.class.getName());
 
	/**
	 * Author Jonas Okwara
	 * Read and return config file value for the thread pool
	 * size
	 * @return int
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public int getPropertyFileValues() {

		try { 
			
			Properties properties = new Properties();
		
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			InputStream inputStream = classloader.getResourceAsStream(PROPERTY_FILE);
			
			properties.load(inputStream);
			
			poolValue = Integer.parseInt(properties.getProperty("ThreadPoolSize"));

			String stringValue = String.valueOf(poolValue);

			logger.info("The ThreadPool_Value is:" + stringValue);

		} catch (FileNotFoundException fnfExcep) {

			fnfExcep.printStackTrace();

		} catch (IOException ioExcep) {
			ioExcep.printStackTrace();

		} finally {

		}
		return poolValue;
	}

	
	
	public static void main(String[] args) {

		new PropertyFileReaderUtility().getPropertyFileValues();

	}

}