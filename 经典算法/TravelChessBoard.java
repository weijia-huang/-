/**
 * 用于解决骑士周游(马踏棋盘)问题：即从棋盘的某一点出发，每次按'日'字形走法，走过的点不重复走。如何走才能走遍棋盘？
 * 思路：使用dfs遍历所有可能性，使用贪心算法加快搜索速度
 */

package com.demo.algorithm;

import java.util.*;

public class TravelChessBoard {
	public static void main(String[] args) {
		ChessBoard cb = new ChessBoard(6, 6);
		long startTime = System.currentTimeMillis();
		travel(cb, 3, 4);
		System.out.printf("总共花费时间：%d ms", System.currentTimeMillis() - startTime);
	}
	
	/**
	 * 周游
	 * @param x	起点的横坐标
	 * @param y	起点的纵坐标
	 */
	public static void travel(ChessBoard cb, int x, int y) {
		cb.travel(new Point(x, y), 1);
		cb.show();
	}

}

//棋盘类
class ChessBoard {
	private int rows;
	private int cols;
	private int[][] chessBoard;// 记录周游次序
	private boolean finished = false;//是否完成了周游

	public ChessBoard(int m, int n) {
		rows = m;
		cols = n;
		chessBoard = new int[m][n];
	}
	
	/**
	 * 周游
	 * @param p 周游到点p
	 * @param step	周游了step步
	 */
	public void travel(Point p, int step) {
		chessBoard[p.x][p.y] = step;
		List<Point> nextPoint = nextPoint(p);
		nextPoint.sort((a, b) -> nextPoint(a).size() - nextPoint(b).size());
		for(Point next: nextPoint) {
			travel(next, step + 1);
		}
		if(step < rows * cols && !finished) {
			chessBoard[p.x][p.y] = 0;
		}else {
			finished = true;
		}
	}
	
	/**
	 * 获取点p的下一个可跳点的集合
	 * @param p
	 * @return
	 */
	public List<Point> nextPoint(Point p) {
		List<Point> nextPoint = new ArrayList();
		int[][] dif = new int[][] { { 2, 1 }, { 2, -1 }, { -2, 1 }, { -2, -1 }, { 1, 2 }, { 1, -2 }, { -1, 2 },
				{ -1, -2 } };
		for(int i = 0; i < dif.length; i++) {
			int x1 = dif[i][0] + p.x, y1 = dif[i][1] + p.y;
			if(x1 >= 0 && x1 < rows && y1 >= 0 && y1 < cols && chessBoard[x1][y1] == 0) {
				nextPoint.add(new Point(x1, y1));
			}
		}
		return nextPoint;
	}
	
	/**
	 * 打印棋盘
	 */
	public void show() {
		for (int i = 0; i < rows; i++) {
			for(int j = 0 ; j < cols; j++) System.out.printf("%5d ", chessBoard[i][j]);
			System.out.printf("\n");
		}
	}
}

//坐标类
class Point {
	int x;
	int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
