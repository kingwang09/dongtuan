package org.hejin.newapp.mixins;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ClientElement;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.InjectContainer;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

public class ModalLink {
	
	@Parameter(required=true,defaultPrefix = BindingConstants.LITERAL)
	String dialogId;
	
	@Inject
    private JavaScriptSupport javaScriptSupport;

    @InjectContainer
    private ClientElement clientElement;
    
    @AfterRender
    void afterRender(){
    	JSONObject param = new JSONObject();
    		param.put("dialogId", dialogId);
    		param.put("dialogLinkId", clientElement.getClientId());
    	javaScriptSupport.require("dialog/dialogModule").with(param);
    }
}
