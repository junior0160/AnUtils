package br.holandajunior.datatransfer.handler;

import br.holandajunior.datatransfer.DataTransferResponse;


/**
 * Implementação de {@link IHandlerResponse} para tipo String
 * @author Holanda Junior
 *
 */
public class StringHandlerResponse extends HandlerResponse<String> implements IHandlerResponse<String>{

	@Override
	public DataTransferResponse<String> handlingResponse(
			DataTransferResponse<String> response) {

		return response;
	}

	
	
	
}
