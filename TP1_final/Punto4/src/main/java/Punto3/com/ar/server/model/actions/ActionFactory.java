package Punto3.com.ar.server.model.actions;

import org.json.JSONObject;

public class ActionFactory {

	public static IAction createAction(String request, boolean conected) {
		JSONObject jsno = new JSONObject(request);
		String type = jsno.getString("type");
		if (type.equals(new String("put"))) {
			return new PutAction(jsno.getJSONObject("message").toString());
		}
		else
			if (type.equals("get"))
				return new GetAction(jsno.getString("target"));
			else
				if (type.equals("ack")) {
					return new ACKAction(jsno.getString("id"));
				}
				else
					if (type.equals("exit")) {
						conected = false;
						return new ExitAction();
					}
		return new IAction();
	}

}
