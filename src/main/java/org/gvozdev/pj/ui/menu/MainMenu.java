package org.gvozdev.pj.ui.menu;

import org.gvozdev.pj.ui.main.MainWindow;

/**
 * Интерфейс главного меню приложения.
 *
 * @param <M> тип панели главного меню
 * @param <E> тип элементов главного меню
 * @author Roman Gvozdev
 */
public interface MainMenu<M, E> {
    /**
     * Инициализирует главное меню приложения.
     *
     * @param mainWindow ссылка на главное окно приложения
     */
    void init(MainWindow<?> mainWindow);

    /**
     * Возвращает панель главного меню.
     */
    M menuBar();

    /**
     * Возвращает элемент главного меню по идентификатору.
     *
     * @param elementId идентификатор элемента
     */
    E elementById(String elementId);
}
