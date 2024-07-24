package core;

import java.util.*;

public class BoardAstar {
	private int[][] board;
	private BoardAstar parent;
	private int[] blankPos;
	private int cost;
	private int heuristicCost;
	
	public BoardAstar(int[][] board, BoardAstar parent, int[] blankPos, int cost) {
		this.board = board;
		this.parent = parent;
		this.blankPos = blankPos;
		this.cost = cost;
		this.heuristicCost = calculateManhattanDistance();
	}
	
	public List<BoardAstar> successorStates(){
		List<BoardAstar> successors = new ArrayList<>();
		int[][] directions = {{-1,-1},{-1,0},{-1,-1},{0,-1},{0,1},{1,-1},{1,0},{1,-1}};
		
		for(int[] dir: directions) {
			int newX = blankPos[0]+dir[0];
			int newY = blankPos[1]+dir[1];
			if(newX>=0 && newX<3 && newY>=0 && newY<3) {
				int[][] newBoard = copyBoard(board);
				newBoard[blankPos[0]][blankPos[1]] = newBoard[newX][newY];
				newBoard[newX][newY] = 0;
				successors.add(new BoardAstar(newBoard, this, new int[]{newX,newY}, cost+1));
			}
		}
		return successors;
	}
	
	
	private int[][] copyBoard(int[][] original){
		int[][] copy = new int[3][3];
		for(int i=0;i<3;i++) {
			System.arraycopy(original[i], 0, copy[i], 0, 3);
		}
		return copy;
	}
	
	private int calculateManhattanDistance() {
		int distance = 0;
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				int value = board[i][j];
				if(value != 0) {
					int targetRow = (value - 1) / 3;
					int targetCol = (value - 1) % 3;
					distance += Math.abs(i - targetRow) + Math.abs(j - targetCol);
				}
			}
		}
		return distance;
	}
	
	public int getTotalCost() {
		return cost + heuristicCost;
	}
	
	public int getCost() {
		return cost;
	}
	
	
	public int[][] getBoard(){
		return board;
	}
	
	
	public BoardAstar getParent() {
		return parent;
	}
	
	
	public boolean isGoal() {
        int count = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 2 && j == 2) {
                    if (board[i][j] != 0) {
                        return false;
                    }
                } else if (board[i][j] != count++) {
                    return false;
                }
            }
        }
        return true;
    }

}
