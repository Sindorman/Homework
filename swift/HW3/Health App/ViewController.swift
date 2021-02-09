//
//  ViewController.swift
//  Health App
//
//  Created by Mykhailo on 1/27/21.
//

import UIKit
import Foundation

class ViewController: UIViewController, UITextFieldDelegate {
    
    var quotes = [Quotation]() // List of Quotes
    var currentIndex = 0 // Current index
    
    var advanceTimer = Timer() // Our timer for the
    
    @IBOutlet weak var DurationSlider: UISlider!

    @IBOutlet weak var DurationLabel: UILabel!
    override func viewDidLoad() {
        super.viewDidLoad()
        self.DurationSlider.value = 5
        self.authorText.delegate = self
        self.quoteText.delegate = self
        quotes.append(Quotation(quote: "Try not to become a man of success. Rather become a man of value.",author: "Albert Einstein"))
        quotes.append(Quotation(quote: "Don't you ever let a soul in the world tell you that you can't be exactly who you are.", author: "Lady Gaga"))
        quotes.append(Quotation(quote: "I have learned over the years that when one's mind is made up, this diminishes fear.", author: "Rosa Parks"))
        quotationText.text = "\"" + self.quotes[self.currentIndex].quote + "\" " + self.quotes[self.currentIndex].author!
        advanceTimer = Timer.scheduledTimer(withTimeInterval: Double(DurationSlider.value), repeats: true){ timer in
            self.switchToNextQuote()
        }
        self.setDurationLabel()
        
    }

    @IBOutlet weak var quoteText: UITextField!
    
    @IBOutlet weak var authorText: UITextField!
    
    
    @IBOutlet weak var quotationText: UILabel!
    
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
        
    @IBAction func SliderValueChanged(_ sender: UISlider) {
        self.setDurationLabel(newTime: Int(sender.value))
        resetTimer(newTime: Double(sender.value))
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder() // remove keyboard on Return
        return false // do default behavior (i.e., nothing)? No
    }

    
    func switchToNextQuote()
    {
        currentIndex += 1
        if (currentIndex > quotes.count - 1)
        {
            currentIndex = 0
        }
        let author = quotes[self.currentIndex].author == nil ? "Anonymous" : quotes[self.currentIndex].author!
        
        quotationText.text = "\"" + quotes[self.currentIndex].quote + "\" " + author
    }
    
    func setDurationLabel(newTime: Int? = nil) {
        let time: Int = newTime == nil ? Int(self.DurationSlider.value) : newTime!
        self.DurationLabel.text = "Duration: " + String(time) + "s"
    }
    
    func resetTimer(newTime: Double) {
        advanceTimer.invalidate()
        advanceTimer = Timer.scheduledTimer(withTimeInterval: newTime, repeats: true){ timer in
            self.switchToNextQuote()
        }
    }

}

