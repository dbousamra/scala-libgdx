package github.dbousamra

import com.badlogic.gdx._
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import github.dbousamra.screens.PlayScreen

class Entry extends Game {
  var batch: SpriteBatch = _

  override def create(): Unit = {
    batch = new SpriteBatch
    setScreen(new PlayScreen(this))
  }

  override def dispose(): Unit =  {

  }

  override def render(): Unit = {
    super.render();
  }
}
