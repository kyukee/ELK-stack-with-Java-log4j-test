package domain;

public class HttpReply {
	private String statusCode;
	private String statusInfo;
	private String headers;
	private String body;
	
	public HttpReply(String statusCode, String statusInfo, String headers, String body) {
		super();
		this.statusCode = statusCode;
		this.statusInfo = statusInfo;
		this.headers = headers;
		this.body = body;
	}
	
	public String getStatusCode() {
		return statusCode;
	}


	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}


	public String getStatusInfo() {
		return statusInfo;
	}


	public void setStatusInfo(String statusInfo) {
		this.statusInfo = statusInfo;
	}


	public String getHeaders() {
		return headers;
	}


	public void setHeaders(String headers) {
		this.headers = headers;
	}


	public String getBody() {
		return body;
	}


	public void setBody(String body) {
		this.body = body;
	}


	@Override
	public String toString() {
		return "statusCode=" + statusCode + "\nstatusInfo=" + statusInfo + "\nheaders=" + headers + "\nbody=" + body;
	}
}