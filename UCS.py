import heapq

class Board:
    ## initialize the class
    def __init__(self, board, parent=None, blankPos=[0,0], cost=0):
        self.board = board
        self.parent = parent
        self.blankPos = self.findBlankPos()
        self.cost = cost
    
    def __lt__(self, other):
        return self.cost < other.cost

    ## get a list of the boards of all the possible next moves
    def successorStates(self):
        successors = []
        directions = [[-1,0],[1,0],[0,-1],[0,1]] ## all the possible moves

        for direc in directions:
            newX = self.blankPos[0]+direc[0]
            newY = self.blankPos[1]+direc[1]
            if(newX>=0 and newX<3 and newY>=0 and newY<3):
                newBoard = [row[:] for row in self.board] ##create a deepcopy
                newBoard[self.blankPos[0]][self.blankPos[1]] = newBoard[newX][newY]
                newBoard[newX][newY] = 0
                successors.append(Board(newBoard, self, [newX,newY],self.cost+1))

        return successors
    

    def findBlankPos(self):
        for i in range(3):
            for j in range(3):
                if(self.board[i][j] == 0):
                    return [i,j]
        return [0,0]

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



def uniformCostSearch(initial):
    frontier = []
    heapq.heappush(frontier, (initial.cost, initial))
    explored = set()

    frontier.append((initial.getCost(),initial))

    while(frontier):
        _, state = heapq.heappop(frontier)

        if(state.isGoal()):
            return state

        explored.add(state)

        for nextState in state.successorStates():
            if(nextState not in explored and (nextState.cost, nextState) not in frontier):
                heapq.heappush(frontier, (nextState.cost, nextState))

    return None





if __name__ == "__main__":
    initial_board = [[0,1,3],[4,2,5],[7,8,6]]
    initial_state = Board(initial_board,None,[0,0],0)

    print("\nThe original board:\n")
    printBoard(initial_state.getBoard())

    boards = initial_state.successorStates()

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
            print("\nStep "+ str(i) + ":")
            printBoard(path[i].getBoard())




    
    
