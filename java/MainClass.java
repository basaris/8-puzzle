package core;

import java.util.*;

public class MainClass {
	
	//method that prints out any board
	public static void printBoard(int[][] board) {
		System.out.println("---------");
		for(int[] row: board) {
			System.out.println(Arrays.toString(row));
		}
		System.out.println("---------");
	}
	
	
	public static int expUCS = 0;
	//the UCS algorithm for exercise 1.a.
	public static Board uniformCostSearch(Board initial) {
		PriorityQueue<Board> frontier = new PriorityQueue<>(Comparator.comparingInt(Board::getCost));
		Set<Board> explored = new HashSet<>();
		
		frontier.add(initial);
		
		while(!frontier.isEmpty()) {
			Board currentState = frontier.poll();
			
			if(currentState.isGoal()) {
				return currentState;
			}
			
			explored.add(currentState);
			
			for(Board nextState : currentState.successorStates()) {
				if(!explored.contains(nextState)) {
					frontier.add(nextState);
					expUCS++;
				}
			}
		}
		return null;
	}
	
	public static int expAstar = 0;
	//the A* algorithm for exercise 1.b.
	public static BoardAstar Astar(BoardAstar initial) {
		PriorityQueue<BoardAstar> frontier = new PriorityQueue<>(Comparator.comparingInt(BoardAstar::getTotalCost));
		Set<BoardAstar> explored = new HashSet<>();
		
		frontier.add(initial);
		
		while(!frontier.isEmpty()) {
			BoardAstar currentState = frontier.poll();
			
			if(currentState.isGoal()) {
				return currentState;
			}
			
			explored.add(currentState);
			
			for(BoardAstar nextState : currentState.successorStates()) {
				if(!explored.contains(nextState) && !frontier.contains(nextState)) {
					frontier.add(nextState);
					expAstar++;
				}else if(frontier.contains(nextState)) {
					for(BoardAstar state : frontier) {
						if(state.equals(nextState) && state.getCost() > nextState.getCost()) {
							frontier.remove(state);
							frontier.add(nextState);
							expAstar++;
							break;
						}
					}
				}
			}
		}
		return null;
	}
	

	public static void main(String[] args) {
		
		int[][] initialBoard = {{4,1,3},{0,2,6},{7,5,8}};
		Board initial_state = new Board(initialBoard, null, new int[] {1,0}, 0);//board for UCS method
		BoardAstar initial_state_A = new BoardAstar(initialBoard, null, new int[] {1,0}, 0);//board for A* method
		
		System.out.println("\nThe original board:\n");
		printBoard(initial_state.getBoard());
		
		//UCS method
		Board solution = uniformCostSearch(initial_state);//find the solution board that has every previous parent.
		System.out.println("\n\n8-puzzle solution with UCS algorithm! \n");
		int finalCost = solution.getCost();
		
		//if there is a solution
		if(solution != null) {
			List<Board> path = new ArrayList<>();//Get a list with all the nodes from initial to final state.
			while(solution != null) {
				path.add(solution);
				solution = solution.getParent();//get parent node till the parent node is NULL. Then the while loop terminates.
			}
			Collections.reverse(path);
			
			//print the list of nodes!
			for(int i = 0; i<path.size();i++) {
				System.out.println("\nStep "+ i + ":");
				printBoard(path.get(i).getBoard());
			}
		}else {
			System.out.println("No solution found.");
		}
		
		System.out.println("\nThe final cost is:"+finalCost);
		System.out.println("The total expansions were made:"+expUCS);
		System.out.println("\n\n-----------------");
		System.out.println("------ END ------");
		System.out.println("-----------------");
		
		
		//UCS method
		BoardAstar solution_A = Astar(initial_state_A);
		System.out.println("\n\n8-puzzle solution with A* algorithm! \n");
		finalCost = solution_A.getCost();

		//same as before but with a different algorithm
		if(solution_A != null) {
			List<BoardAstar> path = new ArrayList<>();
			while(solution_A != null) {
				path.add(solution_A);
				solution_A = solution_A.getParent();
			}
			Collections.reverse(path);
					
			for(int i = 0; i<path.size();i++) {
				System.out.println("\nStep "+ i + ":");
				printBoard(path.get(i).getBoard());
				}
		}else {
			System.out.println("No solution found.");
		} 
		

		System.out.println("\nThe final cost is:"+finalCost);
		System.out.println("The total expansions were made:"+expAstar);
		System.out.println("\n\n-----------------");
		System.out.println("------ END ------");
		System.out.println("-----------------");

	}
}
