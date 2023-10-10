package com.moises.os.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.moises.os.domain.Technician;

@Repository
public interface TechnicianRepository extends JpaRepository<Technician, Integer>{
	
	@Query("SELECT obj FROM Technician obj WHERE obj.cpf =:cpf")
	Technician findByCPF(@Param("cpf") String cpf);    
}
