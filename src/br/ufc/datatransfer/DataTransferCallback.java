package br.ufc.datatransfer;


/**
 * 
 * @author Holanda Junior
 *
 * @param <T> Modelos criados etc.
 */
public interface DataTransferCallback<T> {

	/**
	 * Callback chamado para a aplicação receber a resposta recebida do sevidor e tratada pela aplicação
	 * @param response Instância de {@link DataTransferResponse} a ser passada para aplicação
	 */
	public void onReceiveResponse(DataTransferResponse<T> response);
}
