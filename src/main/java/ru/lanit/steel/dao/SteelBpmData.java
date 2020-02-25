package ru.lanit.steel.dao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class SteelBpmData {
    @Setter
    @Getter
    private String steelPercentValue;

    @Setter
    @Getter
    private String steelModelName;

}