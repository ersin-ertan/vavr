package com.nullcognition.vavr;


/*
* Purely functional data structs ar immutable and persistent, and methods/functions are ref transparent
*
* lists use fifo so the last element inserted is the head
*
* inserted last - head
* inserted mid - my tail is "first"
* insterted first - tail - my tail is null
* */

import java.util.Comparator;
import javaslang.Tuple2;
import javaslang.collection.Array;
import javaslang.collection.List;
import javaslang.collection.Queue;
import javaslang.collection.SortedSet;
import javaslang.collection.Stream;
import javaslang.collection.TreeSet;
import javaslang.collection.Vector;
import javaslang.control.Option;

public class V_1_4_0_Functional_Data_Structs {

  void linkedList() {
    List<Integer> integerList = List.of(1, 2, 3);
  }

  // each object is a separate list node, the tail of the list element is nil
  // ex
  class ListNode<T> {
    T value;
    T next;
  }

  void shareingAcrossLists() {
    // thus values can be shared across lists because we are only moving pointers

    List<Integer> integerList = List.of(1, 2, 3, 4, 5, 6);

    List<Integer> newIntegerList = integerList.tail().prepend(9);
    // now the list has both 9 and 1 pointing to the 2,3,4,5,6

    //integerList.toJavaList()

    // most operations are constant time
    //LinearSeq. for linear time
  }

  void randomAccess() {
    // constant time queries
    Array<Integer> integerArray = Array.of(1, 2, 3);
    integerArray.get(1);
    // array insert and remove are linear time

    // vector is inbetween array and list
    Vector<Integer> integerVector = Vector.of(1, 2, 3);
  }

  void queue() {
    // is implemented by two linked lists, front for dequeued items and read for enqueued, opt @ O(1)

    // list can also be used

    Queue<Integer> integerQueue = Queue.<Integer>empty().enqueue(1, 2, 3);

    // is fifo
    // Front: first, second, third
    // Rear : fifth, fourth

    // front is done enqueue-ing then rear is flipped and sent to Front
    // dequeue-ing returns

    Tuple2<Integer, Queue<Integer>> dequeue = integerQueue.dequeue();
    // will return new queue and first element for persistence
    // 1 , queue(2,3)

    // instead of calling dequeue, call dequeueOption,
    Option<Tuple2<Integer, Queue<Integer>>> dequeueOption = integerQueue.dequeueOption();
    //dequeueOption.getOrElse() todo learn about using option
    dequeueOption.map(integerQueueTuple2 -> {
      // do something
      return integerQueueTuple2._1;
    });
  }

  /*
  * More frequently used than queues, binary serach tree to model them functionally, trees of nodes with
  * up to two children and values at each node
  *
  * binary search trees are built using an ordering via a element Comparator
  *
  * searches run in o(log n) time
  * */
  void sortedSet() {
    Comparator<Integer> c = (a, b) -> b - a;
    SortedSet<Integer> xs = TreeSet.of(c, 1, 3, 7, 9, 6, 8);

    // most tre operations are inherently recursive, java slang usis the red/black tree implementation
    // to keep the tree balanced
  }

}
























































