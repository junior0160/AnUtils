package br.holandajunior.datatransfer.clientrequest;

import br.holandajunior.datatransfer.DataTransferResponse;


public interface IClientRequest<T>{

	public DataTransferResponse<T> doConnection();
}
