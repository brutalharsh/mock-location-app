# Frequently Asked Questions (FAQ)

## General Questions

### 1. What is the Mock Location App?
The Mock Location App is an Android application designed for developers and testers to simulate GPS locations on their devices. It allows you to set custom latitude and longitude coordinates, making it perfect for testing location-based features without physically moving to different locations.

### 2. Is this app legal to use?
Yes, the app is legal for personal use, development, and testing purposes. However, users are responsible for ensuring their use complies with local laws and the terms of service of other applications. Using mock locations to circumvent geographical restrictions or for fraudulent purposes may violate terms of service.

### 3. What Android versions are supported?
The app supports Android 6.0 (Marshmallow, API 23) and above, up to Android 14 (API 34). This covers the vast majority of Android devices currently in use.

### 4. Is this app free to use?
Yes, the Mock Location App is completely free and open-source. You can download, use, modify, and distribute it according to the project's license terms.

### 5. Does this app require root access?
No, the app does not require root access. It works on any standard Android device with Developer Options enabled.

## Installation and Setup

### 6. How do I enable Developer Options on my Android device?
To enable Developer Options:
1. Go to Settings → About Phone
2. Tap on "Build Number" 7 times consecutively
3. You'll see a message saying "You are now a developer!"
4. Developer Options will now appear in your Settings menu

### 7. How do I set this app as my mock location provider?
After installing the app:
1. Go to Settings → Developer Options
2. Find "Select mock location app" or "Mock location app"
3. Select "Mock Location" from the list of available apps
4. The app is now configured as your device's mock location provider

### 8. Why can't I find the "Select mock location app" option?
Make sure Developer Options are enabled first. If you still can't find it, the exact location may vary by device manufacturer. Try searching for "mock" in your Settings search bar, or look under System → Developer Options.

### 9. What permissions does the app need?
The app requires:
- ACCESS_FINE_LOCATION: To access precise location data
- ACCESS_COARSE_LOCATION: To access approximate location data
- ACCESS_MOCK_LOCATION: To set mock locations (granted through Developer Options)

### 10. Can I install this app from the Google Play Store?
Currently, the app is not available on the Google Play Store. You can download the APK from the GitHub releases page or build it from source using Android Studio.

## Usage Questions

### 11. How do I set a mock location?
1. Open the Mock Location app
2. Enter the desired latitude (between -90 and 90)
3. Enter the desired longitude (between -180 and 180)
4. Tap the "Set Location" button
5. Open any map app to verify the location change

### 12. What coordinate format should I use?
Use decimal degrees format:
- Latitude: Values from -90 (South) to +90 (North)
- Longitude: Values from -180 (West) to +180 (East)
Example: San Francisco is at 37.7749, -122.4194

### 13. Can I save favorite locations?
Currently, the app doesn't have a built-in favorites feature, but this is planned for future releases. For now, you'll need to manually enter coordinates each time.

### 14. Does the mock location persist after closing the app?
The mock location remains active until you change it or restart location services. However, for best results, keep the app running in the background while using location-based services.

## Troubleshooting

### 15. Why isn't my mock location working?
Check the following:
- Developer Options are enabled
- The app is selected as the mock location provider
- Location permissions are granted to the app
- Location services are enabled on your device
- You've entered valid coordinates and pressed "Set Location"

### 16. Why do some apps detect that I'm using a mock location?
Some apps (especially banking, gaming, and security-focused apps) have detection mechanisms for mock locations. These apps may:
- Check if mock locations are enabled in Developer Options
- Use advanced location validation techniques
- Access hardware sensors to verify location authenticity
There's no guaranteed way to bypass these protections, and attempting to do so may violate the app's terms of service.

### 17. The app crashes when I try to set a location. What should I do?
Try these steps:
1. Force stop the app and clear its cache (Settings → Apps → Mock Location)
2. Ensure all required permissions are granted
3. Verify Developer Options are enabled
4. Restart your device
5. If the issue persists, check the GitHub issues page or create a new issue with your device details

### 18. Can I use this app on multiple devices simultaneously?
Yes, you can install and use the app on as many devices as you want. Each device will have its own independent mock location setting.

## Development Questions

### 19. Can I contribute to this project?
Absolutely! Contributions are welcome. You can:
- Report bugs or suggest features through GitHub Issues
- Submit pull requests with improvements or bug fixes
- Help improve documentation
- Share the project with others who might find it useful

### 20. How do I build the app from source?
1. Clone the repository: `git clone https://github.com/brutalharsh/mock-location-app.git`
2. Open the project in Android Studio
3. Let Gradle sync and download dependencies
4. Build the project: Build → Make Project
5. Run on an emulator or physical device

### 21. What technologies and libraries are used in this app?
The app is built with:
- **Language**: Kotlin
- **UI Framework**: Material Design Components
- **Architecture**: Single Activity with View Binding
- **Location Services**: Android LocationManager and Google Play Services
- **Build System**: Gradle with Android Plugin 8.1.0
- **Minimum SDK**: API 23 (Android 6.0)
- **Target SDK**: API 34 (Android 14)

## Privacy and Security

### 22. Does this app collect or transmit my location data?
No, the app processes all location data locally on your device. It does not collect, store, or transmit any location information to external servers.

### 23. Is it safe to use this app?
Yes, the app is safe to use. It's open-source, so you can review the code yourself. It only requests necessary permissions and doesn't perform any malicious activities.
