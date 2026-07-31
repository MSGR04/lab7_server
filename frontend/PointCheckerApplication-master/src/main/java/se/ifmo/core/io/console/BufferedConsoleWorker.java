package se.ifmo.core.io.console;

import se.ifmo.core.io.IOWorker;

import java.io.*;

public class BufferedConsoleWorker implements IOWorker {
    private final BufferedReader reader;
    private final BufferedWriter writer;

    public BufferedConsoleWorker() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.writer = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    @Override
    public String read() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public boolean ready() {
        try {
            return reader.ready();
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public void write(String message) {
        try {
            writer.append(message).append("\n").flush();
        } catch (IOException ignored) {}
    }

    @Override
    public void writeWithoutNewLine(String message) {
        try {
            writer.append(message).flush();
        } catch (IOException ignored) {}
    }

    @Override
    public void shutdownGracefully() throws IOException {
        this.reader.close();
        this.writer.close();
    }
}
