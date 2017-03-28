package ru.lightstar.clinic.ui;

import ru.lightstar.clinic.exception.ActionException;
import ru.lightstar.clinic.exception.MenuException;
import ru.lightstar.clinic.io.Input;
import ru.lightstar.clinic.io.Output;
import ru.lightstar.clinic.ui.action.Action;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * User menu object, capable of showing it and running actions associated with it.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class Menu {

    /**
     * <code>Input</code> object used for choosing menu item.
     */
    private final Input input;

    /**
     * <code>Output</code> object used for showing menu.
     */
    private final Output output;

    /**
     * Collection of all known actions mapped by their names.
     */
    private final Map<String, Action> actions;

    /**
     * Constructs <code>Menu</code> object.
     *
     * @param input used for choosing menu item.
     * @param output used for showing menu.
     */
    public Menu(final Input input, final Output output) {
        this.input = input;
        this.output = output;
        this.actions = new LinkedHashMap<>();
    }

    /**
     * Add action to menu.
     *
     * @param action added action.
     */
    public void addAction(final Action action) {
        this.actions.put(action.getName(), action);
    }

    /**
     * Get map of all actions in the menu.
     *
     * @return all actions mapped by their names.
     */
    public Map<String, Action> getActions() {
        return Collections.unmodifiableMap(this.actions);
    }

    /**
     * Show all actions in the menu for user to choose.
     */
    public void show() {
        this.output.println("Menu:");
        this.output.println("-----");

        for (final Action action : this.actions.values()) {
            this.output.println(String.format("%s (%s).", action.getDescription(), action.getName()));
        }

        this.output.println("");
    }

    /**
     * Ask user for action and run it.
     *
     * @throws MenuException thrown when wrong action chosen.
     * @throws ActionException action-specific exception.
     */
    public void run() throws MenuException, ActionException {
        final String actionName = this.input.ask(this.output, "Choose action:");

        if (!this.actions.containsKey(actionName)) {
            throw new MenuException("No such action!");
        }

        this.actions.get(actionName).run();
    }
}
