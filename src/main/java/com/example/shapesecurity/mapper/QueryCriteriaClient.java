package com.example.shapesecurity.mapper;

import com.example.shapesecurity.model.FilterRequest;
import lombok.Data;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import java.util.function.Consumer;
import java.util.function.Predicate;

@Data
public class QueryCriteriaClient implements Consumer<FilterRequest> {
    private Predicate predicate;
    private CriteriaBuilder builder;
    private Root r;

    //wymyślic jakieś jakby castowanie lub cos
    @Override
    public void accept(FilterRequest param) {
//        if (param.getSideFrom() != null ) {
//            predicate = builder.and(predicate, builder
//                    .greaterThanOrEqualTo(r.get(param.getKey()),
//                            param.getValue().toString()));
//        } else if (param.getOperation().equalsIgnoreCase("<")) {
//            predicate = builder.and(predicate, builder.lessThanOrEqualTo(
//                    r.get(param.getKey()), param.getValue().toString()));
//        } else if (param.getOperation().equalsIgnoreCase(":")) {
//            if (r.get(param.getKey()).getJavaType() == String.class) {
//                predicate = builder.and(predicate, builder.like(
//                        r.get(param.getKey()), "%" + param.getValue() + "%"));
//            } else {
//                predicate = builder.and(predicate, builder.equal(
//                        r.get(param.getKey()), param.getValue()));
            }
        }
//    }
//}
