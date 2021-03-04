//
//  ViewController.swift
//  Health App
//
//  Created by Mykhailo on 1/27/21.
//

import UIKit

class AddQuoteViewController: UIViewController {
    
    var quote: Quotation?
    var quoteNum: Int?
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        quotationNum.text = "Quotation #" + String(quoteNum!)
    }
    
    @IBOutlet weak var quotationNum: UILabel!

    @IBOutlet weak var quoteText: UITextField!
    
    @IBOutlet weak var authorText: UITextField!
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder() // remove keyboard on Return
        return false // do default behavior (i.e., nothing)? No
    }
    
    @IBAction func SaveTapped(_ sender: UIBarButtonItem) {
        if (self.quoteText.text == "")
        {
            performSegue(withIdentifier: "unwindFromQuoteView", sender: self)
            return
        }
        var author = self.authorText.text
        if (author == "")
        {
            author = nil
        }

        quote = Quotation(quote: self.quoteText.text!, author: author)
        performSegue(withIdentifier: "unwindFromQuoteView", sender: self)
    }
    
    @IBAction func CancelTapped(_ sender: UIBarButtonItem) {
        performSegue(withIdentifier: "unwindFromQuoteView", sender: self)
    }
    
}

