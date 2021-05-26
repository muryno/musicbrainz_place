# musicbrainz_place

#Project over view

This project is a single activity Android application  with Map place marker implementation

Input is a search term for places used for performing or producing music  

Output is  places as pins on a map                         
Use MusicBrainz API https://wiki.musicbrainz.org/Development

Every pin has a lifespan, meaning after it expires, pin should be removed from the map.   

Lifespan calculation: open_year - 1990 = lifespan_in_seconds. Example: 2017 - 1990 = 27s    

#This project use MVVM Architechture and Dependency Injection Using Hilt
