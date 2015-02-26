package br.ufc.datatransfer.clientrequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.apache.http.NameValuePair;


public abstract class BaseClientRequest<T, K> implements IClientRequest<K>{

	protected final static String CHARSET = "UTF-8";
	
	protected String url;
	protected List<NameValuePair> params;
	protected T connection;
	
	
	public BaseClientRequest(String url, List<NameValuePair> params) {
		this.url = url;
		this.params = params;
	}
	
	
	
	protected String getRequestQuery() throws UnsupportedEncodingException
	{
	    StringBuilder result = new StringBuilder();
	    boolean first = true;

	    for (NameValuePair pair : params)
	    {
	        if (first)
	            first = false;
	        else
	            result.append("&");

	        result.append(URLEncoder.encode(pair.getName(), CHARSET));
	        result.append("=");
	        result.append(URLEncoder.encode(pair.getValue(), CHARSET));
	    }
	    
	    return result.toString();
	}
	
	protected static String readStream(InputStream in) {
		BufferedReader reader = null;
		StringBuffer response = new StringBuffer();
		try {
			reader = new BufferedReader(new InputStreamReader(in));
			String line = "";
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return response.toString();
	} 
	
}
