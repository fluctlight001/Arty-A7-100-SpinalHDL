// Generator : SpinalHDL v1.9.3    git head : 029104c77a54c53f1edda327a3bea333f7d65fd9
// Component : MyTopLevel
// Git hash  : 9e5d32301510f05163e62f78e06a3fe8b863037a

`timescale 1ns/1ps

module MyTopLevel (
  input      [3:0]    io_sw,
  output reg [3:0]    io_led,
  output     [7:0]    io_led_r,
  output     [7:0]    io_led_g,
  output     [7:0]    io_led_b,
  input               clk,
  input               reset
);

  wire                counter_1_hit;
  wire       [7:0]    rgb_1_io_led_r;
  wire       [7:0]    rgb_1_io_led_g;
  wire       [7:0]    rgb_1_io_led_b;

  Counter counter_1 (
    .hit   (counter_1_hit), //o
    .clk   (clk          ), //i
    .reset (reset        )  //i
  );
  RGB rgb_1 (
    .io_led_r (rgb_1_io_led_r[7:0]), //o
    .io_led_g (rgb_1_io_led_g[7:0]), //o
    .io_led_b (rgb_1_io_led_b[7:0]), //o
    .clk      (clk                ), //i
    .reset    (reset              )  //i
  );
  always @(*) begin
    io_led[3 : 2] = io_sw[3 : 2];
    io_led[1 : 0] = (counter_1_hit ? 2'b11 : 2'b00);
  end

  assign io_led_r = rgb_1_io_led_r;
  assign io_led_g = rgb_1_io_led_g;
  assign io_led_b = rgb_1_io_led_b;

endmodule

module RGB (
  output     [7:0]    io_led_r,
  output     [7:0]    io_led_g,
  output     [7:0]    io_led_b,
  input               clk,
  input               reset
);

  reg        [7:0]    led_r;
  reg        [7:0]    led_g;
  reg        [7:0]    led_b;

  assign io_led_r = led_r;
  assign io_led_g = led_g;
  assign io_led_b = led_b;
  always @(posedge clk or posedge reset) begin
    if(reset) begin
      led_r <= 8'hff;
      led_g <= 8'h00;
      led_b <= 8'h00;
    end else begin
      led_b <= led_r;
      led_g <= led_b;
      led_r <= led_g;
    end
  end


endmodule

module Counter (
  output              hit,
  input               clk,
  input               reset
);

  reg        [27:0]   counter_1;

  assign hit = (counter_1[27] ? 1'b0 : 1'b1);
  always @(posedge clk or posedge reset) begin
    if(reset) begin
      counter_1 <= 28'h0000000;
    end else begin
      counter_1 <= (counter_1 + 28'h0000001);
    end
  end


endmodule
