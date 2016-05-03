hunt-for-swamp-ass-API
======

Back end API for the Hunt for the Swamp Ass project. Hunt for the Swamp Ass is created by Daniel Zakharin & Matti Mäki-Kihniä.

LINK

##### Functionality

This is a REST API, containing multiple GET methods for fetching data from the database. All functions generate JSON and return a Response object.

* **/getAll** - returns the necessary data to initialize the game (gamemodes, hunts)
* **/getAllHuntsScores** - returns the scores for each hunt that has some scores
* **/getHuntScoreByUserId/{userId}/{huntId}** - get specific user's score for a specific hunt

##### Database model

![alt text](http://i.imgur.com/Mdo762p.png "Database data model")
