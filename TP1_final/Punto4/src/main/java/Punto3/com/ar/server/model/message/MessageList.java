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
		try {
			if (messageList.size()==0) {
				return "{\"type\":\"VoidMessageList\", \"messages\":\"null\"}";
			}
			else {
				String salida = "{\"type\":\"MessageList\", \"target\":\""+this.target+"\", \"messages\":{";
				int c = 0;
				salida += "\"message" + c + "\": " + messageList.get(0).toString();
				for (int i = 1 ; i<messageList.size() ; i++) {
					c++;
					salida += ", \"message" + c + "\": " + messageList.get(i).toString();
				}
				return salida+"}}";
			}
		}
		catch(Exception e) {
			return "{\"type\":\"VoidMessageList\", \"target\":"+this.target+"\"messages\":\"null\"";
		}
	}

	public void delMessage(String id) {
		String idr = String.valueOf(Integer.valueOf(id) + 1);
		for (int i = 0 ; i<messageList.size() ; i ++) {
			System.out.println(messageList.get(i).getId() +"\n"
					+ (idr + 1) + "\n"
					+ messageList.get(i).getId().equals(idr));
			if (messageList.get(i).getId().equals(idr)) {
				MessageManager.m = messageList.get(i);
				messageList.remove(i);
			}
		}
		/*
		for ( Message msg : messageList) {
			if (msg.getId().equals(id)) {
				m = msg;
				messageList.remove(msg);
			}
		}*/
	}
}
