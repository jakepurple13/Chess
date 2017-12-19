package ChessGUI;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Vector;

public class Server {
	
	public static HashMap<String, String> users;

    private static Vector<PrintStream> streams = new Vector<PrintStream>();


    public static void main(String[] args) throws Exception {
    	startServer();
    }

    public static void startServer() throws IOException {
        users = new HashMap<>();
        System.out.println("The server is running.");
        int clientNumber = 0;
        ServerSocket listener = new ServerSocket(9898);
        try {
            while(true) {
                new Database(listener.accept(), clientNumber++).start();
            }
        } finally {
            listener.close();
        }
    }
    
    public Server(ChessMove chess) throws IOException {
    	users = new HashMap<>();
        System.out.println("The server is running.");
        int clientNumber = 0;
        ServerSocket listener = new ServerSocket(9898);
        try {
            while(true) {
                new Database(listener.accept(), clientNumber++, chess).start();
            }
        } finally {
            listener.close();
        }

    }
    
    private static class Database extends Thread {
        private Socket socket;
        private int clientNumber;
        
        private ChessMove chessMove;

        public Database(Socket socket, int clientNumber) {
            this.socket = socket;
            this.clientNumber = clientNumber;
            log("New connection with client# " + clientNumber + " at " + socket);
        }
        
        public Database(Socket socket, int clientNumber, ChessMove chess) {
            this.socket = socket;
            this.clientNumber = clientNumber;
            log("New connection with client# " + clientNumber + " at " + socket);
            this.chessMove = chess;
        }

        public void run() {
            try {
            	
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                //PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                PrintStream out = new PrintStream(socket.getOutputStream(), true);

                while(socket.isConnected()) {
                    Log.i("We are here");
                    String color = in.readLine();
                    Log.i(color);
                    System.out.println(color);
                    String player = "";
                    String num = "";
                    if(color.equals("0")) {
                        String message;
                        if(users.containsKey("Player 1")) {
                            users.put("Player 2", "2");
                            message = "0]"
                                    +"You are Player 2]"
                                    +"2";
                            player = "You are Player 2";
                            num = "2";
                        } else {
                            users.put("Player 1", "1");
                            message = "0]"
                                    +"You are Player 1]"
                                    +"1";
                            player = "You are Player 1";
                            num = "1";
                        }
                        Log.i(message);
                        /*out.println(color);
                        out.println(player);
                        out.println(num);*/
                        out.println(message);
                        streams.add(out);

                    } else {

                        int rowfrom = Integer.parseInt(in.readLine());
                        int colfrom = Integer.parseInt(in.readLine());
                        int rowto = Integer.parseInt(in.readLine());
                        int colto = Integer.parseInt(in.readLine());

                        /*if(chessMove!=null) {

                            chessMove.move(rowfrom, colfrom, rowto, colto);

                        }*/

                        System.out.println(rowfrom+"");
                        System.out.println(colfrom+"");
                        System.out.println(rowto+"");
                        System.out.println(colto+"");

                        for(PrintStream stream : streams){
                            stream.println(rowfrom+"]"+colfrom+"]"+rowto+"]"+colto);
                        }

                    }

                    System.out.println("------------------");

                }


            } catch (IOException e) {
            	e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    log("Couldn't close a socket, what's going on?");
                }
            }
        }
        
        private void log(String message) {
            System.out.println(message);
        }
    }
    
    public interface ChessMove {
    	void move(int rowfrom, int columnfrom, int rowto, int columnto);
    }
    
}