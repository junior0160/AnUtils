package br.ufc.datatransfer.exception;

/**
 * Interface para possibilitar que as exceções sejam salvas em Log ou enviadas para
 * analytics, por exemplo.
 * @author Holanda Junior
 *
 */
public interface IException {

	public String getLocalizedMessage();
	public Throwable getCause();
}
