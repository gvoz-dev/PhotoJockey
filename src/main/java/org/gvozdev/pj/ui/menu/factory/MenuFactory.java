package org.gvozdev.pj.ui.menu.factory;

import java.util.Map;

/**
 * Интерфейс фабрики меню.
 *
 * @param <ID> тип идентификаторов элементов меню
 * @param <E>  тип элементов меню
 * @author Roman Gvozdev
 */
@FunctionalInterface
public interface MenuFactory<ID, E> {
    /**
     * Возвращает хранилище элементов меню.
     */
    Map<ID, E> menuStorage();
}
