package ru.lightstar.clinic.io;

/**
 * User input interface
 *
 * @author LightStar
 * @since 0.0.1
 */
public interface Input {

    /**
     * Get next string from input.
     *
     * @return next string.
     */
    String next();

    /**
     * Ask user a question and get answer.
     *
     * @param output output for question.
     * @param question question.
     * @return user's answer.
     */
    String ask(Output output, String question);

    /**
     * Close this input.
     */
    void close();
}
