package Punto3.com.ar.client.model.message;

import org.json.JSONObject;

public class Parser {
	
	public static String parse(String json) {
		String o = json;
		try {
			JSONObject jsonObj = new JSONObject(json);
			String op = jsonObj.getString("type");
			if (op.equals("get")) {
				jsonObj = jsonObj.getJSONObject("MessageList");
				op = jsonObj.getString("type");
				if (op.equals("VoidMessageList")) {
					o = "No hay mensajes para el destinatario ingresado";
				}
				else if (op.equals("MessageList")) {
					o = "Nuevos mensajes:\n";
					jsonObj = jsonObj.getJSONObject("messages");
					int i = 0;
					boolean next = true;
					while (next) {
						try {
							JSONObject msg = jsonObj.getJSONObject("message" + i);
							i++;
							o += "Identificador del mensaje: " + msg.getString("id") + "\n"
									+ "Emisor: " + msg.getString("sender") + "\n"
									+ "Remitente: " + msg.getString("target") + "\n"
									+ "Contenido: " + msg.getString("contain") + "\n\n";
						}
						catch (Exception e) {
							next = false;
						}
					}
				}
			}
			else if (op.equals("exit")) {
				o = "Se ha cerrado la conexión con el servidor";
			}
			else
				o = op;
			}
		catch (Exception e) {
			
		}
		return o;
	}
}
