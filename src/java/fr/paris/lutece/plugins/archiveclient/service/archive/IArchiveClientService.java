package fr.paris.lutece.plugins.archiveclient.service.archive;

import fr.paris.lutece.util.httpaccess.HttpAccessException;

public interface IArchiveClientService {

	int generateArchive(String strFolderToArchive,
			String strArchiveDestination, String strArchiveName,
			String strArchiveType)throws HttpAccessException;

	String informationArchive(int archiveItemKey)throws HttpAccessException;

	void removeArchive(int archiveItemKey)throws HttpAccessException;
	
	String getDownloadUrl(int archiveItemKey);
	
}