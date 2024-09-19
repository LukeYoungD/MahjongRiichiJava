# MahjongRiichiJava
A ongoing Java project to simulate Mahjong Riichi with goal of adding AI to suggest best moves/keep track of possible wins and risky discards.

Starting with 3-player. Using Mahjong Soul as my guide for Yaku, scoring, and game flow

Roadmap:
--Create skeleton Classes for Main App, GameState, Player, Tile, and Melds.--v0.01

--Initialize Wall and Dead Wall--v0.02

--Simulate Dealing--v0.02b
    -add sort to player hands

-Add All possible winning Hands

-Add Tenpai Check

-Add draw logic

-Add discard logic

-Add Chi/Pon/Kon (I know there's no chii in 3 player)

-Add Kita Dora (North Wind 'Kan')

-Meld Logic (open, closed)

-Add Score Calculator

-Add Player Input

-Add CPU Input?

-Console Output for Hands, Wall, Dora Indicator

Possible options:
-Integrate GUI using a MEAN Front-End
-Grab GameState from online game like Mahjong Soul
-Make the game more modular to allow other card/tile games to be played using different settings. (Class Card Implements Tile...)
