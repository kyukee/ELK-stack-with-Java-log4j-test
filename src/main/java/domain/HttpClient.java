package domain;

import java.util.Base64;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class HttpClient {

	private static String USER_AGENT = "Mozilla/5.0";
	// private static String USER_AGENT = "Python http auth";

	private String host;
	private String port;
	private String username;
	private String password;
	private HttpMethod method;
	private String urlpath;
	private String contenttype;
	private String body;

	public enum HttpMethod {
        DELETE, GET, HEAD, OPTIONS, POST, PUT
    }

	public HttpClient(String host, String port, String urlpath, String username, String password, HttpMethod method, String contenttype, String body) {
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
		this.method = method;
		this.urlpath = urlpath;
		this.contenttype = contenttype;
		this.body = body;

		System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
	}

	public HttpReply doHttpRequest() throws Exception {

		// default reply values
		String responseCode = "500";
		String responseStatus = "";
		String responseHeaders = "";
		String responseBody = "";
		Response response = null;


		Client client = ClientBuilder.newClient();

		// create a connection to the endpoint
		String urlString = "http://" + host + ":" + port + urlpath;
        WebTarget target = client.target(urlString);



		// base64 encode the username and password
		String credentials = username + ":" + password;
		String auth = "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());


        Invocation.Builder builder = target.request()
			  .header("host", host)
			  .header("User-Agent", USER_AGENT)
			  .header("Content-type", contenttype)
			  .header("Authorization", auth)
			  .header("Content-Length",String.valueOf(body.length()));


        switch (method) {
		case GET:
			response = builder.get();
			break;
		case DELETE:
			response = builder.delete();
			break;
		case POST:
			response = builder.post(Entity.json(body));
			break;
		case PUT:
			response = builder.put(Entity.json(body));
			break;
		case HEAD:
			// not supported
			break;
		case OPTIONS:
			// not supported
			break;
		default:
			new Exception("Invalid HTTP method");
			break;
		}


        // 	status code
        responseCode = String.valueOf(response.getStatus());

        // status info
        responseStatus = response.getStatusInfo().toString();

        // headers
        responseHeaders = response.getHeaders().toString();

        // body
        responseBody = response.readEntity(String.class);

        client.close();


		return new HttpReply(responseCode, responseStatus, responseHeaders, responseBody);

	}

	public static String getUSER_AGENT() {
		return USER_AGENT;
	}

	public static void setUSER_AGENT(String USER_AGENT) {
		HttpClient.USER_AGENT = USER_AGENT;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public HttpMethod getMethod() {
		return method;
	}

	public void setMethod(HttpMethod method) {
		this.method = method;
	}

	public String getUrlpath() {
		return urlpath;
	}

	public void setUrlpath(String urlpath) {
		this.urlpath = urlpath;
	}

	public String getContenttype() {
		return contenttype;
	}

	public void setContenttype(String contenttype) {
		this.contenttype = contenttype;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
