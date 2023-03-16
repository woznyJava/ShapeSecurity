package com.example.shapesecurity.mapper.specification.yyy;

//@Component
//public class ShapeSpecificationFactory {
//    private final Map<String, ShapeSpecification> specificationMap;
//
//    @Autowired
//    public ShapeSpecificationFactory(List<ShapeSpecification> specificationList) {
//        this.specificationMap = specificationList.stream()
//                .collect(Collectors.toMap(spec -> spec.getClass().getSimpleName(), Function.identity()));
//    }
//
//    public Specification<Shape> createSpecification(FilterRequest filterRequest) {
//        List<Specification<Shape>> specifications = new ArrayList<>();
//
//        Field[] fields = filterRequest.getClass().getDeclaredFields();
//        for (Field field : fields) {
//            field.setAccessible(true);
//            try {
//                Object value = field.get(filterRequest);
//                if (value != null) {
//                    ShapeSpecification specification = specificationMap.get(field.getName());
//                    if (specification != null) {
//                        specifications.add(specification.toSpecification((FilterRequest) value));
//                    }
//                }
//            } catch (IllegalAccessException e) {
//                // handle exception
//            }
//        }
//
////        return Specifications.where(specifications.stream().reduce(Specifications::and).orElse(null));
//        return null;
//    }
//}
////    ShapeSpecificationFactory to fabryka, która tworzy specyfikacje na podstawie wartości w FilterRequest. Konstruktor klasy wykorzystuje wstrzykiwanie zależności do zbudowania mapy, która przechowuje instancje specyfikacji, indeksowane po nazwie klasy. Następnie, w metodzie createSpecification, iterujemy po polach w FilterRequest za pomocą refleksji i pobieramy wartość pola. Jeśli wartość istnieje, szukamy specyfikacji o takiej samej nazwie w mapie i tworzymy specyfikację dla tej wartości. Na koniec łączymy wszystkie specyfikacje za pomocą metody and i tworzymy nową specyfikację za pomocą klasy Specifications.
////
////        Zauważ, że w powyższym przykładzie wykorzystano klasę Specifications, która nie jest dostępna w standardowym API JPA, ale jest często używana w bibliotekach takich jak Spring Data JPA. Jeśli nie chcesz korzystać z tej klasy, możesz zamiast tego zastosować CriteriaBuilder do tworzenia specyfikacji.
//
//
//
//
//
