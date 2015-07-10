package utils;

/**
 * Created by Andrii Mozharovskyi on 10.07.2015.
 */
public abstract class Logger {
    private boolean debug = true;

    public abstract void log(String message);

    public abstract void err(String message);

    public abstract void clear();

    protected boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }
}
