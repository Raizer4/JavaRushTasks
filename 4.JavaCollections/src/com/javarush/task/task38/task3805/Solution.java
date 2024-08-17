package com.javarush.task.task38.task3805;

/* 
Улучшения в Java 7 (multiple exceptions)
*/

public class Solution {
    private final Connection connection;

    public Solution() throws SolutionException, WrongDataException, ConnectionException {
        try {
            connection = new ConnectionMock();
            connection.connect();
        } catch (WrongDataException | ConnectionException ex) {
            throw ex;
        }
    }

    public void write(Object data) throws SolutionException, WrongDataException, ConnectionException {
        try {
            connection.write(data);
        }catch (WrongDataException | ConnectionException ex) {
            throw ex;
        }
    }

    public Object read() throws SolutionException, WrongDataException, ConnectionException {
        try {
            return connection.read();
        }catch (WrongDataException | ConnectionException ex) {
            throw ex;
        }
    }

    public void disconnect() throws SolutionException, WrongDataException, ConnectionException {
        try {
            connection.disconnect();
        } catch (WrongDataException | ConnectionException ex) {
            throw ex;
        }
    }

    public static void main(String[] args) {

    }
}
