package ua.com.javarush.quest.drogunov.quest.mapping;

import lombok.SneakyThrows;
import ua.com.javarush.quest.drogunov.quest.dto.FormData;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public interface Mapper<E extends Entity, R> {
    /**
     * From Service to Servlet layer
     * Needed for security data secure transfer of data from the
     * database to the outside
     *
     * @param entity from DB
     * @return DTO entity
     */
    Optional<R> get(E entity);

    /**
     * Data form Servlet convert to new Entity
     *
     * @param formData wrapper from HTTP-request
     * @return entity for DB
     */
    E parse(FormData formData);

    Mapper<User, UserDto> user = new UserMapper();
    Mapper<Quest, QuestDto> quest = new QuestMapper();
    Mapper<Question, QuestionDto> question = new QuestionMapper();
    Mapper<Answer, AnswerDto> answer = new AnswerMapper();
    Mapper<Game, GameDto> game = new GameMapper();


    /**
     * Data form Servlet convert to existing instance Entity
     * demo fill with Reflection API (not easy) - very not Easy!
     *
     * @param entity Object where to write data from formData
     * @param formData wrapper for HTTP-request
     * @return existing entity
     */
    default E fill(E entity, FormData formData) {
        Class<? extends Entity> aClassEntity = entity.getClass();
        Method[] methods = aClassEntity.getMethods();
        for (Method method : methods) {
            String methodName = method.getName();
            if (methodName.startsWith("set")
                    && Modifier.isPublic(method.getModifiers())
                    && method.getParameterCount() == 1
            ) {
                //TODO Нужно поробовать сделать через пакет Apache
                String parameterName = methodName.substring(3, 4).toLowerCase() + methodName.substring(4);
                String value = formData.getValue(parameterName);
                if (Objects.nonNull(value)) {
                    Class<?> typeParameter = method.getParameterTypes()[0];
                    if (InnerMapForPrimitiveData.map.containsKey(typeParameter)) {
                        Object parsingValue = InnerMapForPrimitiveData.map.get(typeParameter).apply(value);
                        set(entity, aClassEntity, typeParameter, parsingValue, methodName);
                    } else if (typeParameter.isEnum()) {
                        for (Object enumConstant : typeParameter.getEnumConstants()) {
                            if (enumConstant.toString().equalsIgnoreCase(value)) {
                                set(entity, aClassEntity, typeParameter, enumConstant, methodName);
                                break;
                            }
                        }
                    }
                }
            }
        }
        return entity;
    }


    //TODO Comeback and write javaDoc with annotation and test return type

    /**
     * @apiNote This is method take name of Type and
     * returns an Object of the given types if it is contained in map
     */
    final
    class InnerMapForPrimitiveData {
        private static final Map<Class<?>, Function<String, Object>> map = Map.of(
                int.class, Integer::parseInt,
                long.class, Long::parseLong,
                double.class, Double::parseDouble,
                Integer.class, Integer::valueOf,
                Long.class, Long::valueOf,
                Double.class, Double::valueOf,
                String.class, String::toString
        );

        private InnerMapForPrimitiveData() {
            throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
        }
    }

    /**
     * @param entity        Object that needs to be changed
     * @param aClassEntity  Class parameter entity
     * @param typeParameter Type parameter value if {@link InnerMapForPrimitiveData}
     * @param value         Concrete value to be embedded in entity
     * @param setterName    Method names for assign a value in entity
     * @apiNote This is method to set a field value be using setter method this entity. Done with be Reflection API
     */

    //TODO Рассмотреть подробнее как работает с энамом
    @SneakyThrows
    private static void set(Object entity,
                            Class<? extends Entity> aClassEntity,
                            Class<?> typeParameter,
                            Object value,
                            String setterName) {
        aClassEntity.getMethod(setterName, typeParameter).invoke(entity, value);
    }
}
