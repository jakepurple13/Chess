
import Board.Board;
import Piece.*;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String g = "-----------------------";
		
		Pawn p = new Pawn(true, false);
		Queen q = new Queen(true, false);
		King k = new King(true, false);
		Bishop b = new Bishop(true, false);
		Rook r = new Rook(true, false);
		Knight n = new Knight(true, false);
		
		Pawn p1 = new Pawn(true, true);
		Queen q1 = new Queen(true, true);
		King k1 = new King(true, true);
		Bishop b1 = new Bishop(true, true);
		Rook r1 = new Rook(true, true);
		Knight n1 = new Knight(true, true);
		
		System.out.println(p);
		System.out.println(q);
		System.out.println(k);
		System.out.println(b);
		System.out.println(r);
		System.out.println(n);
		System.out.println(g);
		System.out.println(p1);
		System.out.println(q1);
		System.out.println(k1);
		System.out.println(b1);
		System.out.println(r1);
		System.out.println(n1);
		
		Board board = new Board();
		System.out.println(board);
		board.setUpAsWhite();
		System.out.println(board);
		
		/*//pawn
		board.movePiece(6, 1, 5, 1);
		System.out.println(board);
		board.movePiece(6, 2, 4, 2);
		System.out.println(board);
		board.movePiece(6, 3, 4, 3);
		System.out.println(board);
		board.movePiece(4, 2, 3, 2);
		System.out.println(board);
		board.movePiece(3, 2, 2, 2);
		System.out.println(board);
		board.movePiece(2, 2, 1, 1);
		System.out.println(board);
		board.movePiece(6, 5, 4, 5);
		System.out.println(board);
		board.movePiece(6, 4, 5, 4);
		System.out.println(board);
		
		//king
		board.movePiece(7, 4, 7, 2);
		System.out.println(board);
		//board.movePiece(7, 4, 7, 5);
		//System.out.println(board);
		board.movePiece(7, 4, 6, 5);
		System.out.println(board);
		board.movePiece(6, 5, 5, 6);
		System.out.println(board);
		board.movePiece(5, 6, 4, 6);
		System.out.println(board);
		board.movePiece(4, 6, 3, 6);
		System.out.println(board);
		board.movePiece(3, 6, 2, 6);
		System.out.println(board);
		board.movePiece(2, 6, 1, 6);
		System.out.println(board);
		
		//bishop
		board.movePiece(7, 5, 6, 4);
		System.out.println(board);
		board.movePiece(6, 4, 3, 7);
		System.out.println(board);
		board.movePiece(3, 7, 1, 5);
		System.out.println(board);
		
		//rook
		board.movePiece(7, 0, 7, 2);
		System.out.println(board);
		board.movePiece(7, 2, 6, 2);
		System.out.println(board);
		board.movePiece(6, 2, 1, 2);
		System.out.println(board);
		board.movePiece(1, 2, 3, 2);
		System.out.println(board);
		board.movePiece(3, 2, 3, 0);
		System.out.println(board);
		board.movePiece(3, 0, 0, 0);
		System.out.println(board);
		
		//queen
		board.movePiece(7, 3, 5, 3);
		System.out.println(board);
		board.movePiece(5, 3, 4, 2);
		System.out.println(board);
		board.movePiece(4, 2, 0, 2);
		System.out.println(board);
		
		//knight
		board.movePiece(7, 1, 5, 2);
		System.out.println(board);
		board.movePiece(5, 2, 3, 1);
		System.out.println(board);
		board.movePiece(3, 1, 1, 2);
		System.out.println(board);*/
		
		/*board.movePiece(7, 3, 7, 5);
		System.out.println(board);
		board.movePiece(7, 3, 7, 1);
		System.out.println(board);*/
		
		//System.out.println(board.columnRow());
		int sum = 1;
		for(int i=1;i<=50;i++) {
			System.out.println(sum);
			sum+=1;
		}
		
		System.out.println(4*sum);
		
		
	}

}
