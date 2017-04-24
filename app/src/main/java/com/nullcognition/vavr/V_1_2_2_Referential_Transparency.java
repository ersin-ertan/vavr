package com.nullcognition.vavr;


public class V_1_2_2_Referential_Transparency {

  // function or expression is refrential transparent if it can be repalced by its values without affecting the program

  // if all expressions in the function are ref trans, then the function is pure

  void rf(){
    Math.random(); // is not

    Math.max(1,3); // is
  }
}
