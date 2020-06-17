package error_handlers;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ErrorHandler {

    private static final Logger LOGGER = Logger.getLogger(ErrorHandler.class.getName());
    private static ErrorHandler errorHandler = null;

    private ErrorHandler() { }

    public static ErrorHandler getInstance() {
        if (errorHandler == null) {
            errorHandler = new ErrorHandler();
        }
        return errorHandler;
    }

    public void logError(StackTraceElement[] stacktrace) {
        String errorMessage = "Exception occurred in " + stacktrace[1].getClassName();
        LOGGER.log(Level.SEVERE, errorMessage);
    }

    public void warn(String message) {
        LOGGER.warning(message);
    }
}
