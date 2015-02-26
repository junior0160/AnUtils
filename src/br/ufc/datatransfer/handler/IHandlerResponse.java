package br.ufc.datatransfer.handler;

import br.ufc.datatransfer.DataTransferResponse;

/**
 * 
 * @author Holanda Junior
 *
 * @param <T> Modelo criado no projeto que define o tipo a ser retornado na resposta da requisição
 */
public interface IHandlerResponse<T> {

	/**
	 * Callback chamado para manipular resposta do servidor
	 * @param httpResponse Resposta do servidor
	 * @return Resposta apropriada para a aplicação {@link DataTransferResponse}
	 */
	public DataTransferResponse<T> handlingResponse(DataTransferResponse<T> response);
		
}
