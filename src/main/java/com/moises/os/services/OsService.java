package com.moises.os.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moises.os.domain.Client;
import com.moises.os.domain.OS;
import com.moises.os.domain.Technician;
import com.moises.os.domain.enuns.Priority;
import com.moises.os.domain.enuns.Status;
import com.moises.os.dtos.OSDTO;
import com.moises.os.repositories.OSRepository;
import com.moises.os.services.exceptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class OsService {

	@Autowired
	private OSRepository repository;
	
	@Autowired
	private TechnicianService technicianService;
	
	@Autowired
	private ClientService clientService;

	public OS findById(Integer id) {
		Optional<OS> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + OS.class.getName()));
	}
	
	public List<OS> findAll(){
		return repository.findAll();
		
	}

	public OS create(@Valid OSDTO obj) {
		return fromDTO(obj);
	}
	
	public OS update(@Valid OSDTO obj) {
		findById(obj.getId());
		return fromDTO(obj);
	}
	
	private OS fromDTO(OSDTO obj) {
		OS newObj = new OS();
		newObj.setId(obj.getId());
		newObj.setComments(obj.getComments());
		newObj.setPriority(Priority.toEnum(obj.getPriority())); 
		newObj.setStatus(Status.toEnum(obj.getStatus()));
		
		Technician tec = technicianService.findById(obj.getTechnician());
		Client cli = clientService.findById(obj.getClient());
		
		newObj.setTechnician(tec);
		newObj.setClient(cli);
		
		if(newObj.getStatus().getCod().equals(2)) {
			newObj.setClosureDate(LocalDateTime.now());
		}
		
		return repository.save(newObj);
	}

}
