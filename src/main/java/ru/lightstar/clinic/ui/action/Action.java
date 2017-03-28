package ru.lightstar.clinic.ui.action;

import ru.lightstar.clinic.exception.ActionException;

/**
 * Interface for all user actions.
 *
 * @author LightStar
 * @since 0.0.1
 */
public interface Action {

    /**
     * Get action's name.
     *
     * @return action's name.
     */
    String getName();

    /**
     * Get action's description.
     *
     * @return action's description.
     */
    String getDescription();

    /**
     * Run action.
     *
     * @throws ActionException thrown when action-specific error encountered.
     */
    void run() throws ActionException;
}
