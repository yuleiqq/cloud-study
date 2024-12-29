#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define SIZE 10
#define MINES 15

int board[SIZE][SIZE];
int revealed[SIZE][SIZE];

void initialize() {
    int i, j;

    for (i = 0; i < SIZE; i++) {
        for (j = 0; j < SIZE; j++) {
            board[i][j] = 0;
            revealed[i][j] = 0;
        }
    }
}

void generateMines() {
    int count = 0;
    srand(time(NULL));

    while (count < MINES) {
        int x = rand() % SIZE;
        int y = rand() % SIZE;

        if (board[x][y] == 0) {
            board[x][y] = -1;
            count++;
        }
    }
}

int countAdjacentMines(int x, int y) {
    int count = 0;
    int i, j;

    for (i = -1; i <= 1; i++) {
        for (j = -1; j <= 1; j++) {
            if (x + i >= 0 && x + i < SIZE && y + j >= 0 && y + j < SIZE) {
                if (board[x + i][y + j] == -1) {
                    count++;
                }
            }
        }
    }

    return count;
}

void revealCell(int x, int y) {
    if (x < 0 || x >= SIZE || y < 0 || y >= SIZE || revealed[x][y] == 1) {
        return;
    }

    revealed[x][y] = 1;

    if (board[x][y] == -1) {
        printf("游戏结束！你踩到了地雷！\n");
        exit(0);
    } else if (board[x][y] > 0) {
        return;
    }

    if (countAdjacentMines(x, y) == 0) {
        int i, j;

        for (i = -1; i <= 1; i++) {
            for (j = -1; j <= 1; j++) {
                revealCell(x + i, y + j);
            }
        }
    }
}

void printBoard() {
    int i, j;

    printf("   ");

    for (i = 0; i < SIZE; i++) {
        printf("%2d ", i);
    }

    printf("\n");

    for (i = 0; i < SIZE; i++) {
        printf("%2d ", i);

        for (j = 0; j < SIZE; j++) {
            if (revealed[i][j] == 1) {
                if (board[i][j] == -1) {
                    printf("*  ");
                } else if (board[i][j] > 0) {
                    printf("%d  ", board[i][j]);
                } else {
                    printf("   ");
                }
            } else {
                printf(".  ");
            }
        }

        printf("\n");
    }
}

int main() {
    int x, y;

    initialize();
    generateMines();

    while (1) {
        printBoard();

        printf("请输入要翻开的单元格的坐标（例如：3 4）：");
        scanf("%d %d", &x, &y);

        revealCell(x, y);

    }
}