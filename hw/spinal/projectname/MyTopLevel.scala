<<<<<<< HEAD
package projectname

import spinal.core._

// Hardware definition
case class MyTopLevel() extends Component {
  val io = new Bundle {
    // val cond0 = in  Bool()
    // val cond1 = in  Bool()
    // val flag  = out Bool()
    // val state = out UInt(8 bits)
    val clk = in Bool()
    val rst = in Bool()
    val sw = in UInt(4 bits)
    val led = out UInt(4 bits)
  }

  val counter = UInt(8 bits)

  val myClockDomain = ClockDomain(
    clock = io.clk,
    reset = io.rst,
    config = ClockDomainConfig(
      clockEdge = RISING,
      resetKind = ASYNC,
      resetActiveLevel = LOW
    )
  )
  
  val myArea = new ClockingArea(myClockDomain){
    val myReg = Reg(UInt(8 bits)) init(0)
    myReg := myReg + 1
    counter := myReg
  }

  // when(io.cond0) {
  //   
  // }

  // io.state := counter
  // io.flag := (counter === 0) | io.cond1
  io.led(3 downto 2) := io.sw(3 downto 2)
  io.led(1 downto 0) := Mux(counter(7),U(3),U(0))
}

object MyTopLevelVerilog extends App {
  Config.spinal.generateVerilog(MyTopLevel())
}

object MyTopLevelVhdl extends App {
  Config.spinal.generateVhdl(MyTopLevel())
}
=======
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
>>>>>>> 08ee79ecf533cffadcc93baa997a4098667b642a
