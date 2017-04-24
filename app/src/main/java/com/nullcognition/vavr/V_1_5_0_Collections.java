package com.nullcognition.vavr;

import android.util.Log;
import javaslang.Tuple;
import javaslang.Tuple2;
import javaslang.collection.List;
import javaslang.collection.Stream;

public class V_1_5_0_Collections {

  List<String> words = List.of("this", "the", "than", "that", "then", "those");
  List<Integer> ints = List.of(1, 2, 3, 4, 5, 6, 7, 8);

  void collections() {
    Stream.of(1, 2, 3).map(integer -> integer * 2);
  }


  /*
  * All collections are iterable and can be used in the for each statements
  *
  * iterable -> Traversable -> Seq -> {IndexedSeq, LinearSeq}
  * IndexedSeq ->{Array, CharSeq, Vector}
  * LinearSeq ->{Stack-List, Stream, Queue} // not sure why stack and list are connected
  * */

  void alternativeForEach() {
    for (String s : List.of("this", "is", "a", "list")) {
      Log.v("", s.toUpperCase());
    }

    /// or use

    List.of("words", "to", "use").forEach(s -> Log.v("", s.toUpperCase()));
  }

  void getRidOfTheNoise() {
    List.of("simple", "to", "operate", "on").intersperse(", ").fold("", String::concat);

    // or - instead of having a join method join(Strings...) {} do above

    // you can use something as simple as this
    List.of(words).mkString(", "); // with delimiter
  }

  void setAndMap() {
    // Iterable -> traversable -> {Set, Map}
    // Set -> {linkedHashSet, HashSet, SortedSet -> TreeSet}
    // Map -> {LinkedHashMap, HashMap, SortedMap -> TreeMap}

    // hash map is backed by a hash array mapped trie hamt @see{http://lampwww.epfl.ch/papers/idealhashtrees.pdf}

    // no need for Entry type, just use Tuple2

    Tuple2<Integer, String> entry = Tuple.of(1, "a");

    Integer key = entry._1;
    String value = entry._2;

    // tuples are a must for multi value return types
    words.zipWithIndex(); // (this, 0), (the, 1), (than, 2) ...
    ints.groupBy(i-> i%2); // even or odd HashMap((0,List(2,4,6), (1,List(1,3,5));

    // try https://projecteuler.net/archives
  }
}
