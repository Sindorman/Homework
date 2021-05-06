//
//  WeatherTableDelegate.swift
//  On Par
//
//  Created by Mykhailo on 5/5/21.
//

import Foundation
import UIKit

class WeatherViewDelegate: NSObject, UITableViewDelegate, UITableViewDataSource {
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let weatherCell = tableView.dequeueReusableCell(withIdentifier: "WeatherCell", for: indexPath) as! WeatherTableViewCell
        
        let weather = self.weatherData[indexPath.row]
        

        weatherCell.highTempLabel.text = String(weather.highTemp!) + " F"
        weatherCell.lowTempLabel.text = String(weather.lowTemp!) + " F"
        
        weatherCell.summaryLabel.text = weather.summary
        
            weatherCell.speedLabel.text = String(weather.windSpeed!) + " m/s"

        return weatherCell
    }
    
  var weatherData = [Weather]()
    
    init(data: [Weather]) {
        self.weatherData = data
  }

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
    return self.weatherData.count
  }
    
    func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }
}
