import java.util.*;

public class Board {
	private int[][] board;
	private Board parent;
	private int[] blankPos;
	private int cost;
	
	//initialize the class
	public Board(int[][] board, Board parent, int[] blankPos, int cost) {
		this.board = board;
		this.parent = parent;
		this.blankPos = blankPos;
		this.cost = cost;
	}
	
	//get a list of the boards of all the possible next moves
	public List<Board> successorStates(){
		List<Board> successors = new ArrayList<>();
		int[][] directions = {{-1,0},{0,-1},{0,1},{1,0}};//all the possible moves
		
		for(int[] dir: directions) {
			int newX = blankPos[0]+dir[0];
			int newY = blankPos[1]+dir[1];
			if(newX>=0 && newX<3 && newY>=0 && newY<3) {//if it is allowed
				int[][] newBoard = copyBoard(board);//a copy of the board
				newBoard[blankPos[0]][blankPos[1]] = newBoard[newX][newY];//reverse the tiles
				newBoard[newX][newY] = 0;
				successors.add(new Board(newBoard, this, new int[]{newX,newY}, cost+1));//add the board to the list.
			}
		}
		return successors;
	}
	
	//method that makes a copy of the board
	private int[][] copyBoard(int[][] original){
		int[][] copy = new int[3][3];
		for(int i=0;i<3;i++) {
			System.arraycopy(original[i], 0, copy[i], 0, 3);
		}
		return copy;
	}
	
	//returns the calculated cost of a series of moves
	public int getCost() {
		return cost;
	}
	
	public int[][] getBoard(){
		return board;
	}
	
	public Board getParent() {
		return parent;
	}
	
	//True if the board if the final one. False otherwise
	public boolean isGoal() {
		int[][] goalBoard = {{1,2,3},{4,5,6},{7,8,0}};
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(board[i][j] != goalBoard[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	
}
