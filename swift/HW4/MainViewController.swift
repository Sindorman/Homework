//
//  SecondViewController.swift
//  Health App
//
//  Created by Mykhailo on 2/16/21.
//

import UIKit

class MainViewController: UIViewController, UITextFieldDelegate {

    var quotes = [Quotation]()
    var index = 0
    var quoteToAdd: Quotation?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        quotes.append(Quotation(quote: "Try not to become a man of success. Rather become a man of value.",author: "Albert Einstein"))
        quotes.append(Quotation(quote: "Don't you ever let a soul in the world tell you that you can't be exactly who you are.", author: "Lady Gaga"))
        quotes.append(Quotation(quote: "I have learned over the years that when one's mind is made up, this diminishes fear.", author: "Rosa Parks"))
        quotationText.text = "\"" + self.quotes[self.index].quote + "\" " + self.quotes[self.index].author!
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if (segue.identifier == "fromMainViewToSecond") {
        let secondVC = segue.destination as! AddQuoteViewController
            secondVC.quoteNum = self.quotes.count + 1
        }
    }
    
    @IBOutlet weak var quotationText: UILabel!
    
    
    @IBAction func NextTapped(_ sender: UIButton) {
        index += 1
        if (index > quotes.count - 1)
        {
            index = 0
        }
        let author = quotes[self.index].author == nil ? "Anonymous" : quotes[self.index].author!
        
        quotationText.text = "\"" + quotes[self.index].quote + "\" " + author
        
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder() // remove keyboard on Return
        return false // do default behavior (i.e., nothing)? No
    }
    
    @IBAction func unwindFromQuoteView (sender: UIStoryboardSegue) {
        if (sender.identifier == "unwindFromQuoteView")
        {
            let secondVC = sender.source as! AddQuoteViewController
            if (secondVC.quote != nil)
            {
                self.quotes.append(secondVC.quote!)
            }
        }
        
    }

}
