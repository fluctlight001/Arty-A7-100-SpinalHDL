// Generator : SpinalHDL v1.9.3    git head : 029104c77a54c53f1edda327a3bea333f7d65fd9
// Component : MyTopLevel
// Git hash  : 8331e8de986b0985fc241f6c10f3ee91ba590246

`timescale 1ns/1ps

module MyTopLevel (
  input      [3:0]    io_sw,
  output reg [3:0]    io_led,
  output              io_led_r,
  output              io_led_g,
  output              io_led_b,
  input               clk,
  input               reset
);

  wire                counter_1_hit;
  wire                rgb_1_io_led_r;
  wire                rgb_1_io_led_g;
  wire                rgb_1_io_led_b;

  Counter counter_1 (
    .hit   (counter_1_hit), //o
    .clk   (clk          ), //i
    .reset (reset        )  //i
  );
  RGB rgb_1 (
    .io_hit   (counter_1_hit ), //i
    .io_led_r (rgb_1_io_led_r), //o
    .io_led_g (rgb_1_io_led_g), //o
    .io_led_b (rgb_1_io_led_b), //o
    .clk      (clk           ), //i
    .reset    (reset         )  //i
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
  input               io_hit,
  output              io_led_r,
  output              io_led_g,
  output              io_led_b,
  input               clk,
  input               reset
);

  reg                 led_r;
  reg                 led_g;
  reg                 led_b;
  reg                 io_hit_regNext;
  wire                when_MyTopLevel_l24;

  assign when_MyTopLevel_l24 = (io_hit && (! io_hit_regNext));
  assign io_led_r = led_r;
  assign io_led_g = led_g;
  assign io_led_b = led_b;
  always @(posedge clk or posedge reset) begin
    if(reset) begin
      led_r <= 1'b1;
      led_g <= 1'b0;
      led_b <= 1'b0;
    end else begin
      if(when_MyTopLevel_l24) begin
        led_b <= led_r;
        led_g <= led_b;
        led_r <= led_g;
      end
    end
  end

  always @(posedge clk) begin
    io_hit_regNext <= io_hit;
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
