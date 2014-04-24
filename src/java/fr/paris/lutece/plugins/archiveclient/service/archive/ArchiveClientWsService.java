/*
 * Copyright (c) 2002-2014, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.archiveclient.service.archive;

import fr.paris.lutece.plugins.archiveclient.service.util.ArchiveClientConstants;
import fr.paris.lutece.plugins.archiveclient.service.util.ArchiveClientException;
import fr.paris.lutece.portal.service.util.AppLogService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.util.httpaccess.HttpAccess;
import fr.paris.lutece.util.httpaccess.HttpAccessException;
import fr.paris.lutece.util.signrequest.RequestAuthenticator;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * ArchiveClientWsService
 *
 * @author merlinfe
 *
 */
public class ArchiveClientWsService extends AbstractArchiveClientService
{
    private RequestAuthenticator _requestAuthenticatorForWS;

    /**
     * {@inheritDoc}
     */
    public int generateArchive( String strFolderToArchive, String strArchiveDestination, String strArchiveName,
        String strArchiveType ) throws ArchiveClientException
    {
        int nIdgenarateArchive = -1;

        String strUrl = AppPropertiesService.getProperty( ArchiveClientConstants.PROPERTY_WEBAPP_ARCHIVE_REST_URL ) +
            ArchiveClientConstants.URL_REST_GENERATE_ARCHIVE;

        // List parameters to post
        Map<String, String> params = new HashMap<String, String>(  );
        params.put( ArchiveClientConstants.PARAM_FOLDER_TO_ARCHIVE, strFolderToArchive );
        params.put( ArchiveClientConstants.PARAM_ARCHIVE_DESTINATION, strArchiveDestination );
        params.put( ArchiveClientConstants.PARAM_ARCHIVE_NAME, strArchiveName );
        params.put( ArchiveClientConstants.PARAM_ARCHIVE_TYPE, strArchiveType );

        // List elements to include to the signature
        List<String> listElements = new ArrayList<String>(  );

        try
        {
            String strResponse = callArchiveWs( strUrl, params, listElements );
            nIdgenarateArchive = Integer.parseInt( strResponse );
        }
        catch ( Exception e )
        {
            AppLogService.error( e );
            throw new ArchiveClientException( e );
        }

        return nIdgenarateArchive;
    }

    /**
     * {@inheritDoc}
     */
    public String informationArchive( int archiveItemKey )
        throws ArchiveClientException
    {
        String strResponse = null;
        String strUrl = AppPropertiesService.getProperty( ArchiveClientConstants.PROPERTY_WEBAPP_ARCHIVE_REST_URL ) +
            ArchiveClientConstants.URL_REST_INFORMATION_ARCHIVE;

        // List parameters to post
        Map<String, String> params = new HashMap<String, String>(  );
        params.put( ArchiveClientConstants.PARAM_ARCHIVE_ITEM_KEY, Integer.toString( archiveItemKey ) );

        // List elements to include to the signature
        List<String> listElements = new ArrayList<String>(  );
        listElements.add( Integer.toString( archiveItemKey ) );

        try
        {
            strResponse = callArchiveWs( strUrl, params, listElements );
        }
        catch ( Exception e )
        {
            AppLogService.error( e );
            throw new ArchiveClientException( e );
        }

        return strResponse;
    }

    /**
     * {@inheritDoc}
     */
    public void removeArchive( int archiveItemKey ) throws ArchiveClientException
    {
        String strUrl = AppPropertiesService.getProperty( ArchiveClientConstants.PROPERTY_WEBAPP_ARCHIVE_REST_URL ) +
            ArchiveClientConstants.URL_REST_REMOVE_ARCHIVE;

        // List parameters to post
        Map<String, String> params = new HashMap<String, String>(  );
        params.put( ArchiveClientConstants.PARAM_ARCHIVE_ITEM_KEY, Integer.toString( archiveItemKey ) );

        // List elements to include to the signature
        List<String> listElements = new ArrayList<String>(  );
        listElements.add( Integer.toString( archiveItemKey ) );

        try
        {
            callArchiveWs( strUrl, params, listElements );
        }
        catch ( Exception e )
        {
            AppLogService.error( e );
            throw new ArchiveClientException( e );
        }
    }

    /**
     * This method calls Rest WS archive
     * @param strUrl the url
     * @param params the params to pass in the post
     * @param listElements the list of elements to include in the signature
     * @return the response as a string
     * @throws HttpAccessException the exception if there is a problem
     */
    private String callArchiveWs( String strUrl, Map<String, String> params, List<String> listElements )
        throws HttpAccessException
    {
        String strResponse = StringUtils.EMPTY;

        try
        {
            HttpAccess httpAccess = new HttpAccess(  );
            strResponse = httpAccess.doPost( strUrl, params, _requestAuthenticatorForWS, listElements );
        }
        catch ( HttpAccessException e )
        {
            String strError = "ArchiveWebServices - Error connecting to '" + strUrl + "' : ";
            AppLogService.error( strError + e.getMessage(  ), e );
            throw new HttpAccessException( strError, e );
        }

        return strResponse;
    }

    /**
     * Setter method for requestAuthenticatorForWS
     * @param requestAuthenticatorForWS the request authenticator
     */
    public void setRequestAuthenticatorForWS( RequestAuthenticator requestAuthenticatorForWS )
    {
        this._requestAuthenticatorForWS = requestAuthenticatorForWS;
    }
}
