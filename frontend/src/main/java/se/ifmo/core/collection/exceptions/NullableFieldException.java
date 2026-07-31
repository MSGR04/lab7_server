package se.ifmo.core.collection.exceptions;

public class NullableFieldException extends RuntimeException {
    public NullableFieldException(String fieldName) {
        super(String.format("Ошибка при инициализации поля %s: поле не может быть null", fieldName));
    }
}
