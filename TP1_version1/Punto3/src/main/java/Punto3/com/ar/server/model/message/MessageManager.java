package Punto3.com.ar.server.model.message;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.ar.model.utils.ICustomLogger;

public class MessageManager {
	
	private static Map<String, MessageList> messages;
	private static ICustomLogger logger;
	
	public static void init() {
		messages = new HashMap<String, MessageList>();
	}
	
	public static String addMessage(String json) {
		JSONObject jso = new JSONObject(json);
		String target = jso.getString("target");
		MessageList targetList;
		if (messages.containsKey(target)) {
			targetList = messages.get(target);
		}
		else {
			targetList = new MessageList(target);
			messages.put(target, targetList);
		}
		String msg = jso.getString("message");
		String sender = jso.getString("sender");
		Message m = new Message(sender,msg,target);
		targetList.add(m);
		//TODO:
		/*int total = json.length();
		int headers = total -(target.length() + sender.length() + msg.length());
		logger.logInfo("Se ha agregado un mensaje, el cual tenía un througput de: " + headers/total + "%",
				"MessageManager", "addMessage");*/
		return "Se ha enviado un mensaje";
	}
	
	public static String getMessage(String target) {
		if (messages.containsKey(target)) {
			return messages.get(target).toString();
		}
		else
			return "\"MessageList\":{\"type\":\"VoidMessageList\", \"messages\":\"null\"}";
	}

	public static ICustomLogger getLogger() {
		return logger;
	}

	public static void setLogger(ICustomLogger logger) {
		MessageManager.logger = logger;
	}
	
}
