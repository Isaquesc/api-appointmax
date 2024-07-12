package com.br.appointmax.infrastucture.spec;

import com.br.appointmax.domain.model.Message;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MessageSpecification {

    public static Specification<Message> filterStatusDateStartEnd(Long idClient, String status, LocalDateTime start, LocalDateTime end) {
        return (root, query, builder) -> {
            var predicates = new ArrayList<Predicate>();

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
        };
    }
}
