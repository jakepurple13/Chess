package ChessGUI;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Runnable {

	private BufferedReader in;
	private PrintWriter out;

    Socket socket;

	int color = 0;

	Move move;

	GUI g;
	
	public Client() {

	}

	public Client(int color, Move move, GUI g) {
		this.color = color;
		this.move = move;
		this.g = g;
		/*try {
			connectToServer();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}
	
	public void connectToServer() throws IOException {
		socket = new Socket("", 9898);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);
        send("0");

        ArrayList<String> responses = new ArrayList<>();
        try {
            Log.i("We are here");
            do {
                while (in.ready()) {
                    responses.add(in.readLine());
                }
            } while(responses.size()==0);
            in.reset();
        } catch (IOException ex) {
            //response = "Error: " + ex;
        }
        String list[] = responses.get(0).split("]");
        color = Integer.parseInt(list[2]);

	}

	private void send(String messageToSend) {
		out.println(messageToSend);
	}

	public void makeMove(int rowfrom, int columnfrom, int rowto, int columnto) {

		String color = this.color+"";

		if(color.equals("0")) {
			send(color);
		} else {
			send(color);
            send(rowfrom+"");
            send(columnfrom+"");
            send(rowto+"");
            send(columnto+"");
		}

	}
	
	public static void main(String[] args) throws Exception {
		Client client = new Client();
		client.connectToServer();
	}

    @Override
    public void run() {
        try {
            connectToServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*try {
            socket = new Socket("", 9898);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        Log.i("We are here");
        boolean connected = false;
        while(socket.isConnected()) {
            Log.i("We are here");
            String response = "";
            ArrayList<String> responses = new ArrayList<>();
            try {
                Log.i("We are here");
                do {
                    while (in.ready()) {
                        response += in.readLine();
                        responses.add(in.readLine());
                        Log.i(response);
                    }
                } while(responses.size()==0);
                in.reset();
            } catch (IOException ex) {
                //response = "Error: " + ex;
            }

            for(String s : responses) {
                Log.i(s);
            }

            try {

                String list[] = responses.get(0).split("]");

                for (String s : list) {
                    System.out.println(s);
                }

                if (connected) {

                    int rowfrom = Integer.parseInt(list[0]);
                    int colfrom = Integer.parseInt(list[1]);
                    int rowto = Integer.parseInt(list[2]);
                    int colto = Integer.parseInt(list[3]);
                    Log.i(rowfrom+"\t"+colfrom+"\t"+rowto+"\t"+colto);
                    move.move(rowfrom, colfrom, rowto, colto);

                    //g.boarded[rowfrom][colfrom].doClick();
                    //g.boarded[rowto][colto].doClick();

                    for(int i=0;i<8;i++) {
                        for(int j=0;j<8;j++) {
                            g.boarded[i][j].setText(g.b.getLocation(i, j).getPiece().getPiece());
                        }
                    }

                } else {
                    connected = true;
                }

            } catch(IndexOutOfBoundsException e) {
                e.printStackTrace();
            }

            responses.clear();

        }
    }

    public interface Move {
        void move(int rowfrom, int columnfrom, int rowto, int columnto);
    }

}