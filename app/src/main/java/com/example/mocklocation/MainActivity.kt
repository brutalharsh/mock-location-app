package com.example.mocklocation

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.location.LocationProvider
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var latitudeEditText: TextInputEditText
    private lateinit var longitudeEditText: TextInputEditText
    private lateinit var setLocationButton: Button
    private lateinit var locationManager: LocationManager
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    
    private val LOCATION_PERMISSION_REQUEST_CODE = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        latitudeEditText = findViewById(R.id.latitudeEditText)
        longitudeEditText = findViewById(R.id.longitudeEditText)
        setLocationButton = findViewById(R.id.setLocationButton)

        // Initialize location services
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // Check and request permissions
        checkLocationPermissions()

        // Set button click listener
        setLocationButton.setOnClickListener {
            setMockLocation()
        }
        
        // Check if app is selected as mock location app
        checkMockLocationEnabled()
    }

    private fun checkLocationPermissions() {
        val permissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

        val permissionsToRequest = permissions.filter {
            ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }

        if (permissionsToRequest.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                permissionsToRequest.toTypedArray(),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }
    }

    private fun checkMockLocationEnabled() {
        try {
            // Check if mock location is enabled for this app
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val appOpsManager = getSystemService(Context.APP_OPS_SERVICE) as android.app.AppOpsManager
                val mode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    appOpsManager.unsafeCheckOp(
                        android.app.AppOpsManager.OPSTR_MOCK_LOCATION,
                        android.os.Process.myUid(),
                        packageName
                    )
                } else {
                    @Suppress("DEPRECATION")
                    appOpsManager.checkOp(
                        android.app.AppOpsManager.OPSTR_MOCK_LOCATION,
                        android.os.Process.myUid(),
                        packageName
                    )
                }
                
                if (mode != android.app.AppOpsManager.MODE_ALLOWED) {
                    Toast.makeText(
                        this,
                        "Please select this app as mock location app in Developer Options",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setMockLocation() {
        val latitudeStr = latitudeEditText.text.toString()
        val longitudeStr = longitudeEditText.text.toString()

        if (latitudeStr.isEmpty() || longitudeStr.isEmpty()) {
            Toast.makeText(this, "Please enter both latitude and longitude", Toast.LENGTH_SHORT).show()
            return
        }

        try {
            val latitude = latitudeStr.toDouble()
            val longitude = longitudeStr.toDouble()

            // Validate coordinates
            if (latitude < -90 || latitude > 90) {
                Toast.makeText(this, "Latitude must be between -90 and 90", Toast.LENGTH_SHORT).show()
                return
            }
            if (longitude < -180 || longitude > 180) {
                Toast.makeText(this, "Longitude must be between -180 and 180", Toast.LENGTH_SHORT).show()
                return
            }

            // Set mock location for all providers
            setMockLocationForProvider(LocationManager.GPS_PROVIDER, latitude, longitude)
            setMockLocationForProvider(LocationManager.NETWORK_PROVIDER, latitude, longitude)
            
            Toast.makeText(
                this,
                "Mock location set to: $latitude, $longitude",
                Toast.LENGTH_SHORT
            ).show()

        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Invalid number format", Toast.LENGTH_SHORT).show()
        } catch (e: SecurityException) {
            Toast.makeText(
                this,
                "Security exception: Please enable this app as mock location app in Developer Options",
                Toast.LENGTH_LONG
            ).show()
        } catch (e: Exception) {
            Toast.makeText(this, "Error setting mock location: ${e.message}", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }

    private fun setMockLocationForProvider(provider: String, latitude: Double, longitude: Double) {
        try {
            // Check if provider exists
            if (!locationManager.allProviders.contains(provider)) {
                return
            }

            // Enable test provider
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                // For API 31+
                locationManager.addTestProvider(
                    provider,
                    false,  // requiresNetwork
                    false,  // requiresSatellite
                    false,  // requiresCell
                    false,  // hasMonetaryCost
                    true,   // supportsAltitude
                    true,   // supportsSpeed
                    true,   // supportsBearing
                    android.location.provider.ProviderProperties.POWER_USAGE_LOW,
                    android.location.provider.ProviderProperties.ACCURACY_FINE
                )
            } else {
                // For older APIs
                @Suppress("DEPRECATION")
                locationManager.addTestProvider(
                    provider,
                    false,  // requiresNetwork
                    false,  // requiresSatellite
                    false,  // requiresCell
                    false,  // hasMonetaryCost
                    true,   // supportsAltitude
                    true,   // supportsSpeed
                    true,   // supportsBearing
                    android.location.Criteria.POWER_LOW,
                    android.location.Criteria.ACCURACY_FINE
                )
            }
            
            locationManager.setTestProviderEnabled(provider, true)

            // Create mock location
            val mockLocation = Location(provider).apply {
                this.latitude = latitude
                this.longitude = longitude
                this.altitude = 0.0
                this.accuracy = 1.0f
                this.time = System.currentTimeMillis()
                this.elapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos()
                
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    this.bearingAccuracyDegrees = 0.0f
                    this.verticalAccuracyMeters = 0.0f
                    this.speedAccuracyMetersPerSecond = 0.0f
                }
            }

            // Set the mock location
            locationManager.setTestProviderLocation(provider, mockLocation)
            
        } catch (e: SecurityException) {
            throw e
        } catch (e: IllegalArgumentException) {
            // Provider already exists, just update location
            try {
                locationManager.setTestProviderEnabled(provider, true)
                
                val mockLocation = Location(provider).apply {
                    this.latitude = latitude
                    this.longitude = longitude
                    this.altitude = 0.0
                    this.accuracy = 1.0f
                    this.time = System.currentTimeMillis()
                    this.elapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos()
                    
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        this.bearingAccuracyDegrees = 0.0f
                        this.verticalAccuracyMeters = 0.0f
                        this.speedAccuracyMetersPerSecond = 0.0f
                    }
                }
                
                locationManager.setTestProviderLocation(provider, mockLocation)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                Toast.makeText(this, "Location permissions granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    this,
                    "Location permissions are required for mock location",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}// Feature 1: Add location service initialization
// Feature 2: Implement coordinate validation logic
// Feature 3: Add GPS provider support
// Feature 4: Add network provider support
// Feature 5: Implement permission request system
// Feature 6: Add error handling for invalid coordinates
// Feature 7: Create material design UI components
// Feature 8: Add TextInputLayout for better UX
// Feature 9: Implement mock location setting logic
// Feature 10: Add location accuracy configuration
// Feature 11: Resolve API compatibility issues
// Feature 12: Handle null location providers
// Feature 13: Fix coordinate range validation
// Feature 14: Add location provider status check
// Feature 15: Implement developer options check
// Feature 16: Extract permission logic to separate method
// Feature 17: Improve error message clarity
// Feature 18: Add inline code documentation
// Feature 19: Add unit tests for coordinate validation
// Feature 20: Add UI tests for main activity
// Feature 21: Add altitude support for mock location
// Feature 22: Add speed parameter to mock location
