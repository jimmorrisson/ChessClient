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
    private Socket socket;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;
    private ObjectOutputStream objectOutputStream;

    public Client(String ip, int port) throws UnknownHostException, IOException {
        socket = new Socket(ip, port);
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        dataInputStream = new DataInputStream(socket.getInputStream());
    }

    public String sendMessage(String message) throws IOException {
        dataOutputStream.writeUTF(message);
        return dataInputStream.readUTF();
    }

    public String sendCommand(Command cmd) throws IOException {
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