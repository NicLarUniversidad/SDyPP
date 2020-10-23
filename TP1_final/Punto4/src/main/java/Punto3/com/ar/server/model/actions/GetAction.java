package Punto3.com.ar.server.model.actions;

import Punto3.com.ar.server.model.message.MessageManager;

public class GetAction extends IAction {

	String messages;
	
	public GetAction(String target) {
		this.messages = "{\"type\":\"get\", " + MessageManager.getMessage(target) + "}";
	}

	@Override
	public String excecute() {
		return this.messages;
	}

}
