from queue import PriorityQueue

class Board:
    ## initialize the class
    def __init__(self, board, parent, blankPos, cost):
        self.board = board
        self.parent = parent
        self.blankPos = blankPos
        self.cost = cost

    ## get a list of the boards of all the possible next moves
    def successorStates(self):
        successors = []
        directions = [[-1,0],[1,0],[0,-1],[0,1]] ## all the possible moves

        for direc in directions:
            newX = self.blankPos[0]+direc[0]
            newY = self.blankPos[1]+direc[1]
            if(newX>=0 and newX<3 and newY>=0 and newY<3):
                newBoard = self.board
                newBoard[self.blankPos[0]][self.blankPos[1]] = newBoard[newX][newY]
                newBoard[newX][newY] = 0
                successors.append(Board(newBoard, self, [newX,newY],self.cost+1))

        return successors
    

    def getCost(self):
        return self.cost

    def getBoard(self):
        return self.board

    def getParent(self):
        return self.parent

    def isGoal(self):
        goalBoard = [[1,2,3],[4,5,6],[7,8,0]]
        for i in range(3):
            for j in range(3):
                if(self.board[i][j] != goalBoard[i][j]):
                    return False
        return True


def printBoard(board):
    print("--------")
    for row in board:
        print(str(row[0])+"  "+str(row[1])+"  "+str(row[2]))
    print("--------")


expUCS = 0
def uniformCostSearch(initial):
    frontier = PriorityQueue()
    explored = []

    frontier.put((initial.getCost(),initial))

    while(not frontier.empty()):
        currentState = frontier.queue[0]

        if(currentState[1].isGoal()):
            return currentState[1]

        explored.append(currentState[1])

        for nextState in currentState[1].successorStates():
            if(nextState in explored):
                frontier.put((nextState.getCost(), nextState))
                expUCS+=1

    return None





if __name__ == "__main__":
    initial_board = [[1,2,3],[4,5,6],[0,7,8]]
    initial_state = Board(initial_board,None,[0,2],0)

    print("\nThe original board:\n")
    printBoard(initial_state.getBoard())

    solution = uniformCostSearch(initial_state)
    print("\n\n8-puzzle solution with UCS algorithm! \n")
    finalCost = solution.getCost()

    if(solution != None):
        path = []
        while(solution != None):
            path.append(solution)
            solution = solution.getParent()
        
        path.reverse()

        for i in range(len(path)):
            print("\nStep "+ i + ":")
            printBoard(path[i].getBoard())




    
    
