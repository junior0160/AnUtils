package br.ufc.datatransfer.clientrequest;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.NameValuePair;

import android.util.Log;
import br.ufc.datatransfer.DataTransferResponse;
import br.ufc.datatransfer.config.AppConfig;

public class HttpRequest extends BaseClientRequest<HttpURLConnection, String>{
	
	public HttpRequest(String url, List<NameValuePair> params) {
		super(url, params);
	}

	@Override
	public DataTransferResponse<String> doConnection() {
	
		DataTransferResponse<String> response = new DataTransferResponse<String>();
		

		try {

			Log.e("Request", "Fazendo conexão...");
			
			URL urlConnection = new URL("http://"+url);

			connection = (HttpURLConnection) urlConnection.openConnection();
			connection.setReadTimeout(10000);
			connection.setConnectTimeout(15000);
			connection.setRequestMethod("POST");
			connection.setDoInput(true);
			connection.setRequestProperty("token", AppConfig.SERVER_TOKEN);
			
			if (params != null) {
			
				Log.e("Request", "Tem parametros...");
				connection.setDoOutput(true);

				OutputStream os = connection.getOutputStream();
				BufferedWriter writer = new BufferedWriter(
						new OutputStreamWriter(os, CHARSET));	
				String requestQuery = getRequestQuery();
				
				Log.e("Request", requestQuery);
				
				writer.write(requestQuery);
				writer.flush();
				writer.close();
				os.close();
			}
			
			
			
			int responseCode = connection.getResponseCode();
			response.setmStatusCode(responseCode);
			
			String responseString;
			
			if(responseCode >= 400)
				responseString = readStream(connection.getErrorStream());
			else
				responseString = readStream(connection.getInputStream());
			
			response.setmValue(responseString);
			
			
			
		} catch (UnsupportedEncodingException e) {
		
			Log.e("Request", "UnsuportedException");
			Log.e("Request", ExceptionUtils.getStackTrace(e));
			response.setmValue("None");
			
		} catch (IOException e) {
			
			Log.e("Request", "IOException");
			Log.e("Request", ExceptionUtils.getStackTrace(e));
			response.setmValue("None");

		}finally{
			
			connection.disconnect();
			Log.e("Request", "Desconectou");
		}
		
		return response;
		
	}

	
	

}
