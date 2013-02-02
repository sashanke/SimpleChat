SimpleChat
==========

Simple Global Chat

Downloads:

[Letzter Erfolgreicher Build](http://ci.skymine.de/view/SimpleChat/job/SimpleChat/lastSuccessfulBuild/)

[Letzter Release Build](http://ci.skymine.de/view/SimpleChat/job/SimpleChat/Release/)

[Bungee Plugin](http://ci.skymine.de/job/SimpleChat-Bungee/Release/)


Config
==========
```
server: Insomnia
global: '&2[G] &f{jobs} <prefix><group> <player><suffix>&2: <msg>'
hilfe: '&e[H] &f{jobs} <prefix><group> <player><suffix>&e: <msg>'
lokal: '&9[L] &f{jobs} <prefix><group> <player><suffix>&9: <msg>'
team: '&b[A] &f{jobs} <prefix><group> <player><suffix>&b: <msg>'
spy: '&7[&4Spy&7] [&4<server>&7] &f<prefix><group> <player><suffix>&7: <msg>'
pmspy: '&8[&4PmSpy&8] [&4<server>] &d<prefix><group> <from><suffix> &d-> <to>&d: <msg>'
srvpm: '&d<prefix><group> <from><suffix> &d-> <to>&d: <msg>'
to: '<prefix><group> <player><suffix>'
lokalchat: true
radius: 100
name: '<prefix><player><suffix>'
debug: false
mysql:
  user: username
  password: password
  hostname: localhost
  database: skymine
  port: 3306
  praefix: chat_
```

Permissions
==========
```
User
    - simplechat.Global
    - simplechat.Hilfe
    - simplechat.Lokal
    - simplechat.Privat

Admin
    - simplechat.Admin
    - simplechat.spy
    - simplechat.pmspy
```
Benutzung
==========
```
! = Global / Serverübergreifend
? = Hilfe / Serverübergreifend
# = Team / Serverübergreifend
~ = Lokal / Lokal
@name = Flüstern / Serverübergreifend
@@name = Setzt einen Flüster Kanal, man kann auch initial eine Nachricht mitsenden @@name nachricht.
/r = Antwort auf das letzte Flüstern
```
