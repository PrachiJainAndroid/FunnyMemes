# FunnyMemes

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [AppArchitecture](#AppArchitecture)

## General info
This project is used to show Memes in a RecyclerView which can be refreshed by swiping
## Technologies
Project is created with:
* Kotlin 1.5.21
* JavaVersion.VERSION_1_8

## Setup
To run this project, clone the repo :
git clone https://github.com/PrachiJainAndroid/FunnyMemes.git

## AppArchitecture
The MVVM architecture is being used.
KOIN is used for DI.
Room DataBase is used to support offline storage
On First Screen, A Meme 'M' Logo with created by text is displayed. This is a splash Screen which gets displayed for 2 Seconds.
It then directs to the second screen containing The Meme List

