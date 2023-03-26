package com.example.shapesecurity.shapeService;

import com.example.shapesecurity.model.FilterRequest;
import com.example.shapesecurity.model.command.CreateShapeCommand;
import com.example.shapesecurity.model.dto.ShapeDto;
import com.example.shapesecurity.model.shape.*;
import com.example.shapesecurity.repository.ShapeRepository;
import com.example.shapesecurity.repository.ShapeViewRepository;
import com.example.shapesecurity.service.ShapeBuildService;
import com.example.shapesecurity.service.ShapeSpecificationFactory;
import com.example.shapesecurity.service.impl.ShapeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShapeServiceTests {

    @InjectMocks
    private ShapeServiceImpl shapeService;
    @Mock
    private ShapeRepository shapeRepository;
    @Mock
    private ShapeBuildService shapeBuildService;
    @Mock
    private ShapeSpecificationFactory shapeSpecificationFactoryImpl;
    @Mock
    private ShapeViewRepository shapeViewRepository;
    @Captor
    private ArgumentCaptor<Shape> shapeArgumentCaptor;

    @Test
    public void testShouldSaveShape_Circle() {

        Map<String, Double> map = new HashMap<>();
        map.put("radius", 2.0);
        CreateShapeCommand createUpdateShapeCommand = new CreateShapeCommand("CIRCLE", map);
        Circle circle = new Circle(2.0);

        when(shapeBuildService.buildShape(createUpdateShapeCommand)).thenReturn(circle);

        shapeService.save(createUpdateShapeCommand);
        verify(shapeRepository).save(shapeArgumentCaptor.capture());

        Circle circle2 = (Circle) shapeArgumentCaptor.getValue();
        assertEquals(2.0, circle2.getRadius());
        assertEquals(circle2.computeArea(), 12.56);
        assertEquals(circle2.computePerimeter(), 12.56);
    }

    @Test
    public void testShouldSaveShape_Square() {

        Map<String, Double> map = new HashMap<>();
        map.put("side", 3.0);
        CreateShapeCommand createUpdateShapeCommand = new CreateShapeCommand("SQUARE", map);
        Square square = new Square(3.0);

        when(shapeBuildService.buildShape(createUpdateShapeCommand)).thenReturn(square);

        shapeService.save(createUpdateShapeCommand);
        verify(shapeRepository).save(shapeArgumentCaptor.capture());

        Square square2 = (Square) shapeArgumentCaptor.getValue();
        assertEquals(3.0, square2.getSide());
        assertEquals(square2.computeArea(), 9.0);
        assertEquals(square2.computePerimeter(), 12.0);
    }

    @Test
    public void testShouldSaveShape_Rectangle() {

        Map<String, Double> map = new HashMap<>();
        map.put("width", 4.0);
        map.put("height", 5.0);
        CreateShapeCommand createUpdateShapeCommand = new CreateShapeCommand("RECTANGLE", map);
        Rectangle rectangle = new Rectangle(4.0, 5.0);

        when(shapeBuildService.buildShape(createUpdateShapeCommand)).thenReturn(rectangle);

        shapeService.save(createUpdateShapeCommand);
        verify(shapeRepository).save(shapeArgumentCaptor.capture());

        Rectangle rectangle2 = (Rectangle) shapeArgumentCaptor.getValue();
        assertEquals(rectangle.getWidth(), rectangle2.getWidth());
        assertEquals(rectangle.getHeight(), rectangle2.getHeight());
        assertEquals(rectangle2.computeArea(), 20.0);
        assertEquals(rectangle2.computePerimeter(), 18.0);
    }

    @Test
    public void testFilter_1() {
        FilterRequest filterRequest = new FilterRequest();
        Specification<ShapeView> spec = Specification.where(null);
        List<Specification<ShapeView>> specs = Collections.singletonList(spec);
        List<ShapeView> shapeViews = Collections.emptyList();

        when(shapeSpecificationFactoryImpl.createSpecifications(filterRequest)).thenReturn(specs);
        when(shapeViewRepository.findAll(spec)).thenReturn(shapeViews);
        when(shapeBuildService.buildShapeDtoListFromListShapeView(shapeViews)).thenReturn(Collections.emptyList());

        List<ShapeDto> result = shapeService.filter(filterRequest);

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(shapeSpecificationFactoryImpl).createSpecifications(filterRequest);
        verify(shapeViewRepository).findAll(spec);
        verify(shapeBuildService).buildShapeDtoListFromListShapeView(shapeViews);
    }

    @Test
    public void testFilter_2() {
        ShapeView shapeView = new ShapeView(1, "CIRCLE", "test", 0L, LocalDateTime.now(), LocalDateTime.now(), "test", 20.0, 40.0, 5.0, null, null, null);
        ShapeView shapeView2 = new ShapeView(2, "SQUARE", "test", 0L, LocalDateTime.now(), LocalDateTime.now(), "test", 206.0, 405.0, null, 10.0, null, null);
        ShapeView shapeView3 = new ShapeView(3, "RECTANGLE", "test", 0L, LocalDateTime.now(), LocalDateTime.now(), "test", 230.0, 406.0, null, null, 5.0, 10.0);

        ShapeDto shapeDto1 = new ShapeDto(1, "CIRCLE", 0L, "test", LocalDateTime.now().toString(), LocalDateTime.now().toString(), "test", 20.0, 40.0);
        ShapeDto shapeDto2 = new ShapeDto(2, "SQUARE", 0L, "test", LocalDateTime.now().toString(), LocalDateTime.now().toString(), "test", 206.0, 405.0);
        ShapeDto shapeDto3 = new ShapeDto(3, "RECTANGLE", 0L, "test", LocalDateTime.now().toString(), LocalDateTime.now().toString(), "test", 230.0, 4060.0);

        List<Specification<ShapeView>> specificationList = new ArrayList<>();
        List<ShapeView> list = new ArrayList<>();
        List<ShapeDto> listDto = new ArrayList<>();
        list.add(shapeView);
        list.add(shapeView2);
        list.add(shapeView3);
        listDto.add(shapeDto1);
        listDto.add(shapeDto2);
        listDto.add(shapeDto3);

        Map<String, Object> map = new HashMap<>();
        FilterRequest filterRequest = new FilterRequest(map);
        Specification<ShapeView> spec = Specification.where(null);

        when(shapeSpecificationFactoryImpl.createSpecifications(filterRequest)).thenReturn(specificationList);
        when(shapeViewRepository.findAll()).thenReturn(list);
        when(shapeBuildService.buildShapeDtoListFromListShapeView(list)).thenReturn(listDto);

        List<ShapeDto> result = shapeService.filter(filterRequest);

        assertNotNull(result);

        verify(shapeSpecificationFactoryImpl).createSpecifications(filterRequest);

        assertEquals(result.get(0).getType(), shapeDto1.getType());
        assertEquals(result.get(0).getType(), shapeView.getType());
        assertEquals(result.get(1).getType(), shapeDto2.getType());
        assertEquals(result.get(1).getType(), shapeView2.getType());
        assertEquals(result.get(2).getType(), shapeDto3.getType());
        assertEquals(result.get(2).getType(), shapeView3.getType());

    }
}
