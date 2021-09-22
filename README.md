<h1 align="center">Note App</h1>
<p align="center">Where you can write your daily notes</p>

<details open ="open">
  <summary>Contains</summary>
  <ol>
    <li>
      <a href='#about-the-project'>About Project</a>
        <ul>
          <li><a href="#built-with">Built With</a></li>
        </ul>
    </li>
    <li>
      <a href='#getting-started'>Getting Started</a>
        <ul>
          <li><a href="#prerequisites">Prerequisites</a></li>
        </ul> 
        <ul>
          <li><a href="#installation">Installation</a></li>
        </ul>
    </li> 
    <li>
      <a href='#usage'>Usage</a>
         <ul>
          <li><a href="#changing-language-and-units">Changing language and units</a></li>
        </ul> 
       <ul>
          <li><a href="#specify-your-own-background-colors">Specyfy your own wather background colors</a></li>
        </ul> 
        <ul>
          <li><a href="#add-new-data-blocks">Add new data blocks</a></li>
        </ul>
    </li>
    <li>
      <a href='#contacts'>Contacts</a> 
    </li>
  </ol>
</details>
  
## About Project
![product-screenshot](https://live.staticflickr.com/65535/51503525721_db06f20a41_k.jpg)

Note app. This application allows you to store your daily notes. Every new entry is saved in local database and further that note is sent to backend server, 
where it is stored. Thanks to server you can access any note from any device, using your profile. 

This app :
*  Have its own server - [NoteAppServer](https://github.com/OzolsUgis/NoteData.git)
*  Encrypts your password using Hash and Salt
*  Make sure, when you log in for the first time, it saves your credentials, so when you restart app it automaticly authenticates you. 
*  You can add/edit notes to local database, and then they will be saved in server.

### Built With 

This application is built in  [Android Studio version 2020.3.1 (Artic Fox)](https://developer.android.com/studio?gclid=CjwKCAjwgb6IBhAREiwAgMYKRlU8WsxaTu6kg3JANeH6rEr8MrWyit5JaDfcTy0v1tTP0-DOmL1QnRoCxrcQAvD_BwE&gclsrc=aw.ds) 
using :

* [Kotlin](https://developer.android.com/kotlin)
* [Jetpack Compose version 1.0.0](https://developer.android.com/jetpack/compose?gclid=EAIaIQobChMImIyxhI-i8gIVlgCiAx3kZgYlEAAYASAAEgL1J_D_BwE&gclsrc=aw.ds)
* [Courotines](https://developer.android.com/kotlin/coroutines?gclid=EAIaIQobChMIqZC4jo-i8gIVsAZ7Ch1rOASzEAAYASAAEgKAwvD_BwE&gclsrc=aw.ds)
* [DaggerHilt](https://developer.android.com/training/dependency-injection/hilt-android)
* [Retrofit & OkHttp](https://square.github.io/retrofit/)
* [Timber](https://github.com/JakeWharton/timber)
* [Room](https://developer.android.com/training/data-storage/room)
* [Shared Preferences](https://developer.android.com/training/data-storage/shared-preferences)
* [Live Data](https://developer.android.com/topic/libraries/architecture/livedata)
* [MVVM](https://developer.android.com/jetpack/guide?gclid=Cj0KCQjwqKuKBhCxARIsACf4XuFNj_xo4rFTlejHioYGxtm43lpLkUWaz5qwLyU1kyGXmO82-w6bZY4aAn-lEALw_wcB&gclsrc=aw.ds)

For Backend [NoteAppServer](https://github.com/OzolsUgis/NoteData.git) : 

* [Ktor](https://ktor.io/)
* [MongoDB](https://www.mongodb.com/cloud/atlas/lp/try2?utm_source=google&utm_campaign=gs_footprint_row_search_core_brand_atlas_desktop&utm_term=mongodb&utm_medium=cpc_paid_search&utm_ad=e&utm_ad_campaign_id=12212624584&gclid=Cj0KCQjwqKuKBhCxARIsACf4XuHnZLwMpGlVEMK6aKnUeCRLZhzG9S2jNUJwbTMP0Rtl55KA5sbe_MAaAoJ9EALw_wcB)
* [Courotines](https://developer.android.com/kotlin/coroutines?gclid=EAIaIQobChMIqZC4jo-i8gIVsAZ7Ch1rOASzEAAYASAAEgKAwvD_BwE&gclsrc=aw.ds)
* [Commons Codec](https://github.com/apache/commons-codec)

## Getting Started
### Prerequisites 

1. You need to install Android Studio version 2020.3.1 (Artic Fox) or newer version who supports Jetpack compose
you can find installation step-by-step in following : 

* [Android Studio version 2020.3.1 (Artic Fox)](https://developer.android.com/studio?gclid=CjwKCAjwgb6IBhAREiwAgMYKRlU8WsxaTu6kg3JANeH6rEr8MrWyit5JaDfcTy0v1tTP0-DOmL1QnRoCxrcQAvD_BwE&gclsrc=aw.ds) 

2. You need to install IntelliJ IDEA Version: 2021.2.2, you can download it here : 

* [IntelliJ IDEA](https://www.jetbrains.com/idea/download/#section=windows)

### Installation 
1. You need to clone Backend server, you can fing step-by-step here - [NoteAppServer](https://github.com/OzolsUgis/NoteData.git)

2. Clone the repository using : 

* Android studio **file -> New -> Project from version control...** And enter this https://github.com/OzolsUgis/NoteApp.git in URL

* Using terminal 
  ```sh
   git clone https://github.com/OzolsUgis/NoteApp.git
   ```
!!! Check if your Port from backend server is the same as it is in utilities -> Constants -> BASE_URL

