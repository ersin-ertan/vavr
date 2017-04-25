package com.nullcognition.vavr;

/*
* Values and transformations of values using functions
* Use Function0-8 to accept parameters,  and CheckedFunction1-8 for check exceptions
* */

import java.util.Objects;
import javaslang.Function0;
import javaslang.Function1;
import javaslang.Function2;
import javaslang.Function3;
import javaslang.control.Option;

public class V_3_2_0_Functions {

  void func() {
    Function2<Integer, Integer, String> toStringNum = (a, b) -> a.toString() + b.toString();
    Function2<Integer, Integer, Integer> sum = (a, b) -> a + b;
    Function2<String, String, String> f =
        Function2.of(this::accepts2); // use of for method references
    // input, input, output                   Function that takes input return output
  }

  String accepts2(String s, String ss) {
    return "";
  }

  // vavar interfaces are j8 functional on steroids, and can offer features like composition, lifting, currying memoization

  void composition() {
    Function1<Integer, Integer> plusOne = a -> a + 1;
    Function1<Integer, Integer> multTwo = a -> a * 2;

    Function1<Integer, Integer> addAndMult = plusOne.andThen(multTwo);
    addAndMult.apply(2).equals(6);
  }

  void lifting() {
    // lift partial function into a total function that returns an option result
    // partial function - only allows a sub set of x->y function mapping
    // Ex.
    Function2<Integer, Integer, Integer> divide = (a, b) -> a / b;
    // only accepts non zero dividers

    // thus use lift to turn divide into a total function that is defined for all inputs

    Function2<Integer, Integer, Option<Integer>> safeDivide = Function2.lift(divide);

    Option<Integer> i1 = safeDivide.apply(1, 0); // returns None
    Option<Integer> i2 = safeDivide.apply(4, 2); // returns Some

    Option.None<Integer> none;

    // you can lift unsafe functions that would have otherwise thrown exceptions, instead returning None or Some
  }

  void partialApplication() {
    // derive new function from existing on by fixing parameters with values, the fixed number defines the arity
    // arity = (original arity - fixed params)           2 = (5-3)

    Function2<Integer, Integer, Integer> sum = (a, b) -> a + b;

    Function1<Integer, Integer> sumBy10 = sum.apply(10);

    sumBy10.apply(1).equals(11);
  }

  // TODO - understand the currying vs partial functions
  void currying() {
    // partially apply a function by fixing values for one of the params, resulting in a Function1 function that returns a Function1
    // thus Function2 is curried, result is indistinguishable from partial application of Function2 because bothe return 1 arity

    Function2<Integer, Integer, Integer> sum = (a, b) -> a + b;
    Function1<Integer, Integer> add2 = sum.curried().apply(2); // the first param is fixed to 2

    add2.apply(4).equals(6);

    // same as partial application but with higher arity it will be apparent

    Function3<Integer, Integer, Integer, Integer> curryMe = (a, b, c) -> a + b + c;
    final Function1<Integer, Function1<Integer, Integer>> addTwo = curryMe.curried().apply(2);
  }

  void memoization() {
    // type of cashing, where the function executes only once, returning the result of the operation from cache for subsequent function calls
    Function0<Double> cached = Function0.of(Math::random).memoized();

    double rand1 = cached.apply();
    double rand2 = cached.apply();

    Objects.equals(rand1, rand2); // true
  }
}
