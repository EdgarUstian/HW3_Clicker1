package clicker.equipment

class Excavators extends Equipment{

  this.name = "Excavator"

  override def goldPerClick(): Double ={
    this.numberOwned * 20.0
  }

  override def goldPerSecond(): Double = {
    val gps: Double = 10.0
    gps * this.numberOwned
  }

  override def costOfNextPurchase(): Double = {
    200.0 * scala.math.pow(1.10, numberOwned)
  }
}
