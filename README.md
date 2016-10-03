# ServerScape
Universal Steam Server Connector

## If you want the binary installer for Windows, the link is here [Splitmane.com](http://splitmane.com) under the downloads section.
GrabMyWaifu is a simple application that allows for the mass downloading of media by thread on 4Chan.  It currently is set-up to download all of the media of a single thread into a created folder in the name of that thread.  The backbone of this application is JavaFX with the parsing done by jSoup.  Currently, the only available native installer is for Windows.  

### What it does-

  * Directly connects to any steam game server.
  * Admins of servers can create their own server files using simple XML
  * Users can get updates on server status, ping, and a message from admins pulled from a website every time.  
  * No login for steam needed, it runs along side of your steam client.

### Best Practices For Admins- 
 * Update message and XML sheet regularly.
 * Properly format XML using tags and TABS.
  
### How-
 * ServerScape uses the connect function of the steam client to launch the game associated with IP and Port of the XML file.  Loading and unloading of XML can be handled within the application and a few clicks.  Mods needed are also viewed from a list in the Mods tab.  Simply click connect and you will join whatever server your admin has set the XML file to.
