package org.hejin.newapp.pages;

import javax.inject.Inject;

import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.OnEvent;


/**
 * Start page of application newapp.
 */
public class BlockCompDemo
{
	@Inject
	AlertManager alertManager;
	
	@OnEvent("alertMessage")
	void callAlertManager(String message){
		alertManager.info(message);
	}
}
