# Murder Mystery Web Game
An interactive murder mystery mobile app game

<details>
  <summary><b>Technology used</b></summary>

- JSP pages for front end
- Java for backend application development
- PostgreSQL for storing game details
- AWS (Elastic Beanstalk/RDS) + Cognito for auth
- Git and GitHub for version control
- Maven for dependency management and builds
- JUnit for testing
- (others?)
</details>

### What's this app all about?
This page acts as a readme file for myself and anyone else who may review this project or want to branch off and create their own murder mystery branch. 

## Problem Statement: 
Have you ever hosted a murder mystery party before? They are quite fun, but managing to keep everyone on track with the story and having all the information easy to view for every player while also hosting and playing a character, albiet a dead one usually, is a lot of moving parts. Players will often forget character details of other players, miss out on new clues discovered, and forget to make their final guess at the end of the game. 

## Proposed Solution:
A murder mystery mobile phone app for all to use, where players can view the story, characters, and clues without raising suspicion when they forget crucial details and have to ask questions. The host will be able to create news games with details such as the story, setting, scenes, characters, and clues. As well as delete and edit details of existing stories. 

## Key features of this app:

### User stories

__Part 1 - general users:__

1. As a player, I want to be able to see my character.
2. As a player, I want to be able to see other characters with their connection and details to the main character.
3. As a player, I want to be able to see the story in one central place
4. As a player, I want to be able to see the clues in one central place
5. As a player, I want to be prompted by other characters to introduce myself during the meet and greet.
6. As a player, I want to be able to make my guess at the end of the game.
7. As the admin player I want to make changes to characters, the story line, and have access to deploy clues when the players are having a hard time getting through it.
8. As a player, I need to be able to punch in the clue’s ID number so we can all see the details of the clue in the clue section of the game

__Part 2 - User logins with customer characters__

1. As a player, I need to be able to make my character Bio, upload a picture, details, traits, and the connection with the main character.
2. As a player, I need to be able to set up an account with my PW and Username so only I can have access to the character and so I can change details on this character later.
3. As an admin player, I want AI to generate the story and decide the murderer based on everyone’s relationship with the main character
4. As an admin player, I need the rest of the t

#### Figma Design
https://www.figma.com/design/O7Aj55HWdf6i8Of9P8ccoK/Mystery-Journal?node-id=0-1&p=f&m=draw
