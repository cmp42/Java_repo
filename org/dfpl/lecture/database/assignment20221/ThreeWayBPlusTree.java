package org.dfpl.lecture.database.assignment20221;

import java.util.*;

@SuppressWarnings("unused")
public class ThreeWayBPlusTree implements NavigableSet<Integer> {

	// Data Abstraction은 예시일 뿐 자유롭게 B+ Tree의 범주 안에서 어느정도 수정가능
	public static final int T = 3;
	private ThreeWayBPlusTreeNode root;
	private LinkedList<ThreeWayBPlusTree> leafList;
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
	}

	@Override
	public Comparator<? super Integer> comparator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer first() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer last() {
		// TODO Auto-generated method stub
		return null;
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
		ThreeWayBPlusTreeNode newNode = new ThreeWayBPlusTreeNode();
		if (isEmpty()) {
			root = newNode;
			newNode.keyList.add(e);
			newNode.m++;
		}
		else {
			int i;
			ThreeWayBPlusTreeNode cn;
			cn = root;
			for (i = 0; i < cn.m; i++) {
				if (cn.keyList.get(i) > e) {
					if (cn.left != null){
						cn = cn.left;
						i = 0;
						continue;
					}
				}
			}
			for (i = 0; i < cn.m; i++) {
				if (cn.keyList.get(i) <= e) {
					if (cn.right != null){
						System.out.println("진행이 되나요");
						cn = cn.right;
						i = 0;
						continue;
					}
				}
			}
			if (cn.m < T) {
				cn.keyList.add(e);
				cn.m++;
				for (int j = 0; j < cn.m; j++) {
					System.out.println(cn.keyList.get(j));
				}
			}
			if (cn.m == T) {
				if (cn.parent == null) {
					ThreeWayBPlusTreeNode newRoot = new ThreeWayBPlusTreeNode();
					ThreeWayBPlusTreeNode newLeft = new ThreeWayBPlusTreeNode();
					ThreeWayBPlusTreeNode newRight;
					newRoot.keyList.add(cn.keyList.get(1));
					newRoot.m++;
					newLeft.keyList.add(cn.keyList.get(0));
					newLeft.m++;
					if (cn.left != null) {
						newLeft.left = cn.left;
						newLeft.right = cn.left.link;
					}
					newRight = cn;
					if (cn.middle == null) {
						newRight.left = newLeft;
					}
					else {
						newRight.left = cn.middle;
					}
					newRight.m--;
					newRight.keyList.remove(0);
					newRoot.left = newLeft;
					newRoot.right = newRight;
					newLeft.parent = newRoot;
					newLeft.link = newRight;
					newRight.parent = newRoot;
					root = newRoot;
				}
				else if (cn.parent != null && cn.parent.m < T) {
					System.out.println("이게 실행이 되어야하는데");
					ThreeWayBPlusTreeNode newMiddle = new ThreeWayBPlusTreeNode();
					newMiddle.keyList.add(cn.keyList.get(0));
					cn.keyList.remove(0);
					cn.parent.keyList.add(cn.keyList.get(0));
					cn.parent.middle = newMiddle;
					cn.parent.left.link = cn.parent.middle;
					cn.parent.link = cn;
					cn.parent.m++;
					cn.m--;
				}
			}
		}
		System.out.println("");
		return true;
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
		return null;
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