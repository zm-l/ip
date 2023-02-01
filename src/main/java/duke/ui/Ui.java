package duke.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private static final String LINE_PREFIX = "|| ";
    private static final String LS = System.lineSeparator();
    private static final String DIVIDER = "===================================================";

    private final Scanner in;
    private final PrintStream out;
    private ArrayList<String> responses;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
        responses = new ArrayList<>();
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String message = "Hello from\n" + logo + "\nWhat can I do for you?";
        showToUser(message);
    }

    public void showToUser(String... message) {
        for (String m : message) {
            responses.add(m.replace("\n", LS));
        }
    }

    public String getResponses() {
        String concatenatedResponse = "";
        for (String r : responses) {
            concatenatedResponse += r + "\n";
            System.out.println(r);
        }
        responses.clear();
        return concatenatedResponse;
    }

    private boolean shouldIgnore(String rawInputLine) {
        return rawInputLine.trim().isEmpty();
    }

    public String readCommand() {
        showToUser(LINE_PREFIX + "Enter command: ");
        String fullInputLine = in.nextLine();

        // silently consume all ignored lines
        while (shouldIgnore(fullInputLine)) {
            fullInputLine = in.nextLine();
        }

        showToUser("[Command entered:" + fullInputLine + "]");
        return fullInputLine;
    }

    public void showGoodbyeMessage() {
        showToUser("Bye. Hope to see you again soon!");
    }

    public void showLine() {
        showToUser(DIVIDER);
    }

    public void showError(String e) {
        showToUser(e);
    }

    public void showLoadingError() {
        showToUser("Error Loading");
    }
}
