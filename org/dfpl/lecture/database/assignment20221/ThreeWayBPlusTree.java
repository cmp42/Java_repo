package org.dfpl.lecture.database.assignment20221;

import java.util.*;

@SuppressWarnings("unused")
public class ThreeWayBPlusTree implements NavigableSet<Integer> {

	// Data Abstraction은 예시일 뿐 자유롭게 B+ Tree의 범주 안에서 어느정도 수정가능
	public static final int T = 3;
	public ThreeWayBPlusTreeNode root;
	public LinkedList<ThreeWayBPlusTreeNode> leafList = new LinkedList<ThreeWayBPlusTreeNode>();
	/**
	 * 과제 Assignment4를 위한 메소드:
	 * 
	 * key로 검색하면 root부터 시작하여, key를 포함할 수 있는 leaf node를 찾고 key가 실제로 존재하면 해당 Node를
	 * 반환하고, 그렇지 않다면 null을 반환한다. 중간과정을 System.out.println(String) 으로 출력해야 함. 3 way
	 * B+ tree에서 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17 이 순서대로
	 * add되었다고 했을 때,
	 * 
	 * 예: getNode(15)을 수행하였을 때 
	 * > start finding 15
	 * > larger than or equal to 9
	 * > larger than or equal to 13
	 * > larger than or equal to 15
	 * > less than 16
	 * > 15 found 
	 * 위의 6 문장을
	 * 콘솔에 출력하고 15를 포함한 ThreeWayBPlusTreeNode를 반환함
	 * 
	 * @param key
	 * @return
	 */
	public ThreeWayBPlusTreeNode getNode(Integer key) {
		// TODO Auto-generated method stub
		System.out.println("start finding " + key);
		int i;
		ThreeWayBPlusTreeNode cn;
		if (isEmpty()) {
			System.out.println("Tree is empty");
			return null;
		}
		cn = root;
		while (!(cn.isLeaf)) {
			i = 0;
			for (;i < cn.m; i++) {
				if (key < cn.keys[i]) {
					System.out.println("less than " + cn.keys[i]);
					cn = cn.children[i];
					break;
				}
				if (i == cn.m - 1) {
					System.out.println("larger than or equal to " + cn.keys[i]);
					cn = cn.children[i + 1];
					break;
				}
			}
		}
		for (int j = 0; j < cn.m; j++) {
			if (cn.keys[j] == key) {
				System.out.println(key + " found");
				return cn;
			}
		}
		System.out.println(key + " not found");
		return null;
	}
	
	/**
	 * 과제 Assignment4를 위한 메소드: 
	 * 
	 * inorder traversal을 수행하여, 값을 오름차순으로 출력한다. 
	 * 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17 이 순서대로
	 * add되었다고 했을 때,
	 * 1
	 * 2
	 * 3
	 * 4
	 * 5
	 * 6
	 * 7
	 * 8
	 * 9
	 * 10
	 * 11
	 * 12
	 * 13
	 * 14
	 * 15
	 * 16
	 * 17
	 * 위와 같이 출력되어야 함. 
	 * Note: Bottom의 LinkedList 순회를 하면 안됨
	 */

	
	public void inorderTraverse() {
		// TODO Auto-generated method stub
		ThreeWayBPlusTreeNode node = root;
		recursiveInorder(node);
	}

	public void recursiveInorder(ThreeWayBPlusTreeNode node) {
		if (node != null) {
			if (node.children[0] != null) {
				recursiveInorder(node.children[0]);
			}
			for (int i = 0; i < node.m; i++) {
				System.out.println(node.keys[i]);
			}
			if (node.children[node.m - 1] != null) {
				recursiveInorder(node.children[1]);
			}
		}
	}
	@Override
	public Comparator<? super Integer> comparator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer first() {
		// TODO Auto-generated method stub
		int i;
		ThreeWayBPlusTreeNode cn;
		cn = root;
		while (!(cn.isLeaf)) {
			i = 0;
			for (;i < cn.m; i++) {
				if (cn.children[i] != null) {
					cn = cn.children[i];
					break;
				}
			}
		}
		return cn.keys[0];
	}

	@Override
	public Integer last() {
		// TODO Auto-generated method stub
		int j = 0;
		ThreeWayBPlusTreeNode cn;
		cn = leafList.get(leafList.size() - 1);
		while (j < cn.m) j++;
		return cn.keys[j - 1];
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub

		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return root == null;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(Integer e) {
		// TODO Auto-generated method stub
		if (isEmpty()) {
			ThreeWayBPlusTreeNode newNode = new ThreeWayBPlusTreeNode();
			root = newNode;
			newNode.keys[0] = e;
			newNode.isLeaf = true;
			newNode.m++;
			leafList.add(newNode);
		}
		else {
			int i;
			ThreeWayBPlusTreeNode cn = root;
			ThreeWayBPlusTreeNode parent = root;
			while (!cn.isLeaf) {
				i = 0;
				for (;i < cn.m; i++) {
					parent = cn;
					if (e < cn.keys[i]) {
						cn = cn.children[i];
						break;
					}
					if (i == cn.m - 1) {
						cn = cn.children[i + 1];
						break;
					}
				}
			}

			if (cn.m < T) {
				i = 0;
				while (i < cn.m && e > cn.keys[i]) {
					i++;
				}

				for (int k = cn.m; k > i; k--) {
					cn.keys[k] = cn.keys[k - 1];
				}

				cn.keys[i] = e;
				cn.m++;

				cn.children[cn.m] = cn.children[cn.m - 1];
				cn.children[cn.m - 1] = null;
			}
			if (cn.m >= T) {
				ThreeWayBPlusTreeNode newLeaf = new ThreeWayBPlusTreeNode();
				Integer[] cpNode = new Integer[T + 2];
				int j;
				int	check = 0;
				for (j = 0; j < T; j++) {
					cpNode[j] = cn.keys[j];
				}
				/*
				i = 0;
				j = 0;
				while (i < T && e > cpNode[i]) {
					i++;
				}
				
				for (j = T + 1; j > i; j--) {
					cpNode[j] = cpNode[j - 1];
				}
				cpNode[i] = e;
				*/
				newLeaf.isLeaf = true;

				cn.m = 1;
				newLeaf.m = 2;

				cn.children[cn.m] = newLeaf;

				newLeaf.children[newLeaf.m] = cn.children[T];

				cn.children[T] = null;

				for (i = 0; i < cn.m; i++) {
					cn.keys[i] = cpNode[i];
				}

				for (i = 0, j = cn.m; i < newLeaf.m; i++, j++) {
					newLeaf.keys[i] = cpNode[j];
				}

				if (cn.isLeaf) {
					for (ThreeWayBPlusTreeNode x : leafList) {
						int checkPoint = 0;
						for (int y = 0; y < x.m; y++) {
							if (x.keys[y] == cn.keys[y]) {
								checkPoint = -1;
								break;
							}
						}
						if (checkPoint == -1) {
							break;
						}
						check++;
					}
					
					if (leafList.size() == 1) {
						leafList.remove();
					}
					else if (leafList.size() != 1 && check < leafList.size()) {
						leafList.remove(check);
					}
					else if (check == leafList.size() && cn.keys[0] < leafList.get(0).keys[0]){
						leafList.removeFirst();
					}
					else if (check == leafList.size() && cn.keys[0] > leafList.get(leafList.size() - 1).keys[0]){
						leafList.removeLast();
					}
					
					leafList.add(cn);
					leafList.add(newLeaf);
					Collections.sort(leafList, new Comparator<ThreeWayBPlusTreeNode>() {
						@Override
						public int compare(ThreeWayBPlusTreeNode o1, ThreeWayBPlusTreeNode o2) {
							return (o1.keys[o1.m - 1] > o2.keys[o2.m - 1]) ? 1 : -1;
						}
					});
				}

				if (cn == root) {
					ThreeWayBPlusTreeNode newRoot = new ThreeWayBPlusTreeNode();
					ThreeWayBPlusTreeNode newChild = new ThreeWayBPlusTreeNode();
					newRoot.keys[0] = newLeaf.keys[0];
					newChild.keys[0] = cn.keys[0];
					newRoot.children[0] = newChild;
					newRoot.children[1] = newLeaf;
					newRoot.isLeaf = false;
					newChild.isLeaf = true;
					newChild.m = 1;
					newRoot.m = 1;
					root = newRoot;
				}
				else {
					addInternal(newLeaf.keys[0], parent, newLeaf);
				}
			}
		}
		return true;
	}

	public boolean addInternal(Integer x, ThreeWayBPlusTreeNode cn, ThreeWayBPlusTreeNode child) {
		// TODO Auto-generated method stub
		if (cn.m < T) {
			int i = 0;
			while (i < cn.m && x > cn.keys[i]) {
				i++;
			}
			for (int j = cn.m; j > i; j--) {
				cn.keys[j] = cn.keys[j - 1];
			}
			
			for (int k = cn.m + 1; k > i + 1; k--) {
				cn.children[k] = cn.children[k - 1];
			}
			
			cn.keys[i] = x;
			cn.m++;
			cn.children[i + 1] = child;
		}
	
		if (cn.m >= T) {
			ThreeWayBPlusTreeNode newInternalNode = new ThreeWayBPlusTreeNode();
			Integer[] cpKeys;
			ThreeWayBPlusTreeNode[] cpNodes;

			cpKeys = new Integer[T + 1];
			cpNodes = new ThreeWayBPlusTreeNode[T + 2];

			for (int i = 0; i < T; i++) {
				cpKeys[i] = cn.keys[i];
			}

			for (int i = 0; i < T + 1; i++) {
				cpNodes[i] = cn.children[i];
			}

			int i = 0, j = 0;

			while (i < T && x > cpKeys[i]) {
				i++;
			}

			for (j = T; j > i; j--) {
				cpKeys[j] = cpKeys[j - 1];
			}
			cpKeys[i] = x;
			
			for (j = T + 1; j > i + 1; j--) {
				cpNodes[j] = cpNodes[j - 1];
			}
			cpNodes[i + 1] = child;
			newInternalNode.isLeaf = false;

			cn.m = 1;
			newInternalNode.m = 1;
			
			if (i == 0) {
				for (i = 0, j = cn.m + 2; i < newInternalNode.m; i++, j++) {
					newInternalNode.keys[i] = cpKeys[j];
				}
	
				for (i = 0, j = cn.m + 2; i < newInternalNode.m + 1; i++, j++) {
					newInternalNode.children[i] = cpNodes[j];
				}	
			}
			else {
				for (i = 0, j = cn.m + 1; i < newInternalNode.m; i++, j++) {
					newInternalNode.keys[i] = cpKeys[j];
				}

				for (i = 0, j = cn.m + 1; i < newInternalNode.m + 1; i++, j++) {
					newInternalNode.children[i] = cpNodes[j];
				}
			}

			if (cn == root) {
				ThreeWayBPlusTreeNode newRoot = new ThreeWayBPlusTreeNode();

				newRoot.keys[0] = cn.keys[1];
				
				newRoot.children[0] = cn;
				newRoot.children[1] = newInternalNode;
				newRoot.isLeaf = false;
				newRoot.m = 1;
				root = newRoot;
			}
			else {
				addInternal(cn.keys[cn.m], findParent(root, cn), newInternalNode);
			}
		}
		return false;
	}

	public ThreeWayBPlusTreeNode findParent(ThreeWayBPlusTreeNode cn, ThreeWayBPlusTreeNode child) {
		// TODO Auto-generated method stub
		ThreeWayBPlusTreeNode realParent;

		if (cn.isLeaf || (cn.children[0].isLeaf)) {
			return null;
		}

		for (int i = 0; i < cn.m + 1; i++) {
			if (cn.children[i] == child) {
				realParent = cn;
				return realParent;
			}
			else {
				realParent = findParent(cn.children[i], child);
				if (realParent != null) {
					return realParent;
				}
			}
		}
		return null;
	}
	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends Integer> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public Integer lower(Integer e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer floor(Integer e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer ceiling(Integer e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer higher(Integer e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer pollFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer pollLast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Integer> iterator() {
		// TODO Auto-generated method stub
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for (int i = 0; i < leafList.size(); i++) {
			for (int j = 0; j < leafList.get(i).m; j++) {
				if (list.contains(leafList.get(i).keys[j])) {
					continue;
				}
				list.add(leafList.get(i).keys[j]);
				
			}
		}
		Collections.sort(list, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return (o1 > o2) ? 1 : -1;
			}
		});
		
		Iterator<Integer> leafs = list.iterator();
		return leafs;
	}

	@Override
	public NavigableSet<Integer> descendingSet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Integer> descendingIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NavigableSet<Integer> subSet(Integer fromElement, boolean fromInclusive, Integer toElement,
			boolean toInclusive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NavigableSet<Integer> headSet(Integer toElement, boolean inclusive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NavigableSet<Integer> tailSet(Integer fromElement, boolean inclusive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedSet<Integer> subSet(Integer fromElement, Integer toElement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedSet<Integer> headSet(Integer toElement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedSet<Integer> tailSet(Integer fromElement) {
		// TODO Auto-generated method stub
		return null;
	}

}