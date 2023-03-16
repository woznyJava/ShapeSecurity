package com.example.shapesecurity.mapper.specification.yyy;

//@Component
//public class ShapeSpecificationsBuilder {
//
//    public static Specification<ShapeView> createSpecification(FilterRequest filterRequest) {
//        return (root, query, cb) -> {
//            List<Predicate> predicates = new ArrayList<>();
//            Field[] fields = filterRequest.getClass().getDeclaredFields();
//            for (Field field : fields) {
//                try {
//                    field.setAccessible(true);
//                    Object value = field.get(filterRequest);
//                    if (value != null) {
//                        if (field.getType().equals(Double.class)) {
//                            predicates.add((Predicate) cb.equal(root.get(field.getName()), value));
//                        } else if (field.getType().equals(String.class)) {
//                            predicates.add((Predicate) cb.like(root.get(field.getName()), "%" + value.toString() + "%"));
//                        } else if (field.getType().equals(LocalDateTime.class)) {
//                            LocalDateTime localDateTime = (LocalDateTime) value;
//                            predicates.add((Predicate) cb.between(root.get(field.getName()), localDateTime, localDateTime.plusDays(1)));
//                        } else {
//                            // handle other types if needed
//                        }
//                    }
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                }
//            }
//            return cb.and((javax.persistence.criteria.Predicate) predicates.toArray(new Predicate[predicates.size()]));
//        };
//    }
//}