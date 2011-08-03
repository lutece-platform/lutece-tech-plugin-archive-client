package fr.paris.lutece.plugins.archiveclient.service.archive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import fr.paris.lutece.plugins.archiveclient.service.util.ArchiveClientConstants;
import fr.paris.lutece.portal.service.util.AppLogService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.util.httpaccess.HttpAccess;
import fr.paris.lutece.util.httpaccess.HttpAccessException;
import fr.paris.lutece.util.signrequest.RequestAuthenticator;

public class ArchiveClientWsService extends AbstractArchiveClientService {


	
	
	private RequestAuthenticator _requestAuthenticatorForWS;
	
	
	public int generateArchive(String strFolderToArchive,
			String strArchiveDestination, String strArchiveName,
			String strArchiveType)throws HttpAccessException {

		
	int nIdgenarateArchive=-1;;
	String strUrl = AppPropertiesService.getProperty( ArchiveClientConstants.PROPERTY_WEBAPP_ARCHIVE_REST_URL ) +
    ArchiveClientConstants.URL_REST_GENERATE_ARCHIVE;

    // List parameters to post
    Map<String, String> params = new HashMap<String, String>(  );
    params.put( ArchiveClientConstants.PARAM_FOLDER_TO_ARCHIVE, strFolderToArchive );
    params.put( ArchiveClientConstants.PARAM_ARCHIVE_DESTINATION, strArchiveDestination );
    params.put( ArchiveClientConstants.PARAM_ARCHIVE_NAME, strArchiveName );
    params.put( ArchiveClientConstants.PARAM_ARCHIVE_NAME, strArchiveType );
    
   
    // List elements to include to the signature
    List<String> listElements = new ArrayList<String>(  );
    listElements.add( strFolderToArchive );
    listElements.add( strArchiveDestination );
    listElements.add( strArchiveName  );
    listElements.add(  strArchiveType );
 
	String strResponse=callArchiveWs(strUrl, params, listElements);	
	
	try
	{
		nIdgenarateArchive=Integer.parseInt(strResponse);
	}
	catch (NumberFormatException e) {
		// TODO: handle exception
	}
		
		
		return nIdgenarateArchive;
	}
	
	
	

	public String informationArchive(int archiveItemKey) throws HttpAccessException {

		

		
		String strUrl = AppPropertiesService.getProperty( ArchiveClientConstants.PROPERTY_WEBAPP_ARCHIVE_REST_URL ) +
	    ArchiveClientConstants.URL_REST_INFORMATION_ARCHIVE;

	    // List parameters to post
	    Map<String, String> params = new HashMap<String, String>(  );
	    params.put( ArchiveClientConstants.PARAM_ARCHIVE_ITEM_KEY, Integer.toString(archiveItemKey ));
	    
	   
	    // List elements to include to the signature
	    List<String> listElements = new ArrayList<String>(  );
	    listElements.add(  Integer.toString(archiveItemKey ));
	 
		String strResponse=callArchiveWs(strUrl, params, listElements);	
		
		return strResponse;
		
		
			
	}
	
	
	
	public void removeArchive(int archiveItemKey) throws HttpAccessException{
		
		String strUrl = AppPropertiesService.getProperty( ArchiveClientConstants.PROPERTY_WEBAPP_ARCHIVE_REST_URL ) +
	    ArchiveClientConstants.URL_REST_REMOVE_ARCHIVE;

	    // List parameters to post
	    Map<String, String> params = new HashMap<String, String>(  );
	    params.put( ArchiveClientConstants.PARAM_ARCHIVE_ITEM_KEY, Integer.toString(archiveItemKey ));
	    
	   
	    // List elements to include to the signature
	    List<String> listElements = new ArrayList<String>(  );
	    listElements.add(  Integer.toString(archiveItemKey ));
	 
		String strResponse=callArchiveWs(strUrl, params, listElements);	
		
		

	}
	
	


	
	/**
     * This method calls Rest WS archive
     * @param strUrl the url
     * @param params the params to pass in the post
     * @param listElements the list of elements to include in the signature
     * @return the response as a string
     * @throws HttpAccessException the exception if there is a problem
     */
    private  String callArchiveWs( String strUrl, Map<String, String> params, List<String> listElements )
        throws HttpAccessException
    {
        String strResponse = StringUtils.EMPTY;

        try
        {
            HttpAccess httpAccess = new HttpAccess(  );
            strResponse = httpAccess.doPost( strUrl, params,
            		_requestAuthenticatorForWS, listElements );
        }
        catch ( HttpAccessException e )
        {
            String strError = "ArchiveWebServices - Error connecting to '" + strUrl + "' : ";
            AppLogService.error( strError + e.getMessage(  ), e );
            throw new HttpAccessException( strError, e );
        }

        return strResponse;
    }




	public void setRequestAuthenticatorForWS(RequestAuthenticator requestAuthenticatorForWS) {
		this._requestAuthenticatorForWS = requestAuthenticatorForWS;
	}








}
