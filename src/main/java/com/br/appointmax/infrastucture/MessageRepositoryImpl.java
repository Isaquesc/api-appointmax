package com.br.appointmax.infrastucture;

import com.br.appointmax.domain.model.Message;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

@Repository
public class MessageRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Message> getFilterMessages(String status, LocalDateTime start, LocalDateTime end) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder(); // Cria um CriteriaBuilder para construir a consulta
        CriteriaQuery<Message> criteriaQuery = builder.createQuery(Message.class); // Cria uma CriteriaQuery para a entidade Message
        Root<Message> root = criteriaQuery.from(Message.class); // Define a raiz da consulta (a partir da entidade Message)

        var predicates = new HashSet<Predicate>(); // Conjunto de predicados para armazenar as condições da consulta

        if (StringUtils.hasLength(status)) {
            predicates.add(builder.equal(root.get("status"), status)); // Adiciona um predicado para o status, se não for nulo e não estiver vazio
        }

        if (start != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("dataMessage"), start)); // Adiciona um predicado para a data inicial, se não for nula
        }

        if (end != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("dataMessage"), end)); // Adiciona um predicado para a data final, se não for nula
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0])); // Define os predicados na consulta
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
