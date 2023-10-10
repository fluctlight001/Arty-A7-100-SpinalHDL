package projectname

import spinal.core._

case class Counter() extends  Component {
  val hit = out Bool()

  val counter = Reg(UInt(28 bits)) init(0)
  counter := counter + 1
  hit := Mux(counter(27),False,True)
}

case class RGB() extends  Component {
  val io = new Bundle {
    val hit = in Bool()
    val led_r = out Bool()
    val led_g = out Bool()
    val led_b = out Bool()
  }
  val led_r = Reg(Bool()) init(True)
  val led_g = Reg(Bool()) init(False)
  val led_b = Reg(Bool()) init(False)
  val half = Reg(Bool()) init(True)

  half := ~half

  when (io.hit.rise()){
    led_b := led_r
    led_g := led_b
    led_r := led_g
  }

  io.led_r := Mux(half,led_r,False)
  io.led_g := Mux(half,led_g,False)
  io.led_b := Mux(half,led_b,False)
}

// Hardware definition
case class MyTopLevel() extends Component {
  val io = new Bundle {
    // val cond0 = in  Bool()
    // val cond1 = in  Bool()
    // val flag  = out Bool()
    // val state = out UInt(8 bits)
    val sw = in UInt(4 bits)
    val led = out UInt(4 bits)
    val led_r = out Bool()
    val led_g = out Bool()
    val led_b = out Bool()
  }

  val counter = Counter()


  io.led(3 downto 2) := io.sw(3 downto 2)
  io.led(1 downto 0) := Mux(counter.hit,U(3),U(0))

  val rgb = RGB()
  rgb.io.hit := counter.hit
  io.led_r := rgb.io.led_r
  io.led_g := rgb.io.led_g
  io.led_b := rgb.io.led_b
}

object MyTopLevelVerilog extends App {
  Config.spinal.generateVerilog(MyTopLevel())
}

object MyTopLevelVhdl extends App {
  Config.spinal.generateVhdl(MyTopLevel())
}
