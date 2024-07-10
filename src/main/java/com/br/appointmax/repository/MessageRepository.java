package com.br.appointmax.repository;

import com.br.appointmax.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByClientId(Long clientId);
    List<Message> findByStatus(String status);
    List<Message> findByClientIdAndStatus(Long clientId, String status);
    List<Message> findByDataMessageBetween(LocalDateTime start, LocalDateTime end);
    List<Message> findByStatusAndDataMessageBetween(String status, LocalDateTime start, LocalDateTime end);

}
