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
    val led_r = out UInt(8 bits)
    val led_g = out UInt(8 bits)
    val led_b = out UInt(8 bits)
  }
  val led_r = Reg(UInt(8 bits)) init(255)
  val led_g = Reg(UInt(8 bits)) init(0)
  val led_b = Reg(UInt(8 bits)) init(0)

  led_b := led_r
  led_g := led_b
  led_r := led_g

  io.led_r := led_r
  io.led_g := led_g
  io.led_b := led_b
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
    val led_r = out UInt(8 bits)
    val led_g = out UInt(8 bits)
    val led_b = out UInt(8 bits)
  }

  val counter = Counter()


  io.led(3 downto 2) := io.sw(3 downto 2)
  io.led(1 downto 0) := Mux(counter.hit,U(3),U(0))

  val rgb = RGB()
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
