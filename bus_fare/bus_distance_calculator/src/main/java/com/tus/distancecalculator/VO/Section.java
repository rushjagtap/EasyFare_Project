package com.tus.distancecalculator.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Section{
    public int startPointIndex;
    public int endPointIndex;
    public String sectionType;
    public String travelMode;
}