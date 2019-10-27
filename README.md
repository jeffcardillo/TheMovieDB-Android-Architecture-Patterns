# TheMovieDB-Android-Architecture-Patterns
I created this project to do an instructional walkthrough of Model-View-ViewModel (MVVM), Data-binding and LiveData to fellow engineers at my workplace. I'm using TheMovieDB API to fetch interesting data and show beautiful movie posters in this demo.

![](presentation/TheMovieDb_example.gif)

A presentation file is available [(presentation/MVVM with Data Binding.pdf)](https://github.com/jeffcardillo/TheMovieDB-Android-Architecture-Patterns/blob/master/presentation/MVVM%20with%20Data%20Binding.pdf) that covers the specified patterns above. Please note that the format of the workshop will have attendees begin with a starter project - most of the code in the project is available but the specific pattern code is omitted. The goal is to then walk through the code and implement the patterns together. The starter project may not be added to this public github repository, but I wanted to outline why the presentation is formatted the way it is.

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

Please view the presentation included in this repository for a more formal walk-through of these concepts. Below I'll do a brief walkthrough of where to find the concepts implemented in code.

### Model-View-ViewModel (MVVM)
The `View` in this example project is the `MainFragment` class and layout. This class sets up the data binding, creates the ModelView

The `ModelView` is implemented in a class called `MainViewModel`. This class will fetch new data from the data model when needed, and transform (sort) the data when an event is passed to it from the UI layer.

The `Model` is represented as a `TheMovieDbMovie` object and the data is provided by the `ApiFactory` class (where an instance of _The Movie DB_ API retrofit object is created).

### DataBinding

Data binding is shown in in the fragment xml file `main_fragment.xml` in two ways: 

1. There is a click event binding to the `viewModel` object that will to send click events of the sort button directly to the view model. 
1. There is a binding to an object that `extends BaseObservable` that will update the text on the sort button for the purpose of demonstrating dynamically updating bindings.

### LiveData

Movies that are retrived from the API are stored in `MainViewModel` as LiveData. The main UI layout will listen for change events to this stored data and update the layout automatically when the data changes. LiveData changed events will fire when new data is retreived from the API service and also when the data is sorted.