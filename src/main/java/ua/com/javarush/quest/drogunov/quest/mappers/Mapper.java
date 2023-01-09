package ua.com.javarush.quest.drogunov.quest.mappers;

import java.util.Collection;
import java.util.List;

public interface Mapper<E, D> {
    D parseEntity(E entity);
    
    List<D> parseEntityAll(Collection<E> entity);
    
    E parseDto(D dto);
    
    List<D> parseDtoAll(Collection<E> entity);
}
