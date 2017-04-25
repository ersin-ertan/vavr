package com.nullcognition.vavr;

/*
* Tuple, Value, and lambda
* */

import javaslang.Tuple;
import javaslang.Tuple2;

public class V_3_0_0_Usage_Guide {

  Tuple2<String, Integer> tuple2 = Tuple.of("a", 1);

  void tuples() {
    // immutable multi type able
    String a = tuple2._1;
    Integer one = tuple2._2;
  }

  void mapTupleComponentWise() {
    Tuple2<String, Integer> that = tuple2.map(st -> st + " addition", in -> in *= 2);
  }

  void mapATupleWithOneMapper() {
    Tuple2<String, Integer> that = tuple2.map((s, i) -> Tuple.of(s + " additional", i * 2));
  }

  void transformMe() {
    String that = tuple2.transform((s, i) -> s + " additional and" + String.valueOf(i));
  }
}
