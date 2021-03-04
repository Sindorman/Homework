//
//  SettingsViewController.swift
//  Health App
//
//  Created by Mykhailo on 3/3/21.
//

import UIKit

class SettingsViewController: UITableViewController {

    override func viewDidLoad() {
        super.viewDidLoad()

        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem
        SortQuotationsSwitch.setOn(UserDefaults.standard.bool(forKey: "sortQuotes"), animated: true)
        ShowAuthorsSwitch.setOn(UserDefaults.standard.bool(forKey: "showAuthors"), animated: true)
        
        FilterAnonLabel.text = "Filter Anonymouse: " +  (UserDefaults.standard.bool(forKey: "filterAnon") ? "On" : "Off")
        
        NotificationCenter.default.addObserver(self, selector: #selector(appActive), name: UIApplication.didBecomeActiveNotification, object: nil)
    }
    
    @objc func appActive() {
        FilterAnonLabel.text = "Filter Anonymouse: " +  (UserDefaults.standard.bool(forKey: "filterAnon") ? "On" : "Off")
    }
    
    
    @IBOutlet weak var SortQuotationsSwitch: UISwitch!
    
    @IBOutlet weak var ShowAuthorsSwitch: UISwitch!
    
    @IBOutlet weak var FilterAnonLabel: UILabel!

    @IBAction func SortQuotationChanged(_ sender: UISwitch) {
        UserDefaults.standard.set(sender.isOn, forKey: "sortQuotes")
    }
    
    @IBAction func ShowAuthorsChanged(_ sender: UISwitch) {
        UserDefaults.standard.set(sender.isOn, forKey: "showAuthors")
    }
    
    @IBAction func BackTapped(_ sender: UIButton) {
        performSegue(withIdentifier: "unwindFromSettingsView", sender: self)
    }
    

    
    
    // MARK: - Table view data source
    override func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 2
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        switch section {
            case 0: return 2
            case 1: return 1
            default: return 0
        }
    }
    
    override func tableView(_ tableView: UITableView,
             didSelectRowAt indexPath: IndexPath)
    {
        if (indexPath.section == 1 && indexPath.row == 0)
        {
            if let settingsURL = URL(string: UIApplication.openSettingsURLString) {
                if UIApplication.shared.canOpenURL(settingsURL) {
                    UIApplication.shared.open(settingsURL, options: [:],
                    completionHandler: nil)
                }
            }
            
        }
    }

    

    /*
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "reuseIdentifier", for: indexPath)

        // Configure the cell...

        return cell
    }
    */

    /*
    // Override to support conditional editing of the table view.
    override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the specified item to be editable.
        return true
    }
    */

    /*
    // Override to support editing the table view.
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            // Delete the row from the data source
            tableView.deleteRows(at: [indexPath], with: .fade)
        } else if editingStyle == .insert {
            // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
        }    
    }
    */

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
