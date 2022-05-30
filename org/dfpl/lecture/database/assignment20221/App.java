package org.dfpl.lecture.database.assignment20221;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Random;
import java.util.TreeSet;

public class App {

	public static void main(String[] args) {
		System.out.println("Assignment 4: ");
		ThreeWayBPlusTree bpTree = new ThreeWayBPlusTree();
		for (int i = 1; i < 18; i++) {
			bpTree.add(i);
		}
		bpTree.getNode(15);
		System.out.println();
		bpTree.getNode(9);
		System.out.println();
		bpTree.inorderTraverse();
		/*
		System.out.println("Assignment 5: ");
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		Random r = new Random();
		for (int i = 0; i < 100; i++) {
			list.add(r.nextInt(10000));
		}
		NavigableSet<Integer> treeSet = new TreeSet<Integer>();
		for (Integer val : list) {
			treeSet.add(val);
		}

		NavigableSet<Integer> yourBTree = new ThreeWayBPlusTree();
		for (Integer val : list) {
			yourBTree.add(val);
		}

		System.out.println("first test: " + treeSet.first().equals(yourBTree.first()));
		System.out.println("last test: " + treeSet.last().equals(yourBTree.last()));
		Iterator<Integer> treeIterator = treeSet.iterator();
		Iterator<Integer> yourBTreeIterator = yourBTree.iterator();
		boolean isPass = true;
		while (treeIterator.hasNext() && yourBTreeIterator.hasNext()) {
			if (!treeIterator.next().equals(yourBTreeIterator.next())) {
				isPass = false;
				break;
			}
		}
		System.out.println("iterator test: " + isPass);

		for (int i = 0; i < list.size() / 2; i++) {
			treeSet.remove(list.get(i));
			yourBTree.remove(list.get(i));
		}
		treeIterator = treeSet.iterator();
		yourBTreeIterator = yourBTree.iterator();
		isPass = true;
		while (treeIterator.hasNext() && yourBTreeIterator.hasNext()) {
			if (!treeIterator.next().equals(yourBTreeIterator.next())) {
				isPass = false;
				break;
			}
		}
		System.out.println("remove test: " + isPass);*/
	}
}
