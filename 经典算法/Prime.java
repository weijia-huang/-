/**
 * Prime算法，用于生成稠密图的MST。
 */

package com.demo.algorithm;

import java.util.ArrayList;
import java.util.Arrays;

public class Prime {
	static int INF = Integer.MAX_VALUE;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[] nodes = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };// 顶点
		int[][] weights = new int[][] { // 权值
				{ 0, 12, INF, INF, INF, 16, 14 }, 
				{ 12, 0, 10, INF, INF, 7, INF }, 
				{ INF, 10, 0, 3, 5, 6, INF },
				{ INF, INF, 3, 0, 4, INF, INF }, 
				{ INF, INF, 5, 4, 0, 2, 8 }, 
				{ 16, 7, 6, INF, 2, 0, 9 },
				{ 14, INF, INF, INF, 8, 9, 0 } };
		Graph g = new Graph(nodes, weights);
		Edge[] res = g.prime(0);
		for (int i = 0; i < res.length; i++) {
			Edge edge = res[i];
			System.out.println("<" + nodes[edge.start] + "," + nodes[edge.end] + ">:" + edge.weight);
		}
	}

}

class Graph {
	char[] nodes;// 顶点
	int[][] weights;// 权值
	int edgeNums = 0;// 边数
	ArrayList<Edge> edges = new ArrayList<>();// 边
	static int INF = Integer.MAX_VALUE;

	public Graph(char[] nodes, int[][] weights) {
		this.nodes = Arrays.copyOf(nodes, nodes.length);
		this.weights = new int[weights.length][weights[0].length];
		for (int i = 0; i < weights.length; i++) {
			this.weights[i] = Arrays.copyOf(weights[i], weights[i].length);
		}
		for (int i = 0; i < weights.length; i++) {
			for (int j = i + 1; j < weights[i].length; j++) {
				if (weights[i][j] < INF) {
					edgeNums++;
					edges.add(new Edge(i, j, weights[i][j]));
				}
			}
		}
	}

	Edge[] prime(int v) {
		Edge[] res = new Edge[nodes.length - 1];// 存放MST的边
		int[] visited = new int[nodes.length];// 存放访问情况
		visited[v] = 1;
		int h1 = -1, h2 = -1;// 边的两个顶点
		int minWeight = INF;// 最小权值
		for (int k = 0; k < res.length; k++) {
			for (int i = 0; i < nodes.length; i++) {
				for (int j = 0; j < nodes.length; j++) {
					if (visited[i] == 1 && visited[j] == 0 && weights[i][j] < minWeight) {
						h1 = i;
						h2 = j;
						minWeight = weights[i][j];
					}
				}
			}
			visited[h2] = 1;
			Edge edge = new Edge(h1, h2, minWeight);
			res[k] = edge;
			minWeight = INF;
		}
		return res;
	}

	void showEdges() {
		for (Edge edge : this.edges) {
			System.out.println("<" + nodes[edge.start] + "," + nodes[edge.end] + ">:" + edge.weight);
		}
	}
}

class Edge {
	int start;
	int end;
	int weight;

	public Edge(int s, int e, int weight) {
		this.start = s;
		this.end = e;
		this.weight = weight;
	}

}
