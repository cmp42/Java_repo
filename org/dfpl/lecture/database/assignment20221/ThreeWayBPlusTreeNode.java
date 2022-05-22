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
	public DictPair[] dict;
	public int degree;
	public int maxDegree;
	public int minDegree;
	public int maxPairs;
	public int minPairs;
	public int numPairs;

	public class DictPair implements Comparable<DictPair> {
		int	key;
		int	value;
	
		public DictPair (int key, int value) {
			this.key = key;
			this.value = value;
		}

		public int compareTo(DictPair o) {
			if (key == o.key) {
				return 0;
			} else if (key > o.key) {
				return 1;
			} else {
				return -1;
			}
		}
	}

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

	private int nullSearch(DictPair[] dps) {
		for (int i = 0; i < dps.length; i++) {
			if (dps[i] == null) {
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

	public void delete(int index) {
		this.dict[index] = null;
		numPairs--;
	}

	public boolean insert(DictPair dp) {
		if (this.LisFull()) {
			return false;
		} else {
			this.dict[numPairs] = dp;
			numPairs++;
			Arrays.sort(this.dict, 0, numPairs);
		}
		return true;
	}
	public boolean LisLess() {
		return this.numPairs < this.minPairs;
	}

	public boolean LisFull() {
		return this.numPairs == this.maxPairs;
	}

	public boolean LisLend() {
		return this.numPairs > this.minPairs;
	}
	  
	public boolean LisMerge() {
		return this.numPairs == this.minPairs;
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
	
	public ThreeWayBPlusTreeNode(DictPair dp) {
		this.maxPairs = T - 1;
		this.minPairs = (int) (Math.ceil(T / 2) - 1);
		this.dict = new DictPair[T];
		this.numPairs = 0;
		this.insert(dp);
		}

	public ThreeWayBPlusTreeNode(DictPair[] dps, ThreeWayBPlusTreeNode parent) {
		this.maxPairs = T - 1;
		this.minPairs = (int) (Math.ceil(T / 2) - 1);
		this.dict = dps;
		this.numPairs = nullSearch(dps);
		this.parent = parent;
	}
}
