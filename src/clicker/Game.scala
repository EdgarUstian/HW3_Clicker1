package clicker

import clicker.equipment._
import play.api.libs.json.{JsValue, Json}


class Game {

  // Do not change these state variable names, types, or initial values
  //
  // These same names, types, and initial values will be the same in all submissions on AutoLab so you can
  //  use these in your test cases
  var gold: Double = 0.0
  var lastUpdateTime: Long = System.nanoTime()
  var equipment: Map[String, Equipment] = Map("shovel" -> new Shovels, "excavator" -> new Excavators, "mine" -> new GoldMines)
  //

  def goldPerSecond(): Double = {
    var gsp: Double = 0.0
    for (item <- equipment.values){
      gsp += item.goldPerSecond()
    }
    gsp
  }

  def goldPerClick(): Double = {
    var gsc: Double = 0.0
    for (item <- equipment.values){
      gsc += item.goldPerClick()
    }
    gsc + 1
  }


  def clickGold(): Unit = {
    gold += goldPerClick()
  }

  def buyEquipment(equipmentKey: String): Unit = {
    for ((item, stats) <- equipment){
      if (equipmentKey == item){
        if (gold >= stats.costOfNextPurchase()){
          gold -= stats.costOfNextPurchase()
          stats.numberOwned += 1
        }
      }
    }
  }

  /**
    * takes the current epoch time in nanoseconds
    */
  def update(time: Long): Unit = {
    val timePassed: Long = time - lastUpdateTime
    val secondsPassed: Double = timePassed/ 1e9
    gold += goldPerSecond() * secondsPassed
    lastUpdateTime = time
  }


  def toJSON(): String = {
    val money: JsValue = Json.toJson(gold)
    val time: JsValue = Json.toJson(System.nanoTime())
    val equipmentMap: Map[String, Map[String, String]] = Map(
      "shovel" -> Map(
        "numberOwned" -> equipment("shovel").numberOwned.toString,
        "name" -> equipment("shovel").name
      ),
      "excavator" -> Map(
        "numberOwned" -> equipment("excavator").numberOwned.toString,
        "name" -> equipment("excavator").name
      ),
      "mine" -> Map(
        "numberOwned" -> equipment("mine").numberOwned.toString,
        "name" -> equipment("mine").name
      )
    )
    val jsonEquipment: JsValue = Json.toJson(equipmentMap)

    val jsonMap: Map[String, JsValue] = Map(
      "gold" -> money,
      "lastUpdateTime" -> time,
      "equipment" -> jsonEquipment
    )

    Json.stringify(Json.toJson(jsonMap))
  }


  def fromJSON(jsonGameState: String): Unit = {
    val parsed: JsValue = Json.parse(jsonGameState)
    gold = (parsed \ "gold").as[Double]
    lastUpdateTime = (parsed \ "lastUpdateTime").as[Long]
    val equipmentMap: Map[String, Map[String, String]] = (parsed \ "equipment").as[Map[String, Map[String, String]]]
    equipment("shovel").numberOwned = equipmentMap("shovel")("numberOwned").toInt
    equipment("excavator").numberOwned = equipmentMap("excavator")("numberOwned").toInt
    equipment("mine").numberOwned = equipmentMap("mine")("numberOwned").toInt
  }


  // Given
  def goldString(): String = {
    f"$gold%1.0f"
  }

  def buttonText(equipmentKey: String): String = {
    val thing: Equipment = this.equipment.getOrElse(equipmentKey, null) // will crash program if key not found
    val cost = thing.costOfNextPurchase()
    val gpc = thing.goldPerClick()
    val gps = thing.goldPerSecond()
    thing.name + f"\n$cost%1.0f gold\n$gpc%1.0f gpc\n$gps%1.0f gps\nowned: " + thing.numberOwned
  }

  //

}
