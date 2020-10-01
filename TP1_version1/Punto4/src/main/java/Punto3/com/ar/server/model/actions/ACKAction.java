package Punto3.com.ar.server.model.actions;

import Punto3.com.ar.server.model.message.MessageManager;

public class ACKAction extends IAction {
	
	private String id;

	public ACKAction(String id) {
		this.id = id;
	}

	@Override
	public String excecute() {
		return MessageManager.delMsg(id);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
