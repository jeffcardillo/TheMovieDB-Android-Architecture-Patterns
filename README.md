# TheMovieDB-Android-Architecture-Patterns
I created this project to do an instructional walkthrough of MVVM, Databinding and LiveData to fellow engineers at my workplace. I'm using TheMovieDB API to fetch interesting data and show beautiful movie posters in this demo.

I will be adding a presentation slide deck to accompany the code soon.

![](presentation/TheMovieDb_example.gif)

## Project Setup

### API Key for The Movie DB
In order to run this project you will need to provide your own API KEY from [The Movie DB](https://www.themoviedb.org/). Simply sign up on their website and register an app - it is free and approval is instant. Once you have your API KEY enter it in your `local.properties` file like the following (where you replace the x's with your API KEY):

```
THE_MOVIE_DB_API_KEY = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"
```

This is needed because the following entry in the `build.gradle` file will pull that value at build time and place it in the `BuildConfig` class:

```
    defaultConfig {
        Properties properties = new Properties()
        properties.load(project.rootProject.file('local.properties').newDataInputStream())
        buildConfigField "String", "THE_MOVIE_DB_API_KEY", properties.getProperty("THE_MOVIE_DB_API_KEY")
    }
```
 The value of the API KEY can be used statically with `BuildConfig.THE_MOVIE_DB_API_KEY` in code. This approach allows you to use a local API KEY that will not be added to source control:
 

## Overview of Code Structure

//TODO

