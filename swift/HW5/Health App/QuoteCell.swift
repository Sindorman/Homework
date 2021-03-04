//
//  QuoteCell.swift
//  Health App
//
//  Created by Mykhailo on 2/24/21.
//

import UIKit

class QuoteCell: UITableViewCell {

    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    @IBOutlet weak var QuoteLabelText: UILabel!
    @IBOutlet weak var AuthorLabelText: UILabel!
    
}
