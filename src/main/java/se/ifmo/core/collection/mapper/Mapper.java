package se.ifmo.core.collection.mapper;

public interface Mapper<O, D> {
    O fromDto(D dto);
    D toDto(O obj);
}
