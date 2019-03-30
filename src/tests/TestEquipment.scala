package tests

import org.scalatest._
import clicker.equipment._

class TestEquipment extends FunSuite {
  test("Test Shovels"){
    val emptyShovel: Shovels = new Shovels
    emptyShovel.numberOwned = 0
    assert(emptyShovel.costOfNextPurchase() == 10)
    assert(emptyShovel.goldPerClick() == 0.0)
    assert(emptyShovel.goldPerSecond() == 0.0)

    val shovel: Shovels = new Shovels
    shovel.numberOwned = 3
    assert(shovel.costOfNextPurchase() == 11.576250000000002)
    assert(shovel.goldPerClick() == 3.0)
    assert(shovel.goldPerSecond() == 0.0)
  }

  test("Test Excavators"){
    val emptyExcavator: Excavators = new Excavators
    emptyExcavator.numberOwned = 0
    assert(emptyExcavator.costOfNextPurchase() == 200)
    assert(emptyExcavator.goldPerClick() == 0.0)
    assert(emptyExcavator.goldPerSecond() == 0.0)

    val excavator: Excavators = new Excavators
    excavator.numberOwned = 5
    assert(excavator.costOfNextPurchase() == 322.1020000000001)
    assert(excavator.goldPerClick() == 100.0)
    assert(excavator.goldPerSecond() == 50.0)
  }

  test("Test Goldmines"){
    val emptyMine: GoldMines = new GoldMines
    emptyMine.numberOwned = 0
    assert(emptyMine.costOfNextPurchase() == 1000)
    assert(emptyMine.goldPerClick() == 0.0)
    assert(emptyMine.goldPerSecond() == 0.0)

    val mine: GoldMines = new GoldMines
    mine.numberOwned = 7
    assert(mine.costOfNextPurchase() == 1948.7171000000012)
    assert(mine.goldPerClick() == 0.0)
    assert(mine.goldPerSecond() == 700.0)
  }
}
