package com.javarush.task.task33.task3305;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
@JsonAutoDetect
@JsonTypeInfo(
        use = JsonTypeInfo.Id.CLASS,
        include = As.PROPERTY,
        property = "className"
)
@JsonSubTypes(value = {
        @JsonSubTypes.Type(value = RacingBike.class, name = "RacingBike"),
        @JsonSubTypes.Type(value = Motorbike.class, name = "Motorbike"),
        @JsonSubTypes.Type(value = Car.class, name = "Car")
})
public abstract class Vehicle {
    protected String name;
    protected String owner;
    protected int age;
}