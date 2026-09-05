package net.teujaem.spProxyData.player.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Map;

@Converter
public class MapJsonConverter implements AttributeConverter<Map<Integer, String>, String> {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<Integer, String> attribute) {
        if (attribute == null) {
            return null;
        }

        try {
            return OBJECT_MAPPER.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Map을 JSON으로 변환할 수 없습니다.", e);
        }
    }

    @Override
    public Map<Integer, String> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isBlank()) {
            return Map.of();
        }

        try {
            return OBJECT_MAPPER.readValue(
                    dbData,
                    new TypeReference<Map<Integer, String>>() {}
            );
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("JSON을 Map으로 변환할 수 없습니다.", e);
        }
    }
}