package fr.paris.lutece.plugins.archiveclient.service;

import fr.paris.lutece.plugins.archive.service.archive.IArchiveService;
import fr.paris.lutece.plugins.archiveclient.service.archive.ArchiveClientWsService;
import fr.paris.lutece.plugins.archiveclient.service.archive.ArchiveClientLocalService;
import fr.paris.lutece.plugins.archiveclient.service.util.ArchiveClientConstants;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.util.signrequest.HeaderHashAuthenticator;
import fr.paris.lutece.util.signrequest.RequestHashAuthenticator;
import fr.paris.lutece.util.signrequest.security.HashService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class ArchiveClientProducer
{

    private static final String PROPERTY_PRIVATE_KEY = "archive-client.privateKey";
    private static final List<String> SIGNATURE_ELEMENTS = Collections.singletonList( ArchiveClientConstants.PARAM_ARCHIVE_ITEM_KEY );

    @Inject
    @Named( "signrequest.Sha1HashService" )
    private HashService _hashService;

    @Produces
    @ApplicationScoped
    @Named( "archive-client.requestAuthenticatorForWS" )
    public HeaderHashAuthenticator getRequestAuthenticatorForWS( )
    {
        String strPrivateKey = AppPropertiesService.getProperty( PROPERTY_PRIVATE_KEY );
        return new HeaderHashAuthenticator( _hashService, SIGNATURE_ELEMENTS, strPrivateKey );
    }

    @Produces
    @ApplicationScoped
    @Named( "archive-client.requestAuthenticatorForUrl" )
    public RequestHashAuthenticator getRequestAuthenticatorForUrl( )
    {
        String strPrivateKey = AppPropertiesService.getProperty( PROPERTY_PRIVATE_KEY );
        return new RequestHashAuthenticator( _hashService, SIGNATURE_ELEMENTS, strPrivateKey );
    }

    @Produces
    @ApplicationScoped
    @Named( "archive-client.archiveClientServiceWS" )
    public ArchiveClientWsService getArchiveClientWsService(
            @Named( "archive-client.requestAuthenticatorForWS" ) HeaderHashAuthenticator authenticatorWs,
            @Named( "archive-client.requestAuthenticatorForUrl" ) RequestHashAuthenticator authenticatorUrl )
    {
        ArchiveClientWsService service = new ArchiveClientWsService( );
        service.setRequestAuthenticatorForWS( authenticatorWs );
        service.setRequestAuthenticatorForUrl( authenticatorUrl );
        return service;
    }

    @Produces
    @ApplicationScoped
    @Named( "archive-client.archiveClientLocalService" )
    public ArchiveClientLocalService getArchiveClientLocalService(
            @Named( "archive-client.requestAuthenticatorForUrl" ) RequestHashAuthenticator authenticatorUrl,
            IArchiveService archiveService )
    {
        ArchiveClientLocalService service = new ArchiveClientLocalService( );
        service.setRequestAuthenticatorForUrl( authenticatorUrl );
        service.setArchiveService( archiveService );
        return service;
    }
}
