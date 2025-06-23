package com.example.cper_core.enums.Interface;

public interface EnumWithId<T extends Enum<T> & EnumWithId<T>> {
    int getId();

    default String getLabel() {
        String raw = ((Enum<?>) this).name().toLowerCase().replace("_", " ");
        String[] palavras = raw.split(" ");
        StringBuilder sb = new StringBuilder();

        for (String palavra : palavras) {
            if (!palavra.isEmpty()) {
                sb.append(Character.toUpperCase(palavra.charAt(0)))
                        .append(palavra.substring(1))
                        .append(" ");
            }
        }

        return sb.toString().trim();
    }

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