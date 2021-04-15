//
//  ViewController.swift
//  Health App
//
//  Created by Mykhailo on 1/27/21.
//

import UIKit
import CoreData

class AddQuoteViewController: UIViewController {

    var quoteNum: Int?
    var managedObjectContext: NSManagedObjectContext!
    var appDelegate: AppDelegate!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        appDelegate = UIApplication.shared.delegate as? AppDelegate
        managedObjectContext = appDelegate.persistentContainer.viewContext
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

        self.insertQuote(quote: self.quoteText.text!, author: author!)
        performSegue(withIdentifier: "unwindFromQuoteView", sender: self)
    }
    
    func insertQuote(quote: String, author: String) {
        let player = NSEntityDescription.insertNewObject(forEntityName:
        "Quotation", into: self.managedObjectContext)
        player.setValue(quote, forKey: "quote")
        player.setValue(author, forKey: "author")
        appDelegate.saveContext() // In AppDelegate.swift
    }
    
    @IBAction func CancelTapped(_ sender: UIBarButtonItem) {
        performSegue(withIdentifier: "unwindFromQuoteView", sender: self)
    }
    
}

