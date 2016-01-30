# Play Slick compile time dependency injection sample

I spent some time trying to create an project using playframework 2.4 and play-slick module with compile time DI.

So I translated the play-slick [di sample](https://github.com/playframework/play-slick/tree/master/samples/di) to use compile time di.

The sample features:
* [Evolutions](https://github.com/NicolaeNMV/play-slick-compile-time-di-sample/blob/master/app/AppMain.scala#L21)
by mixing `with SlickComponents with SlickEvolutionsComponents with EvolutionsComponents` into `ApplicationComponents` and initializing evolutions when the app starts.
```
class ApplicationComponents(context: Context) extends BuiltInComponentsFromContext(context)
  with SlickComponents with SlickEvolutionsComponents with EvolutionsComponents {
...
  // This is required by EvolutionsComponents
  lazy val dynamicEvolutions: DynamicEvolutions = new DynamicEvolutions

  def onStart() = {
    // applicationEvolutions is a val and requires evaluation
    applicationEvolutions
  }

  onStart()
}
```

* [In memory](https://github.com/NicolaeNMV/play-slick-compile-time-di-sample/blob/master/test/TestEnvironment.scala#L12) database configuration for tests
* Access to your your app components from tests by using a specs2 scope:

```
  "CatDAO" should {
    "work as expected" in new WithApplicationComponents {
      val dao: CatDAO = appComponents.catDao
      ..
    }
  }
```

I did a [pull-request](https://github.com/playframework/play-slick/pull/337) to play-slick project, but it's still to be merged.