// Generator : SpinalHDL v1.9.3    git head : 029104c77a54c53f1edda327a3bea333f7d65fd9
// Component : MyTopLevel
// Git hash  : 72c2f216c6ba6df9867f7437f960a3bd5a96d581

`timescale 1ns/1ps

module MyTopLevel (
  input      [3:0]    io_sw,
  output reg [3:0]    io_led
);


  always @(*) begin
    io_led[3 : 2] = io_sw[3 : 2];
    io_led[1 : 0] = 2'b00;
  end


endmodule
