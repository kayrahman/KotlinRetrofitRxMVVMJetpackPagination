//
//  ViewController.swift
//  LoadMultipleImageFromUrl
//
//  Created by khalilur rahman on 28/04/20.


import UIKit
import Alamofire
import SDWebImage
class ViewController: UITableViewController {

    var myData:MasterResponse?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        self.getJSONData()
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        
        return myData?.data.count ?? 0
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "MyTableViewCell") as? MyTableViewCell
        cell?.label.text =
            myData?.data[indexPath.row].first_name
            //myData?.result?[indexPath.row].data?.first_name
        
       
        // print(myData?.result?[indexPath.row].data?.first_name)
        
        cell?.imageView?.sd_setShowActivityIndicatorView(true)
        cell?.imageView?.sd_setIndicatorStyle(UIActivityIndicatorView.Style.gray)
        cell?.imageView?.sd_setImage(with: URL(string: myData?.data[indexPath.row].avatar ?? ""), placeholderImage: #imageLiteral(resourceName: "download"), options: .handleCookies, completed: nil)
        return cell!

    }
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        
    }
    
    override func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 200
    }
    override func tableView(_ tableView: UITableView, estimatedHeightForRowAt indexPath: IndexPath) -> CGFloat {
        return 200
    }
    
    func getJSONData(){
        Alamofire.request(URL(string: "https://reqres.in/api/users") ?? "").responseJSON { (response) in
           
            do {
                let myData = try JSONDecoder().decode(MasterResponse.self, from: response.data!)
                self.myData = myData
                
            
                self.tableView.reloadData()
            }catch let error {
                print(error.localizedDescription)
            }
        }
    }
}

