package com.br.appointmax.domain.repository;

import com.br.appointmax.domain.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByClientId(Long clientId);
    List<Message> findByClientIdAndStatus(Long clientId, String status);
    List<Message> getFilterMessages(String status, LocalDateTime start, LocalDateTime end);

}
