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
package fr.paris.lutece.plugins.archiveclient.service.util;


/**
 * 
 * Constants
 * 
 */
public final class ArchiveClientConstants {
	public static String ARCHIVE_STATE_INITIAL = "INIT";
	public static String ARCHIVE_STATE_USED = "USED";
	public static String ARCHIVE_STATE_ERROR = "ERROR";
	public static String ARCHIVE_STATE_FINAL = "FINAL";
	public static String ARCHIVE_TYPE_ZIP = "ZIP";

	// PARAMETERS
	public static final String PARAM_FOLDER_TO_ARCHIVE = "folder_to_archive";
	public static final String PARAM_ARCHIVE_DESTINATION = "archive_destination";
	public static final String PARAM_ARCHIVE_NAME = "archive_name";
	public static final String PARAM_ARCHIVE_TYPE = "archive_type";
	public static final String PARAM_ARCHIVE_ITEM_KEY = "archive_item_key";
	// URL REST ARCHIVE
	public static final String URL_REST_GENERATE_ARCHIVE = "/rest/archive/generateArchive";
	public static final String URL_REST_INFORMATION_ARCHIVE= "/rest/archive/informationArchive";
	public static final String URL_REST_REMOVE_ARCHIVE= "/rest/archive/removeArchive";
	//URL DOWNLOAD ARCHIVE 
	public static final String URL_DOWNLOAD_ARCHIVE = "/downloadArchive";
	// PROPERTIES
    public static final String PROPERTY_WEBAPP_ARCHIVE_REST_URL = "archive-client.webapp.archive.rest.url";
    
	
	/**
	 * Constants
	 */
	private ArchiveClientConstants() {
	}
}
