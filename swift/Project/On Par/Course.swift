//
//  Quotation.swift
//  Health App
//
//  Created by Mykhailo on 2/2/21.
//

import Foundation
import MapKit

class Course {
    
    var name: String
    var distance: Double
    var hoursOfOperation: String?
    var review: Double?
    var coordinate: CLLocationCoordinate2D?
    var imageURls: [String]?
    var phoneNumber: String?
    var yelpURL: String?
    
    
    internal init(name: String, distance: Double, coordinate: CLLocationCoordinate2D) {
        self.name = name
        self.distance = distance
        self.coordinate = coordinate
    }
}
