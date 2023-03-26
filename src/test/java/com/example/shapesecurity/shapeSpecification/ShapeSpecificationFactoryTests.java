package com.example.shapesecurity.shapeSpecification;

import com.example.shapesecurity.model.FilterRequest;
import com.example.shapesecurity.model.shape.ShapeView;
import com.example.shapesecurity.service.impl.ShapeSpecificationFactoryImpl;
import com.example.shapesecurity.strategy.filter.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class ShapeSpecificationFactoryTests {

    private ShapeSpecificationFactoryImpl shapeSpecificationFactory;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        Map<String, ShapeSpecification<?>> shapeSpecificationMap = new HashMap<>();
        shapeSpecificationMap.put("areaFrom", new AreaFrom());
        shapeSpecificationMap.put("areaTo", new AreaTo());
        shapeSpecificationMap.put("createdAtFrom", new CreatedAtFrom());
        shapeSpecificationMap.put("createdAtTo", new CreatedAtTo());
        shapeSpecificationMap.put("createdBy", new CreatedBy());
        shapeSpecificationMap.put("heightFrom", new HeightFrom());
        shapeSpecificationMap.put("heightTo", new HeightTo());
        shapeSpecificationMap.put("perimeterFrom", new PerimeterFrom());
        shapeSpecificationMap.put("perimeterTo", new PerimeterTo());
        shapeSpecificationMap.put("radiusFrom", new RadiusFrom());
        shapeSpecificationMap.put("radiusTo", new RadiusTo());
        shapeSpecificationMap.put("shapeType", new ShapeType());
        shapeSpecificationMap.put("sideFrom", new SideFrom());
        shapeSpecificationMap.put("sideTo", new SideTo());
        shapeSpecificationMap.put("version", new Version());
        shapeSpecificationMap.put("widthFrom", new WidthFrom());
        shapeSpecificationMap.put("widthTo", new WidthTo());
        shapeSpecificationFactory = new ShapeSpecificationFactoryImpl(shapeSpecificationMap);
    }

    @Test
    public void testCreateSpecifications_All() {

        Map<String, Object> shapeSpecificationMap = new HashMap<>();
        shapeSpecificationMap.put("areaFrom", 10.0);
        shapeSpecificationMap.put("areaTo", 10.0);
        shapeSpecificationMap.put("createdAtFrom", LocalDateTime.now());
        shapeSpecificationMap.put("createdAtTo", LocalDateTime.now());
        shapeSpecificationMap.put("createdBy", "test");
        shapeSpecificationMap.put("heightFrom", 10.0);
        shapeSpecificationMap.put("heightTo", 10.0);
        shapeSpecificationMap.put("perimeterFrom", 10.0);
        shapeSpecificationMap.put("perimeterTo", 10.0);
        shapeSpecificationMap.put("radiusFrom", 10.0);
        shapeSpecificationMap.put("radiusTo", 10.0);
        shapeSpecificationMap.put("shapeType", "CIRCLE");
        shapeSpecificationMap.put("sideFrom", 10.0);
        shapeSpecificationMap.put("sideTo", 10.0);
        shapeSpecificationMap.put("version", 1);
        shapeSpecificationMap.put("widthFrom", 10.0);
        shapeSpecificationMap.put("widthTo", 10.0);
        FilterRequest filterRequest = new FilterRequest(shapeSpecificationMap);

        List<Specification<ShapeView>> specifications = shapeSpecificationFactory.createSpecifications(filterRequest);
        assertTrue(specifications.size() == 17);

    }

    @Test
    public void testCreateSpecifications_AreaFrom() {

        Map<String, Object> filterMap = new HashMap<>();
        filterMap.put("areaFrom", 10.0);
        FilterRequest filterRequest = new FilterRequest(filterMap);

        List<Specification<ShapeView>> specifications = shapeSpecificationFactory.createSpecifications(filterRequest);

        assertTrue(specifications.get(0).getClass().getSimpleName().startsWith("AreaFrom"));
    }

    @Test
    public void testCreateSpecifications_AreaTo() {

        Map<String, Object> filterMap = new HashMap<>();
        filterMap.put("areaTo", 10.0);
        FilterRequest filterRequest = new FilterRequest(filterMap);

        List<Specification<ShapeView>> specifications = shapeSpecificationFactory.createSpecifications(filterRequest);

        assertTrue(specifications.get(0).getClass().getSimpleName().startsWith("AreaTo"));
    }

    @Test
    public void testCreateSpecifications_CreatedAtFrom() {

        Map<String, Object> filterMap = new HashMap<>();
        filterMap.put("createdAtFrom", LocalDateTime.now());
        FilterRequest filterRequest = new FilterRequest(filterMap);

        List<Specification<ShapeView>> specifications = shapeSpecificationFactory.createSpecifications(filterRequest);

        assertTrue(specifications.get(0).getClass().getSimpleName().startsWith("CreatedAtFrom"));
    }

    @Test
    public void testCreateSpecifications_CreatedAtTo() {

        Map<String, Object> filterMap = new HashMap<>();
        filterMap.put("createdAtTo", LocalDateTime.now());
        FilterRequest filterRequest = new FilterRequest(filterMap);

        List<Specification<ShapeView>> specifications = shapeSpecificationFactory.createSpecifications(filterRequest);

        assertTrue(specifications.get(0).getClass().getSimpleName().startsWith("CreatedAtTo"));
    }

    @Test
    public void testCreateSpecifications_CreatedBy() {

        Map<String, Object> filterMap = new HashMap<>();
        filterMap.put("createdBy", "test");
        FilterRequest filterRequest = new FilterRequest(filterMap);

        List<Specification<ShapeView>> specifications = shapeSpecificationFactory.createSpecifications(filterRequest);

        assertTrue(specifications.get(0).getClass().getSimpleName().startsWith("CreatedBy"));
    }

    @Test
    public void testCreateSpecifications_HeightFrom() {

        Map<String, Object> filterMap = new HashMap<>();
        filterMap.put("heightFrom", 10.0);
        FilterRequest filterRequest = new FilterRequest(filterMap);

        List<Specification<ShapeView>> specifications = shapeSpecificationFactory.createSpecifications(filterRequest);

        assertTrue(specifications.get(0).getClass().getSimpleName().startsWith("HeightFrom"));
    }

    @Test
    public void testCreateSpecifications_HeightTo() {

        Map<String, Object> filterMap = new HashMap<>();
        filterMap.put("heightTo", 10.0);
        FilterRequest filterRequest = new FilterRequest(filterMap);

        List<Specification<ShapeView>> specifications = shapeSpecificationFactory.createSpecifications(filterRequest);

        assertTrue(specifications.get(0).getClass().getSimpleName().startsWith("HeightTo"));
    }

    @Test
    public void testCreateSpecifications_PerimeterFrom() {

        Map<String, Object> filterMap = new HashMap<>();
        filterMap.put("perimeterFrom", 10.0);
        FilterRequest filterRequest = new FilterRequest(filterMap);

        List<Specification<ShapeView>> specifications = shapeSpecificationFactory.createSpecifications(filterRequest);

        assertTrue(specifications.get(0).getClass().getSimpleName().startsWith("PerimeterFrom"));
    }

    @Test
    public void testCreateSpecifications_PerimeterTo() {

        Map<String, Object> filterMap = new HashMap<>();
        filterMap.put("perimeterTo", 10.0);
        FilterRequest filterRequest = new FilterRequest(filterMap);

        List<Specification<ShapeView>> specifications = shapeSpecificationFactory.createSpecifications(filterRequest);

        assertTrue(specifications.get(0).getClass().getSimpleName().startsWith("PerimeterTo"));
    }

    @Test
    public void testCreateSpecifications_RadiusFrom() {

        Map<String, Object> filterMap = new HashMap<>();
        filterMap.put("radiusFrom", 10.0);
        FilterRequest filterRequest = new FilterRequest(filterMap);

        List<Specification<ShapeView>> specifications = shapeSpecificationFactory.createSpecifications(filterRequest);

        assertTrue(specifications.get(0).getClass().getSimpleName().startsWith("RadiusFrom"));
    }

    @Test
    public void testCreateSpecifications_RadiusTo() {

        Map<String, Object> filterMap = new HashMap<>();
        filterMap.put("radiusTo", 10.0);
        FilterRequest filterRequest = new FilterRequest(filterMap);

        List<Specification<ShapeView>> specifications = shapeSpecificationFactory.createSpecifications(filterRequest);

        assertTrue(specifications.get(0).getClass().getSimpleName().startsWith("RadiusTo"));
    }

    @Test
    public void testCreateSpecifications_ShapeType() {

        Map<String, Object> filterMap = new HashMap<>();
        filterMap.put("shapeType", "CIRCLE");
        FilterRequest filterRequest = new FilterRequest(filterMap);

        List<Specification<ShapeView>> specifications = shapeSpecificationFactory.createSpecifications(filterRequest);

        assertTrue(specifications.get(0).getClass().getSimpleName().startsWith("ShapeType"));
    }

    @Test
    public void testCreateSpecifications_SideFrom() {

        Map<String, Object> filterMap = new HashMap<>();
        filterMap.put("sideFrom", 10.0);
        FilterRequest filterRequest = new FilterRequest(filterMap);

        List<Specification<ShapeView>> specifications = shapeSpecificationFactory.createSpecifications(filterRequest);

        assertTrue(specifications.get(0).getClass().getSimpleName().startsWith("SideFrom"));
    }

    @Test
    public void testCreateSpecifications_SideTo() {

        Map<String, Object> filterMap = new HashMap<>();
        filterMap.put("sideTo", 10.0);
        FilterRequest filterRequest = new FilterRequest(filterMap);

        List<Specification<ShapeView>> specifications = shapeSpecificationFactory.createSpecifications(filterRequest);

        assertTrue(specifications.get(0).getClass().getSimpleName().startsWith("SideTo"));
    }

    @Test
    public void testCreateSpecifications_Version() {

        Map<String, Object> filterMap = new HashMap<>();
        filterMap.put("version", 1);
        FilterRequest filterRequest = new FilterRequest(filterMap);

        List<Specification<ShapeView>> specifications = shapeSpecificationFactory.createSpecifications(filterRequest);

        assertTrue(specifications.get(0).getClass().getSimpleName().startsWith("Version"));
    }

    @Test
    public void testCreateSpecifications_WidthFrom() {

        Map<String, Object> filterMap = new HashMap<>();
        filterMap.put("widthFrom", 10.0);
        FilterRequest filterRequest = new FilterRequest(filterMap);

        List<Specification<ShapeView>> specifications = shapeSpecificationFactory.createSpecifications(filterRequest);

        assertTrue(specifications.get(0).getClass().getSimpleName().startsWith("WidthFrom"));
    }

    @Test
    public void testCreateSpecifications_WidthTo() {

        Map<String, Object> filterMap = new HashMap<>();
        filterMap.put("widthTo", 10.0);
        FilterRequest filterRequest = new FilterRequest(filterMap);

        List<Specification<ShapeView>> specifications = shapeSpecificationFactory.createSpecifications(filterRequest);

        assertTrue(specifications.get(0).getClass().getSimpleName().startsWith("WidthTo"));
    }
}

