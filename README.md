# 2021-DEV1-072

To start the application go to the application's folder using a terminal and 

run this command on mac/linux: ./mvnw spring-boot:run or for windows: mvnw spring-boot:run

You can then use postman to call the services inside the TennisController

Match creation: (post)localhost:8080/tennis/startmatch 
  params: form-data: playerOneName 
                     playerTwoName
                     
PlayerOneScores: (post)localhost:8080/tennis/playerOneScores
PlayerTwoScores: (post)localhost:8080/tennis/playerTwoScores
