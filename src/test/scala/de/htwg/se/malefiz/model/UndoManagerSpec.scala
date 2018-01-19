package de.htwg.se.malefiz.model.GameBoard

import de.htwg.se.malefiz.Util.UndoManager
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class UndoManagerSpec extends WordSpec with Matchers{
  "A UndoManager" when {
    "new" should {
      var undoManager = new UndoManager()
      "undo with empty stack" in{
        undoManager.undoStep()
        undoManager.isRedoStackEmpty() shouldBe(true)
      }
      "redo with empty Stack" in{
        undoManager.redoStep()
        undoManager.isRedoStackEmpty() shouldBe(true)
      }
    }
}}
