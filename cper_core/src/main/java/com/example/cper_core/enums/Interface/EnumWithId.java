package com.example.cper_core.enums.Interface;

public interface EnumWithId<T extends Enum<T> & EnumWithId<T>> {
    int getId();

    static <E extends Enum<E> & EnumWithId<E>> int getIdFromEnum(E e) {
        return e.getId();
    }

    static <E extends Enum<E> & EnumWithId<E>> E fromId(Class<E> enumClass, int id) {
        for (E e : enumClass.getEnumConstants()) {
            if (e.getId() == id) return e;
        }
        throw new IllegalArgumentException("ID inválido para enum " + enumClass.getSimpleName() + ": " + id);
    }

    static <E extends Enum<E>> E fromName(Class<E> enumClass, String name) {
        for (E e : enumClass.getEnumConstants()) {
            if (e.name().equalsIgnoreCase(name)) return e;
        }
        throw new IllegalArgumentException("Nome inválido para enum " + enumClass.getSimpleName() + ": " + name);
    }
}
