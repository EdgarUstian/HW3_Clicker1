package clicker.equipment

class GoldMines extends Equipment{

  this.name = "Gold Mine"

  override def goldPerClick(): Double ={
    0.0
  }

  override def goldPerSecond(): Double = {
    val gps: Double = 100.0
    gps * this.numberOwned
  }

  override def costOfNextPurchase(): Double = {
    1000.0 * scala.math.pow(1.10, numberOwned)
  }
}
