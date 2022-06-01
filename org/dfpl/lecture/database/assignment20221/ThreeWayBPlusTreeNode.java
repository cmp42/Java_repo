package org.dfpl.lecture.database.assignment20221;

import java.util.*;

@SuppressWarnings("unused")
public class ThreeWayBPlusTreeNode {

	public static final int T = 3;
	// Data Abstraction은 예시일 뿐 자유롭게 B+ Tree의 범주 안에서 어느정도 수정가능
	public ThreeWayBPlusTreeNode parent;
	public ThreeWayBPlusTreeNode[] children;
	public Integer[] keys;
	public boolean isLeaf;
	public int m;

	public ThreeWayBPlusTreeNode() {
		this.parent = null;
		this.children = new ThreeWayBPlusTreeNode[T + 1];
		this.keys = new Integer[T];
		this.m = 0;
		this.isLeaf = false;
	}
}