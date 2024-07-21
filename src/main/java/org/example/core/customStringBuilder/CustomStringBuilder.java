package org.example.core.customStringBuilder;

import java.util.Stack;

public class CustomStringBuilder {
    private StringBuilder sb;
    private Stack<String> history;

    public CustomStringBuilder() {
        this.sb = new StringBuilder();
        this.history = new Stack<>();
    }

    private void saveState() {
        history.push(sb.toString());
    }

    public CustomStringBuilder append(String str) {
        saveState();
        sb.append(str);
        return this;
    }

    public CustomStringBuilder delete(int start, int end) {
        saveState();
        sb.delete(start, end);
        return this;
    }

    public CustomStringBuilder insert(int offset, String str) {
        saveState();
        sb.insert(offset, str);
        return this;
    }

    public CustomStringBuilder replace(int start, int end, String str) {
        saveState();
        sb.replace(start, end, str);
        return this;
    }

    public String undo() {
        if (!history.isEmpty()) {
            sb = new StringBuilder(history.pop());
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}
