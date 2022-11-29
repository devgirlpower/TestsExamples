package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.example.demo.model.Address;
import com.google.gson.Gson;

public abstract class IntegrationClassCep {
	 
	
	public static Address integrationCep(String cep) throws Exception {
		String test = "https://viacep.com.br/ws/"+cep+"/json/";
		//localiza recurso
		URL url = new URL(test);
		URLConnection connection = url.openConnection();	
		InputStream input = connection.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(input,"UTF-8"));
		// manipulando dados 
		String cep2;
		StringBuilder jsoncep = new StringBuilder();
		while((cep2 = reader.readLine()) != null) {
			jsoncep.append(cep2);
		}
		Address address = new Gson().fromJson(jsoncep.toString(), Address.class);
		return new Address(address.getLogradouro(),address.getBairro(),address.getLocalidade());
		
		
	}
}
