package github.dbousamra.screens

import com.badlogic.gdx._
import com.badlogic.gdx.graphics._
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.maps.tiled._
import com.badlogic.gdx.maps.tiled.renderers._
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.utils.viewport._
import github.dbousamra._
import github.dbousamra.actors.Action
import github.dbousamra.actors.Mario

class PlayScreen(game: Entry) extends Screen {

  val atlas: TextureAtlas = new TextureAtlas("Mario_and_Enemies.pack")
  val world = new World(new Vector2(0, -10), true)
  val camera = new OrthographicCamera()
  val viewPort = new FitViewport(Constants.V_WIDTH / Constants.PPM, Constants.V_HEIGHT / Constants.PPM, camera)
  val map = new TmxMapLoader().load("level1.tmx")
  Box2DWorldCreator.create(this)

  val renderer = new OrthogonalTiledMapRenderer(map, 1 / Constants.PPM)
  val b2dr = new Box2DDebugRenderer()

  camera.position.set(viewPort.getWorldWidth() / 2, viewPort.getWorldHeight() / 2, 0)

  val mario = Mario(this)

  def render(delta: Float) = {
    update(delta)

    //Clear the game screen with Black
    Gdx.gl.glClearColor(0, 0, 0, 1)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

    //render our game map
    renderer.render()

    b2dr.render(world, camera.combined)

    game.batch.setProjectionMatrix(camera.combined)
    game.batch.begin()
    mario.render(game.batch)
    game.batch.end()
  }

  def update(delta: Float): Unit = {

    if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
      mario.move(Action.Jump)
    } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
      mario.move(Action.MoveRight)
    } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
      mario.move(Action.MoveLeft)
    }
    world.step(1 / 60f, 6, 2)
    mario.update(delta)
//    camera.translate(0.01f, 0)
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
