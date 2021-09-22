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
*  Have its own server - * [NoteAppServer](https://github.com/OzolsUgis/NoteData.git)
*  Encrypts your password using Hash and Salt
*  Make sure, when you log in for the first time, it saves your credentials, so when you restart app it automaticly authenticates you. 
*  You can add/edit notes to local database, and then they will be saved in server.

