# BuildingInfo
![Build status](https://travis-ci.org/Deloryn/BuildingInfo.svg?branch=master)

# About
This is a team project for my software engineering classes at my university. The goal was to focus on software engineering principles and tools. The topic of the project was less important. It's an application to manage a building or a group of buildings (including inner floors, rooms etc).

# Technologies & Software Engineering Principles
- Gson
- JUni4
- Mockito
- UML modeling
- CI: Travis
- design patterns: composite

Data structure:
- Location is a building, a floor or a room
- A building can consist of floors and those can consist of rooms
- Each location is characterized by:
    - id – unique id
    - name – optional location name
- A room is additionally characterized by:
    - area = area of the room (m^2)
    - cube = cube of the room (m^3)
    - heating = the level of the energy usage for the room heating
    - light = total power of lighting power
