 
<img src="http://eml.ubc.ca/files/2018/05/Soil_TopArgraphy_Logo.png" width="20%" height="20%" style="display:block;margin:0 auto">

## Objective
The [Soil TopARgraphy](eml.ubc.ca/projects/soil-topargraphy/) phone app allows UBC students, enrolled in the APBI 200 – Introduction to Soil Science course, to view topographical distribution of different soil types. The objective of the Soil TopARgraphy app is to allow students to learn about the effects of topography on the formation of different soil types through an immersive and visual Augmented Reality (AR) terrain.
This app brings interactivity to the APBI 200 lectures and laboratory sessions focused on soil formation and classification, and in turn, promotes student engagement and deeper comprehension of the material.
## Background
As a part of the curriculum, students in the APBI 200 course learn about 10 soil orders, where the soil order is the broadest classification category in the Canadian system of soil classification. The topics of soil formation and classification are of direct importance for land use and management. Hence, it is essential that our future land managers have a solid understanding of soil formation and factors – like topography – that drive it. These topics are currently covered in the APBI 200 course through a series of lectures, laboratory activities, video footage, web-based resources, and a couple of campus-based field trips. However, there is still a need to enhance students’ learning on this important topic, which led to the development of the Soil TopARgraphy app which brought in visual and tactile elements to the learning experience. 

Augmented Reality (AR) is a technology that overlays digital enhancements on top of existing reality, in this case through your phone like in Pokémon GO. With AR, different soil orders are shown within one real-life terrain across different parts of topography. We chose an area just north of Kamloops, BC as an example of the terrain model since the region is characterized by a great diversity of soil types.
## Description
With the Soil TopARgraphy app, which allows viewing and manipulating a terrain model, APBI 200 students will learn how topography impacts the distribution of soil orders through its effects on microclimate (i.e. temperature and water). Students will be able to view the terrain model with a color-coded height map or a photorealistic satellite image. Furthermore, students can tap on flags to read about different soil orders, see images, watch a video, and take a self-study quiz to review their understanding.

<img src="http://eml.ubc.ca/files/2018/05/topar-1-1.png" width="50%" height="50%" style="display:block;margin:0 auto">

## Platform: Android  
## Tools: Unity, Vuforia, Blender, Mapbox, Github

## Progress
The Minimum Viable Product of the app is complete. However, there are parts of the app that are hard-coded and the user is not capable of creating their own terrain or information panel. We began approaching this problem in Android, where we create an "Edit" mode. The user would be able to set their own coordinate, generate terrain, create flags and questions however they wish. 

The app will be tested by students in soil science and will be modified according to the feedback received. The projected release date is January 2019. The app would be launched into the Apple App Store and Google Play Store for convenient installation. It will be presented at "SOILS ACROSS LATITUDES", the North American Soil Science conference. We will also help students in APBI 200 to set-up and integrate into their course. 

## The Team
Faculty 
- Dr. Maja Krzic, Faculty of Forestry | Faculty of Land and Food Systems

Students
- Daphne Liu, Team Lead & Developer (Jan 2018 - present)
- Alex Zhang, Developer (Oct 2018 - present)
- Ranky Lau, Developer (Oct 2018 - present)
- Sarah Bornais, Developer (Oct 2018 - present)
- Sherwyn D'Souza, Developer (Oct 2018 - present) 
- Sophie Berger, Developer (Oct 2018 - present)

## Development

1. Install [Unity](https://unity3d.com/) with the Android Build Support and [Vuforia](https://library.vuforia.com/articles/Training/getting-started-with-vuforia-in-unity.html) Augmented Reality Support components.
2. Clone this project
3. Open the project in Unity

# Build iOS 

See also: https://unity3d.com/learn/tutorials/topics/mobile-touch/building-your-unity-game-ios-device-testing

1. Open Unity
2. File -> Build Setting 
3. Click "iOS" then the "Switch Platform" button if it isn't disabled
4. "Build" button then save somewhere on your computer
5. When building is complete, open the new folder and open the `.xcodeproj` file.
6. Plug in your iPhone and make sure to turn on development mode if prompted on the Mac.
7. With XCode open, click "Unity-iPhone" in the top of the left sidebar.
8. Resolve any errors on the "General" page that opens.
9. Press the play icon button to build.
10. Make sure your iPhone trusts the developer team set on the Mac. Settings -> General -> Device Management -> Developer App -> TopARGraphy
11. Done, you can run and debug.
