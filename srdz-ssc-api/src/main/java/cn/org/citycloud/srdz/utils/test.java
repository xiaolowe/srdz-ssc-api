package cn.org.citycloud.srdz.utils;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.json.JSONObject;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;
import com.auth0.jwt.internal.org.apache.commons.codec.binary.Base64;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class test {
	public static void main (String [] args) throws Exception {
       /*JWTSigner jwtSigner = new JWTSigner("6789");
       Map<String, Object> claims = new HashMap<String, Object>();
       String str = jwtSigner.sign(claims);
       System.out.println(str);
       JWTVerifier jwtVerifier = new JWTVerifier("6789");
       claims = jwtVerifier.verify(str);
       System.out.println(claims);
       for(String key:claims.keySet()){
    	   System.out.println("key:"+key);
    	   System.out.println("value:"+claims.get(key));
       }*/
		System.out.println("Send message success.");
		test test = new test();
		String httpResponse = test.testSend();
        try {
           JSONObject jsonObj = new JSONObject( httpResponse );
           int error_code = jsonObj.getInt("error");
           String error_msg = jsonObj.getString("msg");
           if(error_code==0){
               System.out.println("Send message success.");
           }else{
               System.out.println("Send message failed,code is "+error_code+",msg is "+error_msg);
           }
       } catch (JSONException ex) {
           ex.printStackTrace();
       }
    }
	
	public  String testSend(){
        // just replace key here
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter(
            "api","key-c6864eb5cccb3c050090b7d0b2bc4de9"));
        WebResource webResource = client.resource(
            "http://sms-api.luosimao.com/v1/send.json");
        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        formData.add("mobile", "18009691019");
        formData.add("message", "验证码：286221【CCDC-ZWHS】");
        ClientResponse response =  webResource.type( MediaType.APPLICATION_FORM_URLENCODED ).
        post(ClientResponse.class, formData);
        String textEntity = response.getEntity(String.class);
        int status = response.getStatus();
        //System.out.print(textEntity);
        //System.out.print(status);
        return textEntity;
    }
	
	private  String testStatus(){
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter(
            "api","key-c6864eb5cccb3c050090b7d0b2bc4de9"));
        WebResource webResource = client.resource( "http://sms-api.luosimao.com/v1/status.json" );
        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        ClientResponse response =  webResource.get( ClientResponse.class );
        String textEntity = response.getEntity(String.class);
        int status = response.getStatus();
        //System.out.print(status);
        //System.out.print(textEntity);
        return textEntity;
    }
}
