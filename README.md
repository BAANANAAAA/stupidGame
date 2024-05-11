## Introduction

This is a simple escape room game. Players can log in with identities registered in the database, create a room by setting the IP address of the room host, obtain a room number, and invite friends to join the room using the room number.

During the game, both parties need to communicate their clues through a chat room to complete the game.

## Run

1. Launch the server. The file is located at src/chat/Server.java. It will connect to a database and retrieve user information from it.
2. The IP address of the Server is in src/chat/Chat.java: line 19. You need to modify it to your own server's IP.
3. If you want to create a room, to ensure the correctness of the address, you need to write your device's IP address into src/chat/WLAN_IP.txt.
4. Run src/Main.java