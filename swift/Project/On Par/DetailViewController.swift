//
//  SecondViewController.swift
//  Health App
//
//  Created by Mykhailo on 2/16/21.
//

import UIKit
import SafariServices

class DetailViewController: UIViewController, UITextFieldDelegate {
    var course: Course?
    
    var weatherURL = "http://api.openweathermap.org/data/2.5/onecall"
    var accessToken = "4cf6942f310bd5565d8f5839564b0891"
    
    var tableViewDelegate: WeatherViewDelegate?
    var index: Int = 0
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        
        nameLabel?.text = self.course!.name

        if (course!.imageURls != nil && course!.imageURls!.count > 0)
        {
            self.loadCourseImage(course!.imageURls![index])
            let timer = Timer.scheduledTimer(withTimeInterval: 5.0, repeats: true) { timer in
                self.index += 1
                if (self.index > (self.course?.imageURls!.count)! - 1)
                {
                    self.index = 0
                }
                
                self.loadCourseImage(self.course!.imageURls![self.index])
            }
        }
        else
        {
            self.loadCourseImage("default_image")
        }
        
        if ((course?.review)! < 0)
        {
            self.reviewLabel.text = "No reviews!"
        }
        else
        {
            self.reviewLabel.text = String(self.course!.review!) + " / 5.0"
        }
        
        if (course?.phoneNumber != nil)
        {
            self.phoneLabel.text = self.course!.phoneNumber

        }
        else
        {
            self.phoneLabel.text = "No phone number!"
        }
        
        if (course?.hoursOfOperation == nil)
        {
            self.hoursLabel.text = "No hours defined"
        }
        else
        {
            self.hoursLabel.text = self.course!.hoursOfOperation
        }
        
        
        self.getWeatherInfo()
    }
    
    @IBOutlet weak var nameLabel: UILabel!

    @IBOutlet weak var imagesView: UIImageView!
    @IBOutlet weak var phoneLabel: UILabel!
    @IBOutlet weak var reviewLabel: UILabel!
    
    @IBOutlet weak var hoursLabel: UILabel!

    @IBOutlet weak var weatherTableView: UITableView!

    @IBAction func viewOnYelpTapped(_ sender: Any) {
        if (self.course!.yelpURL == nil || self.course!.yelpURL == "")
        {
            return
        }

        let url = URL(string: self.course!.yelpURL!)
        let safariVC = SFSafariViewController(url: url!)
        present(safariVC, animated: true, completion: nil)
    }

    func loadCourseImage(_ urlString: String) {
        // URL comes from API response; definitely needs some safety checks
        let defImage = UIImage(systemName: "photo")
        self.imagesView.image = defImage
        
        if let urlStr = urlString.addingPercentEncoding(
            withAllowedCharacters: .urlQueryAllowed) {
            if let url = URL(string: urlStr) {
                let dataTask = URLSession.shared.dataTask(with: url,
                completionHandler: {(data, response, error) -> Void in
                if let imageData = data {
                    let image = UIImage(data: imageData)
                    DispatchQueue.main.async {
                        self.imagesView.image = image
                    }
                }
                if (error != nil)
                {
                    let image = UIImage(systemName: "photo")
                    DispatchQueue.main.async {
                        self.imagesView.image = image
                    }
                }
            })
                dataTask.resume()
            }
        }
    }
    
    
    func getWeatherInfo() {

        // May not know exactly what's in the URL, so replace special characters with % encoding
        let urlMixed = self.weatherURL + "?lat=\(self.course!.coordinate!.latitude)&lon=\(self.course!.coordinate!.longitude)&exclude=current,minutely,hourly,alerts&appid=\(accessToken)" // limit to 150 results and golf category
        if let url = URL(string: urlMixed) {
            var request = URLRequest(url: url)
            request.httpMethod = "GET"

            let dataTask = URLSession.shared.dataTask(with: request, completionHandler: handleWeatherResponse)
            dataTask.resume()
        }
        
    }
    
    func handleWeatherResponse (data: Data?, response: URLResponse?, error: Error?) {
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
            let weatheJson = jsonObj as? [String: Any],
            let weatherArray = weatheJson["daily"] as? [[String : Any]],
            weatherArray.count > 0 else {
                print("error: invalid JSON data")
                return
        }

        // 6. Everything seems okay

        DispatchQueue.main.async {
            var tableweatherArray = [Weather]()
            for index in 1...(weatherArray.count - 1){
                let weatherCell = Weather()
                let temps = weatherArray[index]["temp"] as! [String: Double]
                weatherCell.highTemp = Int(temps["max"]!)
                weatherCell.lowTemp = Int(temps["min"]!)
                
                let weather = weatherArray[index]["weather"] as! [[String: Any]]
                let weatherData = weather[0]
                weatherCell.summary = weatherData["main"] as? String
                
                weatherCell.windSpeed = Int(weatherArray[index]["wind_speed"] as! Double)
                tableweatherArray.append(weatherCell)
                
            }
            
            self.tableViewDelegate = WeatherViewDelegate(data: tableweatherArray)
            
            self.weatherTableView.delegate = self.tableViewDelegate
            self.weatherTableView.dataSource = self.tableViewDelegate
            self.weatherTableView.reloadData()
        }
    }
    
}
