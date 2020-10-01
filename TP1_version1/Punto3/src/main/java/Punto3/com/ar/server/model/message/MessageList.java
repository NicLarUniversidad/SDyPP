package Punto3.com.ar.server.model.message;

import java.util.ArrayList;

public class MessageList {
	private ArrayList<Message> messageList;
	private String target;
	

	public MessageList(String target) {
		super();
		this.target = target;
		this.messageList = new ArrayList<Message>();
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public boolean add(Message arg0) {
		return messageList.add(arg0);
	}

	public Message get(int arg0) {
		return messageList.get(arg0);
	}

	public Message remove(int arg0) {
		return messageList.remove(arg0);
	}

	public boolean remove(Object arg0) {
		return messageList.remove(arg0);
	}

	public ArrayList<Message> getMessageList() {
		return messageList;
	}

	public void setMessageList(ArrayList<Message> messageList) {
		this.messageList = messageList;
	}
	
	@Override
	public String toString() {
		if (messageList.size()<=0) {
			return "{\"type\":\"VoidMessageList\", \"messages\":\"null\"}";
		}
		else {
			String salida = "{\"type\":\"MessageList\", \"target\":\""+this.target+"\", \"messages\":{";
			int c = 0;
			salida += "\"message" + c + "\": " + messageList.get(0).toString(0);
			for (int i = 1 ; i<messageList.size() ; i++) {
				c++;
				salida += ", \"message" + c + "\": " + messageList.get(i).toString(i);
			}
			return salida+"}}";
		}
	}
}
