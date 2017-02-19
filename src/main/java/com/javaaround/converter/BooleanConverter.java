package com.javaaround.converter;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class BooleanConverter implements AttributeConverter<Boolean, String> {

 
   @Override
   public String convertToDatabaseColumn(Boolean isActive) {
     return Boolean.TRUE.equals(isActive) ? "T" : "F";
   }
   
   @Override
   public Boolean convertToEntityAttribute(String value) {
       return "T".equals(value);
   }

}