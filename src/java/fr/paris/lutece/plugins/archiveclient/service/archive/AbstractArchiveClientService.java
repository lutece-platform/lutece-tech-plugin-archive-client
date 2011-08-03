package fr.paris.lutece.plugins.archiveclient.service.archive;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.paris.lutece.plugins.archiveclient.service.util.ArchiveClientConstants;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.util.signrequest.AbstractAuthenticator;
import fr.paris.lutece.util.signrequest.RequestAuthenticator;

public abstract class AbstractArchiveClientService implements
		IArchiveClientService {

	private RequestAuthenticator _requestAuthenticatorForUrl;

	public String getDownloadUrl(int archiveItemKey) {

		List<String> listElements = new ArrayList<String>();
		listElements.add(Integer.toString(archiveItemKey));

		String strSignature = ((AbstractAuthenticator)_requestAuthenticatorForUrl).buildSignature(
				listElements, Long.toString(new Date().getTime()));

		StringBuilder strUrl = new StringBuilder();
		strUrl
				.append(AppPropertiesService
						.getProperty(ArchiveClientConstants.PROPERTY_WEBAPP_ARCHIVE_REST_URL));
		strUrl.append(ArchiveClientConstants.URL_DOWNLOAD_ARCHIVE);
		strUrl.append("?");
		strUrl.append(ArchiveClientConstants.PARAM_ARCHIVE_ITEM_KEY);
		strUrl.append("=");
		strUrl.append(strSignature);

		return strUrl.toString();

	}

	public void setRequestAuthenticatorForUrl(
			RequestAuthenticator requestAuthenticatorForUrl) {
		this._requestAuthenticatorForUrl = requestAuthenticatorForUrl;
	}
}
