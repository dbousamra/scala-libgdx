package github.dbousamra.screens

import com.badlogic.gdx._
import com.badlogic.gdx.graphics._
import com.badlogic.gdx.maps.tiled._
import com.badlogic.gdx.maps.tiled.renderers._
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.viewport._
import github.dbousamra._

class PlayScreen(game: Entry) extends Screen {

  var camera = new OrthographicCamera()
  var viewPort = new FitViewport(Constants.V_WIDTH / Constants.PPM, Constants.V_HEIGHT / Constants.PPM, camera);
  var map = new TmxMapLoader().load("level1.tmx")
  var renderer = new OrthogonalTiledMapRenderer(map, 1  / Constants.PPM);
  camera.position.set(viewPort.getWorldWidth() / 2, viewPort.getWorldHeight() / 2, 0);

  def render(delta: Float) = {
    update(delta)

    //Clear the game screen with Black
    Gdx.gl.glClearColor(0, 0, 0, 1)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

    //render our game map
    renderer.render()

    game.batch.setProjectionMatrix(camera.combined)
  }

  def update(delta: Float): Unit = {
    camera.translate(new Vector2(.01f, 0.0f))
    camera.update()
    renderer.setView(camera)
  }

  def dispose() = ()

  def show() = ()

  def resume() = ()

  def pause() = ()

  def hide() = ()

  def resize(width: Int, height: Int) = {
    viewPort.update(width, height)
  }
}
