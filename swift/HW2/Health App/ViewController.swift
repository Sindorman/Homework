//
//  ViewController.swift
//  Health App
//
//  Created by Mykhailo on 1/27/21.
//

import UIKit

class ViewController: UIViewController, UITextFieldDelegate {
    
    var quotes = [Quotation]()
    var index = 0
    
    override func viewDidLoad() {
        super.viewDidLoad()
        authorText.delegate = self
        quoteText.delegate = self
        quotes.append(Quotation(quote: "Try not to become a man of success. Rather become a man of value.",author: "Albert Einstein"))
        quotes.append(Quotation(quote: "Don't you ever let a soul in the world tell you that you can't be exactly who you are.", author: "Lady Gaga"))
        quotes.append(Quotation(quote: "I have learned over the years that when one's mind is made up, this diminishes fear.", author: "Rosa Parks"))
        quotationText.text = "\"" + self.quotes[self.index].quote + "\" " + self.quotes[self.index].author!
    }

    @IBOutlet weak var quoteText: UITextField!
    
    @IBOutlet weak var authorText: UITextField!
    
    
    @IBOutlet weak var quotationText: UILabel!
    
    @IBAction func NextTapped(sender: UIButton)
    {
        index += 1
        if (index > quotes.count - 1)
        {
            index = 0
        }
        let author = quotes[self.index].author == nil ? "Anonymous" : quotes[self.index].author!
        
        quotationText.text = "\"" + quotes[self.index].quote + "\" " + author
        
    }
    
    @IBAction func AddTapped(_ sender: UIButton) {
        
        if (quoteText.text == "")
        {
            return
        }
        var author = authorText.text
        if (author == "")
        {
            author = nil
        }
        let quote: Quotation = Quotation(quote: quoteText.text!, author: author)
        self.quotes.append(quote)
        quoteText.text = ""
        authorText.text = ""
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
    textField.resignFirstResponder() // remove keyboard on Return
    return false // do default behavior (i.e., nothing)? No
    }
    

}

