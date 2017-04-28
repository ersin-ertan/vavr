package com.nullcognition.vavr;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import javaslang.Lazy;
import javaslang.control.Option;
import javaslang.control.Try;

/*
* A value is the normal form, where the immutable object is the final
*
* Vavar value abstracts over immutable objects, by sharing immutable memory between instances, free thread safty
* */
public class V_3_3_Values {

  void option() {
    // Monadic container type for optional values, instances of Some, or None

    Option<Integer> option = Option.of(3);

    // different from javas optional .map results in null will result in empty Optional but javaslang
    // it would result to Some(null) which leads to NullPointerEXception

    // when

    Optional<String> maybeFoo = Optional.of("foo"); // option is Some("foo")
    Objects.equals(maybeFoo.get(), "foo");

    Optional<String> maybeBar = maybeFoo.map(s -> (String) null).map(s -> s.toUpperCase() + "bar");
    Objects.equals(maybeBar.isPresent(), false); // resulting option is empty because of the map

    // but in javaslang this map will trigger a NullPointerException

    // this adheres to the requirement of the monad to maintain computational context when calling .map, thus
    // Some will never change to a None like in j8 version
  }

  void aTry() {
    // monadic container for computation that may result in exception, or return successfully the computed value
    // similar to Either, but are only Success or Failure

    Try.of(() -> doWork()).getOrElse(4);

    Integer result = Try.of(this::doWork).recover(new Function<Throwable, Integer>() {
      @Override public Integer apply(Throwable throwable) {
        return 1;
      }
    }).getOrElse(4);
  }

  int doWork() {
    return 1;
  }

  void lazy() {
    // monadic container lazy value, zaly is memoizing evaluating only once

    Lazy<Double> l = Lazy.of(Math::random);
    l.isEvaluated(); // false
    l.get();
    l.isEvaluated(); // true
    l.get(); // memoized

    // interface lazy values
    CharSequence c = Lazy.val(() -> "test", CharSequence.class);
  }

  void either() {

  }
}
