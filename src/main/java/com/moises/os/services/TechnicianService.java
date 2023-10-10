package com.moises.os.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moises.os.domain.Person;
import com.moises.os.domain.Technician;
import com.moises.os.dtos.TechnicianDTO;
import com.moises.os.repositories.PersonRepository;
import com.moises.os.repositories.TechnicianRepository;
import com.moises.os.services.exceptions.DataIntegratyViolationException;
import com.moises.os.services.exceptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class TechnicianService {

	@Autowired
	private TechnicianRepository repository;
	
	@Autowired
	private PersonRepository personRepository;

	public Technician findById(Integer id) {
		Optional<Technician> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Technician.class.getName()));
	}

	public List<Technician> findAll() {
		return repository.findAll();
	}

	public Technician create(TechnicianDTO objDTO) {
		// Technician newObj = new Technician(null, objDTO.getName(), objDTO.getCpf(),
		// objDTO.getTelephone());
		// return repository.save(newObj);

		if (findByCPF(objDTO) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}

		return repository.save(new Technician(null, objDTO.getName(), objDTO.getCpf(), objDTO.getTelephone()));
	}

	public Technician update(Integer id, @Valid TechnicianDTO objDTO) {
		Technician oldObj = findById(id);

		if (findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}

		oldObj.setName(objDTO.getName());
		oldObj.setCpf(objDTO.getCpf());
		oldObj.setTelephone(objDTO.getTelephone());
		return repository.save(oldObj);

	}

	public void delete(Integer id) {
		Technician obj = findById(id);
		if (obj.getList().size() > 0) {
			throw new DataIntegratyViolationException("Técnico com ordem de serviço, não pode ser deletado!");
		}
		repository.deleteById(id);
	}

	private Person findByCPF(TechnicianDTO objDTO) {
		Person obj = personRepository.findByCPF(objDTO.getCpf());
		if (obj != null) {
			return obj;
		}
		return null;
	}

}
