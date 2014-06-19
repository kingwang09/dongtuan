package org.hejin.newapp.web.services;


import org.apache.tapestry5.*;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This module is automatically included as part of the Tapestry IoC Registry if <em>tapestry.execution-mode</em>
 * includes <code>development</code>.
 */
public class DevelopmentModule
{	private final static Logger log = LoggerFactory.getLogger(DevelopmentModule.class);
    public static void contributeApplicationDefaults(
            MappedConfiguration<String, Object> configuration)
    {
    	log.debug("DevelopmentModule contributeApplicationDefaults");
        // The factory default is true but during the early stages of an application
        // overriding to false is a good idea. In addition, this is often overridden
        // on the command line as -Dtapestry.production-mode=false
        configuration.add(SymbolConstants.PRODUCTION_MODE, false);

        // The application version number is incorprated into URLs for some
        // assets. Web browsers will cache assets because of the far future expires
        // header. If existing assets are changed, the version number should also
        // change, to force the browser to download new versions.
        configuration.add(SymbolConstants.APPLICATION_VERSION, "0.1-SNAPSHOT-DEV");
    }
}
