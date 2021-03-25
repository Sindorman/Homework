//
//  ViewController.swift
//  HealthApp
//
//  Created by Mykhailo on 3/19/21.
//

import UIKit
import CoreLocation
import MapKit

class MainViewController: UIViewController, CLLocationManagerDelegate  {
    
    var locationManager = CLLocationManager()
    var location: CLLocation?
    var travelled: Int = 0

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        self.initializeLocation()
        self.map.showsUserLocation = true
        self.traveNumber.text = String(self.travelled) + " meters"
    }
    
    func initializeLocation() { // called from start up method
        locationManager.delegate = self
        locationManager.distanceFilter = kCLDistanceFilterNone
        locationManager.desiredAccuracy = kCLLocationAccuracyBest
        let status = locationManager.authorizationStatus
        switch status {
            case .authorizedAlways, .authorizedWhenInUse:
                print("location authorized")
            case .denied, .restricted:
                print("location not authorized")
            case .notDetermined:
                locationManager.requestWhenInUseAuthorization()
            @unknown default:
                print("unknown location authorization")
        }
    }
    
    func locationManager(_ manager: CLLocationManager,
        didChangeAuthorization status: CLAuthorizationStatus) {
        if ((status == .authorizedAlways) || (status == .authorizedWhenInUse)) {
            print("location changed to authorized")
        } else {
            print("location changed to not authorized")
            self.stopLocation()
        }
    }
    
    @IBOutlet weak var traveNumber: UILabel!
    
    @IBOutlet weak var hospitalName: UILabel!
    
    @IBOutlet weak var distanceToHospital: UILabel!

    @IBAction func StartTapped(_ sender: UIButton) {
        self.startLocation()
    }

    @IBOutlet weak var map: MKMapView!
    
    @IBAction func StopTapped(_ sender: UIButton) {
        self.stopLocation()
    }
    
    
    func startLocation () {
        self.map.userTrackingMode = .follow
        let status = locationManager.authorizationStatus
        if (status == .authorizedAlways) || (status == .authorizedWhenInUse) {
            locationManager.startUpdatingLocation()
        }
        else {
            // display an alert
            let alert = UIAlertController(title: "Location Services Disabled", message: "Go to device settings to enable location services for this app", preferredStyle: .alert)
            let goAction = UIAlertAction(title: "Okay", style: .default, handler: { (action) in })
            alert.addAction(goAction)
            alert.preferredAction = goAction
            present(alert, animated: true, completion: nil)
        }
        self.searchMap("hospital")
    }
    
    func stopLocation () {
        self.map.userTrackingMode = .none
        self.map.showsUserLocation = false
        locationManager.stopUpdatingLocation()
    }
    
    // Delegate method called when location changes
    func locationManager(_ manager: CLLocationManager, didUpdateLocations locations: [CLLocation]) {
        // calculate last location
        let newLocation = locations.last
        let distance = Int(self.location?.distance(from: newLocation!) ?? 0)
        
        self.location = newLocation
        self.updateTravelled(distance: distance)
        self.searchMap("hospital")
    }
    
    func updateTravelled(distance: Int)
    {
        self.travelled += distance
        self.traveNumber.text = String(self.travelled) + " meters"
    }
    
    // Delegate method called if location unavailable (recommended)
    func locationManager(_ manager: CLLocationManager, didFailWithError error: Error) {
        print("locationManager error: \(error.localizedDescription)")
    }
    
    func searchMap(_ query: String) {
        let request = MKLocalSearch.Request()
        request.naturalLanguageQuery = query
        request.region = self.map.region
        let search = MKLocalSearch(request: request)
        search.start(completionHandler: searchHandler)
    }

    func searchHandler (response: MKLocalSearch.Response?, error: Error?) {
        if let err = error {
            print("Error occured in search: \(err.localizedDescription)")
            self.hospitalName.text = "?"
            self.distanceToHospital.text = "?"
        } else if let resp = response {
            self.map.removeAnnotations(self.map.annotations)
            var closestHospital = ""
            var travelDistance = Int.max
            var hospitalAnnotation: MKPointAnnotation?
            self.map.removeAnnotations(self.map.annotations) // clear out old annotations
            for item in resp.mapItems {
                let annotation = MKPointAnnotation()
                annotation.coordinate = item.placemark.coordinate
                annotation.title = item.name
                let distance = Int(self.location?.distance(from: CLLocation(latitude: annotation.coordinate.latitude, longitude: annotation.coordinate.longitude)) ?? 0)
                
                if (distance < travelDistance)
                {
                    hospitalAnnotation = annotation
                    travelDistance = distance
                    closestHospital = annotation.title!
                }
            }
            
            self.hospitalName.text = closestHospital
            self.distanceToHospital.text = String(travelDistance) + " meters"
            self.map.addAnnotation(hospitalAnnotation!)
        }
    }

}

