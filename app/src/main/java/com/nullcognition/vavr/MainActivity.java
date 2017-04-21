package com.nullcognition.vavr;

import android.app.Activity;
import android.os.Bundle;
import javaslang.Function2;
import javaslang.Function3;
import javaslang.Tuple;
import javaslang.Tuple2;
import javaslang.control.Option;

import static javaslang.API.$;
import static javaslang.API.Case;
import static javaslang.API.Match;

public class MainActivity extends Activity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Tuple2<String, Integer> java8 = Tuple.of("Java", 8);
    String str = java8._1;
    Integer in = java8._2;

    Tuple2<String, Integer> mapComponentWise = java8.map(s -> s + "slang", i -> i / 4);
    Tuple2<String, Integer> mapUsingOneMapper = java8.map((s, i) -> Tuple.of(s + "slang", i / 4));

    String transformTuple = java8.transform((s, i) -> s + "s" + i / 4);

    Function2<Integer, Integer, Integer> sum = (a, b) -> a + b;

    // which is short hand for
    Function2<Integer, Integer, Integer> oldWayToSum = new Function2<Integer, Integer, Integer>() {
      @Override public Integer apply(Integer a, Integer b) {
        return a + b;
      }
    };

    oldWayToSum.apply(1, 2);
    sum.apply(1, 2);

    Function3<String, String, String, String> func3 = Function3.of(this::methodAccepting3Params);

    // Pattern Matching

    int intToMatch = 1;
    String patternMatch =
        Match(intToMatch).of(Case($(1), "one"), Case($(2), "two"), Case($(3), "three"));

    // The last wildcard pattern $() saves us from a MatchError which is thrown if no case matches.

    Option<String> optionResult = Match(intToMatch).option(Case($(0), "zero"));

    // If the first argument of a Case is a conditional pattern $(predicate), it can be simplified by directly writing

    // Case()


  }

  private <R> R methodAccepting3Params(String s, String s1, String s2) {
    return null;
  }
}
