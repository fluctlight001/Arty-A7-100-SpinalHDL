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
