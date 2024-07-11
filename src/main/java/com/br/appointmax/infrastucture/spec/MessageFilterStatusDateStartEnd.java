package com.br.appointmax.infrastucture.spec;

import com.br.appointmax.domain.model.Message;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.HashSet;

public class MessageFilterStatusDateStartEnd implements Specification<Message> {

    private String status;
    private LocalDateTime start;
    private LocalDateTime end;
    private Long idClient;

    public MessageFilterStatusDateStartEnd(Long idClient, String status, LocalDateTime start, LocalDateTime end) {
        this.idClient = idClient;
        this.status = status;
        this.start = start;
        this.end = end;
    }

    @Override
    public Predicate toPredicate(Root<Message> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        var predicates = new HashSet<>();

        if (idClient != null){
            predicates.add(builder.equal(root.get("client").get("id"), idClient));
        }

        if (status != null && !status.isEmpty()) {
            predicates.add(builder.equal(root.get("status"), status));
        }

        if (start != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("dataMessage"), start));
        }

        if (end != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("dataMessage"), end));
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
