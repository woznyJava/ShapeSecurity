package com.example.shapesecurity.shapeMapper;

import com.example.shapesecurity.model.FilterRequest;
import com.example.shapesecurity.model.shape.ShapeView;
import com.example.shapesecurity.strategy.filter.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class ShapeSpecification {

    @InjectMocks
    private AreaFrom areaFrom;
    @InjectMocks
    private AreaTo areaTo;
    @InjectMocks
    private CreatedAtFrom createdAtFrom;
    @InjectMocks
    private CreatedAtTo createdAtTo;
    @InjectMocks
    private CreatedBy createdBy;
    @InjectMocks
    private HeightFrom heightFrom;
    @InjectMocks
    private HeightTo heightTo;
    @InjectMocks
    private PerimeterFrom perimeterFrom;
    @InjectMocks
    private PerimeterTo perimeterTo;
    @InjectMocks
    private RadiusFrom radiusFrom;
    @InjectMocks
    private RadiusTo radiusTo;
    @InjectMocks
    private ShapeType shapeType;
    @InjectMocks
    private SideFrom sideFrom;
    @InjectMocks
    private SideTo sideTo;
    @InjectMocks
    private Version version;
    @InjectMocks
    private WidthFrom widthFrom;
    @InjectMocks
    private WidthTo widthTo;

    @Test
    public void testToSpecification_RadiusFrom() {
        Map<String, Object> map = new HashMap<>();
        map.put("radiusFrom", 5.0);
        FilterRequest filterRequest = new FilterRequest(map);

        Specification<ShapeView> specification = radiusFrom.toSpecification(filterRequest);

        assertNotNull(specification);
    }

    @Test
    public void toSpecification_WithoutRadiusFrom_ShouldReturnNull_RadiusFrom() {
        Map<String, Object> map = new HashMap<>();
        FilterRequest filterRequest = new FilterRequest(map);

        Specification<ShapeView> specification = radiusFrom.toSpecification(filterRequest);

        assertNull(specification);
    }

    @Test
    public void testToSpecification_AreaFrom() {
        Map<String, Object> map = new HashMap<>();
        map.put("areaFrom", 5.0);
        FilterRequest filterRequest = new FilterRequest(map);

        Specification<ShapeView> specification = areaFrom.toSpecification(filterRequest);

        assertNotNull(specification);
    }

    @Test
    public void toSpecification_WithoutAreaFrom_ShouldReturnNull_AreaFrom() {
        Map<String, Object> map = new HashMap<>();
        FilterRequest filterRequest = new FilterRequest(map);

        Specification<ShapeView> specification = areaFrom.toSpecification(filterRequest);

        assertNull(specification);
    }

    @Test
    public void testToSpecification_AreaTo() {
        Map<String, Object> map = new HashMap<>();
        map.put("areaTo", 5.0);
        FilterRequest filterRequest = new FilterRequest(map);

        Specification<ShapeView> specification = areaTo.toSpecification(filterRequest);

        assertNotNull(specification);
    }

    @Test
    public void toSpecification_WithoutRadiusFrom_ShouldReturnNull_AreaTo() {
        Map<String, Object> map = new HashMap<>();
        FilterRequest filterRequest = new FilterRequest(map);

        Specification<ShapeView> specification = areaTo.toSpecification(filterRequest);

        assertNull(specification);
    }

    @Test
    public void testToSpecification_createdAtFrom() {
        Map<String, Object> map = new HashMap<>();
        map.put("createdAtFrom", LocalDateTime.now());
        FilterRequest filterRequest = new FilterRequest(map);

        Specification<ShapeView> specification = createdAtFrom.toSpecification(filterRequest);

        assertNotNull(specification);
    }

    @Test
    public void toSpecification_WithoutCreatedAtTo_ShouldReturnNull_CreatedAtFrom() {
        Map<String, Object> map = new HashMap<>();
        FilterRequest filterRequest = new FilterRequest(map);

        Specification<ShapeView> specification = createdAtFrom.toSpecification(filterRequest);

        assertNull(specification);
    }

    @Test
    public void testToSpecification_createdAtTo() {
        Map<String, Object> map = new HashMap<>();
        map.put("createdAtTo", LocalDateTime.now());
        FilterRequest filterRequest = new FilterRequest(map);

        Specification<ShapeView> specification = createdAtTo.toSpecification(filterRequest);

        assertNotNull(specification);
    }

    @Test
    public void toSpecification_WithoutCreatedAtTo_ShouldReturnNull_CreatedAtTo() {
        Map<String, Object> map = new HashMap<>();
        FilterRequest filterRequest = new FilterRequest(map);

        Specification<ShapeView> specification = createdAtTo.toSpecification(filterRequest);

        assertNull(specification);
    }

    @Test
    public void testToSpecification_CreatedBy() {
        Map<String, Object> map = new HashMap<>();
        map.put("createdBy", "test@gmail.com");
        FilterRequest filterRequest = new FilterRequest(map);

        Specification<ShapeView> specification = createdBy.toSpecification(filterRequest);

        assertNotNull(specification);
    }

    @Test
    public void toSpecification_WithoutCreatedByShouldReturnNull_CreatedBy() {
        Map<String, Object> map = new HashMap<>();
        FilterRequest filterRequest = new FilterRequest(map);

        Specification<ShapeView> specification = createdBy.toSpecification(filterRequest);

        assertNull(specification);
    }

    @Test
    public void testToSpecification_HeightFrom() {
        Map<String, Object> map = new HashMap<>();
        map.put("heightFrom", 5.0);
        FilterRequest filterRequest = new FilterRequest(map);

        Specification<ShapeView> specification = heightFrom.toSpecification(filterRequest);

        assertNotNull(specification);
    }

    @Test
    public void toSpecification_WithoutHeightFrom_ShouldReturnNull_HeightFrom() {
        Map<String, Object> map = new HashMap<>();
        FilterRequest filterRequest = new FilterRequest(map);

        Specification<ShapeView> specification = heightFrom.toSpecification(filterRequest);

        assertNull(specification);
    }

    @Test
    public void testToSpecification_HeightTo() {
        Map<String, Object> map = new HashMap<>();
        map.put("heightTo", 5.0);
        FilterRequest filterRequest = new FilterRequest(map);

        Specification<ShapeView> specification = heightTo.toSpecification(filterRequest);

        assertNotNull(specification);
    }

    @Test
    public void toSpecification_WithoutHeightTo_ShouldReturnNull_HeightTo() {
        Map<String, Object> map = new HashMap<>();
        FilterRequest filterRequest = new FilterRequest(map);

        Specification<ShapeView> specification = heightTo.toSpecification(filterRequest);

        assertNull(specification);
    }

    @Test
    public void testToSpecification_PerimeterFrom() {
        Map<String, Object> map = new HashMap<>();
        map.put("perimeterFrom", 5.0);
        FilterRequest filterRequest = new FilterRequest(map);

        Specification<ShapeView> specification = perimeterFrom.toSpecification(filterRequest);

        assertNotNull(specification);
    }

    @Test
    public void toSpecification_WithoutPerimeterFrom_ShouldReturnNull_PerimeterFrom() {
        Map<String, Object> map = new HashMap<>();
        FilterRequest filterRequest = new FilterRequest(map);

        Specification<ShapeView> specification = perimeterFrom.toSpecification(filterRequest);

        assertNull(specification);
    }

    @Test
    public void testToSpecification_PerimeterTo() {
        Map<String, Object> map = new HashMap<>();
        map.put("perimeterTo", 5.0);
        FilterRequest filterRequest = new FilterRequest(map);

        Specification<ShapeView> specification = perimeterTo.toSpecification(filterRequest);

        assertNotNull(specification);
    }

    @Test
    public void toSpecification_WithoutPerimeterTo_ShouldReturnNull_PerimeterTo() {
        Map<String, Object> map = new HashMap<>();
        FilterRequest filterRequest = new FilterRequest(map);

        Specification<ShapeView> specification = perimeterTo.toSpecification(filterRequest);

        assertNull(specification);
    }

    @Test
    public void testToSpecification_RadiusTo() {
        Map<String, Object> map = new HashMap<>();
        map.put("radiusTo", 5.0);
        FilterRequest filterRequest = new FilterRequest(map);

        Specification<ShapeView> specification = radiusTo.toSpecification(filterRequest);

        assertNotNull(specification);
    }

    @Test
    public void toSpecification_WithoutRadiusTo_ShouldReturnNull_RadiusTo() {
        Map<String, Object> map = new HashMap<>();
        FilterRequest filterRequest = new FilterRequest(map);

        Specification<ShapeView> specification = radiusTo.toSpecification(filterRequest);

        assertNull(specification);
    }

    @Test
    public void testToSpecification_ShapeType() {
        Map<String, Object> map = new HashMap<>();
        map.put("shapeType", "CIRCLE");
        FilterRequest filterRequest = new FilterRequest(map);

        Specification<ShapeView> specification = shapeType.toSpecification(filterRequest);

        assertNotNull(specification);
    }

    @Test
    public void toSpecification_WithoutShapeType_ShouldReturnNull_ShapeType() {
        Map<String, Object> map = new HashMap<>();
        FilterRequest filterRequest = new FilterRequest(map);

        Specification<ShapeView> specification = shapeType.toSpecification(filterRequest);

        assertNull(specification);
    }

    @Test
    public void testToSpecification_SideFrom() {
        Map<String, Object> map = new HashMap<>();
        map.put("sideFrom", 5.0);
        FilterRequest filterRequest = new FilterRequest(map);

        Specification<ShapeView> specification = sideFrom.toSpecification(filterRequest);

        assertNotNull(specification);
    }

    @Test
    public void toSpecification_WithoutSideFrom_ShouldReturnNull_SideFrom() {
        Map<String, Object> map = new HashMap<>();
        FilterRequest filterRequest = new FilterRequest(map);

        Specification<ShapeView> specification = sideFrom.toSpecification(filterRequest);

        assertNull(specification);
    }

    @Test
    public void testToSpecification_SideTo() {
        Map<String, Object> map = new HashMap<>();
        map.put("sideTo", 5.0);
        FilterRequest filterRequest = new FilterRequest(map);


        Specification<ShapeView> specification = sideTo.toSpecification(filterRequest);

        assertNotNull(specification);
    }

    @Test
    public void toSpecification_WithoutSide_ShouldReturnNull_SideTo() {
        Map<String, Object> map = new HashMap<>();
        FilterRequest filterRequest = new FilterRequest(map);

        Specification<ShapeView> specification = sideTo.toSpecification(filterRequest);

        assertNull(specification);
    }

    @Test
    public void testToSpecification_Version() {
        Map<String, Object> map = new HashMap<>();
        map.put("version", 1);
        FilterRequest filterRequest = new FilterRequest(map);

        Specification<ShapeView> specification = version.toSpecification(filterRequest);

        assertNotNull(specification);
    }

    @Test
    public void toSpecification_WithoutVersion_ShouldReturnNull_Version() {
        Map<String, Object> map = new HashMap<>();
        FilterRequest filterRequest = new FilterRequest(map);

        Specification<ShapeView> specification = version.toSpecification(filterRequest);

        assertNull(specification);
    }

    @Test
    public void testToSpecification_WidthFrom() {
        Map<String, Object> map = new HashMap<>();
        map.put("widthFrom", 5.0);
        FilterRequest filterRequest = new FilterRequest(map);

        Specification<ShapeView> specification = widthFrom.toSpecification(filterRequest);

        assertNotNull(specification);
    }

    @Test
    public void toSpecification_WithoutWidthFrom_ShouldReturnNull_WidthFrom() {
        Map<String, Object> map = new HashMap<>();
        FilterRequest filterRequest = new FilterRequest(map);

        Specification<ShapeView> specification = widthFrom.toSpecification(filterRequest);

        assertNull(specification);
    }

    @Test
    public void testToSpecification_WidthTo() {
        Map<String, Object> map = new HashMap<>();
        map.put("widthTo", 5.0);
        FilterRequest filterRequest = new FilterRequest(map);

        Specification<ShapeView> specification = widthTo.toSpecification(filterRequest);

        assertNotNull(specification);
    }

    @Test
    public void toSpecification_WithoutWidthTo_ShouldReturnNull_WidthTo() {
        Map<String, Object> map = new HashMap<>();
        FilterRequest filterRequest = new FilterRequest(map);

        Specification<ShapeView> specification = widthTo.toSpecification(filterRequest);

        assertNull(specification);
    }
}





