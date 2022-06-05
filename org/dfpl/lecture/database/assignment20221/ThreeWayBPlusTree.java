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
		System.out.println("");
		System.out.println("start finding " + key);
		// getNode는 매개변수 key에 해당하는 값이 Tree에 있는지를 확인합니다.

		int i;
		ThreeWayBPlusTreeNode cn;

		// int i와 ThreeWayBPlusTreeNode (이하 Node) cn(copynode)을 선언합니다.

		if (isEmpty()) {
			System.out.println("Tree is empty");	// tree가 비어있을 경우 empty를 출력합니다.
			return null;
		}
		cn = root;	// 변수 cn에 root를 넣고,

		while (!(cn.isLeaf)) {	// cn이 leaf가 아닐 때 반복문을 돌게됩니다.
			i = 0;
			for (;i < cn.m; i++) {
				if (key < cn.keys[i]) {	// leaf가 아닌 cn의 keys[i]값을 매개변수 key와 비교하면서 보다 작다면 좌측 children으로
					System.out.println("");
					System.out.println("less than " + cn.keys[i]);
					cn = cn.children[i];
					break;
				}
				if (i == cn.m - 1) {	// cn의 keys[i]값들이 전부 key보다 크면 i는 cn.m - 1에 도달하게 되어 우측 children으로 갑니다.
					System.out.println("");
					System.out.println("larger than or equal to " + cn.keys[i]);
					cn = cn.children[i + 1];
					break;
				}
			}
			// break된 for문에서 children값이 leaf라면 while문이 종료됩니다.
		}

		for (int j = 0; j < cn.m; j++) {
			if (cn.keys[j] == key) {	// 이제 leaf인 cn의 keys[i] 값에서 key가 발견된다면 key + found를 출력하고 cn을 리턴합니다.
				System.out.println("");
				System.out.println(key + " found");
				return cn;
			}
		}

		// 만약 찾지 못할 경우 not found를 출력하고 null을 리턴합니다.
		System.out.println("");
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
		// inorderTraverse는 중위순회를 구현하는 함수입니다. 재귀를 통해 좌측 
		ThreeWayBPlusTreeNode node = root;
		// Node 변수인 node에 root값을 넣어준 후,
		recursiveInorder(node); // 재귀함수를 시행합니다.
	}

	public void recursiveInorder(ThreeWayBPlusTreeNode node) {
		// 만약 node가 null이 아닐 때,
		if (node != null) {
			if (node.children[0] != null) { // node의 좌측 children이 null이 아니면 재귀함수를 시행합니다.
				recursiveInorder(node.children[0]);
			}
			for (int i = 0; i < node.m; i++) {	// 중위순회는 좌측 children에 가고 난 후에, 우측 children에 가기 전에 key값 들을 출력합니다. 
				System.out.println(node.keys[i]);	// for문을 쓰는 이유는 node.m이 1이라면 좌측과 우측 children만 보면 되지만,
				if (node.children[i] != null) {		// node.m이 1보다 클 경우, 모든 값들을 순회하기 위하여 사용하였습니다.
					recursiveInorder(node.children[i + 1]); // 이후 우측 children으로 재귀함수를 적용합니다.
				}
			}
		}
	}
	@Override
	public Comparator<? super Integer> comparator() {	// 구현하지 않았습니다.
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer first() {
		// TODO Auto-generated method stub
		// first의 경우, children을 이용하여 가장 좌측 leaf로 이동한 후, 첫 번째 값을 리턴하는 식으로 구현하였습니다.

		ThreeWayBPlusTreeNode cn;
		// node cn을 선언합니다.

		cn = root; // cn에 root를 넣고,
		
		while (!(cn.isLeaf)) {	// cn이 leaf가 아닐 동안 while문을 탐색합니다.
			if (cn.children[0] != null) { // cn의 좌측 child이 null이 아닐 경우, cn을 cn의 좌측 child로 만듭니다. 
				cn = cn.children[0];
			}
		}
		return cn.keys[0]; // cn의 첫 번째 값을 리턴합니다.
	}

	@Override
	public Integer last() {
		// TODO Auto-generated method stub
		// last의 경우, leafList에 저장되어 있는 값을 이용하여 leafList의 size - 1 인덱스를 이용하여 마지막 node를 찾고,
		// 마지막 node의 마지막 key값을 리턴하는 식으로 구현하였습니다.

		ThreeWayBPlusTreeNode cn;
		// node cn을 선언합니다.

		cn = leafList.get(leafList.size() - 1); // cn에 leafList의 size - 1 인덱스 값을 이용하여 마지막 node를 넣어줍니다.
		return cn.keys[cn.m - 1];
	}

	@Override
	public int size() { // 구현하지 않았습니다.
		// TODO Auto-generated method stub

		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return root == null; // root가 null인 것을 isEmpty로 설정하였습니다.
	}

	@Override
	public boolean contains(Object o) { // 구현하지 않았습니다.
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object[] toArray() { // 구현하지 않았습니다.
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) { // 구현하지 않았습니다.
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(Integer e) {
		// TODO Auto-generated method stub
		// add는 3Way인 것을 감안하여 적재적소에 배열의 index값을 이용하여 배치해주었습니다.
		// 자세한 설명은 주석을 통해 설명하겠습니다.

		if (isEmpty()) { // 만약 root가 null일 경우 init을 해주었습니다.
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
			ThreeWayBPlusTreeNode parent = root; // 특이점은 parent를 add함수 내의 변수로 선언함으로써 유기적으로 활용하였습니다.

			while (!cn.isLeaf) { // cn이 leaf가 아닐 때 while문을 돌며,
				i = 0;
				for (;i < cn.m; i++) { // for문을 이용해 cn.m 개수 만큼 index를 탐색합니다.
					parent = cn; // children으로 가기전에 parent에 cn을 넣어줍니다.
					if (e < cn.keys[i]) { // 매개 변수 e가 cn.keys[i]값보다 작을 때는, 해당하는 i 인덱스 값을 이용해 좌측 child로,
						cn = cn.children[i];
						break;
					}
					if (i == cn.m - 1) { // 모든 cn.keys[i]값이 e보다 클 경우, 마지막 index에 우측 child로 넣어줍니다.
						cn = cn.children[i + 1];
						break;
					}
				}
			}

			if (cn.m < T) { // 만약 cn.m 이 (T == 3, 3way이므로) T보다 작을 경우
				i = 0;
				while (i < cn.m && e > cn.keys[i]) { // while문을 통해 e를 기준으로 i 인덱스의 위치를 구하고
					i++;
				}

				for (int k = cn.m; k > i; k--) { // cn.keys값을 i를 기준으로 오른쪽으로 옮겨준 뒤,
					cn.keys[k] = cn.keys[k - 1];
				}

				cn.keys[i] = e;	// 해당 i 인덱스 값에 cn.keys[i]에 e를 넣어줍니다.
				cn.m++;	// 개수가 늘었으니 m을 1 증가시켜줍니다.

				cn.children[cn.m] = cn.children[cn.m - 1]; // children의 경우도 오른쪽으로 한 칸 옮겨준 후,
				cn.children[cn.m - 1] = null; // 이전의 children을 null로 만들어줍니다.
			}
			if (cn.m == T) { // 만약 위의 if문을 통해 cn.m이 T가 되었을 경우 split해주어야 합니다.
				ThreeWayBPlusTreeNode newLeaf = new ThreeWayBPlusTreeNode();
				Integer[] cpNode = new Integer[T]; // newLeaf와 cn에 keys값을 넣어주기 위해 Integer배열 cpNode를 선언합니다.
				int j;
				int	check = 0; // int check는 밑에 leafList에 remove를 해야할 때, 해당하는 index값을 찾기 위해 작동합니다. 

				for (j = 0; j < T; j++) {
					cpNode[j] = cn.keys[j]; // 먼저 cpNode에 cn.keys 값들을 넣어줍니다.
				}

				cn.isLeaf = true; // cn은 split시 왼쪽 child로써, isLeaf를 true로 설정해줍니다.
				newLeaf.isLeaf = true; // newLeaf는 split시 오른쪽 child로써, isLeaf를 true로 설정해줍니다.

				cn.m = 1; // split시 왼쪽 child로써 작동하는 cn의 m 값은 1,
				newLeaf.m = 2; // newLeaf의 m 값은 2로 설정해줍니다. (3Way이기 때문에 split시 왼쪽 node는 key 1개, 오른쪽 node는 key 2개이기 때문입니다.)

				for (i = 0; i < cn.m; i++) { // cn.keys값에 cn.m만큼 cpNode의 값을 넣어줍니다.
					cn.keys[i] = cpNode[i];
				}

				for (i = 0, j = cn.m; i < newLeaf.m; i++, j++) { // newLeaf.keys의 i = 0부터, cpNode의 j = cn.m부터 이용하여
					newLeaf.keys[i] = cpNode[j];	// newLeaf.m만큼 newLeaf에 값을 넣어줍니다.
				}

				if (cn.isLeaf) { // cn이 leaf일 시,
					for (ThreeWayBPlusTreeNode x : leafList) { // leafList의 값들을 탐색합니다.
						int checkPoint = 0; // checkPoint를 만들어서
						for (int y = 0; y < x.m; y++) {
							if (x.keys[y] == cn.keys[y]) { // x.keys[y]와 cn.keys[y]의 값이 같을 경우,
								checkPoint = -1;
								break;
							}
						}
						if (checkPoint == -1) { // for문을 끝내게 됩니다.
							break;
						}
						check++; // 이는 x.keys값을 통해 check를 ++해주며 x.keys값이 cn.keys값과 일치하는 leafList내의 index값을 지칭합니다.
					}
					
					if (leafList.size() == 1) { // leafList의 size가 1이면 remove를 해주며,
						leafList.remove();
					}
					else if (leafList.size() != 1 && check < leafList.size()) { // size가 1이 아니고 leafList index에 해당하는 check가
						leafList.remove(check);	// leafList의 size보다 작다면 remove(index)를 통해 해당 index의 node를 지워줍니다.
					}
					else if (check == leafList.size() && cn.keys[0] < leafList.get(0).keys[0]){ // check가 leafList.size()
						// 와 같다는 뜻은, leafList 내에서 x.keys 값을 못찾았다는 뜻이므로 cn.keys[0] 값과 leafList(get(0).keys[0])을 비교하여
						// 만약 cn.keys[0]이 더 작다면, 새로운 cn.keys[0]값이 해당 leafList 내에서 가장 작으므로 leafList 첫 번째 index의 값을 지워주고
						// 그 자리에 cn과 newLeaf를 추가해야 합니다. 따라서 removeFirst를 사용합니다.
						leafList.removeFirst();
					}
					else if (check == leafList.size() && cn.keys[0] > leafList.get(leafList.size() - 1).keys[0]){
						// 위의 사례와 반대로, cn.keys[0]이 leafList의 마지막 node의 값보다 큰 경우 이므로, leafList의 마지막 index의 값을 지워주고
						// 그 자리에 cn과 newLeaf를 추가해야 합니다. 따라서 removeLast를 사용합니다.
						leafList.removeLast();
					}
					
					leafList.add(cn);
					leafList.add(newLeaf); // 이후 node cn과 newLeaf를 리스트에 추가해줍니다.

					Collections.sort(leafList, new Comparator<ThreeWayBPlusTreeNode>() {
						@Override
						public int compare(ThreeWayBPlusTreeNode o1, ThreeWayBPlusTreeNode o2) {
							// leafList의 경우 두 개의 node의 keys 값을 토대로 Sort 해주어야 합니다. 이는 node에 해당하는 key가 여러 개 일 수 있으므로
							// 해당하는 node의 마지막 key값을 토대로 sort해야 합니다. 이에 해당하는 삼항 연산자는 다음과 같습니다.
							return (o1.keys[o1.m - 1] > o2.keys[o2.m - 1]) ? 1 : -1;
						}
					});
				}

				if (cn == root) { // cn이 root 일 경우,
					ThreeWayBPlusTreeNode newRoot = new ThreeWayBPlusTreeNode(); // new root와
					ThreeWayBPlusTreeNode newChild = new ThreeWayBPlusTreeNode(); // new child(왼쪽 node)를 선언하여
					// 적절한 index값을 통해 split을 진행해줍니다.
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
				else { // 만약 cn이 root가 아닐 경우, addInternal을 진행합니다.
					addInternal(newLeaf.keys[0], parent, newLeaf); // newLeaf.keys[0] (key가 3개로 찼을 경우 index 1의 값)
					// parent와 newLeaf를 매개변수로 받습니다.
				}
			}
		}
		return true;
	}

	public boolean addInternal(Integer x, ThreeWayBPlusTreeNode cn, ThreeWayBPlusTreeNode child) {
		// TODO Auto-generated method stub
		if (cn.m < T) {	// parent를 매개 변수로 받은 cn.m이 (T==3)보다 작을 경우,
			int i = 0;
			while (i < cn.m && x > cn.keys[i]) { // while문을 통해 해당하는 index값을 구하고,
				i++;
			}
			for (int j = cn.m; j > i; j--) { // cn.keys 값을 i를 기준으로 오른쪽으로 한 칸 씩 이동합니다.
				cn.keys[j] = cn.keys[j - 1];
			}
			
			for (int k = cn.m + 1; k > i + 1; k--) { // cn.children 또한 i를 기준으로 오른쪽으로 한 칸 씩 이동합니다.
				cn.children[k] = cn.children[k - 1];
			}
			
			cn.keys[i] = x; // 해당하는 i의 인덱스 값에 x를 넣어줍니다.
			cn.m++;
			cn.children[i + 1] = child; // cn(parent)의 오른쪽 child 값으로 매개변수 child(newLeaf)를 넣어줍니다.
		}
	
		if (cn.m == T) { // 만약 cn.m이 (T==3)이 될 경우, split을 진행해줍니다.
			ThreeWayBPlusTreeNode newInternalNode = new ThreeWayBPlusTreeNode();
			Integer[] cpKeys = new Integer[T + 1];
			ThreeWayBPlusTreeNode[] cpNodes = new ThreeWayBPlusTreeNode[T + 2];

			int i = 0, j = 0;

			for (i = 0; i < T; i++) {
				cpKeys[i] = cn.keys[i]; // cpKeys에 cn.keys 값들을,
			}

			for (i = 0; i < T + 1; i++) { // cpNodes에 cn.children 값들을 넣어줍니다.
				cpNodes[i] = cn.children[i];
			}

			i = 0;

			while (i < T && x > cpKeys[i]) { // x를 기준으로 i++을 통해 index를 찾아주고,
				i++;
			}

			for (j = T; j > i; j--) { // i를 기준으로 오른쪽으로 한 칸 씩 값들을 이동해줍니다.
				cpKeys[j] = cpKeys[j - 1];
			}

			for (j = T + 1; j > i + 1; j--) { // i + 1을 기준으로 cpNodes도 오른쪽으로 한 칸 씩 값들을 이동해줍니다.
				cpNodes[j] = cpNodes[j - 1];
			}

			cpKeys[i] = x; // i에 해당하는 index 값에 x값을 넣어줍니다.
			cpNodes[i + 1] = child; // i + 1에 해당하는 index 값에 child를 넣어줍니다.

			newInternalNode.isLeaf = false; // 

			cn.m = 1; // split 시, 왼쪽 child에 해당하는 cn의 m 값은 1,
			newInternalNode.m = 1; // split 시, 오른쪽 child에 해당하는 node의 m 값은 1로 설정해줍니다.
			
			if (i == 0) { // index가 만약 0일 경우, (새롭게 추가된 x의 값이 cpkeys의 첫 번째 위치일 경우)
				for (i = 0, j = cn.m + 2; i < newInternalNode.m; i++, j++) {
					newInternalNode.keys[i] = cpKeys[j]; // newInternalNode에 cpKeys의 인덱스 cn.m + 2 값을 넣어주고,
				}
	
				for (i = 0, j = cn.m + 2; i < newInternalNode.m + 1; i++, j++) {
					newInternalNode.children[i] = cpNodes[j]; // newInternalNode child에 cpNodes의 인덱스 cn.m + 2 값을 넣어줍니다.
				}	
			}
			else { // 그렇지 않다면,
				for (i = 0, j = cn.m + 1; i < newInternalNode.m; i++, j++) {
					newInternalNode.keys[i] = cpKeys[j]; // newInternalNode에 cpKeys의 인덱스 cn.m + 1 값을 넣어주고,
				}

				for (i = 0, j = cn.m + 1; i < newInternalNode.m + 1; i++, j++) {
					newInternalNode.children[i] = cpNodes[j]; // newInternalNode child에 cpNodes의 인덱스 cn.m + 1 값을 넣어줍니다.
				}
			}

			if (cn == root) { // 만약 cn이 root일 경우,
				ThreeWayBPlusTreeNode newRoot = new ThreeWayBPlusTreeNode();

				// 적절한 index값을 통해 split을 진행해줍니다.
				newRoot.keys[0] = cn.keys[1];
				
				newRoot.children[0] = cn;
				newRoot.children[1] = newInternalNode;
				newRoot.isLeaf = false;
				newRoot.m = 1;
				root = newRoot;
			}
			else {
				// 만약 cn이 root가 아닐 경우, addInternal을 재귀로 진행합니다.
				addInternal(cn.keys[cn.m], findParent(root, cn), newInternalNode);
				// 매개변수로 cn.keys[cn.m], findParent를 통해 parent를 찾은 값, 그리고 newInternalNode를 받습니다.
			}
		}
		return true;
	}

	public ThreeWayBPlusTreeNode findParent(ThreeWayBPlusTreeNode cn, ThreeWayBPlusTreeNode child) {
		// TODO Auto-generated method stub
		// findParent는 root와 cn을 통해 해당하는 parent를 구하는 재귀함수입니다.
		ThreeWayBPlusTreeNode realParent;

		if (cn.isLeaf || (cn.children[0].isLeaf)) { // cn이 leaf거나, cn의 좌측 child가 leaf면 null을 반환합니다.
			return null;
		}

		for (int i = 0; i < cn.m + 1; i++) { // cn.m + 1만큼 반복하는 for문을 통해
			if (cn.children[i] == child) { // cn.children[i]에 해당하는 매개변수 child가 있다면
				realParent = cn; // realParent에 cn을 넣어 리턴합니다.
				return realParent;
			}
			else { // 그렇지 않을 경우 cn.children[i]를 매개변수로 재귀함수를 진행합니다.
				realParent = findParent(cn.children[i], child);
				if (realParent != null) { // 만약 realParent가 null이 아닐 때, realParent를 리턴합니다.
					return realParent;
				}
			}
		}
		return null;
	}

	@Override
	public boolean remove(Object o) { // 구현하지 않았습니다.
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) { // 구현하지 않았습니다.
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends Integer> c) { // 구현하지 않았습니다.
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) { // 구현하지 않았습니다.
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) { // 구현하지 않았습니다.
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() { // 구현하지 않았습니다.
		// TODO Auto-generated method stub

	}

	@Override
	public Integer lower(Integer e) { // 구현하지 않았습니다.
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer floor(Integer e) { // 구현하지 않았습니다.
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer ceiling(Integer e) { // 구현하지 않았습니다.
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer higher(Integer e) { // 구현하지 않았습니다.
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer pollFirst() { // 구현하지 않았습니다.
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer pollLast() { // 구현하지 않았습니다.
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Integer> iterator() {
		// TODO Auto-generated method stub
		// iterator는 leafList에 해당하는 값들을 저장하고 iterator로 변환 시켜주는 함수입니다.

		ArrayList<Integer> list = new ArrayList<Integer>(); // 먼저 list라는 ArrayList<Integer>를 선언합니다.
		
		for (int i = 0; i < leafList.size(); i++) { // leafList의 size만큼
			for (int j = 0; j < leafList.get(i).m; j++) { // leafList의 인덱스에 해당하는 node의 m만큼 2중 반복문을 돌며
				if (list.contains(leafList.get(i).keys[j])) { // 만약 그 값이 중첩된다면 continue 시켜주고
					continue;
				}
				list.add(leafList.get(i).keys[j]); // 그렇지 않다면 list에 그 값들을 저장합니다.
				
			}
		}
		Collections.sort(list, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) { // 저장된 값들을 sort 해주고,
				return (o1 > o2) ? 1 : -1;
			}
		});
		
		Iterator<Integer> leafs = list.iterator(); // 이를 Iterator<Integer> leafs에 list.iterator()를 통해 넣어주고 leafs를 리턴합니다.
		return leafs;
	}

	@Override
	public NavigableSet<Integer> descendingSet() { // 구현하지 않았습니다.
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Integer> descendingIterator() { // 구현하지 않았습니다.
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NavigableSet<Integer> subSet(Integer fromElement, boolean fromInclusive, Integer toElement,
			boolean toInclusive) { // 구현하지 않았습니다.
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NavigableSet<Integer> headSet(Integer toElement, boolean inclusive) { // 구현하지 않았습니다.
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NavigableSet<Integer> tailSet(Integer fromElement, boolean inclusive) { // 구현하지 않았습니다.
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedSet<Integer> subSet(Integer fromElement, Integer toElement) { // 구현하지 않았습니다.
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedSet<Integer> headSet(Integer toElement) { // 구현하지 않았습니다.
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedSet<Integer> tailSet(Integer fromElement) { // 구현하지 않았습니다.
		// TODO Auto-generated method stub
		return null;
	}

}