package br.ufc.datatransfer;

import java.util.Map;

import br.ufc.datatransfer.handler.IHandlerResponse;

public interface IDataTransfer<T> {

	public void execute();
	
}
