//
//  CourseCell.swift
//  Health App
//
//  Created by Mykhailo on 2/24/21.
//

import UIKit

class CourseCell: UITableViewCell {

    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    @IBOutlet weak var NameLabel: UILabel!
    @IBOutlet weak var DistanceLabel: UILabel!
    
    @IBOutlet weak var hourseOfOperationLabel: UILabel!
    @IBOutlet weak var yelpReviewLabel: UILabel!
    
    @IBOutlet weak var courseImageView: UIImageView!
}
