package Punto3.com.ar.server.model.message;

public class Message {
	private String sender;
	private String content;
	private String target;
	private String id;
	
	public Message (String sender, String content, String target) {
		this.sender = sender;
		this.content = content;
		this.target = target;
		this.id = "null";
	}
	
	public Message (String sender, String content, String target, int id) {
		this.sender = sender;
		this.content = content;
		this.target = target;
		this.id = Integer.toString(id);
	}
	
	@Override
	public String toString() {
		String salida ="{\"id\":\"null\", \"sender\":\"null\", \"target\":\"null\", \"contain\":\"null\"}";
		/*JSONStringer js = new JSONStringer();
		salida = js.key("message")
					.value(this.sender)
					.value(this.content)
					.value(this.target)
					.toString();*/
		if ((this.sender!=null)||(this.content!=null)||(this.target!=null)) {
			salida = "{ \"id\":\"" + this.id + "\", \"sender\":\""+this.sender + "\", "
					+ "\"target\": \"" + this.target + "\", "
					+ "\"contain\": \"" + this.content + "\""
					+ "}";
		}
		return salida;
	}
	
	public String toString(int c) {
		String salida ="{\"id\":\"null\", \"sender\":\"null\", \"target\":\"null\", \"contain\":\"null\"}";
		if ((this.sender!=null)||(this.content!=null)||(this.target!=null)) {
			salida = "{ \"id\":\"" + c + "\", \"sender\":\""+this.sender + "\", "
					+ "\"target\": \"" + this.target + "\", "
					+ "\"contain\": \"" + this.content + "\""
					+ "}";
		}
		return salida;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
