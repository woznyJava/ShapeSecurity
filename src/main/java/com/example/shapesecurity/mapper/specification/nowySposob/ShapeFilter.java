package com.example.shapesecurity.mapper.specification.nowySposob;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShapeFilter {
//
//    private Map<String, ShapeSpecification> specifications;
//    private final ShapeRepository shapeRepository;
//    private final ShapeViewRepository shapeViewRepository;
//
//    @Autowired
//    public void ShapeSpecificationBuilder(Map<String, ShapeSpecification> specifications) {
//        this.specifications = specifications;
//    }
//
//    public Specification<Shape> buildSpecification(FilterRequest filterRequest) {
//        List<Specification<Shape>> specs = new ArrayList<>();
//        for (Map.Entry<String, ShapeSpecification> entry : specifications.entrySet()) {
//            String beanName = entry.getKey();
//            ShapeSpecification spec = entry.getValue();
//            Object value = getFieldValue(filterRequest, beanName);
//            if (value != null) {
//                specs.add(spec.toSpecification((FilterRequest) value));
//            }
//        }
//        return specs.isEmpty() ? null : specs.stream().reduce((s1, s2) -> s1.and(s2)).get();
//    }
//
//    private Object getFieldValue(FilterRequest filterRequest, String fieldName) {
//        try {
//            Field field = filterRequest.getClass().getDeclaredField(fieldName);
//            field.setAccessible(true);
//            return field.get(filterRequest);
//        } catch (Exception ex) {
//            return null;
//        }
//    }
//    public List<Shape> filterShapes(FilterRequest filterRequest, Pageable pageable) {
////        Specification<ShapeView> shapeViewSpecification = Specifications.where(null);
////        for (ShapeSpecification shapeSpecification : shapeSpecifications) {
////            shapeViewSpecification = shapeViewSpecification.and(shapeSpecification.toSpecification(filterRequest));
////        }
////        List<ShapeView> filteredShapeViews = shapeViewRepository.findAll(shapeViewSpecification, pageable).getContent();
////        List<Shape> filteredShapes = new ArrayList<>();
////        for (ShapeView shapeView : filteredShapeViews) {
////            filteredShapes.add(shapeView.getShape());
////        }
////        return filteredShapes;
//        return null;
//    }

}