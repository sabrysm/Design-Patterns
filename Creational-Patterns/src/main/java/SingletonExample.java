/*
* Task
Create a thread-safe Singleton class called Logger in Java. This class should do the following:
    - Allow only one instance to be created.
    - Provide a method logMessage(String message) that prints the message with a timestamp.
    - Demonstrate the thread-safe nature by simulating multiple threads attempting to access the Logger instance.
Requirements
    - Implement thread safety using the synchronized keyword or the double-checked locking pattern.
    - Print messages from multiple threads to show that all threads use the same Logger instance.
* */
import java.sql.Timestamp;
import java.time.Instant;

public class SingletonExample {
    public static void main(String[] args) {
        // Running multiple Threads
        for (int i = 0; i < 10; i++) {
            int currentThread = i;
            new Thread(() -> Logger.getLogger().logMessage("Running from Thread " + currentThread)).start();
        }
    }
}

class Logger {
    private static volatile Logger instance;

    private Logger() {
    }

    public static Logger getLogger() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    public void logMessage(String message) {
        Timestamp timestamp = Timestamp.from(Instant.now());
        System.out.printf("%s: %s\n", timestamp, message);
    }
}

