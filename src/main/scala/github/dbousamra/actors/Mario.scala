package github.dbousamra.actors

import com.badlogic.gdx.graphics.g2d._
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d._
import github.dbousamra.Constants
import github.dbousamra.screens._

case class Mario(screen: PlayScreen) {

  var isJumping = false

  val sprite = new Sprite(new TextureRegion(screen.atlas.findRegion("little_mario"), 0, 0, 16, 16))
  sprite.setBounds(0, 0, 16 / Constants.PPM, 16 / Constants.PPM)

  val bodyDef = new BodyDef()
  bodyDef.`type` = BodyDef.BodyType.DynamicBody
  bodyDef.position.set(new Vector2(0.5f, 0.5f))
  val body = screen.world.createBody(bodyDef)

  val shape = new PolygonShape()
  shape.setAsBox(sprite.getWidth() / 2, sprite.getHeight() / 2)

  val fDef = new FixtureDef()
  fDef.shape = shape
  body.createFixture(fDef).setUserData(sprite)

  def render(batch: SpriteBatch): Unit = {
    sprite.draw(batch)
  }

  def update(delta: Float): Unit = {
    sprite.setPosition(
      body.getPosition().x - sprite.getWidth() / 2f,
      body.getPosition().y - sprite.getHeight() / 2f
    )
  }

  def move(action: Action): Unit = {
    action match {
      case Action.Jump => {
        if (!isJumping) {
          isJumping = true
          body.applyLinearImpulse(new Vector2(0f, 4f), body.getWorldCenter(), true)
        }
      }
      case Action.MoveRight => {
        if (body.getLinearVelocity().x <= 2)
          body.applyLinearImpulse(new Vector2(.1f, 0), body.getWorldCenter(), true)
      }
      case Action.MoveLeft => {
        if (body.getLinearVelocity().x >= -2)
          body.applyLinearImpulse(new Vector2(-.1f, 0), body.getWorldCenter(), true)
      }
    }
  }
}

sealed trait Action

object Action {
  case object Jump extends Action
  case object MoveLeft extends Action
  case object MoveRight extends Action
}
