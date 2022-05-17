package org.dfpl.lecture.database.assignment20221;

import java.util.List;

@SuppressWarnings("unused")
public class ThreeWayBPlusTreeNode {

	// Data Abstraction은 예시일 뿐 자유롭게 B+ Tree의 범주 안에서 어느정도 수정가능
	public static final int T = 3;
	public ThreeWayBPlusTreeNode parent;
	public List<Integer> keyList;
	public List<ThreeWayBPlusTreeNode> children;
	public int mNum = 0;
	public int mData = 0;
	public ThreeWayBPlusTreeNode leftNode;
	public ThreeWayBPlusTreeNode rightNode;

	private boolean mIsLeafNode;
}
