package com.moises.os.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moises.os.domain.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{    

}
