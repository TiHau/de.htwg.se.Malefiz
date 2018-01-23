package de.htwg.se.malefiz.model.gameboard

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
@RunWith(classOf[JUnitRunner])
class StoneSpec extends WordSpec with Matchers {
  "A PlayerStone" when {
    "new" should {
      val stone: PlayerStone = PlayerStone(Field(0, 0, null), Field(0, 0, null), 1)
      "have a Player" in {
        stone.playerColor should be(1)
      }
      "have a startField" in {
        stone.startField should be(Field(0, 0, null))
      }
      "have stonetype p" in {
        stone.sort should be('p')
      }
      stone.actualField = Field(1, 1, null)
      stone.sort = 'p'

      "have a acctualField after change" in {
        stone.actualField should be(Field(1, 1, null))
      }
      "have a char after change" in {
        stone.sort should be('p')
      }

    }
  }
  "A BlockStone" when {
    "new" should {
      val stone: BlockStone = BlockStone()

      "have stonetype b" in {
        stone.sort should be('b')
      }
    }
  }
  "A FreeStone" when {
    "new" should {
      val stone: FreeStone = FreeStone()

      "have stonetype f" in {
        stone.sort should be('f')
      }

    }
  }
  "A Stone" when {
    "new" should {
      val stone: Stone = new Stone(' ')

      "have stonetype ' '" in {
        stone.sort should be(' ')
      }

    }
  }
}
