
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.json.JSONObject;

import chess.com.*;

public class Client {
    private final Socket socket;
    private final ObjectOutputStream objectOutputStream;
    private final ObjectInputStream objectInputStream;

    public Client(final String ip, final int port) throws UnknownHostException, IOException {
        socket = new Socket(ip, port);
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectInputStream = new ObjectInputStream(socket.getInputStream());
    }

    public String getPlayerName() throws IOException {
        objectOutputStream.writeObject(new CommandGetPlayerColor());
        try {
            Response response = (Response) objectInputStream.readObject();
            if (response.getResponseType() == ResponseType.Text) {
                return (String) response.getData();
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

    public String sendMessage(final String message) throws IOException {
        return "";
    }

    public String sendCommand(final Command cmd) throws IOException {
        objectOutputStream.writeObject(cmd);
        try {
            Response response = (Response) objectInputStream.readObject();
            if (response.getResponseType() == ResponseType.Text) {
                return (String) response.getData();
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject getBoardContext() throws IOException {
        objectOutputStream.writeObject(new CommandGetBoardContext());
        Response response;
        try {
            response = (Response) objectInputStream.readObject();
            if (response.getResponseType() == ResponseType.Json) {
                return new JSONObject((String) response.getData());
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public void stopConnection() throws IOException {
        objectInputStream.close();
        socket.close();
    }
}