//
//  SecondViewController.swift
//  Health App
//
//  Created by Mykhailo on 2/16/21.
//

import UIKit

class DetailViewController: UIViewController, UITextFieldDelegate {
    var quote: Quotation?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        if (self.quote != nil)
        {
            let author = self.quote!.author == nil ? "anonymous" : self.quote?.author!
            quotationText?.text = "\"" + self.quote!.quote + "\" " + author!
        }
    }
    
    @IBOutlet weak var quotationText: UILabel!

}
