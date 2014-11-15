import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

public class ConsoleApplication {

    public static ConsoleApplication launchJar(String pathToJar) throws IOException {
        Process process = Runtime.getRuntime().exec("java -jar " + pathToJar);
        return new ConsoleApplication(process);
    }

    private final Process process;
    private final OutputStream appInput;
    private final Scanner appOutput;

    public ConsoleApplication(Process process) {
        this.process = process;
        appInput = process.getOutputStream();
        appOutput = new Scanner(process.getInputStream());
    }

    public void waitForAStart() throws InterruptedException {
        ensureAppIsAlive();
        Thread.sleep(1000);
    }

    private void ensureAppIsAlive() {
        try {
            process.exitValue();
            // process is dead
            throw new AssertionError("Monitored app is dead");
        } catch (IllegalThreadStateException e) {
            // process is alive
        }
    }

    public void enterCommand(String command) throws IOException {
        ensureAppIsAlive();
        appInput.write(command.getBytes());
        appInput.flush();
    }

    public void ensureMessage(String message) throws AssertionError {
        ensureAppIsAlive();
        while (appOutput.hasNextLine()) {
            String line = appOutput.nextLine();
            System.out.println(line);
            if (lineContainsMessage(line, message)) {
                return;
            }
        }
        throw new AssertionError("Can't ensure presence of message = " + message + " in monitored app's STDOUT");
    }

    private boolean lineContainsMessage(String line, String message) {
        return line.toLowerCase().contains(message.toLowerCase());
    }

    public void shutDown() throws InterruptedException {
        process.destroy();
        process.waitFor();
    }
}
