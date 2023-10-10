package com.moises.os.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moises.os.domain.Client;
import com.moises.os.domain.OS;
import com.moises.os.domain.Technician;
import com.moises.os.domain.enuns.Priority;
import com.moises.os.domain.enuns.Status;
import com.moises.os.repositories.ClientRepository;
import com.moises.os.repositories.OSRepository;
import com.moises.os.repositories.TechnicianRepository;

@Service
public class DBService {

	@Autowired
	private TechnicianRepository technicianRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private OSRepository osRepository;

	
	public void instantiateDB() {
		Technician t1 = new Technician(null, "Fulano de Tal", "782.590.534-20", "(81) 3428-5509");
		Technician t2 = new Technician(null, "Beltrano de Tal", "641.760.040-88", "(81) 3428-5509");
		Client c1 = new Client(null, "Cicrano de Tal", "598.508.200-80", "(81) 3428-5510");

		OS os1 = new OS(null, Priority.ALTA, "Teste create DB", Status.ANDAMENTO, t1, c1);

		 t1.getList().add(os1);
		 c1.getList().add(os1);

		technicianRepository.saveAll(Arrays.asList(t1, t2));
		clientRepository.saveAll(Arrays.asList(c1));
		osRepository.saveAll(Arrays.asList(os1));

	}

}
