package com.example.shapesecurity.mapper.specification.nowySposob;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ItemCriteria {
    private int page = 0;
    private int size = 25;
    private List<String> filter;
}
