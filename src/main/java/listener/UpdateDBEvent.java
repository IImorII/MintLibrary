package listener;

import java.util.EventObject;

public class UpdateDBEvent extends EventObject {

    private String message;

    public UpdateDBEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public UpdateDBEvent(Object source) {
        this(source, "");
    }

    public UpdateDBEvent(String s) {
        this(null, s);
    }

    public String getMessage() {
        return message;
    }

}
