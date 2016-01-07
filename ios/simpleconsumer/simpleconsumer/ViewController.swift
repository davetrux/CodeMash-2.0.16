//
//  ViewController.swift
//  simpleconsumer
//
//  Created by David Truxall on 11/10/15.
//  Copyright © 2015 Hewlett Packard Enterprise. All rights reserved.
//

import UIKit
import Alamofire

class ViewController: UIViewController, UITableViewDataSource, UITableViewDelegate {

    @IBOutlet weak var dataTable: UITableView!
    
    var personList: Array<Person>?
    
    let cellIdentifier = "cellIdentifier"
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        
        // Register the UITableViewCell class with the tableView
        self.dataTable?.registerClass(UITableViewCell.self, forCellReuseIdentifier: self.cellIdentifier)
        
   }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    func getUrl() -> String {
        if let path = NSBundle.mainBundle().pathForResource("Configuration", ofType: "plist"), dict = NSDictionary(contentsOfFile: path) as? [String: AnyObject] {
            
                return (dict["ServiceUrl"] as? String)!
            }
        
        return ""
    }
    
    @IBAction func fetchDataTouch(sender: UIButton) {

        let url = getUrl()
        
        self.personList?.removeAll(keepCapacity: true)
        
        Alamofire.request(.GET, url)
            .responseJSON { response in
                do {
                    let json = try NSJSONSerialization.JSONObjectWithData(response.data!, options: .MutableContainers)
                
                    if  json is Array<AnyObject> {
                        print("anyobject")
                        
                        var result = [Person]()
                        
                        for item in json as! Array<AnyObject>{
                            
                            let person = Person(first: (item["firstName"] as AnyObject? as? String) ?? "", last: (item["lastName"] as AnyObject? as? String) ?? "", gender: (item["gender"] as AnyObject? as? String) ?? "")
                            
                            result.append(person)
                        }
                        
                        self.loadPersons(result)
                    }
                    
                }catch {
                    print("error")
                }
        }
        
    }

    private func loadPersons(persons: Array<Person>?){
        self.personList = persons
        
        //dispatch_async(dispatch_get_main_queue(), {self.dataTable.reloadData}())
        
        dispatch_async(dispatch_get_main_queue(), { () -> Void in
            self.dataTable.reloadData()
        })
    }
    
    // UITableViewDataSource methods
    func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        
        return (personList?.count ?? 0)
        
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        
        let cell = tableView.dequeueReusableCellWithIdentifier(self.cellIdentifier)! as UITableViewCell
        
        if personList?.count > 0 {
            cell.textLabel?.text =  "\(personList![indexPath.row].firstName) \(personList![indexPath.row].lastName)"
        }
        
        return cell
    }
}

