//
//  TableViewController.swift
//  Health App
//
//  Created by Mykhailo on 2/24/21.
//

import UIKit
import CoreData

class TableViewController: UITableViewController, UITextFieldDelegate {
    
    var quotes = [NSManagedObject]()
    var managedObjectContext: NSManagedObjectContext!
    var appDelegate: AppDelegate!

    override func viewDidLoad() {
        super.viewDidLoad()
        appDelegate = UIApplication.shared.delegate as? AppDelegate
        managedObjectContext = appDelegate.persistentContainer.viewContext
        
        self.quotes = fetchQuotes()
    }
    
    func fetchQuotes() -> [NSManagedObject] {
        let fetchRequest = NSFetchRequest<NSManagedObject>(entityName: "Quotation")
        var quotes: [NSManagedObject] = []
        do {
            quotes = try self.managedObjectContext.fetch(fetchRequest)
        } catch {
            print("getQuotation error: \(error)")
        }
        return quotes
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return self.quotes.count
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if (segue.identifier == "fromMainViewToSecond") {
            let secondVC = segue.destination as! AddQuoteViewController
                secondVC.quoteNum = self.quotes.count + 1
        }
        else if (segue.identifier == "FromMainToDetail") {
            let detailVC = segue.destination as! DetailViewController
            let quoteObject = self.quotes[self.tableView.indexPathForSelectedRow!.row]
            detailVC.quote = (quoteObject.value(forKey: "quote") as? String)!
            detailVC.author = (quoteObject.value(forKey: "author") as? String)!
        }
    }
    
    @IBAction func unwindFromQuoteView (sender: UIStoryboardSegue) {
        self.quotes = self.fetchQuotes()
        self.tableView.reloadData()
    }


    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "QuoteCell", for: indexPath) as! QuoteCell

        let quote = self.quotes[indexPath.row]
        cell.QuoteLabelText?.text = quote.value(forKey: "quote") as? String
        cell.AuthorLabelText?.text = quote.value(forKey: "author") as? String
        

        return cell
    }


    // Override to support conditional editing of the table view.
    override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the specified item to be editable.
        return true
    }

    // Override to support editing the table view.
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            // Delete the row from the data source first
            let quote = self.quotes[indexPath.row]
            self.deleteQuote(quote)
            self.tableView.reloadData()
        }
    }
    
    func deleteQuote(_ quote: NSManagedObject) {
        managedObjectContext.delete(quote)
        appDelegate.saveContext()
    }

    /*
    // Override to support rearranging the table view.
    override func tableView(_ tableView: UITableView, moveRowAt fromIndexPath: IndexPath, to: IndexPath) {

    }
    */

    /*
    // Override to support conditional rearranging of the table view.
    override func tableView(_ tableView: UITableView, canMoveRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the item to be re-orderable.
        return true
    }
    */

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
