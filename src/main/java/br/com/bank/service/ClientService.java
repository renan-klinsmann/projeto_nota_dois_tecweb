package br.com.bank.service;

import java.util.List;

import br.com.bank.model.Client;

public interface ClientService {

	public Client getClient(String email, String senha);
	
	public List<Client> getAll();
	
	public boolean save(Client client);
	
	public Boolean deleteById(Long idClient);
}
