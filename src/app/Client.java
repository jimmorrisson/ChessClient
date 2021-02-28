package app;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.json.JSONObject;

import model.Command;

public class Client {
    private final Socket socket;
    private final DataOutputStream dataOutputStream;
    private final DataInputStream dataInputStream;
    private final ObjectOutputStream objectOutputStream;

    public Client(final String ip, final int port) throws UnknownHostException, IOException {
        socket = new Socket(ip, port);
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        dataInputStream = new DataInputStream(socket.getInputStream());
    }

    public String getPlayerName() throws IOException {
        return dataInputStream.readUTF();
    }

    public String sendMessage(final String message) throws IOException {
        dataOutputStream.writeUTF(message);
        return dataInputStream.readUTF();
    }

    public String sendCommand(final Command cmd) throws IOException {
        objectOutputStream.writeObject(cmd);
        return dataInputStream.readUTF();
    }

	public JSONObject getBoardContext() throws IOException {
        objectOutputStream.writeObject(new Command(true));
        return new JSONObject(dataInputStream.readUTF());
    }

    public void stopConnection() throws IOException {
        dataInputStream.close();
        dataOutputStream.close();
        socket.close();
    }
}