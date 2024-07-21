

class Board:
    ## initialize the class
    def __init__(self, board, parent, blankPos, cost):
        self.board = board
        self.parent = parent
        self.blankPos = blankPos
        self.cost = cost

    ## get a list of the boards of all the possible next moves
    def successorStates():
        successors = []
        directions = [[-1,0],[1,0],[0,-1],[0,1]] ## all the possible moves

        for direc in directions:
            newX = blankPos[0]+direc[0]
            newY = blankPos[1]+direc[1]
            if(newX>=0 and newX<3 and newY>=0 and newY<3):
                newBoard = board
                newBoard[blankPos[0]][blankPos[1]] = newBoard[newX][newY]
                newBoard[newX][newY] = 0
                successors.append(Board(newBoard, self, [newX,newY],cost+1))

        return successors
    

    def getCost():
        return cost

    def getBoard():
        return board

    def getParent():
        return parent

    def isGoal():
        goalBoard = [[1,2,3],[4,5,6],[7,8,0]]
        for i in range(3):
            for j in range(3):
                if(board[i][j] != goalBoard[i][j]):
                    return False
        return True


def printBoard(board):
    print("--------")
    for row in board:
        print(str(row[0])+"  "+str(row[1])+"  "+str(row[2]))
    print("--------")


if __name__ == "__main__":
    board = [[1,2,3],[4,5,6],[7,8,0]]
    myBoard = Board(board,None,[2,2],0)

    printBoard(board)
    
    
