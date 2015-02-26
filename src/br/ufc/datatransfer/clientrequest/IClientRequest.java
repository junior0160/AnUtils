package br.ufc.datatransfer.clientrequest;

import br.ufc.datatransfer.DataTransferResponse;


public interface IClientRequest<T>{

	public DataTransferResponse<T> doConnection();
}
