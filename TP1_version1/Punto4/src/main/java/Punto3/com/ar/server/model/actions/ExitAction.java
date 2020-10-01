package Punto3.com.ar.server.model.actions;

public class ExitAction extends IAction {

	@Override
	public String excecute() {
		return "{\"type\":\"ack\"}";
	}

}
