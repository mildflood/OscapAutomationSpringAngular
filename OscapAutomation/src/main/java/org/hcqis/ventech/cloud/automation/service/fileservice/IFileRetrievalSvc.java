/**
 * Author JOnas  Okwara
 * Date 10-15-2018
 * interface that spels out
 * contract for file transfer
 */

package org.hcqis.ventech.cloud.automation.service.fileservice;

import org.hcqis.ventech.cloud.automation.service.IService;

public interface IFileRetrievalSvc extends IService{ 
	
	public final String NAME = "IFileRetrievalSvc"; 
	
	public   boolean  transferGeneratedFile(String localFilePath, String remoteDirPath, String remoteFileName);
	
}
