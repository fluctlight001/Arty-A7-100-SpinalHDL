// Generator : SpinalHDL v1.9.3    git head : 029104c77a54c53f1edda327a3bea333f7d65fd9
// Component : MyTopLevel
// Git hash  : 5297ace4e8c747a7a5a6bc5142fa16bc295702a8

`timescale 1ns/1ps

module MyTopLevel (
  input               io_clk,
  input               io_rst,
  input      [3:0]    io_sw,
  output reg [3:0]    io_led
);

  wire       [27:0]   counter;
  reg        [27:0]   myArea_myReg;

  assign counter = myArea_myReg;
  always @(*) begin
    io_led[3 : 2] = io_sw[3 : 2];
    io_led[1 : 0] = (counter[27] ? 2'b11 : 2'b00);
  end

  always @(posedge io_clk or negedge io_rst) begin
    if(!io_rst) begin
      myArea_myReg <= 28'h0000000;
    end else begin
      myArea_myReg <= (myArea_myReg + 28'h0000001);
    end
  end


endmodule
