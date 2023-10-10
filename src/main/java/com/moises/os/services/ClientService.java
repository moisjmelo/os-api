package com.moises.os.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moises.os.domain.Person;
import com.moises.os.domain.Client;
import com.moises.os.dtos.ClientDTO;
import com.moises.os.repositories.PersonRepository;
import com.moises.os.repositories.ClientRepository;
import com.moises.os.services.exceptions.DataIntegratyViolationException;
import com.moises.os.services.exceptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;
	
	@Autowired
	private PersonRepository personRepository;

	public Client findById(Integer id) {
		Optional<Client> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Client.class.getName()));
	}

	public List<Client> findAll() {
		return repository.findAll();
	}

	public Client create(ClientDTO objDTO) {

		if (findByCPF(objDTO) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}

		return repository.save(new Client(null, objDTO.getName(), objDTO.getCpf(), objDTO.getTelephone()));
	}

	public Client update(Integer id, @Valid ClientDTO objDTO) {
		Client oldObj = findById(id);

		if (findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}

		oldObj.setName(objDTO.getName());
		oldObj.setCpf(objDTO.getCpf());
		oldObj.setTelephone(objDTO.getTelephone());
		return repository.save(oldObj);

	}

	public void delete(Integer id) {
		Client obj = findById(id);
		if (obj.getList().size() > 0) {
			throw new DataIntegratyViolationException("Cliente com ordem de serviço, não pode ser deletado!");
		}
		repository.deleteById(id);
	}

	private Person findByCPF(ClientDTO objDTO) {
		Person obj = personRepository.findByCPF(objDTO.getCpf());
		if (obj != null) {
			return obj;
		}
		return null;
	}

}
