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

    override func viewDidLoad() {
        super.viewDidLoad()
        self.initializeLocation()
        self.map.showsUserLocation = true
        courses.append(Course(quote: "Try not to become a man of success. Rather become a man of value.",author: "Albert Einstein"))
        courses.append(Course(quote: "Don't you ever let a soul in the world tell you that you can't be exactly who you are.", author: "Lady Gaga"))
        courses.append(Course(quote: "I have learned over the years that when one's mind is made up, this diminishes fear.", author: "Rosa Parks"))
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
            detailVC.quote = self.courses[self.tableView.indexPathForSelectedRow!.row]
        }
    }


    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "QuoteCell", for: indexPath) as! CourseCell

        let quote = self.courses[indexPath.row]
        cell.QuoteLabelText?.text = quote.quote
        cell.AuthorLabelText?.text = quote.author == nil ? "anonymous" : quote.author

        return cell
    }


    // Override to support conditional editing of the table view.
    override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the specified item to be editable.
        return true
    }

    // Override to support editing the table view.
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            // Setup alert
            let alert = UIAlertController(title: "Delete Quotation?", message: self.courses[indexPath.row].quote, preferredStyle: .alert)
            
            let cancelAction = UIAlertAction(title: "Cancel", style: .default, handler: { (action) in
                return
            })
            
            let deleteAction = UIAlertAction(title: "Delete", style: .destructive, handler: { (action) in
                // Delete the row from the data source first
                self.courses.remove(at: indexPath.row)
                tableView.deleteRows(at: [indexPath], with: .fade)
            })
            
            alert.addAction(cancelAction)
            alert.addAction(deleteAction)
            alert.preferredAction = cancelAction
            
            present(alert, animated: true, completion: nil)
        }
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
