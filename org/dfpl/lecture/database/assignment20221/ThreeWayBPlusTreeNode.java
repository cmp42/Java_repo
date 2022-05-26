package org.dfpl.lecture.database.assignment20221;

import java.util.*;

@SuppressWarnings("unused")
public class ThreeWayBPlusTreeNode {

	// Data Abstraction은 예시일 뿐 자유롭게 B+ Tree의 범주 안에서 어느정도 수정가능
	public static final int T = 3;
	public ThreeWayBPlusTreeNode parent;
	public Integer[] keys;
	public ThreeWayBPlusTreeNode[] children;
	public ThreeWayBPlusTreeNode leftNode;
	public ThreeWayBPlusTreeNode rightNode;
	public ThreeWayBPlusTreeNode leftSibling;
	public ThreeWayBPlusTreeNode rightSibling;
	public int degree;
	public int maxDegree;
	public int minDegree;

	public void linkChildren(ThreeWayBPlusTreeNode p) {
		this.children[degree] = p;
		this.degree++;
	}

	public int findIndex(ThreeWayBPlusTreeNode p) {
		for (int i = 0; i < children.length; i++) {
			if (children[i] == p) {
				return i;
			}
		}
		return -1;
	}

	public void insertChildren (ThreeWayBPlusTreeNode p, int index) {
		for (int i = degree - 1; i >= index; i--) {
			children[i + 1] = children[i];
		}
		this.children[index] = p;
		this.degree++;
	}

	public void appendFirst(ThreeWayBPlusTreeNode p) {
		for (int i = degree - 1; i >= 0; i--) {
			children[i + 1] = children[i];
		}
		this.children[0] = p;
		this.degree++;
	}

	public void removeKey(int index) {
		this.keys[index] = null;
	}

	private void removePointer(int index) {
		this.children[index] = null;
		this.degree--;
	}
  
	private void removePointer(ThreeWayBPlusTreeNode p) {
		for (int i = 0; i < children.length; i++) {
			if (children[i] == p) {
				this.children[i] = null;
			}
		}
		this.degree--;
	}

	private int nullSearch(ThreeWayBPlusTreeNode[] ps) {
		for (int i = 0; i < ps.length; i++) {
			if (ps[i] == null) {
				return i;
			}
		}
		return -1;
	}

	private boolean isLess() {
		return this.degree < this.minDegree;
	}

	private boolean isLend() {
		return this.degree > this.minDegree;
	}

	private boolean isMerge() {
		return this.degree == this.minDegree;
	}

	private boolean isOver() {
		return this.degree == maxDegree + 1;
	}
	
	public ThreeWayBPlusTreeNode(Integer[] keys) {
		this.maxDegree = T;
		this.minDegree = (int) Math.ceil(T / 2.0);
		this.degree = 0;
		this.keys = keys;
		this.children = new ThreeWayBPlusTreeNode[this.maxDegree + 1];
	}

	private ThreeWayBPlusTreeNode(Integer[] keys, ThreeWayBPlusTreeNode[] ps) {
		this.maxDegree = T;
		this.minDegree = (int) Math.ceil(T / 2.0);
		this.degree = nullSearch(ps);
		this.keys = keys;
		this.children = ps;
	}
}
