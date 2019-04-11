/**
 * Author JOnas  Okwara
 * Date 10-15-2018
 * interface that spels out
 * contract for file transfer
 */

package hcqis.cloud.automation.model.services.fileservice;



import hcqis.cloud.automation.model.services.IService;

public interface IFileRetrievalSvc extends IService{ 
	
	public final String NAME = "IFileRetrievalSvc"; 
	
	public   boolean  transferGeneratedFile(String localFilePath, String remoteDirPath, String remoteFileName);
	
}
