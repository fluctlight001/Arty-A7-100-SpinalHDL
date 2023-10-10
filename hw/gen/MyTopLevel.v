// Generator : SpinalHDL v1.9.3    git head : 029104c77a54c53f1edda327a3bea333f7d65fd9
// Component : MyTopLevel
// Git hash  : 3b04d0cde05b90f07f83a9448acd9b534ee6b406

`timescale 1ns/1ps

module MyTopLevel (
  input               io_clk,
  input               io_rst,
  input      [3:0]    io_sw,
  output reg [3:0]    io_led
);

  wire       [27:0]    counter;
  reg        [27:0]    myArea_myReg;

  assign counter = myArea_myReg;
  always @(*) begin
    io_led[3 : 2] = {io_sw[3], io_rst};
    // io_led[1] = 1;
    // io_led[0] = 0;
    io_led[1 : 0] = (counter[27] ? 2'b11 : 2'b00);
  end

  always @(posedge io_clk or posedge io_rst) begin
    if(io_rst) begin
      myArea_myReg <= 0;
    end else begin
      myArea_myReg <= (myArea_myReg + 1);
    end
  end


endmodule
