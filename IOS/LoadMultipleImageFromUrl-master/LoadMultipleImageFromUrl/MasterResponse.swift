

import Foundation

public class MasterResponse:Decodable {
	//public var _meta : _meta?
//	public var data : Array<Result>?
    public var total : Int?
      public var total_pages : Int?
      public var data : Array<_data>
    
    
}

public class _data : Decodable{
    public var first_name : String?
    public var avatar : String?
}

