package services;

import domain.HttpReply;

public interface ElasticSearchCommand {

	public HttpReply execute() throws Exception;

}
