package org.dfpl.lecture.database.assignment20221;

import java.util.*;

@SuppressWarnings("unused")
public class ThreeWayBPlusTreeNode {

	// Data Abstraction은 예시일 뿐 자유롭게 B+ Tree의 범주 안에서 어느정도 수정가능
	public static final int T = 3;

	public ThreeWayBPlusTreeNode parent;
	public List<Integer> keyList;
	public List<ThreeWayBPlusTreeNode> children;
	public int m;

	public ThreeWayBPlusTreeNode() {
		this.parent = null;
		this.keyList = new ArrayList<Integer>();
		this.children = new ArrayList<ThreeWayBPlusTreeNode>();
		this.m = 0;
	}
}