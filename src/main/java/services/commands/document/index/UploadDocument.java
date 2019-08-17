package services.commands.document.index;

import java.rmi.RemoteException;

import com.fasterxml.jackson.databind.ObjectMapper;

import domain.HttpClient;
import domain.HttpReply;
import domain.json.UploadDocumentRequest;
import domain.HttpClient.HttpMethod;
import services.ElasticSearchCommand;

public class UploadDocument implements ElasticSearchCommand {

	private static String index = "machines";
	private static String type = "red_machines";
	protected UploadDocumentRequest request;

	public UploadDocument(String name, int cpu, int memory, String time) {
		this.request = new UploadDocumentRequest(name, type, cpu, memory, time);
	}

	@Override
	public HttpReply execute() throws Exception {

		String jsonRequest = "";

		ObjectMapper Obj = new ObjectMapper();
		jsonRequest = Obj.writeValueAsString(request);

		System.out.println("Executing script. HTTP request body: "+jsonRequest);

		HttpClient client = new HttpClient("localhost", "9200", "/"+index+"/_doc", "elastic", "pass", HttpMethod.POST, "application/json", jsonRequest);
		HttpReply reply = client.doHttpRequest();

		System.out.println("Executed script. Response:\n"+reply.toString());

		switch (Integer.valueOf(reply.getStatusCode())) {
		case 201:
			break;

		default:
			throw new RemoteException("HTTP request failed. Expected HTTP status 201 as response to PUT request to upload new document. Got: "+reply.getStatusCode());
		}

		return reply;
	}

	public UploadDocumentRequest getRequest() {
		return request;
	}

	public void setRequest(UploadDocumentRequest request) {
		this.request = request;
	}

}
