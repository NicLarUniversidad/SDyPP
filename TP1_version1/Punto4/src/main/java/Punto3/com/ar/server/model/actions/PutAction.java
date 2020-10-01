package Punto3.com.ar.server.model.actions;

import Punto3.com.ar.server.model.message.MessageManager;

public class PutAction extends IAction {

	private String notify;
	private String content;
	
	public PutAction(String request) {
		this.setContent(request);
		this.notify = "ready";
	}
	
	@Override
	public String toString() {
		return this.notify;
	}

	@Override
	public String excecute() {
		this.notify = MessageManager.addMessage(content);
		return this.notify;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
