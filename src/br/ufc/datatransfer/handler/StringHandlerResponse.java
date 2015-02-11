package br.ufc.datatransfer.handler;


/**
 * Implementação de {@link IHandlerResponse} para tipo String
 * @author Holanda Junior
 *
 */
public class StringHandlerResponse extends HandlerResponse<String> implements IHandlerResponse<String>{

	public StringHandlerResponse() {
		this.mDataType = String.class;
	}
	
	
}
