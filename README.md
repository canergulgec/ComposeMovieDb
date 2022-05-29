# ComposeMovieDb

### 💫 &nbsp; Powered by [TheMovieDb](https://www.themoviedb.org) built with Jetpack Compose.

![alt text](https://cdn-images-1.medium.com/max/1200/1*vIR7iO-1GnY2xYxL6NiYkw.png)

## ⚙️ Configuration

In order to use MovieDB:
- You need to get API KEY from TMDb. You can do that by clicking [here](https://www.themoviedb.org/signup).
- Once you obtain key, create `secure.properties` which should be on the same level with build.gradle(Project:MovieDb)
- Add your key into secure.properties `MOVIE_API_KEY = "xxx" `
- Use JDK 11 to build this project.

## 🌞 Light Mode

<img src="/art/movie_screen_light.png" width="250" /> &nbsp;&nbsp;&nbsp; <img src="/art/movie_detail_light.png" width="250" /> &nbsp;&nbsp;&nbsp; <img src="/art/search_list_light.png" width="250" />

## 🌚 Dark Mode

<img src="/art/movie_screen_dark.png" width="250" /> &nbsp;&nbsp;&nbsp; <img src="/art/movie_detail_dark.png" width="250" /> &nbsp;&nbsp;&nbsp; <img src="/art/search_list_dark.png" width="250" />

## 🛠 Built With

- [Kotlin](https://kotlinlang.org)
- [Ktor Client](https://ktor.io/docs/client.html)
- [Dagger Hilt](https://dagger.dev/hilt/)
- [Coroutines Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html)
- [Splash Screen API](https://developer.android.com/guide/topics/ui/splash-screen/migrate)
- [Jetpack](https://developer.android.com/jetpack?gclid=CjwKCAiA25v_BRBNEiwAZb4-ZRLrSzIFlpm0NDTFGSuapyosjuVKi0AVLXGgVqSwqe46gejCg31LvRoCAwIQAvD_BwE&gclsrc=aw.ds)
    * [Compose](https://developer.android.com/jetpack/compose)
    * [DataStore](https://developer.android.com/topic/libraries/architecture/datastore)
    * [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle)
    * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
- [Accompanist](https://google.github.io/accompanist/)
    * [System UI Controller](https://google.github.io/accompanist/systemuicontroller/)
    * [Pager](https://google.github.io/accompanist/pager/)
    * [Flow Layouts](https://google.github.io/accompanist/flowlayout/)
- [Coil](https://github.com/coil-kt/coil)
- [Landscapist](https://github.com/skydoves/Landscapist)

## 🗼 Architecture

- [Model-View-ViewModel](https://en.wikipedia.org/wiki/Model–view–viewmodel)(MVVM) pattern helps to completely separate the business and presentation logic from the UI    

## 🎯 Roadmap
- [ ] Testing in Jetpack Compose
