//
//  WeatherTableViewCell.swift
//  On Par
//
//  Created by Mykhailo on 5/5/21.
//

import UIKit

class WeatherTableViewCell: UITableViewCell {

    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    @IBOutlet weak var dateLabel: UILabel!
    @IBOutlet weak var summaryLabel: UILabel!
    @IBOutlet weak var speedLabel: UILabel!
    @IBOutlet weak var highTempLabel: UILabel!
    
    @IBOutlet weak var lowTempLabel: UILabel!
}
