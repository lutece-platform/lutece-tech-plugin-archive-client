/*
 * Copyright (c) 2002-2011, Mairie de Paris
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
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.util.signrequest.AbstractAuthenticator;
import fr.paris.lutece.util.signrequest.RequestAuthenticator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public abstract class AbstractArchiveClientService implements IArchiveClientService
{
    private RequestAuthenticator _requestAuthenticatorForUrl;

    public String getDownloadUrl( int archiveItemKey )
    {
        List<String> listElements = new ArrayList<String>(  );
        listElements.add( Integer.toString( archiveItemKey ) );

        String strTime = Long.toString( new Date(  ).getTime(  ) );
        String strSignature = ( (AbstractAuthenticator) _requestAuthenticatorForUrl ).buildSignature( listElements,
                strTime );

        StringBuilder strUrl = new StringBuilder(  );
        strUrl.append( AppPropertiesService.getProperty( ArchiveClientConstants.PROPERTY_WEBAPP_ARCHIVE_REST_URL ) );
        strUrl.append( ArchiveClientConstants.URL_DOWNLOAD_ARCHIVE );
        strUrl.append( "?" );
        strUrl.append( ArchiveClientConstants.PARAM_ARCHIVE_ITEM_KEY );
        strUrl.append( "=" );
        strUrl.append( strSignature );
        strUrl.append( "&" );
        strUrl.append( "timestamp=" );
        strUrl.append( strTime );

        return strUrl.toString(  );
    }

    public void setRequestAuthenticatorForUrl( RequestAuthenticator requestAuthenticatorForUrl )
    {
        this._requestAuthenticatorForUrl = requestAuthenticatorForUrl;
    }
}
