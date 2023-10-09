package projectname

import spinal.core._

// Hardware definition
case class MyTopLevel() extends Component {
  val io = new Bundle {
    // val cond0 = in  Bool()
    // val cond1 = in  Bool()
    // val flag  = out Bool()
    // val state = out UInt(8 bits)
    val sw = in UInt(4 bits)
    val led = out UInt(4 bits)
  }

  // val counter = Reg(UInt(8 bits)) init 0

  // when(io.cond0) {
  //   counter := counter + 1
  // }

  // io.state := counter
  // io.flag := (counter === 0) | io.cond1
  io.led(3 downto 2) := io.sw(3 downto 2)
  io.led(1 downto 0) := 0
}

object MyTopLevelVerilog extends App {
  Config.spinal.generateVerilog(MyTopLevel())
}

object MyTopLevelVhdl extends App {
  Config.spinal.generateVhdl(MyTopLevel())
}
