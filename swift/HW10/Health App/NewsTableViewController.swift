//
//  TableViewController.swift
//  Health App
//
//  Created by Mykhailo on 2/24/21.
//

import UIKit
import SafariServices

class NewsTableViewController: UITableViewController, UITextFieldDelegate {
    
    var news = [NewsItem]()
    let newsURLString = "https://newsapi.org/v2/top-headlines?country=us&category=health&apiKey=2d3b06580d974d6da2dc42f167d7ce66"

    override func viewDidLoad() {
        super.viewDidLoad()
        self.getNews()
    }

    override func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return self.news.count
    }
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "QuoteCell", for: indexPath) as! NewsItemCell

        let newsItem = self.news[indexPath.row]
        cell.titleLabel.text = newsItem.title
        cell.sourceLabel.text = newsItem.source

        self.loadNewsImage(newsItem.imageURL!, cell)

        return cell
    }

    @IBAction func UpdateTapped(_ sender: UIBarButtonItem) {
        self.news.removeAll()
        self.getNews()
    }
    
    
    func getNews() {
        // May not know exactly what's in the URL, so replace special characters with % encoding
        if let urlStr = newsURLString.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed) {
            if let url = URL(string: urlStr) {
                let dataTask = URLSession.shared.dataTask(with: url, completionHandler: handleNewsResponse)
                dataTask.resume()
            }
        }
    }
    
    func handleNewsResponse (data: Data?, response: URLResponse?, error: Error?) {
        // 1. Check for error in request (e.g., no network connection)
        if let err = error {
            print("error: \(err.localizedDescription)")
            return
        }
        // 2. Check for improperly-formatted response
        guard let httpResponse = response as? HTTPURLResponse else {
            print("error: improperly-formatted response")
            return
        }
        let statusCode = httpResponse.statusCode
        // 3. Check for HTTP error
        guard statusCode == 200 else {
            let msg = HTTPURLResponse.localizedString(forStatusCode: statusCode)
            print("HTTP \(statusCode) error: \(msg)")
            return
        }
        // 4. Check for no data
        guard let somedata = data else {
            print("error: no data")
            return
        }

        // 5. Check for properly-formatted JSON data
        guard let jsonObj = try? JSONSerialization.jsonObject(with: somedata),
            let jsonDict1 = jsonObj as? [String: Any],
            let articleArray = jsonDict1["articles"] as? [Any],
            articleArray.count > 0 else {
                print("error: invalid JSON data")
                return
        }

        // 6. Everything seems okay

        DispatchQueue.main.async {
            // Populate our stuff
            for index in 1...(articleArray.count - 1) {
                let articleDict = articleArray[index] as? [String: Any]
                let newsItem = NewsItem()
                newsItem.title = articleDict?["title"] as? String
                newsItem.URL = articleDict?["url"] as? String
                newsItem.imageURL = articleDict?["urlToImage"] as? String
                if (newsItem.imageURL == "" || newsItem.imageURL == nil)
                {
                    newsItem.imageURL = "photo"
                }

                let source = articleDict?["source"] as? [String: Any]
                if (source != nil && source!.count > 0)
                {
                    newsItem.source = source?["name"] as? String
                }
                
                self.news.append(newsItem)
            }
            self.tableView.reloadData()
        }
    }
    
    func loadNewsImage(_ urlString: String, _ newsItemCell: NewsItemCell) {
        // URL comes from API response; definitely needs some safety checks

        if let urlStr = urlString.addingPercentEncoding(
            withAllowedCharacters: .urlQueryAllowed) {
            if let url = URL(string: urlStr) {
                let dataTask = URLSession.shared.dataTask(with: url,
                completionHandler: {(data, response, error) -> Void in
                if let imageData = data {
                    let image = UIImage(data: imageData)
                    DispatchQueue.main.async {
                        newsItemCell.newsImage.image = image
                    }
                }
            })
                dataTask.resume()
            }
        }
    }


    // Override to support conditional editing of the table view.
    override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the specified item to be editable.
        return true
    }
    
    override func tableView(_ tableView: UITableView, didSelectRowAt: IndexPath) {
        let url = URL(string: self.news[didSelectRowAt.row].URL!)
        let safariVC = SFSafariViewController(url: url!)
        present(safariVC, animated: true, completion: nil)
        
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
