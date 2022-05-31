package org.dfpl.lecture.database.assignment20221;

import java.util.*;

@SuppressWarnings("unused")
public class ThreeWayBPlusTreeNode {

	// Data Abstraction은 예시일 뿐 자유롭게 B+ Tree의 범주 안에서 어느정도 수정가능
	public ThreeWayBPlusTreeNode parent;
	public List<Integer> keyList;
	public ThreeWayBPlusTreeNode left;
	public ThreeWayBPlusTreeNode middle;
	public ThreeWayBPlusTreeNode right;
	public int m;

	public ThreeWayBPlusTreeNode() {
		this.parent = null;
		this.keyList = new ArrayList<Integer>();
		this.left = null;
		this.middle = null;
		this.right = null;
		this.m = 0;
	}
}