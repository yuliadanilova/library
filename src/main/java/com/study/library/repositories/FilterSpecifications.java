package com.study.library.repositories;

import com.study.library.dto.BookFilterDto;
import com.study.library.entities.BookEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class FilterSpecifications {
    public static Specification<BookEntity> byFilter(BookFilterDto bookFilterDto) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (!StringUtils.isEmpty(bookFilterDto.getName())) {
                predicates.add(criteriaBuilder.equal(criteriaBuilder.upper(root.get("name")), bookFilterDto.getName()));
            }

            if (bookFilterDto.getCount() != null ) {
                predicates.add(criteriaBuilder.equal(root.get("count"), bookFilterDto.getCount()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[] {}));
        };
    }
}
