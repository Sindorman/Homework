//
//  TableViewController.swift
//  Health App
//
//  Created by Mykhailo on 2/24/21.
//

import UIKit

class TableViewController: UITableViewController, UITextFieldDelegate {
    
    var quotes = [Quotation]()
    var index = 0
    var quoteToAdd: Quotation?

    override func viewDidLoad() {
        super.viewDidLoad()

        quotes.append(Quotation(quote: "Try not to become a man of success. Rather become a man of value.",author: "Albert Einstein"))
        quotes.append(Quotation(quote: "Don't you ever let a soul in the world tell you that you can't be exactly who you are.", author: "Lady Gaga"))
        quotes.append(Quotation(quote: "I have learned over the years that when one's mind is made up, this diminishes fear.", author: "Rosa Parks"))
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
            detailVC.quote = self.quotes[self.tableView.indexPathForSelectedRow!.row]
        }
    }
    
    @IBAction func unwindFromQuoteView (sender: UIStoryboardSegue) {
        if (sender.identifier == "unwindFromQuoteView")
        {
            let secondVC = sender.source as! AddQuoteViewController
            if (secondVC.quote != nil)
            {
                self.quotes.append(secondVC.quote!)
                self.tableView.reloadData()
            }
        }
        
    }


    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "QuoteCell", for: indexPath) as! QuoteCell

        let quote = self.quotes[indexPath.row]
        cell.QuoteLabelText?.text = quote.quote
        cell.AuthorLabelText?.text = quote.author == nil ? "anonymous" : quote.author
        

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
            self.quotes.remove(at: indexPath.row)
            tableView.deleteRows(at: [indexPath], with: .fade)
        }
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
