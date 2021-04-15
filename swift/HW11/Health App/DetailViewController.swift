//
//  SecondViewController.swift
//  Health App
//
//  Created by Mykhailo on 2/16/21.
//

import UIKit

class DetailViewController: UIViewController, UITextFieldDelegate {
    var quote: String?
    var author: String?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        if (self.quote != nil)
        {
            quotationText?.text = "\"" + self.quote! + "\" " + self.author!
        }
    }
    
    @IBOutlet weak var quotationText: UILabel!

}
