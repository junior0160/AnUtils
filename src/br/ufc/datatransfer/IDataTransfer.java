package br.ufc.datatransfer;

import java.util.Map;

import br.ufc.datatransfer.handler.IHandlerResponse;

public interface IDataTransfer<T> {

	public void sendDataGet(Map<String, Object> data);
	public void sendDataPost(Map<String, Object> data);
}
