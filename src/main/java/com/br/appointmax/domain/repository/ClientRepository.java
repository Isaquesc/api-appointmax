package com.br.appointmax.domain.repository;

import com.br.appointmax.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByCpf(String cpf);
    Optional<Client> findByAddress_City(String email);
    Optional<Client> findByPhone(String phone);
}
