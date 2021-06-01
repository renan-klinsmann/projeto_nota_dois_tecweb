package br.com.bank.dao;

import java.util.List;

import br.com.bank.model.Client;

public interface ClientDao {

	public Client getClient(String email, String senha);
	
	public Client getClientId(Long id);

	public List<Client> getAll();

	public boolean save(Client client);
	
	public boolean update(Client client);

	public Boolean deleteById(Long idClient);
}
