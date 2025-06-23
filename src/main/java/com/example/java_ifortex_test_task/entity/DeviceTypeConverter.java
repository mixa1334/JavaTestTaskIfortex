package com.example.java_ifortex_test_task.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class DeviceTypeConverter implements AttributeConverter<DeviceType, Integer>{

    @Override
    public Integer convertToDatabaseColumn(DeviceType arg0) {
        return arg0.getCode();
    }

    @Override
    public DeviceType convertToEntityAttribute(Integer arg0) {
        return DeviceType.fromCode(arg0);
    }

}
