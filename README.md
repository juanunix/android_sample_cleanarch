# Android Sample Code (CleanArch)

@author Felipe Porge Xavier - contact@felipeporge.com

=== Introduction ===

This project was published on Google Play Store: https://play.google.com/store/apps/details?id=com.felipeporge.sample

This repository stores a sample Android project to demonstrate my code style. This project was developed by using Dagger 2 and applying the concept of the Clean Architecture.

=== Preconditions ===

This project was developed locally using Android Studio IDE (v2.2.2).
It is important to have the same version (or above) to avoid compatibility problems.

=== Instructions ===

Please, prepare your machine installing Android Studio and its requirements:
https://developer.android.com/studio/index.html

NOTE: You should have a computer connected to the Internet to download the project dependencies.

1- Open your Android Studio, click on "Open an existing Android Studio project" and select the project folder.

2- Wait the project configuration process.

3- If the Android Studio shows an error describing that is necessary to download some dependencies,
please, download it. You can download it by clicking on "Tools > Android > SDK Manager" or using the
link available on the showed errors.

4- This project uses:
- Android SDK Build-Tools v25.0.0 (NOTE: The Android SDK Build-Tools v25.0.1 is freezing Android Bottom Navigation Bar animations).
- Google repository v39;
- Android Support Repository v40.0.0;

5- The SQLite database is automatically created/initialized on the first app usage.
NOTE: Sometimes the first usage demands 1+ minute on the SplashScreen because the Android is optimizing it.

6- To run the project, please, click on "Build > Rebuild Project" and after click on "Run". This is because I used Dagger 2 for
dependency injection, and sometimes it needs a "rebuild". You need to run the module called "presentation".


=== To Run the Unit Tests ===

I created 2 Tests Suite: DataUnitTestSuite (for data layer) and PresentationUnitTestSuite (for presentation layer).
- /data/src/test/java/com/felipeporge/sample/data/DataUnitTestSuite.java
- /presentation/src/test/java/com/felipeporge/sample/presentation/PresentationUnitTestSuite.java

To run it, you just need to right click on it and select "Run 'filename'...'" using the Android Studio.
Unfortunately, I did not have time enough to implement Espresso and Mockito on my tests.
