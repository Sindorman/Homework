//
//  TableViewController.swift
//  Health App
//
//  Created by Mykhailo on 2/24/21.
//

import UIKit
import CoreLocation
import MapKit

class CoursesTableViewController: UITableViewController, UITextFieldDelegate, CLLocationManagerDelegate {
    
    // Location stuff
    var locationManager = CLLocationManager()
    var location: CLLocation?
    
    // Courses stuff
    var courses = [Course]()
    var index = 0
    
    let yelpSearchURL = "https://api.yelp.com/v3/businesses/search"
    let yelpDetailsURL = "https://api.yelp.com/v3/businesses/"
    
    let accessToken = "hhpVjqPt5BQYA4h-LPlPX3oZwd-4rIaoeEUNX_eODgMnH7yfPueSSl6cMFF3LXD-mG2CjaRug41fA_QM4Fancu9Ly4mJSxBwKNTJpSC7GvyDBQ9kaqCVnfogvSyTYHYx"
    
    @IBOutlet weak var map: MKMapView!
    

    override func viewDidLoad() {
        super.viewDidLoad()
        self.initializeLocation()
        self.map.showsUserLocation = true
        self.startLocation()
        self.searchMap("Golf Course")
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
            // TODO: not sure if to do something here
        }
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
    }
    
    func locationManager(_ manager: CLLocationManager, didUpdateLocations locations: [CLLocation]) {
        // calculate last location
        self.location = locations.last
    }
    
    
    @IBAction func RefreshTapped(_ sender: UIBarButtonItem) {
        self.searchMap("Golf Course")
    }
    
    
    func searchMap(_ query: String) {
        let request = MKLocalSearch.Request()
        request.naturalLanguageQuery = query
        request.region = self.map.region
        let search = MKLocalSearch(request: request)
        self.courses.removeAll()
        search.start(completionHandler: searchHandler)
    }
    
    func searchHandler (response: MKLocalSearch.Response?, error: Error?) {
        if let err = error {
            print("Error occured in search: \(err.localizedDescription)")
        } else if let resp = response {
            self.map.removeAnnotations(self.map.annotations) // clear out old annotations
            for item in resp.mapItems {
                let distance = self.location?.distance(from: CLLocation(latitude: item.placemark.coordinate.latitude, longitude: item.placemark.coordinate.longitude)) ?? 0
                let miles = Double(round(10 * (Double(distance) * 0.00062137)) / 10) // convert to miles
                let course = Course(name: item.name!, distance: miles, coordinate: item.placemark.coordinate)
                course.review = -1
                
                self.courses.append(course)
            }
            self.courses = self.courses.sorted(by: {$0.distance < $1.distance})
            let params = "latitude=" + String((self.location?.coordinate.latitude)!) + "&longitude=" + String((self.location?.coordinate.longitude)!)
            self.getYelpSearchInfo(params: params)
            //self.tableView.reloadData()
        }
    }
    
    func getYelpSearchInfo(params: String) {
        // May not know exactly what's in the URL, so replace special characters with % encoding
        let urlMixed = self.yelpSearchURL + "?" + params + "&categories=golf" // limit to 150 results and golf category

        if let url = URL(string: urlMixed) {
            var request = URLRequest(url: url)
            request.httpMethod = "GET"
            request.setValue("Bearer \(accessToken)", forHTTPHeaderField: "Authorization")
            
            let dataTask = URLSession.shared.dataTask(with: request, completionHandler: handleYelpSearchResponse)
            dataTask.resume()
        }
    }
    
    func getYelpDetailsInfo(id: String) {
        // May not know exactly what's in the URL, so replace special characters with % encoding
        let urlMixed = self.yelpDetailsURL + id // limit to 150 results and golf category
        if let url = URL(string: urlMixed) {
            var request = URLRequest(url: url)
            request.httpMethod = "GET"
            request.setValue("Bearer \(accessToken)", forHTTPHeaderField: "Authorization")

            let dataTask = URLSession.shared.dataTask(with: request, completionHandler: handleYelpDetailsResponse)
            dataTask.resume()
        }
        
    }
    
    func handleYelpSearchResponse (data: Data?, response: URLResponse?, error: Error?) {
        // 1. Check for error in request (e.g., no network connection)
        if let err = error {
            print("error: \(err.localizedDescription)")
            return
        }
        // 2. Check for improperly-formatted response
        guard let httpResponse = response as? HTTPURLResponse else {
            print("error: improperly-formatted response")
            return
        }
        let statusCode = httpResponse.statusCode
        // 3. Check for HTTP error
        guard statusCode == 200 else {
            let msg = HTTPURLResponse.localizedString(forStatusCode: statusCode)
            print("HTTP \(statusCode) error: \(msg)")
            return
        }
        // 4. Check for no data
        guard let somedata = data else {
            print("error: no data")
            return
        }

        // 5. Check for properly-formatted JSON data
        guard let jsonObj = try? JSONSerialization.jsonObject(with: somedata),
            let businessesJson = jsonObj as? [String: Any],
            let businessesArray = businessesJson["businesses"] as? [[String : Any]],
            businessesArray.count > 0 else {
                print("error: invalid JSON data")
                return
        }

        // 6. Everything seems okay

        DispatchQueue.main.async {
            for index in 1...(self.courses.count - 1) {
                let course = self.courses[index]
                let business = businessesArray.first(where: {$0["name"] as! String  == course.name})
                if (business == nil)
                {
                    continue
                }
                let rating = business!["rating"] as? Double
                let id = business!["id"] as? String
                
                if (rating != nil)
                {
                    self.courses[index].review = rating
                }
                else
                {
                    self.courses[index].review = -1
                }
                
                course.yelpURL = business!["url"] as? String
                
                self.getYelpDetailsInfo(id: id!)
            }
            self.tableView.reloadData()
        }
    }
    
    func handleYelpDetailsResponse (data: Data?, response: URLResponse?, error: Error?) {
        // 1. Check for error in request (e.g., no network connection)
        if let err = error {
            print("error: \(err.localizedDescription)")
            return
        }
        // 2. Check for improperly-formatted response
        guard let httpResponse = response as? HTTPURLResponse else {
            print("error: improperly-formatted response")
            return
        }
        let statusCode = httpResponse.statusCode
        // 3. Check for HTTP error
        guard statusCode == 200 else {
            let msg = HTTPURLResponse.localizedString(forStatusCode: statusCode)
            print("HTTP \(statusCode) error: \(msg)")
            return
        }
        // 4. Check for no data
        guard let somedata = data else {
            print("error: no data")
            return
        }

        // 5. Check for properly-formatted JSON data
        guard let jsonObj = try? JSONSerialization.jsonObject(with: somedata),
            let businessDetailJson = jsonObj as? [String: Any],
            businessDetailJson["id"] != nil else {
                print("error: invalid JSON data")
                return
        }

        // 6. Everything seems okay

        DispatchQueue.main.async {
            let index = self.courses.firstIndex(where: {$0.name == businessDetailJson["name"] as! String})
            let hours = businessDetailJson["hours"] as? [[String: Any]]
            
            let images = businessDetailJson["photos"] as? [String]
            
            if (images != nil)
            {
                self.courses[index!].imageURls = images
            }
            
            if (hours == nil)
            {
                self.courses[index!].hoursOfOperation = "No data"
                return
            }
            
            if (businessDetailJson["display_phone"] != nil)
            {
                self.courses[index!].phoneNumber = businessDetailJson["display_phone"] as? String
            }
            else
            {
                self.courses[index!].phoneNumber = "No phone number!"
            }
            
            if (!(hours![0]["is_open_now"] as! Bool))
            {
                let actualHours = hours![0]["open"] as? [[String: Any]]
                let start = actualHours![0]["start"] as! String
                let end = actualHours![0]["end"] as! String
                
                self.courses[index!].hoursOfOperation = start + " - " + end
                
            }
            else
            {
                self.courses[index!].hoursOfOperation = "Closed!"
            }
            
            self.tableView.reloadData()
        }
    }
    
    func loadCourseImage(_ urlString: String, _ courseCell: CourseCell) {
        // URL comes from API response; definitely needs some safety checks
        let defImage = UIImage(systemName: "photo")
        courseCell.courseImageView.image = defImage
        
        if let urlStr = urlString.addingPercentEncoding(
            withAllowedCharacters: .urlQueryAllowed) {
            if let url = URL(string: urlStr) {
                let dataTask = URLSession.shared.dataTask(with: url,
                completionHandler: {(data, response, error) -> Void in
                if let imageData = data {
                    let image = UIImage(data: imageData)
                    DispatchQueue.main.async {
                        courseCell.courseImageView.image = image
                    }
                }
                if (error != nil)
                {
                    let image = UIImage(systemName: "photo")
                    DispatchQueue.main.async {
                        courseCell.courseImageView.image = image
                    }
                }
            })
                dataTask.resume()
            }
        }
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return self.courses.count
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if (segue.identifier == "FromMainToDetail") {
            let detailVC = segue.destination as! DetailViewController
            detailVC.course = self.courses[self.tableView.indexPathForSelectedRow!.row]
        }
    }


    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "CourseCell", for: indexPath) as! CourseCell

        let course = self.courses[indexPath.row]
        cell.NameLabel?.text = course.name
        cell.DistanceLabel?.text = String(course.distance) + "mi"
        if (course.hoursOfOperation == "" || course.hoursOfOperation == nil)
        {
            course.hoursOfOperation = "No Data!"
        }

        cell.hourseOfOperationLabel?.text = course.hoursOfOperation
        if (course.review! < 0)
        {
            cell.yelpReviewLabel?.text = "No reviews!"
        }
        else
        {
            cell.yelpReviewLabel?.text = String(course.review!) + " / 5.0"
        }
        
        if (course.imageURls != nil && course.imageURls!.count > 0)
        {
            self.loadCourseImage(course.imageURls![0], cell)
            print(course.name)
        }
        else
        {
            self.loadCourseImage("photo", cell)
        }
        

        return cell
    }


    // Override to support conditional editing of the table view.
    override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the specified item to be editable.
        return false
    }
    
    /*
     TODO: finished this
    func handleNotification(_ response: UNNotificationResponse) {
        quoteOfTheDayID = response.notification.request.content.userInfo["id"] as! String
        self.tableView.reloadData()
    }
    */

    /*
    // Override to support rearranging the table view.
    override func tableView(_ tableView: UITableView, moveRowAt fromIndexPath: IndexPath, to: IndexPath) {

    }
    */

    /*
    // Override to support conditional rearranging of the table view.
    override func tableView(_ tableView: UITableView, canMoveRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the item to be re-orderable.
        return true
    }
    */

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
