package clicker.equipment

class Shovels extends Equipment {

  this.name = "Shovel"

  override def goldPerClick(): Double ={
    this.numberOwned * 1.0
  }

  override def goldPerSecond(): Double = {
    val gps: Double = 0.0
    gps * this.numberOwned
  }

  override def costOfNextPurchase(): Double = {
    10.0 * scala.math.pow(1.05, numberOwned)
  }
}
