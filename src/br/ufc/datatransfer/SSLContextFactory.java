package br.ufc.datatransfer;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import android.content.Context;
import android.util.Base64;

public class SSLContextFactory {

    private static SSLContextFactory theInstance = null;
    private Context context;
    
    
    private SSLContextFactory(Context context) {
    	this.context = context;
    }

    public static SSLContextFactory getInstance(Context context) {
        if(theInstance == null) {
            theInstance = new SSLContextFactory(context);
        }
        return theInstance;
    }

    public SSLContext makeSSLContext(){
    	
    	SSLContext context = null;
    	
		try {
		
			TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			tmf.init(createKeyStoreFromCA());
			context = SSLContext.getInstance("TLS");
			context.init(null, tmf.getTrustManagers(), null);
		
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}

		return context;
		
    }
    
    public Certificate loadCA(){
    	
    	Certificate ca = null;

    	try {
		
    		CertificateFactory cf = CertificateFactory.getInstance("X.509");
			ca = cf.generateCertificate(context.getAssets().open("server.crt"));

		} catch (IOException e) {
			e.printStackTrace();

		} catch (CertificateException e) {
			e.printStackTrace();
		} 
    	
    	return ca;
    }
    
    private KeyStore createKeyStoreFromCA(){
    	
    	KeyStore keyStore = null;

    	try {
    	
    		String keyStoreType = KeyStore.getDefaultType();
			keyStore = KeyStore.getInstance(keyStoreType);
    		keyStore.load(null, null);
			keyStore.setCertificateEntry("ca", loadCA());
		
    	} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	return keyStore;
    }
}