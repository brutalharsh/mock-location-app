# Mock Location App

A comprehensive Android application for testing and development purposes that allows setting custom GPS locations.

## Features

- **Custom Location Setting**: Set any latitude and longitude as your device location
- **Material Design UI**: Modern and intuitive user interface with Material Components
- **Permission Management**: Automatic handling of location permissions
- **Mock Location Provider**: Works with both GPS and Network providers
- **Input Validation**: Validates coordinate ranges before setting location
- **API Compatibility**: Supports Android API 23+ (Android 6.0 Marshmallow and above)
- **Error Handling**: Comprehensive error handling and user feedback

## Requirements

- Android Studio Arctic Fox or later
- Android SDK 34
- Gradle 8.0+
- Kotlin 1.9+
- Minimum Android API 23 (Android 6.0)
- Target Android API 34 (Android 14)

## Setup Instructions

### 1. Enable Developer Options

1. Go to **Settings** → **About Phone**
2. Tap **Build Number** 7 times
3. Developer Options will be enabled

### 2. Configure Mock Location

1. Go to **Settings** → **Developer Options**
2. Find **Select mock location app**
3. Choose **Mock Location** from the list

### 3. Grant Permissions

The app will automatically request the following permissions:
- `ACCESS_FINE_LOCATION`
- `ACCESS_COARSE_LOCATION`
- `ACCESS_MOCK_LOCATION` (handled through Developer Options)

## Installation

### Option 1: Build from Source

1. Clone the repository:
```bash
git clone https://github.com/brutalharsh/mock-location-app.git
cd mock-location-app
```

2. Open the project in Android Studio

3. Build the project:
   - Click **Build** → **Make Project**
   - Or use keyboard shortcut: `Ctrl+F9` (Windows/Linux) or `Cmd+F9` (Mac)

4. Run the app:
   - Click **Run** → **Run 'app'**
   - Or use keyboard shortcut: `Shift+F10` (Windows/Linux) or `Ctrl+R` (Mac)

### Option 2: Install APK

1. Download the latest APK from the releases section
2. Enable **Install from Unknown Sources** in your device settings
3. Install the APK file

## Usage

1. **Launch the App**
   - Open the Mock Location app from your app drawer

2. **Enter Coordinates**
   - **Latitude**: Enter a value between -90 and 90
   - **Longitude**: Enter a value between -180 and 180
   - Default values are set to Google's headquarters (37.4220, -122.0841)

3. **Set Location**
   - Tap the **Set Location** button
   - The mock location will be applied immediately

4. **Verify Location**
   - Open any map application (Google Maps, etc.)
   - Your location should appear at the coordinates you set

## Project Structure

```
MockLocationApp/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/mocklocation/
│   │   │   │   └── MainActivity.kt
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   │   └── activity_main.xml
│   │   │   │   ├── values/
│   │   │   │   │   ├── colors.xml
│   │   │   │   │   ├── strings.xml
│   │   │   │   │   └── themes.xml
│   │   │   │   └── drawable/
│   │   │   └── AndroidManifest.xml
│   │   └── test/
│   └── build.gradle
├── gradle/
├── build.gradle
├── settings.gradle
└── README.md
```

## Technical Implementation

### Core Components

1. **LocationManager**: System service for managing location providers
2. **Test Provider**: Simulates GPS/Network location providers
3. **FusedLocationProviderClient**: Google Play Services location API
4. **Material Components**: Modern UI elements with Material Design

### Key Methods

- `checkLocationPermissions()`: Verifies and requests necessary permissions
- `checkMockLocationEnabled()`: Checks if app is selected as mock location provider
- `setMockLocation()`: Validates input and sets mock location
- `setMockLocationForProvider()`: Configures mock location for specific provider

### API Compatibility

The app handles different Android API levels:
- **API 31+**: Uses `ProviderProperties` for location provider configuration
- **API 23-30**: Uses deprecated `Criteria` constants for backward compatibility
- **API 26+**: Enhanced location accuracy parameters

## Troubleshooting

### Mock Location Not Working

1. **Verify Developer Options are enabled**
2. **Confirm app is selected as mock location app**
3. **Check location permissions are granted**
4. **Ensure location services are enabled on device**

### App Crashes

1. **Clear app data and cache**
2. **Reinstall the application**
3. **Check Android Studio logcat for error details**

### Location Not Updating

1. **Force stop other location-based apps**
2. **Restart location services**
3. **Reboot your device**

## Development

### Building the Project

```bash
# Clean build
./gradlew clean

# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease

# Run tests
./gradlew test
```

### Code Style

The project follows Kotlin coding conventions:
- 4 spaces for indentation
- CamelCase for class names
- camelCase for function and variable names
- ALL_CAPS for constants

## Dependencies

- **AndroidX Core KTX**: 1.12.0
- **AppCompat**: 1.6.1
- **Material Components**: 1.11.0
- **ConstraintLayout**: 2.1.4
- **Play Services Location**: 21.0.1
- **JUnit**: 4.13.2 (testing)
- **Espresso**: 3.5.1 (UI testing)

## Security Considerations

- This app requires mock location permissions which can only be granted through Developer Options
- Mock locations are only active while the app is running
- The app does not collect or transmit any location data
- All location data is processed locally on the device

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is for educational purposes. Please ensure you comply with your local laws and regulations when using mock location functionality.

## Disclaimer

This application is intended for development and testing purposes only. Users are responsible for ensuring their use of this app complies with all applicable laws and terms of service of other applications.

## Support

For issues, questions, or suggestions, please open an issue on GitHub.

## Version History

- **1.0** (Current)
  - Initial release
  - Basic mock location functionality
  - Material Design UI
  - Support for Android 6.0+

## Future Enhancements

- [ ] Location history/favorites
- [ ] Map integration for visual location selection
- [ ] Automated location paths/routes
- [ ] Widget for quick location switching
- [ ] Export/Import location profiles
- [ ] Background service for continuous mock location
- [ ] Speed and altitude customization
- [ ] Multiple coordinate format support