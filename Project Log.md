# Infinite-Runner-App
Senior Project 


Goal for Friday 9/25/2015
  Start looking up Android Game Dynamics and start a working bibliography 

Goal for Friday 10/2/2015
  Work on first two sections of preproposal and have ready for class to have critiqued by classmates
  
Goal for Monday 9/28/2015
  Find possible open source sprites for character and obstacles
  
Goal for Wednesday 9/30/2015
  Decision was made to make an 8-bit game.  Now need to look into other 8-bit games for reference and make an attempt at a first   draft character sprite
  
Goal for Wednesday 10/7/2015
  Reading on Moodle. Think of how it applies to project
  
Goal for Monday 10/12/2015
  Write down use cases with a breakdown of usage scenarios.
  
Goal for Wednesday 10/14/2015
  Reading on Moodle. Start to think about system side of use cases



Goal for the end of the Semester
  Have a working start screen that opens into an empty game world.  Have a start on animation for the character sprite and a    couple obstacles created.   
  
  
Goal for week of 11/9/2015
  Do a tutorial for piskel and start character anamation and object creation.

Week of 11/9/2015
  Tutorial completed and have a 2 frame walking model that will be used for character sprite.  Still trying to create           obstacles.
  
Goal for week of 11/16/2015
  Start working on start screen that opens into game world.  Do a tutorial that will aid in this and try to implement it.       Also continue to work on obstacles.

Week of 11/16/2015
  Got a start menu up and it runs on the emulaiton, but still tyring to get the start game button to open into another screen.   created new obstacles 
  
Goal for week of 11/23/2015
  Get start button to open into blank screen.  Take UDemy class on Android Development (200 lectures) and get good              progress on that.  
  
Week of 11/23/2015
  Made good progress on UDemy class on Android Development (completed 43 lectures).  Still debugging the start screen crashing   when the start button is pressed.  UDemy class has lecture about it, but working my way to that since this course has a lot   of good material it covers. 

Goal for week 11/30/2015
  Continue going through UDemy course and try to fix the crash with my start button.
  
Week of 11/30/2015
  Good progress on UDemy course and fixed button crash.  
  
Goal for week 12/7/2015
  Get a ground element crerated and displayed on the screen.  Possibly get character to be standing on it.  
  
Winter Break 12/18/2015 - 1/10/2016
  Goals:
  1) get ground element into the game state (COMPLETED)
  2) get character(static) onto ground surface (COMPLETED)
  3)get character(animation) into game state (Problem looking to be resolved)
  4)get one obstacle into game state (COMPLETED)
  5)get obstacle to move towards player 
  6)get obstacle to return to opposite side of screen and continue to move
  7)get character to jump (jumps but does not ineract with ground element and falls off of screen)
  8)start to set up event collisions
  9)create a game over screen
  10)have a heads up display
  
  Discovery: found out my initial approach to creating the game was not working and have implemented the open source gaming library libgdx (https://libgdx.badlogicgames.com/) which is widely used to make games on mobile platforms.  This will take care of a lot of the background game loop processing and keep overall memory usage lower than if I implemented this game without it.


As of 1/7/2016: 
I have gotten a jumping system in place but need the character to intract with the ground element so that he does not fall off the screen when falling back down after the peak of the jump.  Obstacles are in the game but stationary and need to make a few more obstacles so that there is a variety of things to jump over.  Start screen and game over screen needs to be implemented.  Collisioin detection has been researched and I'm working on implementing it into the game.  HUD is simple enough just trying to get everything else working since it is not a major component to the game running. 

Update 1/21/2016:
Character no longer falls through the floor and does not fly off the screen.  Obstacles randomly generate and move through world at random y coordinates.  Fixing that is the next big challenge. I have 7 total obstacles and the score is on the screen.  Score still needs to be updated as obstacles are cleared, but that will be addressed once obstacles are placed and spaced properly.  Collision detection postponed until obstacles are fixed (mentioned before).
