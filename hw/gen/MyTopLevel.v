// Generator : SpinalHDL v1.9.3    git head : 029104c77a54c53f1edda327a3bea333f7d65fd9
// Component : MyTopLevel
// Git hash  : 3e4a793577aa057d38ca71acad11407b411bcd38

`timescale 1ns/1ps

module MyTopLevel (
  input      [3:0]    io_sw,
  output     [3:0]    io_led
);


  assign io_led = io_sw;

endmodule
