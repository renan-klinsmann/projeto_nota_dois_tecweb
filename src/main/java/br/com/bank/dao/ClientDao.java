package br.com.bank.dao;

import java.util.List;

import br.com.bank.model.Client;

public interface ClientDao {

	public Client getClient(String email, String senha);

	public List<Client> getAll();

	public boolean save(Client client);

	public Boolean deleteById(Long idClient);
}
