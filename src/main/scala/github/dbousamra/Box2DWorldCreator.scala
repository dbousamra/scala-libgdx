package github.dbousamra

import com.badlogic.gdx.maps.objects.RectangleMapObject
import com.badlogic.gdx.physics.box2d._
import github.dbousamra.screens.PlayScreen

object Box2DWorldCreator {

  def create(screen: PlayScreen) = {
    screen.map.getLayers.get(2).getObjects.getByType(classOf[RectangleMapObject]).items.foreach { m =>
      if (m != null) {
        val rect = m.getRectangle
        val bodyDef = new BodyDef()
        bodyDef.`type` = BodyDef.BodyType.StaticBody
        bodyDef.position.set((rect.getX + rect.getWidth / 2) / Constants.PPM, (rect.getY + rect.getHeight / 2) / Constants.PPM)

        val body = screen.world.createBody(bodyDef)
        val shape = new PolygonShape()
        shape.setAsBox(rect.getWidth / 2 / Constants.PPM, rect.getHeight / 2 / Constants.PPM)

        val fixtureDef = new FixtureDef()
        fixtureDef.shape = shape
        body.createFixture(fixtureDef)
      }
    }
  }

}
