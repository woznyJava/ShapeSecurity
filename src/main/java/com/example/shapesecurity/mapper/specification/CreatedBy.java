package com.example.shapesecurity.mapper.specification;

import com.example.shapesecurity.model.FilterRequest;
import com.example.shapesecurity.model.shape.ShapeView;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component("createdBy")
public class CreatedBy implements ShapeSpecification {
    @Override
    public Specification<ShapeView> toSpecification(FilterRequest filterRequest) {
        return filterRequest.getMap().get("createdBy") != null ? (root, query, cb) -> cb.equal(root.get("createdBy"), filterRequest.getMap().get("createdBy").toString()) : null;

        //query.where(filterRequest.getWidthTo()
        // != null ? qShapeView.shape.type.in("RECTANGLE").and(qShapeView.width.loe(filterRequest.getWidthTo())) : null);
    }

    @Override
    public String getSupportedField() {
        return "CreatedBy";
    }
}
//@FunctionalInterface
//public interface ShapeSpecification {
//    Specification<Shape> toSpecification(Object value);
//}
//
//@Component("CREATEDBYSPECIFICATION")
//public class CreatedBySpecification implements ShapeSpecification {
//    @Override
//    public Specification<Shape> toSpecification(Object value) {
//        return (root, query, cb) -> cb.equal(root.get("createdBy"), value);
//    }
//}
//
//@Component("TYPESPECIFICATION")
//public class TypeSpecification implements ShapeSpecification {
//    @Override
//    public Specification<Shape> toSpecification(Object value) {
//        return (root, query, cb) -> cb.equal(root.get("type"), value);
//    }
//}
//
//// i tak dalej dla pozostałych specyfikacji
//
//@Component
//public class ShapeFilter {
//
//    private final Map<String, ShapeSpecification> shapeSpecifications;
//
//    public ShapeFilter(List<ShapeSpecification> specifications) {
//        this.shapeSpecifications = specifications.stream()
//            .collect(Collectors.toMap(s -> s.getClass().getSimpleName().toUpperCase(Locale.ROOT),
//                                      Function.identity()));
//    }
//
//    public List<ShapeDto> filterShapes(FilterRequest filterRequest) {
//        List<Specification<Shape>> specs = new ArrayList<>();
//        Field[] fields = filterRequest.getClass().getDeclaredFields();
//        for (Field field : fields) {
//            try {
//                Object value = field.get(filterRequest);
//                if (value != null) {
//                    ShapeSpecification spec = shapeSpecifications.get(field.getName().toUpperCase(Locale.ROOT));
//                    Specification<Shape> shapeSpec = spec.toSpecification(value);
//                    specs.add(shapeSpec);
//                }
//            } catch (IllegalAccessException e) {
//                // obsłuż wyjątek
//            }
//        }
//        Specification<Shape> finalSpec = specs.stream().reduce(Specification::and).orElse(null);
//        // reszta kodu
//    }
//}