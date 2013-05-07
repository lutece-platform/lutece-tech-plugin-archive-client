/*
 * Copyright (c) 2002-2013, Mairie de Paris
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

import fr.paris.lutece.plugins.archiveclient.service.util.ArchiveClientException;


/**
 * class for calling archive service
 * @author merlinfe
 *
 */
public interface IArchiveClientService
{
    /**
     * request for archiving an folder
     * @param strFolderToArchive path to the folder to archive
     * @param strArchiveDestination path to the destination folder which will store the archive
     * @param strArchiveName the name of the archive
     * @param strArchiveType the archive type(zip,..)
     * @return the archive id
     * @throws ArchiveClientException exception if there is a problem
     */
    int generateArchive( String strFolderToArchive, String strArchiveDestination, String strArchiveName,
        String strArchiveType ) throws ArchiveClientException;

    /**
     * return the state of the archive with the ID parameter is provided
     * @param archiveItemKey the archive id
     * @return the state of the archive
     * @throws ArchiveClientException exception if there is a problem
     */
    String informationArchive( int archiveItemKey ) throws ArchiveClientException;

    /**
     * Remove the archive with the ID parameter is provided
     * @param archiveItemKey the archive id
     * @throws ArchiveClientException exception if there is a problem
     */
    void removeArchive( int archiveItemKey ) throws ArchiveClientException;

    /**
     * get the url of the archive with the ID parameter is provided
     * @param archiveItemKey the archive id
     * @return the url of the archive
     */
    String getDownloadUrl( int archiveItemKey );
}
