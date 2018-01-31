package github.dbousamra

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration

object Boot extends App {
  val cfg: LwjglApplicationConfiguration = new LwjglApplicationConfiguration
  cfg.title = "Hello world"
  cfg.width = 1200
  cfg.height = 624

  new LwjglApplication(new Entry, cfg)
}
