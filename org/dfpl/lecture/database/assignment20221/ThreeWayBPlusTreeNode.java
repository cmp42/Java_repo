package org.dfpl.lecture.database.assignment20221;

import java.util.List;

@SuppressWarnings("unused")
public class ThreeWayBPlusTreeNode {

	// Data Abstraction은 예시일 뿐 자유롭게 B+ Tree의 범주 안에서 어느정도 수정가능
	private ThreeWayBPlusTreeNode parent;
	private List<Integer> keyList;
	private List<ThreeWayBPlusTreeNode> children;

}
