package utils.logging.impl;

import utils.logging.Logger;

import java.io.*;

/**
 * Created by Andrii Mozharovskyi on 10.07.2015.
 */
public class CustomFileLogger extends Logger {

    private static final String TEST_FILE_PATH = "D:\\Projects\\IhubCustomSecurity";

    private static final String FILE_NAME = "ihub_custom_logs.txt";

    private static volatile CustomFileLogger instance;
    public static CustomFileLogger getInstance() {
        CustomFileLogger localInstance = instance;
        if (localInstance == null) {
            synchronized (CustomFileLogger.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new CustomFileLogger();
                }
            }
        }
        return localInstance;
    }

    private CustomFileLogger() { }

    @Override
    public void log(String message) {
        if(isDebug()){
            try {
                PrintWriter out = getWriter(true);
                out.println(message);
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void err(Throwable t) {
        log("--- ERROR stack trace --- " + t.toString());
        for(StackTraceElement s : t.getStackTrace()) {
            log("     " + s);
        }
        log("--- ERROR stack trace END ---");
    }

    @Override
    public void clear() {
        if(isDebug()){
            try {
                PrintWriter out = getWriter(false);
                out.print("");
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private PrintWriter getWriter(boolean edit) throws IOException {
        //return new PrintWriter(new BufferedWriter(new FileWriter(getCurrentWorkingDir() + "/" + FILE_NAME, edit)));
        return new PrintWriter(new BufferedWriter(new FileWriter(TEST_FILE_PATH + "/" + FILE_NAME, edit)));
    }

    private String getCurrentWorkingDir() {
        return System.getProperty("user.dir");
    }
}