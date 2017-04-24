package com.nullcognition.vavr;

import android.app.Activity;
import android.os.Bundle;
import java.util.Arrays;
import java.util.stream.IntStream;
import javaslang.Function2;
import javaslang.Function3;
import javaslang.Tuple;
import javaslang.Tuple2;
import javaslang.collection.CharSeq;
import javaslang.collection.List;
import javaslang.collection.Stream;
import javaslang.control.Option;
import javaslang.control.Try;
import javaslang.control.Validation;

import static javaslang.API.$;
import static javaslang.API.Case;
import static javaslang.API.Match;

/*
* Why - side-effect that affects our programs state
 * break normal control flow
 *
 * and created new collections class
 *
 * always favor the encapsulation of sideeffects with deterministic outputs on error
 * Try.of(()-> 0/0); which returns a safe object to operate on
* */

public class MainActivity extends Activity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Try<Integer> result = divide(0, 0);

    result.andThen(() -> result.stderr()); // runnable
    result.andThen(integer -> integer.toString()); // consume
    result.andThenTry(new Try.CheckedRunnable() {
      @Override public void run() throws Throwable {
      }
    });
    result.eq(divide(3, 4));
    result.get();
    result.onSuccess(integer -> {
    });
    result.onFailure(throwable -> {
    });
    //result...



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

    int age = 3;
    String name = "hello";
    Validation.combine(validateName(name), validateAge(age)).ap(Person::with);

    // Collections
    // not working
    /*for (double random : Stream.gen(Math::random).take(1000)) {
    }*/

    // Lists
    // j8
    Arrays.asList(1, 2, 3).stream().reduce((i, j) -> i + j);

    int sumOfIntStream = IntStream.of(1, 2, 3).sum();

    //vavr
    List.of(1, 2, 3).sum();
    // Stream is a lazy linked list, thus computed when needed, most operations performed i constant time
    Stream.from(1).filter(i -> i % 2 == 0);

    // Property Checking - generated random data, which is passed to a user defined check function. for functional property testing
    List<Integer> ints = List.of(1, 2, 3);

    // not working
    /*Arbitrary<Integer> ints = Arbitrary.integer();

    // square(int) >= 0: OK, passed 1000 tests.
    Property.def("square(int) >= 0")
        .forAll(ints)
        .suchThat(i -> i * i >= 0)
        .check()
        .assertIsSatisfied();*/

    // scala pattern matching
    /*val s = i match {
      case 1 => "one"
      case 2 => "two"
      case _ => "?"
    }*/

    //Match(3).of(Case($(1)),
    //    Case($(2))).opt

  }

  Try<Integer> divide(Integer i, Integer d) {
    return Try.of(() -> d / i);
  }

  Validation<String, Integer> validateAge(int age) {
    // error invalid and type valid
    return age > 1 ? Validation.invalid("Must be > 1") : Validation.valid(age);
  }

  Validation<String, String> validateName(String name) {
    // error invalid and type valid
    return CharSeq.of(name).isEmpty() ? Validation.invalid("Must have any name")
        : Validation.valid(name);
  }

  private <R> R methodAccepting3Params(String s, String s1, String s2) {
    return null;
  }
}

class Person {

  public final String name;
  public final int age;

  private Person() {
    throw new AssertionError();
  }

  private Person(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public static Person with(String name, int age) {
    return new Person(name, age);
  }
}