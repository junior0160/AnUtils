package br.holandajunior.datatransfer;

import java.util.Map;

import br.holandajunior.datatransfer.handler.IHandlerResponse;

public interface IDataTransfer<T> {

	public void execute();
	
}
